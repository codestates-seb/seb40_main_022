package com.backend.fitchallenge.domain.member.dto.request;

import com.backend.fitchallenge.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
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

    @Builder
    private MemberUpdate(String password, String username, String job,
                        String address, String gender, Integer age,
                        Integer height, Integer weight, Integer kilogram,
                        Integer split, String profileImage) {
        this.password = password;
        this.username = username;
        this.job = job;
        this.address = address;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.kilogram = kilogram;
        this.split = split;
        this.profileImage = profileImage;
    }

    public static MemberUpdate of(Member member){
        return MemberUpdate.builder()
                .password(member.getPassword())
                .username(member.getUsername())
                .job(member.getJob())
                .address(member.getAddress())
                .gender(member.getGender())
                .age(member.getAge())
                .height(member.getHeight())
                .weight(member.getWeight())
                .kilogram(member.getMemberActivity().getKilogram())
                .split(member.getSplit())
                .profileImage(member.getProfileImage())
                .build();
    }
}
