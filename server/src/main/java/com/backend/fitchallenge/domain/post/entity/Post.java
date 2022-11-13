package com.backend.fitchallenge.domain.post.entity;

import com.backend.fitchallenge.domain.picture.challenge.Member;
import com.backend.fitchallenge.domain.picture.entity.Picture;
import com.backend.fitchallenge.domain.post.dto.PostCreateVO;
import com.backend.fitchallenge.domain.tag.domain.Tag;
import com.backend.fitchallenge.global.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = " post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name ="member_id")
    private Member member;


    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "view", nullable = false)
    private Long view;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Picture> pictures = new ArrayList<>();

//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTag> postTags = new ArrayList<>();

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
    public static Post toPost(PostCreateVO postCreateVO, Member member, List<String> imagePathList) {
        Post post = Post.builder()
                .member(member)
                .content(postCreateVO.getContent())
                .build();

        imagePathList.forEach(path -> Picture.createPicture(path,post));

        return post;
    }

    /**
     *  Tag를 추가한 post 생성메서드
     *  post 생성후, addPostTag 메서드로 Tag 객체와 postId를 가진 PostTag 생성
     *  createPicture 메서드로 각 이미지의 경로와 postId를 가진 Picture 생성
     */
    public static Post toPostWithTag(PostCreateVO postCreateVO, List<Tag> tags, Member member, List<String> imagePathList) {
        Post post = Post.builder()
                .member(member)
                .content(postCreateVO.getContent())
                .build();

        tags.forEach(tag -> PostTag.addPostTag(post, tag));
        imagePathList.forEach(path -> Picture.createPicture(path,post));

        return post;
    }
}
