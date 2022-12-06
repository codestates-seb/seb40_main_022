package com.backend.fitchallenge.domain.postcomment.dto;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.postcomment.entity.PostComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreate {

        @NotBlank(message = "댓글 내용은 공백이 아니어야 합니다.")
        private String content;

        public PostComment toEntity(Post post, Member member) {
                return PostComment.builder()
                        .content(content)
                        .post(post)
                        .member(member)
                        .build();
        }
}
