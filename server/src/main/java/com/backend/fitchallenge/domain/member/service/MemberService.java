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

    /**
     * [회원가입]
     * 1. 이미 존재하는 이메일인지 확인한다.
     * 2. 패스워드를 인코딩 하여 member Entity로 변환하고, 기본 이미지 경로를 설정해준다.
     * 3. 저장한다.
     * 4. 멤버 아이디를 리턴한다.
     * @param memberCreate : DTO
     * @return Long : 생성된 멤버의 아이디
     */
    public Long createMember(MemberCreate memberCreate){

        checkAlreadyExist(memberCreate.getEmail());

        // todo. 기본 이미지 path를 가진 profileImage생성 후 member에 넣어주기. - checked
        ProfileImage profileImage = ProfileImage.createWithPath("\"https://pre-project-bucket-seb40-017.s3.ap-northeast-2.amazonaws.com/00398f65-51c3-4c1d-baac-38070910c5b3.png%22)%7B");
        Member member = memberCreate.toMember(passwordEncoder, profileImage);
        profileImage.setMember(member);

        memberRepository.save(member);

        return findVerifiedMember(member.getEmail()).getId();
    }

    /**
     * [회원 정보 수정]
     * 1. request에서 로그인 멤버의 이메일을 추출한다.
     * 2. 해당 이메일로 DB에서 멤버정보를 가져온다.
     * 3. Member엔티티의 update 메서드 이용하여 업데이트한다.
     * 4. 저장한다.
     * 5. 필요 정보만 MemberUpdate에 담아서 리턴한다.
     * todo 비밀번호 변경시 로그아웃 처리를 해야할 것인가.
     */
    public UpdateResponse updateMember(String loginEmail, MemberUpdateVO memberUpdateVO){

        // 준비단계
        Member findMember = findVerifiedMember(loginEmail);

        // todo. 저장, 새로운 path획득, member의 profileImage의 path 수정, 저장. - checked
        String oldImagePath = findMember.getProfileImage().getPath(); // 수정할 주소를 가져온다.
        String newImagePath = memberAwsS3Service.updateFile(memberUpdateVO.getProfileImage(), oldImagePath); // s3 사진 교체, path
        findMember.getProfileImage().updatePath(newImagePath);

        // db에서 변환
        findMember.update(memberUpdateVO, passwordEncoder);

        memberRepository.save(findMember);

        return UpdateResponse.of(findMember);
    }

    /**
     * [마이페이지]
     * 1. 로그인 한 멤버의 이메일 추출
     * 2. 이메일로 정보 찾아옴
     * 3 (todo) 작성 picture 목록 불러오기
     * 4. 필요정보만 MyPageResponse에 담는다.
     */
    @Transactional(readOnly = true)
    public DetailsMemberResponse getMyInfo(Long lastPostId, String loginEmail, Pageable pageable){

        Member findMember = findVerifiedMember(loginEmail);

        // todo. post연결 - checked
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

    /**
     *[특정 유저 정보 조회]
     * @param memberId : 조회하고자 하는 유저의 id
     * @return DetailsMemberResponse
     */
    @Transactional(readOnly = true)
    public DetailsMemberResponse getMember(Long lastPostId, Long memberId, Pageable pageable){

        Member findMember = findVerifiedMemberById(memberId);

        // todo. post연결 - checked
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
    

    /**
     * [회원 정보 삭제]
     * 1. 현재 로그인 한 회원의 이메일 추출
     * 2. 추출한 이메일로 멤버 정보를 조회
     * 3. DB에서 멤버 삭제
     * 4. 로그아웃 처리 (redis, db에서 토큰정보삭제)
     */
    public Long deleteMember(String accessToken, String loginEmail){

        Member findMember = findVerifiedMember(loginEmail);

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

    /**
     * 아래부터는 기능 메서드
     */
    // 이미 가입된 이메일인지 확인하는 메서드
    public void checkAlreadyExist(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(optionalMember.isPresent())
            throw new MemberExist();
    }

    // 이메일로 DB에서 멤버 정보 조회
    public Member findVerifiedMember(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        return optionalMember.orElseThrow(()->new MemberNotExist());
    }

    // ID값으로 DB에서 멤버 정보 조회
    public Member findVerifiedMemberById(Long memberId){
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        return optionalMember.orElseThrow(() -> new MemberNotExist());
    }

    // 무한 스크롤 처리
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
