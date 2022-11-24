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

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @DeleteMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response){

        String refreshToken = request.getHeader("RefreshToken");
        String accessToken = request.getHeader("Authorization").substring(7);

        authService.logoutMember(refreshToken, accessToken);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/reissue")
    public ResponseEntity reIssue(HttpServletRequest request, HttpServletResponse response){

        String refreshToken = request.getHeader("RefreshToken");
        String accessToken = request.getHeader("Authorization").substring(7);

        String reIssueAccessToken = authService.reissueAccessToken(refreshToken, accessToken);
        String reIssueRefreshToken = authService.reissueRefreshToken(refreshToken);

        response.setHeader("Authorization", "Bearer " + reIssueAccessToken);
        response.setHeader("RefreshToken", reIssueRefreshToken);

        return new ResponseEntity(HttpStatus.OK);
    }
}
