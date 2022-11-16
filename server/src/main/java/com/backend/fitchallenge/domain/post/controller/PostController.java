package com.backend.fitchallenge.domain.post.controller;

import com.backend.fitchallenge.domain.post.dto.MultiResponse;
import com.backend.fitchallenge.domain.post.dto.PostCreateVO;
import com.backend.fitchallenge.domain.post.dto.PostSearch;
import com.backend.fitchallenge.domain.post.dto.PostUpdateVO;
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

@RestController
@RequestMapping("/dailyposts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final AwsS3Service awsS3Service;

    /**
     * 게시물 작성
     * @param memberDetails  로그인세션 정보
     * @param postCreate 게시물 작성 요청 정보
     * @return 생성된 게시물 id, 응답상태코드 - created
     */
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
     * @param lastPostId 현재 유저가 보고있는 게시물의 마지막 postId
     * @param pageable default page = 0, size = 3
     * @return 최신순으로 페이지네이션된 게시물 목록, 응답 상태 코드 OK
     */
    @GetMapping
    public ResponseEntity<MultiResponse<?>> getList(
            @RequestParam Long lastPostId,
            @PageableDefault(size = 3) Pageable pageable) {
        return new ResponseEntity<>(postService.getPostList(lastPostId, pageable), HttpStatus.OK);
    }


    /**
     * 게시물 수정
     * @param memberDetails 로그인세션 정보
     * @param id 수정할 게시물 postId
     * @param postUpdate 게시물 수정 요청 정보
     * @return 수정된 게시물 정보, 응답상태코드 OK
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?>  update(
            @AuthMember MemberDetails memberDetails, @PathVariable Long id,
             PostUpdateVO postUpdate) {
        return new ResponseEntity<>(postService.updatePost(id,memberDetails.getMemberId(), postUpdate),HttpStatus.OK);
    }

    /**
     * 게시물 삭제
     * @AuthenticationPrincipal을 통해 로그인세션 정보를 불러옴
     * @param id 삭제할 게시물 postId
     * @return 응답상태코드 OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @AuthMember MemberDetails memberDetails,
            @PathVariable Long id
    ) {
        postService.deletePost(id, memberDetails.getMemberId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<MultiResponse<?>> getSearchList( @PageableDefault Pageable pageable,
                                                       @ModelAttribute  PostSearch postSearch,
                                                        @RequestParam Long lastPostId){

        List<String> tagNames = postSearch.queryParsing(postSearch.getTag());
        tagNames.forEach(tag -> log.info("tag = {}",tag));

        return new ResponseEntity<>(postService.getSearchList(pageable,lastPostId, tagNames),HttpStatus.OK);


    }



}
