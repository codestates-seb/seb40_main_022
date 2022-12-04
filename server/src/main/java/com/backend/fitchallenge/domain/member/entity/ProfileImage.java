package com.backend.fitchallenge.domain.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFILE_IMAGE_ID")
    private Long id;

    @Column(name = "PATH", nullable = false)
    private String path;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    private ProfileImage(String path, Member member) {
        this.path = path;
        this.member = member;
    }

    public static ProfileImage createWithPath(String path){
        return ProfileImage.builder()
                .path(path)
                .build();
    }

    public void setMember(Member member){
        this.member = member;
    }

    public void updatePath(String path){
        this.path = path;
    }
}
