package com.backend.fitchallenge.domain.postcomment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdate {

        @NotBlank(message = "댓글 내용은 공백이 아니어야 합니다.")
        private String content;

}
