package com.backend.fitchallenge.domain.member.entity;

import com.backend.fitchallenge.domain.member.dto.request.MemberCreate;
import com.backend.fitchallenge.domain.member.dto.request.MemberUpdate;
import com.backend.fitchallenge.global.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "EMAIL", nullable = false, updatable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PROFILE_IMAGE")
    private String profileImage;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "JOB")
    private String job;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "HEIGHT")
    private Integer height;

    @Column(name = "WEIGHT")
    private Integer weight;

    @Column(name = "SPLIT")
    private Integer split;

//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<String> roles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Authority authority = Authority.ROLE_USER;

    @Embedded
    private MemberActivity memberActivity;

    //MemberCreate에서 사용하기 위함.
    @Builder(builderMethodName = "createBuilder")
    public Member(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.memberActivity = new MemberActivity();
    }

    //멤버 정보를 업데이트 해주는 메서드
    public void update(MemberUpdate memberUpdate, PasswordEncoder passwordEncoder){

        this.password = memberUpdate.getPassword() == null ?
                this.password : passwordEncoder.encode(memberUpdate.getPassword());
        this.username = memberUpdate.getUsername() == null ? this.username : memberUpdate.getUsername();
        this.job = memberUpdate.getJob() == null ? this.job : memberUpdate.getJob();
        this.address = memberUpdate.getAddress() == null ? this.address : memberUpdate.getAddress();
        this.gender = memberUpdate.getGender() == null ? this.gender : memberUpdate.getGender();
        this.age = memberUpdate.getAge() == null ? this.age : memberUpdate.getAge();
        this.height = memberUpdate.getHeight() == null ? this.height : memberUpdate.getHeight();
        this.weight = memberUpdate.getWeight() == null ? this.weight : memberUpdate.getWeight();
        this.memberActivity = MemberActivity.builder().kilogram(
                memberUpdate.getKilogram() == null ? this.memberActivity.getKilogram() : memberUpdate.getKilogram())
                .build();
        this.split = memberUpdate.getSplit() == null ? this.split : memberUpdate.getSplit();
        this.profileImage = memberUpdate.getProfileImage() == null ? this.profileImage : memberUpdate.getProfileImage();
    }
}
