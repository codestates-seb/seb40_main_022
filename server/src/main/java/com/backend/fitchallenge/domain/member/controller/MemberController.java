package com.backend.fitchallenge.domain.member.controller;

import com.backend.fitchallenge.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity create(){

        return new ResponseEntity(HttpStatus.CREATED);
    }

    //회원 정보 수정
    @PatchMapping("/myPage")
    public ResponseEntity update(){

        return new ResponseEntity(HttpStatus.OK);
    }

    //마이페이지
    @GetMapping("/myPage")
    public ResponseEntity myInfoDetails(){

        return new ResponseEntity(HttpStatus.OK);
    }

    //특정 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity memberDetails(){

        return new ResponseEntity(HttpStatus.OK);
    }

    //인플루언서 목록
    @GetMapping("/profession")
    public ResponseEntity professionalList(){

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/myPage/delete")
    public ResponseEntity delete(){

        return new ResponseEntity(HttpStatus.OK);
    }
}
