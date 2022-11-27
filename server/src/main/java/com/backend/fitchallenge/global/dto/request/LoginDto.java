package com.backend.fitchallenge.global.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginDto {

    @NotBlank(message = "아이디는 공백이 아니여야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 공백이 아니여야 합니다.")
    private String password;
}
