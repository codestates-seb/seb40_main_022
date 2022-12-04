package com.backend.fitchallenge.domain.member.dto.request;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.entity.ProfileImage;
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

    public Member toEntity(PasswordEncoder passwordEncoder, ProfileImage profileImage){
        return Member.createBuilder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .username(username)
                .profileImage(profileImage)
                .build();
    }

    public Member toEntity(){
        return Member.createBuilder()
                .email(email)
                .username(username)
                .password(password)
                .build();
    }
}
