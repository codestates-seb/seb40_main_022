package com.backend.fitchallenge.domain.post.entity;

import com.backend.fitchallenge.domain.tag.domain.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    public PostTag(Post post, Tag tag) {
        this.post = post;
        this.tag = tag;
    }

    /**
     * 연관 관계 편의 메서드, postTag 생성후 post에 추가
     */
    public static void create(Post post, Tag tag) {
        PostTag postTag = PostTag.builder()
                .tag(tag)
                .post(post)
                .build();

        post.getPostTags().add(postTag);
    }
}
