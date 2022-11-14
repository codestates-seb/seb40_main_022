package com.backend.fitchallenge.domain.member.entity;

import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.global.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "USERNAME", nullable = false)
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
    private String split;

//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<String> roles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Authority authority = Authority.ROLE_USER;

    @Embedded
    private MemberActivity memberActivity = new MemberActivity();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    //MemberCreate로 부터 받아오기 위함.
    @Builder(builderMethodName = "createBuilder")
    public Member(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
