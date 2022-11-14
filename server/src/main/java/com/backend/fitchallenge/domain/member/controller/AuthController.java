package com.backend.fitchallenge.domain.member.controller;

import com.backend.fitchallenge.domain.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @DeleteMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response){
        String refreshToken = request.getHeader("RefreshToken");
        authService.logoutMember(refreshToken);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/reissue")
    public ResponseEntity reIssue(HttpServletRequest request, HttpServletResponse response){

        String reIssueAccessToken = authService.reissueAccessToken(request.getHeader("refreshToken"));
        String reIssueRefreshToken = authService.reissueRefreshToken(request.getHeader("refreshToken"));

        response.setHeader("Authorization", "Bearer " + reIssueAccessToken);
        response.setHeader("RefreshToken", reIssueRefreshToken);

        return new ResponseEntity(HttpStatus.OK);
    }
}
