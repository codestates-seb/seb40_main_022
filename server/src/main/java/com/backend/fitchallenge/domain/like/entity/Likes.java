package com.backend.fitchallenge.domain.like.entity;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.global.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="member_post_uq", //unique 제약조건 이름
                        columnNames = {"member_id","post_id"} //unique 제약조건 적용할 컬럼명
                )
        }
)
public class Likes extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Likes(Member member, Post post) {
        this.member = member;
        this.post = post;
    }

    public static Likes toEntity(Member member, Post post) {
      return  Likes.builder()
                .member(member)
                .post(post)
                .build();
    }

}
