package com.backend.fitchallenge.domain.member.dto.request;

import com.backend.fitchallenge.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreate {

    @Email(message = "이메일 형식으로 입력해주세요.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "활동을 위한 닉네임을 설정해주세요.")
    private String username;

    public Member toMember(){
        return Member.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
    }
}
