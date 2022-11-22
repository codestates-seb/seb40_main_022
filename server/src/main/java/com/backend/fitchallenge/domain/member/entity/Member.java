package com.backend.fitchallenge.domain.member.entity;


import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.member.dto.request.MemberUpdateVO;
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

    @Column(name = "PERIOD")
    private Integer period;

    @Enumerated(EnumType.STRING)
    private Authority authority = Authority.ROLE_USER;

    @Embedded
    private MemberActivity memberActivity;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProfileImage profileImage;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @Builder(builderMethodName = "createBuilder")
    public Member(String email, String password, String username, ProfileImage profileImage) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.profileImage = profileImage;
        this.memberActivity = new MemberActivity();
    }

    public void update(MemberUpdateVO memberUpdateVO, PasswordEncoder passwordEncoder){

        this.password = memberUpdateVO.getPassword() == null ?
                this.password : passwordEncoder.encode(memberUpdateVO.getPassword());
        this.username = memberUpdateVO.getUsername() == null ? this.username : memberUpdateVO.getUsername();
        this.job = memberUpdateVO.getJob() == null ? this.job : memberUpdateVO.getJob();
        this.address = memberUpdateVO.getAddress() == null ? this.address : memberUpdateVO.getAddress();
        this.gender = memberUpdateVO.getGender() == null ? this.gender : memberUpdateVO.getGender();
        this.age = memberUpdateVO.getAge() == null ? this.age : memberUpdateVO.getAge();
        this.height = memberUpdateVO.getHeight() == null ? this.height : memberUpdateVO.getHeight();
        this.weight = memberUpdateVO.getWeight() == null ? this.weight : memberUpdateVO.getWeight();
        this.memberActivity = MemberActivity.builder().kilogram(
                memberUpdateVO.getKilogram() == null ? this.memberActivity.getKilogram() : memberUpdateVO.getKilogram())
                .build();
        this.split = memberUpdateVO.getSplit() == null ? this.split : memberUpdateVO.getSplit();
        this.period = memberUpdateVO.getPeriod() == null ? this.period : memberUpdateVO.getPeriod();
    }


    public void addChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public void suspendChallenge() {
        this.challenge = null;
    }
}
