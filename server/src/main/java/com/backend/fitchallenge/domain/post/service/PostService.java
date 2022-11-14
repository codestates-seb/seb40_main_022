package com.backend.fitchallenge.domain.post.service;

import com.backend.fitchallenge.domain.member.SimplePostMemberResponse;
import com.backend.fitchallenge.domain.member.Member;
import com.backend.fitchallenge.domain.post.dto.*;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.post.exception.PostNotFound;
import com.backend.fitchallenge.domain.post.repository.PostRepository;
import com.backend.fitchallenge.domain.tag.domain.Tag;
import com.backend.fitchallenge.domain.tag.service.TagService;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.backend.fitchallenge.domain.picture.entity.QPicture.picture;
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
        }else {
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
     *  무한 스크롤 페이지네이션
     *  postId에 해당하는 사진들을 picture 테이블에 저장한 image path로 불러온다.
     */
    @Transactional(readOnly = true)
    public MultiResponse<?> getPostList(Pageable pageable) {

        Long total = postRepository.pagingCount(pageable);
        Map<Long, List<String>> postPictureMap = postRepository.findListWithPicture(pageable).stream().collect(
                groupingBy(tuple -> tuple.get(post.id),
                        mapping(tuple -> tuple.get(picture.path), toList())));


        PageImpl<PostResponse> postResponses = new PageImpl<>(
                postRepository.findListWithTag(pageable).stream()
                        .map(post ->
                                PostResponse.builder()
                                        .member(SimplePostMemberResponse.toResponse(post.getMember()))
                                        .post(SimplePostResponse.toResponse(post))
                                        .tags(post.getPostTags().stream()
                                                .map(postTag -> postTag.getTag().getContent()).collect(toList()))
                                        .pictures(postPictureMap.get(post.getId()))
                                        .build()
                        )
                        .collect(toList()), pageable, total);

        return MultiResponse.of(postResponses);

    }

    public PostUpdateResponse updatePost(Long postId, PostUpdateVO postUpdate) {

        Post post = findPostById(postId);

        List<String> paths = post.getPictures().stream().
                map(picture -> {
                    int index = picture.getPath().lastIndexOf("/");
                    return picture.getPath().substring(index);}).collect(toList());

        List<String> imagePaths = awsS3Service.UpdateFile(paths, postUpdate.getFiles());

        if(postUpdate.getTagDtos() != null) {
            List<Tag> tags = createTag(postUpdate.getTagDtos());
            post.patchWithTag(postUpdate.getContent(), imagePaths, tags);
        }else{
            post.patch(postUpdate.getContent(), imagePaths);
        }
        return PostUpdateResponse.toResponse(post);
    }

    public void deletePost(Long postId) {

        Post post = findPostById(postId);

        List<String> paths = post.getPictures().stream().
                map(picture -> {
                    int index = picture.getPath().lastIndexOf("/");
                    return picture.getPath().substring(index);}).collect(toList());
        //s3에서 포스트에 해당하는 사진 삭제
        awsS3Service.DeleteFile(paths);

        //db에서 post삭제
        postRepository.delete(post);

    }
    private Post findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFound::new);
    }

    public Member init() {
       return  new Member(new Random().nextLong(), "abc@gmail.com", "1234", "잉스기", "https://unsplash.com/photos/yMSecCHsIBc",
                "남", "개발자", "경기도 성남시 판교로", 25L, 180L, 75L, "3분할", "user");
    }



}
