package com.backend.fitchallenge.domain.member.service;

import com.backend.fitchallenge.domain.member.dto.request.MemberCreate;
import com.backend.fitchallenge.domain.member.dto.request.MemberUpdateVO;
import com.backend.fitchallenge.domain.member.dto.response.DetailsMemberResponse;
import com.backend.fitchallenge.domain.member.dto.response.UpdateResponse;
import com.backend.fitchallenge.domain.member.dto.response.extract.DailyPost;
import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractActivity;
import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractMember;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.entity.ProfileImage;
import com.backend.fitchallenge.domain.member.exception.MemberExist;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.post.repository.PostRepository;
import com.backend.fitchallenge.domain.refreshtoken.RefreshTokenRepository;
import com.backend.fitchallenge.domain.refreshtoken.exception.TokenNotExist;
import com.backend.fitchallenge.global.dto.response.SliceMultiResponse;
import com.backend.fitchallenge.global.redis.RedisService;
import com.backend.fitchallenge.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisService redisService;
    private final MemberAwsS3Service memberAwsS3Service;
    private final PostRepository postRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public Long createMember(MemberCreate memberCreate){

        isMemberExist(memberCreate.getEmail());

        ProfileImage profileImage = ProfileImage.createWithPath("https://pre-project-bucket-seb40-017.s3.ap-northeast-2.amazonaws.com/00398f65-51c3-4c1d-baac-38070910c5b3.png");
        Member member = memberCreate.toEntity(passwordEncoder, profileImage);
        profileImage.setMember(member);

        memberRepository.save(member);

        return findMember(member.getEmail()).getId();
    }

    public UpdateResponse updateMember(String loginEmail, MemberUpdateVO memberUpdateVO){

        Member findMember = findMember(loginEmail);

        String oldImagePath = findMember.getProfileImage().getPath(); // 수정할 주소를 가져온다.
        String newImagePath = memberAwsS3Service.updateFile(memberUpdateVO.getProfileImage(), oldImagePath); // s3 사진 교체, path
        findMember.getProfileImage().updatePath(newImagePath);

        findMember.update(memberUpdateVO, passwordEncoder);

        memberRepository.save(findMember);

        return UpdateResponse.of(findMember);
    }

    @Transactional(readOnly = true)
    public DetailsMemberResponse getMyInfo(Long lastPostId, String loginEmail, Pageable pageable){

        Member findMember = findMember(loginEmail);

        List<Post> pages = memberRepository.findList(lastPostId, findMember.getId(), pageable);
        List<DailyPost> dailyPosts = pages.stream()
                .map(post -> post == null ? DailyPost.of() : DailyPost.of(post))
                .collect(Collectors.toList());

        Slice<DailyPost> result = checkLastPage(dailyPosts, pageable);
        SliceMultiResponse<DailyPost> sliceResult = SliceMultiResponse.createSliceDailyPost(result);

        List<Post> posts = postRepository.findByMemberId(findMember.getId());
        Integer postCounts = posts.size();

        return DetailsMemberResponse.of(ExtractMember.of(findMember), ExtractActivity.of(findMember.getMemberActivity()), sliceResult, postCounts);
    }

    @Transactional(readOnly = true)
    public DetailsMemberResponse getMember(Long lastPostId, Long memberId, Pageable pageable){

        Member findMember = findMemberById(memberId);

        List<Post> pages = memberRepository.findList(lastPostId, findMember.getId(), pageable);
        List<DailyPost> dailyPosts = pages.stream()
                .map(post -> post == null ? DailyPost.of() : DailyPost.of(post))
                .collect(Collectors.toList());

        Slice<DailyPost> result = checkLastPage(dailyPosts, pageable);
        SliceMultiResponse<DailyPost> sliceResult = SliceMultiResponse.createSliceDailyPost(result);

        List<Post> posts = postRepository.findByMemberId(memberId);
        Integer postCounts = posts.size();

        return DetailsMemberResponse.of(ExtractMember.of(findMember), ExtractActivity.of(findMember.getMemberActivity()), sliceResult, postCounts);
    }

    public Long deleteMember(String accessToken, String loginEmail){

        Member findMember = findMember(loginEmail);

        Long deletedMemberId = findMember.getId();
        String deletedEmail = findMember.getEmail();
        Long untilExpiration = jwtTokenProvider.calExpDuration(accessToken);

        memberRepository.delete(findMember);
        redisService.deleteValues(deletedEmail);
        redisService.setBlackListValues(accessToken, "BlackList", untilExpiration);
        refreshTokenRepository.delete(refreshTokenRepository.findByOwnerEmail(deletedEmail)
                .orElseThrow(()->new TokenNotExist()));

        return deletedMemberId;
    }

    public void isMemberExist(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(optionalMember.isPresent())
            throw new MemberExist();
    }

    public Member findMember(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        return optionalMember.orElseThrow(()->new MemberNotExist());
    }

    public Member findMemberById(Long memberId){
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        return optionalMember.orElseThrow(() -> new MemberNotExist());
    }

    private Slice<DailyPost> checkLastPage(List<DailyPost> postResponses, Pageable pageable) {

        boolean hasNext = false;

        //조회 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (postResponses.size() > pageable.getPageSize()) {
            hasNext = true;
            // 확인용으로 추가한데이터 remove(findList에서 limit에 +1해서 조회한 데이터)
            postResponses.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(postResponses, pageable, hasNext);
    }
}
