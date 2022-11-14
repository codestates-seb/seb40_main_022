package com.backend.fitchallenge.domain.picture.entity;

import com.backend.fitchallenge.domain.post.entity.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Picture {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path", nullable = false)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Picture(String path, Post post) {
        this.path = path;
        this.post = post;
    }

    /**
     * 연관관계 편의 메소드
     * 받아온 파라미터로 Picture 생성후, post에 추가
     */
    public static void createPicture( Post post,String path) {
        Picture picture = Picture.builder()
                .path(path)
                .post(post)
                .build();

        post.getPictures().add(picture);
    }
}
