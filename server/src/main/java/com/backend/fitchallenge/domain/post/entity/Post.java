package com.backend.fitchallenge.domain.post.entity;


import com.backend.fitchallenge.domain.like.entity.Likes;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.post.dto.PostCreateVO;
import com.backend.fitchallenge.domain.postcomment.entity.PostComment;
import com.backend.fitchallenge.domain.tag.domain.Tag;
import com.backend.fitchallenge.global.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.*;

@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = " post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")
    private Member member;


    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "view", nullable = false)
    private Long view;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Picture> pictures = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTag> postTags = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComment> postComments = new ArrayList<>();

    @Builder
    public Post(Member member, String content, Long view) {
        this.member = member;
        this.content = content;
        this.view = 0L;
    }

    /**
     *   Tag가 없는 post 생성메서드
     *   post 생성후, createPicture 메서드로 각 이미지의 경로와 postId를 가진 Picture 생성
     */
    public static Post create(PostCreateVO postCreateVO, Member member, List<String> paths) {
        Post post = Post.builder()
                .member(member)
                .content(postCreateVO.getContent())
                .build();

        paths.forEach(path -> Picture.create(post,path));

        return post;
    }
    /**
     *  Tag를 추가한 post 생성메서드
     *  post 생성후, addPostTag 메서드로 Tag 객체와 postId를 가진 PostTag 생성
     *  createPicture 메서드로 각 이미지의 경로와 postId를 가진 Picture 생성
     */
    public static Post toPostWithTag(PostCreateVO postCreateVO, List<Tag> tags, Member member, List<String> paths) {
        Post post = Post.builder()
                .member(member)
                .content(postCreateVO.getContent())
                .build();

        tags.forEach(tag -> PostTag.create(post, tag));
        paths.forEach(path -> Picture.create(post,path));

        return post;
    }


    public void  patchWithTag(String content, List<String> paths, List<Tag> tags ) {
        this.content = content;
        //기존 태그,사진 지운다
        this.postTags.clear();
        this.pictures.clear();

        tags.forEach(tag ->PostTag.create(this, tag) );
        paths.forEach(path -> Picture.create(this,path));
    }


    public void patch(String content, List<String> paths) {
        this.content = content;
        this.pictures.clear();
        paths.forEach(path -> Picture.create(this,path));
    }
}
