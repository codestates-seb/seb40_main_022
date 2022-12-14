package com.backend.fitchallenge.domain.post.service;

import com.backend.fitchallenge.domain.member.SimplePostMemberResponse;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.post.dto.*;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.post.exception.CannotDeletePost;
import com.backend.fitchallenge.domain.post.exception.CannotUpdatePost;
import com.backend.fitchallenge.domain.post.exception.PostNotFound;
import com.backend.fitchallenge.domain.post.repository.PostRepository;
import com.backend.fitchallenge.domain.tag.domain.Tag;
import com.backend.fitchallenge.domain.tag.dto.TagDto;
import com.backend.fitchallenge.domain.tag.repository.QueryTagRepository;
import com.backend.fitchallenge.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.backend.fitchallenge.domain.like.entity.QLikes.likes;
import static com.backend.fitchallenge.domain.post.entity.QPost.post;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final TagService tagService;
    private final AwsS3Service awsS3Service;
    private final MemberRepository memberRepository;
    private final QueryTagRepository queryTagRepository;

    public Long createPost(Long memberId, PostCreateVO postCreate, List<String> imagePathList) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);

        //Tag ?????? ??????
        if (postCreate.getTagDtos() != null) {
            List<Tag> tags = createTag(postCreate.getTagDtos());

            tags.forEach(tag -> log.info("tag = {}", tag.getContent()));

            // Tag ???????????? DB??? ????????? ????????? ????????? Post,PostTag ??????
            Post postWithTag = Post.toPostWithTag(postCreate, tags, member, imagePathList);
            return postRepository.save(postWithTag).getId();
        } else {
            // Tag ????????????, Tag ???????????? ?????? Post ??????
            Post post = Post.create(postCreate, member, imagePathList);
            return postRepository.save(post).getId();
        }
    }

    /**
     * ?????? ????????? ??????
     * ?????? ????????? ?????????????????? - noOffset, Slice
     * 1. DB??????  member??? fetchJoin???  Post??? ????????? ??????
     * 2. post??? ????????? PostTag, Tag, Picture ?????? - Batch Size??? ?????????????????? N+1?????? ??????
     * 3. PostResponse??? build
     * 4. ?????? ????????? ??????
     */
    @Transactional(readOnly = true)
    public MultiResponse<?> getPostList(Long lastPostId, Long memberId, Pageable pageable) {

        List<PostResponse> postResponses = postRepository.findList(lastPostId, memberId, pageable).stream()
                .map(postTuple ->
                        PostResponse.builder()
                                .member(SimplePostMemberResponse.toResponse(postTuple.get(post).getMember()))
                                .post(SimplePostResponse.of(postTuple.get(post), postTuple.get(post).getLikes().size(), postTuple.get(post.postComments.size())))
                                .tags(postTuple.get(post).getPostTags().stream()
                                        .map(postTag -> postTag.getTag().getContent()).collect(toList()))
                                .pictures(postTuple.get(post).getPictures().stream()
                                        .map(picture -> picture.getPath()).collect(toList()))
                                .likeState(Optional.ofNullable(postTuple.get(likes.id)).isPresent())
                                .build()
                ).collect(toList());

        log.info("postResponse size = {}", postResponses.size());

        //?????? ????????? ??????
        Slice<PostResponse> result = checkLastPage(postResponses, pageable);

        return MultiResponse.of(result);
    }

    /**
     * ?????? ????????? ?????? - ????????? ??????
     */
    @Transactional(readOnly = true)
    public MultiResponse<?> getPostListWithoutLogin(Long lastPostId, Pageable pageable) {


        List<PostResponse> postResponses = postRepository.findListWithoutLogin(lastPostId,pageable).stream()
                .map(postTuple ->
                        PostResponse.builder()
                                .member(SimplePostMemberResponse.toResponse(postTuple.get(post).getMember()))
                                .post(SimplePostResponse.of(postTuple.get(post), postTuple.get(post).getLikes().size(), postTuple.get(post.postComments.size())))
                                .tags(postTuple.get(post).getPostTags().stream()
                                        .map(postTag -> postTag.getTag().getContent()).collect(toList()))
                                .pictures(postTuple.get(post).getPictures().stream()
                                        .map(picture -> picture.getPath()).collect(toList()))
                                .likeState(false)
                                .build()
                ).collect(toList());

        log.info("postResponse size = {}", postResponses.size());

        //?????? ????????? ??????
        Slice<PostResponse> result = checkLastPage(postResponses, pageable);

        return MultiResponse.of(result);
    }

    /**
     * ????????? ?????? - ?????? ??????
     * 1.DB?????? ?????? ????????? ????????? ????????? Id ?????? ????????????.
     * 2.?????? Id ????????? ???????????? post??? id ???????????? ??????????????? ????????? ???????????? ????????????.
     * 3. post id??? ???????????? ????????? + ????????? ?????? + ?????????????????? ????????????
     * 4. ??????????????? ?????????????????? ??????
     */
    public MultiResponse<?> getSearchList(Long memberId, Pageable pageable, Long lastPostId, List<String> tagNames) {

        // 2. ?????? Id????????? ???????????? postid ????????????
        List<Long> tagIds = queryTagRepository.findTagsByContent(tagNames);
        if (tagIds.isEmpty()) {
            throw new PostNotFound();
        }

        // 3. post id??? ???????????? ????????? + ????????? ?????? + ????????? ????????? ???????????? response??? ??????
        List<Long> postIds = queryTagRepository.findPostByTag(lastPostId, tagIds, pageable);

        List<PostResponse> responses = postRepository.findSearchList(memberId, postIds).stream()
                .map(tuple -> PostResponse.builder()
                        .member(SimplePostMemberResponse.toResponse(tuple.get(post).getMember()))
                        .post(SimplePostResponse.of(tuple.get(post), tuple.get(post).getLikes().size(), tuple.get(post.postComments.size())))
                        .tags(tuple.get(post).getPostTags().stream()
                                .map(postTag -> postTag.getTag().getContent()).collect(toList()))
                        .pictures(tuple.get(post).getPictures().stream()
                                .map(picture -> picture.getPath()).collect(toList()))
                        .likeState(Optional.ofNullable(tuple.get(likes.id)).isPresent())
                        .build()).collect(toList());

        Slice<PostResponse> result = checkLastPage(responses, pageable);

        return MultiResponse.of(result);
    }

    /**
     * ????????? ?????? - ?????? ??????, ????????? X
     */
    public MultiResponse<?> getSearchListWithoutLogin( Pageable pageable, Long lastPostId, List<String> tagNames) {

        List<Long> tagIds = queryTagRepository.findTagsByContent(tagNames);
        if (tagIds.isEmpty()) {
            throw new PostNotFound();
        }

        List<Long> postIds = queryTagRepository.findPostByTag(lastPostId, tagIds, pageable);

        List<PostResponse> responses = postRepository.findSearchListWithoutLogin(postIds).stream()
                .map(tuple -> PostResponse.builder()
                        .member(SimplePostMemberResponse.toResponse(tuple.get(post).getMember()))
                        .post(SimplePostResponse.of(tuple.get(post), tuple.get(post).getLikes().size(), tuple.get(post.postComments.size())))
                        .tags(tuple.get(post).getPostTags().stream()
                                .map(postTag -> postTag.getTag().getContent()).collect(toList()))
                        .pictures(tuple.get(post).getPictures().stream()
                                .map(picture -> picture.getPath()).collect(toList()))
                        .likeState(false)
                        .build()).collect(toList());

        Slice<PostResponse> result = checkLastPage(responses, pageable);

        return MultiResponse.of(result);
    }

    /**
     * ????????? ??????
     * 1. ????????? ????????? ????????? ??????????????? ??????
     * 2. DB??? ????????? imagePath??? ?????? S3??? ?????? ????????? ?????? ??????
     * 3. Tag ??????
     */
    public PostUpdateResponse updatePost(Long postId, Long memberId, PostUpdateVO postUpdate) {

        Post post = findPostById(postId);

        if (memberId != post.getMember().getId()) {
            throw new CannotUpdatePost();
        }
        // ????????? ????????? ?????????
        List<String> paths = post.getPictures().stream().
                map(picture -> {
                    int index = picture.getPath().lastIndexOf("/");
                    return picture.getPath().substring(index + 1);
                }).collect(toList());

        List<String> imagePaths = awsS3Service.UpdateFile(paths, postUpdate.getFiles());

        if (postUpdate.getTagDtos() != null) {
            List<Tag> tags = createTag(postUpdate.getTagDtos());
            post.patchWithTag(postUpdate.getContent(), imagePaths, tags);
        } else {
            post.patch(postUpdate.getContent(), imagePaths);
        }
        return PostUpdateResponse.of(post);
    }

    /**
     * ????????? ??????
     * ????????? ????????? ????????? ??????????????? ??????
     * post ??? S3??? ????????? ??????????????? ??????
     */
    public void deletePost(Long postId, Long memberId) {

        Post post = findPostById(postId);

        if (memberId != post.getMember().getId()) {
            throw new CannotDeletePost();
        }

        List<String> paths = post.getPictures().stream().
                map(picture -> {
                    int index = picture.getPath().lastIndexOf("/");
                    return picture.getPath().substring(index + 1);
                }).collect(toList());
        awsS3Service.DeleteFile(paths);

        postRepository.delete(post);

    }

    private List<Tag> createTag(List<TagDto> tagDtos) {
        return tagDtos.stream()
                .map(dto ->
                        tagService.create(dto))
                .collect(toList());
    }

    private Slice<PostResponse> checkLastPage(List<PostResponse> postResponses, Pageable pageable) {

        boolean hasNext = false;

        //?????? ?????? ????????? ????????? ????????? ??????????????? ?????? ?????? ??? ??????, next = true
        if (postResponses.size() > pageable.getPageSize()) {
            hasNext = true;
            // ??????????????? ?????????????????? remove(findList?????? limit??? +1?????? ????????? ?????????)
            postResponses.remove(pageable.getPageSize());
        }

        log.info("Slice PostResponse size = {}", postResponses.size());

        return new SliceImpl<>(postResponses, pageable, hasNext);
    }

    public Post findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFound::new);
    }

}
