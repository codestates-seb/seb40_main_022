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

        //Tag 있는 경우
        if (postCreate.getTagDtos() != null) {
            List<Tag> tags = createTag(postCreate.getTagDtos());

            tags.forEach(tag -> log.info("tag = {}", tag.getContent()));

            // Tag 있을경우 DB에 저장후 태그를 포함한 Post,PostTag 생성
            Post postWithTag = Post.toPostWithTag(postCreate, tags, member, imagePathList);
            return postRepository.save(postWithTag).getId();
        } else {
            // Tag 없을경우, Tag 포함하지 않는 Post 생성
            Post post = Post.create(postCreate, member, imagePathList);
            return postRepository.save(post).getId();
        }

    }

    /**
     * 전체 게시물 조회
     * 무한 스크롤 페이지네이션 - noOffset, Slice
     * 1. DB에서  member를 fetchJoin한  Post와 댓글수 조회
     * 2. post와 연관된 PostTag, Tag, Picture 조회 - Batch Size를 설정함으로써 N+1문제 해결
     * 3. PostResponse로 build
     * 4. 무한 스크롤 처리
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

        //무한 스크롤 처리
        Slice<PostResponse> result = checkLastPage(postResponses, pageable);

        return MultiResponse.of(result);
    }

    /**
     * 전체 게시물 조회 - 로그인 없이
     * 무한 스크롤 페이지네이션 - noOffset, Slice
     * 1. DB에서  member를 fetchJoin한  Post와 댓글수 조회
     * 2. post와 연관된 PostTag, Tag, Picture 조회 - Batch Size를 설정함으로써 N+1문제 해결
     * 3. PostResponse로 build
     * 4. 무한 스크롤 처리
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

        //무한 스크롤 처리
        Slice<PostResponse> result = checkLastPage(postResponses, pageable);

        return MultiResponse.of(result);
    }

    /**
     * 게시물 검색 - 태그 기반
     * 1.DB에서 태그 이름과 일치한 태그의 Id 목록 가져온다.
     * 2.태그 Id 목록에 해당하는 post의 id 가져오기 태그목록중 한개만 포함해도 가져온다.
     * 3. post id에 해당하는 게시물 + 좋아요 상태 + 게시물댓글수 가져오기
     * 4. 무한스크롤 페이지네이션 처리
     */
    public MultiResponse<?> getSearchList(Long memberId, Pageable pageable, Long lastPostId, List<String> tagNames) {

        // 2. 태그 Id목록에 해당하는 postid 가져오기
        List<Long> tagIds = queryTagRepository.findTagsByContent(tagNames);
        if (tagIds.isEmpty()) {
            throw new PostNotFound();
        }

        // 3. post id에 해당하는 게시물 + 좋아요 상태 + 게시물 댓글수 가져와서 response로 반환
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
     * 게시물 검색 - 태그 기반, 로그인 X
     * 1.DB에서 태그 이름과 일치한 태그의 Id 목록 가져온다.
     * 2.태그 Id 목록에 해당하는 post의 id 가져오기 태그목록중 한개만 포함해도 가져온다.
     * 3. post id에 해당하는 게시물 + 게시물댓글수 가져오기
     * 4. 무한스크롤 페이지네이션 처리
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
     * 게시물 수정
     * 1. 로그인 유저가 게시물 작성자인지 체크
     * 2. DB에 저장된 imagePath를 통해 S3에 있는 이미지 파일 수정
     * 3. Tag 수정
     *
     * @return 수정된 Post 정보
     */
    public PostUpdateResponse updatePost(Long postId, Long memberId, PostUpdateVO postUpdate) {

        Post post = findPostById(postId);

        if (memberId != post.getMember().getId()) {
            throw new CannotUpdatePost();
        }
        // 이미지 경로를 불러옴
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
     * 게시물 삭제
     * 로그인 유저가 게시물 작성자인지 체크
     * post 및 S3에 저장된 이미지파일 삭제
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

        //조회 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (postResponses.size() > pageable.getPageSize()) {
            hasNext = true;
            // 확인용으로 추가한데이터 remove(findList에서 limit에 +1해서 조회한 데이터)
            postResponses.remove(pageable.getPageSize());
        }

        log.info("Slice PostResponse size = {}", postResponses.size());

        return new SliceImpl<>(postResponses, pageable, hasNext);
    }

    public Post findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFound::new);
    }

}
