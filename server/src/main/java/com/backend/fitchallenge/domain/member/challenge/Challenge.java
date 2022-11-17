package com.backend.fitchallenge.domain.member.challenge;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.global.audit.Auditable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHALLENGE_ID")
    private Long id;

    @OneToMany(mappedBy = "challenge")
    private List<Member> members = new ArrayList<>();
}
