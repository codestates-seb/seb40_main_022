package com.backend.fitchallenge.domain.member.dto.request;

import com.backend.fitchallenge.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class MemberCreate {

    @Email(message = "이메일 형식으로 입력해주세요.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "활동을 위한 닉네임을 설정해주세요.")
    private String username;

    @Builder
    public MemberCreate(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public Member toMember(PasswordEncoder passwordEncoder){
        return Member.createBuilder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .username(username)
                .build();
    }

    public Member toMember(){
        return Member.createBuilder()
                .email(email)
                .build();
    }
}
