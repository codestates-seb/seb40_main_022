package com.backend.fitchallenge.domain.post.service;

import com.backend.fitchallenge.domain.member.Member;
import com.backend.fitchallenge.domain.member.SimplePostMemberResponse;
import com.backend.fitchallenge.domain.post.dto.*;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.post.exception.PostNotFound;
import com.backend.fitchallenge.domain.post.repository.PostRepository;
import com.backend.fitchallenge.domain.tag.domain.Tag;
import com.backend.fitchallenge.domain.tag.dto.TagDto;
import com.backend.fitchallenge.domain.tag.service.TagService;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Random;


import static com.backend.fitchallenge.domain.post.entity.QPicture.picture;
import static com.backend.fitchallenge.domain.post.entity.QPost.post;
import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostService {


    private final PostRepository postRepository;
    private final TagService tagService;
    private final AwsS3Service awsS3Service;

    /**
     * toPostWithTag - Tag 있을경우 DB에 저장후 태그를 포함한 Post,PostTag 생성
     * toPost - Tag 없을경우, Tag 포함하지 않는 Post 생성
     */
    public Long createPost(Long memberId, PostCreateVO postCreate, List<String> imagePathList) {

        // 현재 사용자 가져오는 로직으로 수정 필요
        Member member = init();

        //Tag 있는 경우
        if (postCreate.getTagDtos() != null) {
            List<Tag> tags = createTag(postCreate.getTagDtos());
            Post postWithTag = Post.toPostWithTag(postCreate, tags, member, imagePathList);
            return postRepository.save(postWithTag).getId();
        } else {
            //Tag 없는 경우
            Post post = Post.toPost(postCreate, member, imagePathList);
            return postRepository.save(post).getId();
        }

    }

    private List<Tag> createTag(List<TagDto> tagDtos) {
        return tagDtos.stream()
                .map(dto ->
                        tagService.createTag(dto))
                .collect(toList());
    }

    /**
     * 무한 스크롤 페이지네이션
     * postId에 해당하는 사진들을 picture 테이블에 저장한 image path로 불러온다.
     */
    @Transactional(readOnly = true)
    public MultiResponse<?> getPostList(Long lastPostId, Pageable pageable) {


        List<PostResponse> postResponses = postRepository.findListWithTag(lastPostId,pageable).stream()
                .map(postTuple ->
                        PostResponse.builder()
                                .member(SimplePostMemberResponse.toResponse(postTuple.get(post).getMember()))
                                .post(SimplePostResponse.toResponse(postTuple.get(post), postTuple.get(post.postComments.size())))
                                .tags(postTuple.get(post).getPostTags().stream()
                                        .map(postTag -> postTag.getTag().getContent()).collect(toList()))
                                .pictures(postTuple.get(post).getPictures().stream()
                                        .map(picture -> picture.getPath()).collect(toList()))

                                .build()
                ).collect(toList());

        log.info("postResponse size = {}", postResponses.size());

        Slice<PostResponse> result = checkLastPage(postResponses, pageable);

        return MultiResponse.of(result);
    }

    // 무한 스크롤 처리
    private Slice<PostResponse> checkLastPage(List<PostResponse> postResponses, Pageable pageable ) {

        boolean hasNext = false;

        //조회 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (postResponses.size() > pageable.getPageSize()) {
            hasNext = true;
            // 확인용으로 추가한데이터 remove
            postResponses.remove(pageable.getPageSize());
        }
        
        log.info("Slice PostResponse size = {}", postResponses.size());
        
        return new SliceImpl<>(postResponses, pageable, hasNext);
    }
    public PostUpdateResponse updatePost(Long postId, PostUpdateVO postUpdate) {

        Post post = findPostById(postId);

        List<String> paths = post.getPictures().stream().
                map(picture -> {
                    int index = picture.getPath().lastIndexOf("/");
                    return picture.getPath().substring(index+1);
                }).collect(toList());

        List<String> imagePaths = awsS3Service.UpdateFile(paths, postUpdate.getFiles());

        if (postUpdate.getTagDtos() != null) {
            List<Tag> tags = createTag(postUpdate.getTagDtos());
            post.patchWithTag(postUpdate.getContent(), imagePaths, tags);
        } else {
            post.patch(postUpdate.getContent(), imagePaths);
        }
        return PostUpdateResponse.toResponse(post);
    }

    public void deletePost(Long postId) {

        Post post = findPostById(postId);

        List<String> paths = post.getPictures().stream().
                map(picture -> {
                    int index = picture.getPath().lastIndexOf("/");
                    return picture.getPath().substring(index+1);
                }).collect(toList());
        //s3에서 포스트에 해당하는 사진 삭제
        awsS3Service.DeleteFile(paths);

        //db에서 post삭제
        postRepository.delete(post);

    }

    public Post findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFound::new);
    }

    public Member init() {
        return new Member(new Random().nextLong(), "abc@gmail.com", "1234", "잉스기", "https://unsplash.com/photos/yMSecCHsIBc",
                "남", "개발자", "경기도 성남시 판교로", 25L, 180L, 75L, "3분할", "user");
    }


}
