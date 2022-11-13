package com.backend.fitchallenge.domain.post.controller;

import com.backend.fitchallenge.domain.post.dto.PostCreateVO;
import com.backend.fitchallenge.domain.post.dto.PostUpdate;
import com.backend.fitchallenge.domain.post.service.AwsS3Service;
import com.backend.fitchallenge.domain.post.service.PostService;
import com.backend.fitchallenge.global.dto.request.PageRequest;
import com.backend.fitchallenge.global.dto.response.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
     * AuthenticationPrincipal을 통해 로그인세션 정보를 불러옴
     * @param postCreateVO 게시물 작성 요청 정보
     * @return 생성된 게시물 id, 응답상태코드 - created
     */
    @PostMapping
    public ResponseEntity<?> create(
            //@AuthenticationPrincipal AuthMember authMember,
            PostCreateVO postCreateVO
    ) {
        //S3에 파일업로드 후 저장된경로 리스트 반환
        List<String> imagePathList = awsS3Service.StoreFile(postCreateVO.getFiles());
        Long postId = postService.createPost(1L, postCreateVO, imagePathList);

        return new ResponseEntity<>(new SingleResponse<>(postId), HttpStatus.CREATED);
    }

    /**
     * 전체 게시물 조회
     * 무한 스크롤 페이지네이션
     * @return  최신순으로 페이지네이션된 게시물 목록
     */

    @GetMapping
    public ResponseEntity<?> getList(@ModelAttribute PageRequest pageRequest) {
        log.info("pageRequest = {}", pageRequest.getPage());
        log.info("offset = {}", pageRequest.getOffset());
        log.info("size = {}", pageRequest.getSize());

        postService.getPostList();

        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    /**
     * 게시물 수정
     * @AuthenticationPrincipal을 통해 로그인세션 정보를 불러옴
     * @param id 수정할 게시물 postId
     * @param postUpdate 게시물 수정 요청 정보
     * @return 수정된 게시물 정보, 응답상태코드 OK
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?>  update(
//            @AuthenticationPrincipal AuthMember authMember,
            @PathVariable Long id,
            @Valid @RequestBody PostUpdate postUpdate) {

//        log.info("authMember = {}", authMember);
//        Long memberId = authMember.getMemberId();




        return new ResponseEntity<>(null);
    }

    /**
     * 게시물 삭제
     * @AuthenticationPrincipal을 통해 로그인세션 정보를 불러옴
     * @param id 삭제할 게시물 postId
     * @return 응답상태코드 OK
     */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(
//            //@AuthenticationPrincipal AuthMember authMember,
//            @PathVariable Long id
//    )

//    @GetMapping("/search")
//    public ResponseEntity<MultiResponse<?>> getSearchPostList(PageRequest pageRequest,
//                                                              @ModelAttribute PostSearch postSearch ) {
//        log.info("pageRequest = {}", pageRequest.getPage());
//        log.info("offset = {}", pageRequest.getOffset());
//        log.info("size = {}", pageRequest.getSize());
//
//
//    }



}
