package com.backend.fitchallenge.domain.challenge.entity;

import com.backend.fitchallenge.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor

public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="challenge_id")
    private Long id;

    @OneToMany
    private List<Member> members = new ArrayList<>();


    private Challenge(List<Member> members) {
        this.members = members;
    }

    public static Challenge of(List<Member> members) {
       return new Challenge(members);
    }
}
