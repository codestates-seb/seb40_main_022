package com.backend.fitchallenge.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdate {

    private String password;
    private String username;
    private String job;
    private String address;
    private String gender;
    private Integer age;
    private Integer height;
    private Integer weight;
    private Integer kilogram;
    private Integer split;
    private String profileImage;

}
