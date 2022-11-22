package com.backend.fitchallenge.domain.member.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateVO {

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
    private MultipartFile profileImage;
    private Integer period;

    @Builder
    public MemberUpdateVO(String password, String username, String job, String address, String gender, Integer age,
                          Integer height, Integer weight, Integer kilogram, Integer split, MultipartFile profileImage, Integer period) {
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
        this.period = period;
    }
}

//    public static MemberUpdateVO of(Member member){
//        return MemberUpdateVO.builder()
//                .password(member.getPassword())
//                .username(member.getUsername())
//                .job(member.getJob())
//                .address(member.getAddress())
//                .gender(member.getGender())
//                .age(member.getAge())
//                .height(member.getHeight())
//                .weight(member.getWeight())
//                .kilogram(member.getMemberActivity().getKilogram())
//                .split(member.getSplit())
////                .profileImage(member.getProfileImage())
//                .period(member.getPeriod())
//                .build();
//    }
//}
