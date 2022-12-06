package com.backend.fitchallenge.domain.member.entity;


import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.member.dto.request.MemberUpdateVO;
import com.backend.fitchallenge.domain.record.entity.Record;
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
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String username;

    @Column
    private String gender;

    @Column
    private String job;

    @Column
    private String address;

    @Column
    private Integer age;

    @Column
    private Integer height;

    @Column
    private Integer weight;

    @Column
    private Integer split;

    @Column
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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Record> records = new ArrayList<>();

    @Builder(builderMethodName = "createBuilder")
    public Member(Long memberId, String email, String password, String username, ProfileImage profileImage) {
        this.id = memberId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.profileImage = profileImage;
        this.memberActivity = new MemberActivity();
    }

    @Builder(builderMethodName = "dummyBuilder")
    public Member(Long id, String email, String password, String username,
                  String gender, String job, String address, Integer age,
                  Integer height, Integer weight, Integer split, Integer period,
                  Authority authority, MemberActivity memberActivity, ProfileImage profileImage) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.job = job;
        this.address = address;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.split = split;
        this.period = period;
        this.authority = authority;
        this.memberActivity = memberActivity;
        this.profileImage = profileImage;
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

        if(memberUpdateVO.getKilogram() == null){
            this.memberActivity = this.memberActivity;
        }
        else{
            this.memberActivity = memberActivity.update(memberUpdateVO.getKilogram());
        }

        this.split = memberUpdateVO.getSplit() == null ? this.split : memberUpdateVO.getSplit();
        this.period = memberUpdateVO.getPeriod() == null ? this.period : memberUpdateVO.getPeriod();
    }

    public void oauth2Update(ProfileImage profileImage){
        this.profileImage = profileImage;
    }


    public void addChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public void suspendChallenge() {
        this.challenge = null;
    }
}
