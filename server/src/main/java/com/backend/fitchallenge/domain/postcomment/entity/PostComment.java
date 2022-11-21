package com.backend.fitchallenge.domain.postcomment.entity;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.postcomment.dto.CommentUpdate;
import com.backend.fitchallenge.global.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class PostComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_comment_id")
    private Long id;


    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "member_id")
    private Member member;

    @Builder
    public PostComment(String content, Post post, Member member) {
        this.content = content;
        this.post = post;
        this.member = member;
    }

    public void patch(CommentUpdate commentUpdate) {
        String updateContent = commentUpdate.getContent();
        this.content = updateContent == null ? this.content : updateContent;
    }
}
