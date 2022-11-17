package com.backend.fitchallenge.domain.member.controller;

import com.backend.fitchallenge.domain.member.dto.request.MemberCreate;
import com.backend.fitchallenge.domain.member.dto.request.MemberUpdate;
import com.backend.fitchallenge.domain.member.dto.response.MyPageResponse;
import com.backend.fitchallenge.domain.member.dto.response.DetailsMemberResponse;
import com.backend.fitchallenge.domain.member.service.MemberService;
import com.backend.fitchallenge.global.annotation.AuthMember;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity create(@RequestBody @Valid MemberCreate memberCreate){

        Long createdId = memberService.createMember(memberCreate);

        return new ResponseEntity(createdId, HttpStatus.CREATED);
    }

    //회원 정보 수정
    @PatchMapping("/myPage")
    public ResponseEntity update(@AuthMember MemberDetails memberDetails,
                                 @RequestBody @Valid MemberUpdate memberUpdate){

        MemberUpdate updatedMember = memberService.updateMember(memberDetails.getEmail(), memberUpdate);

        return new ResponseEntity(updatedMember, HttpStatus.OK);
    }

    //마이페이지
    @GetMapping("/myPage")
    public ResponseEntity myInfoDetails(@AuthMember MemberDetails memberDetails){

        System.out.println(memberDetails.toString());
        MyPageResponse myPageResponse = memberService.getMyInfo(memberDetails.getEmail());

        return new ResponseEntity(myPageResponse, HttpStatus.OK);
    }

    //특정 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity memberDetails(@PathVariable("id") @Positive Long memberId){

        DetailsMemberResponse detailsMemberResponse = memberService.getMember(memberId);

        return new ResponseEntity(detailsMemberResponse, HttpStatus.OK);
    }

    //인플루언서 목록
    @GetMapping("/profession")
    public ResponseEntity professionalList(){

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/myPage/delete")
    public ResponseEntity delete(@AuthMember MemberDetails memberDetails){

        Long deletedMemberId = memberService.deleteMember(memberDetails.getEmail());

        return new ResponseEntity(deletedMemberId, HttpStatus.OK);
    }
}
