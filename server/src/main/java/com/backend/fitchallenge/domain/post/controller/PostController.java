package com.backend.fitchallenge.domain.post.controller;

import com.backend.fitchallenge.domain.post.dto.*;
import com.backend.fitchallenge.domain.post.service.AwsS3Service;
import com.backend.fitchallenge.domain.post.service.PostService;
import com.backend.fitchallenge.global.annotation.AuthMember;
import com.backend.fitchallenge.global.dto.response.SingleResponse;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//fixme: 카멜케이스 적용
@RestController
@RequestMapping("/dailyPosts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final AwsS3Service awsS3Service;

    @PostMapping
    public ResponseEntity<?> create(
            @AuthMember MemberDetails memberDetails,
            PostCreateVO postCreate
    ) {
        //S3에 파일업로드 후 저장된경로 리스트 반환
        List<String> imagePathList = awsS3Service.StoreFile(postCreate.getFiles());

        imagePathList.forEach(path -> log.info("path ={}", path));
        log.info("로그인 유저 Id ={}", memberDetails.getMemberId());

        Long postId = postService.createPost(memberDetails.getMemberId(), postCreate, imagePathList);

        return new ResponseEntity<>(new SingleResponse<>(postId), HttpStatus.CREATED);
    }

    /**
     * 전체 게시물 조회
     *
     * @param postGet  현재 유저가 보고있는 게시물의 마지막 postId를 담고있는 객체
     * @param pageable default page = 0, size = 3
     */
    @GetMapping
    public ResponseEntity<MultiResponse<?>> getList(
            @ModelAttribute PostGet postGet,
            @AuthMember MemberDetails memberDetails,
            @PageableDefault(size = 3) Pageable pageable) {

        log.info("lastPostId = {}", postGet.getLastPostId());

        if (memberDetails == null) {
            return new ResponseEntity<>(postService.getPostListWithoutLogin(postGet.getLastPostId(), pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(postService.getPostList(postGet.getLastPostId(), memberDetails.getMemberId(), pageable), HttpStatus.OK);
        }
    }

    /**
     * 게시물 검색
     * #~~ 형식의 tag 검색어 parsing 해서 tagNames List에 추가
     * @param pageable   size default 3
     * @param postSearch tag + lastPostId(마지막게시물 포스트 Id)
     */
    @GetMapping("/search")
    public ResponseEntity<MultiResponse<?>> getSearchList(@PageableDefault(size = 3) Pageable pageable,
                                                          @ModelAttribute PostSearch postSearch,
                                                          @AuthMember MemberDetails memberDetails) {

        List<String> tagNames = postSearch.queryParsing(postSearch.getTag());
        tagNames.forEach(tag -> log.info("tag ={}", tag));

        log.info("lastPostId = {}", postSearch.getLastPostId());

        if (memberDetails == null) {
            return new ResponseEntity<>(postService.getSearchListWithoutLogin(pageable, postSearch.getLastPostId(), tagNames), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(postService.getSearchList(memberDetails.getMemberId(), pageable, postSearch.getLastPostId(), tagNames), HttpStatus.OK);
        }
    }

    /**
     * 게시물 수정
     *
     * @param postUpdate    게시물 수정 요청 정보
     */
    @PostMapping("/{id}")
    public ResponseEntity<?> update(
            @AuthMember MemberDetails memberDetails, @PathVariable Long id,
            PostUpdateVO postUpdate) {
        return new ResponseEntity<>(postService.updatePost(id, memberDetails.getMemberId(), postUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @AuthMember MemberDetails memberDetails,
            @PathVariable Long id
    ) {
        postService.deletePost(id, memberDetails.getMemberId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
