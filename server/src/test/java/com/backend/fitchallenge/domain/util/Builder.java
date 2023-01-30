package com.backend.fitchallenge.domain.util;

import com.backend.fitchallenge.domain.challenge.dto.response.ChallengeResponse;
import com.backend.fitchallenge.domain.challenge.dto.response.RankingResponse;
import com.backend.fitchallenge.domain.member.SimplePostMemberResponse;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.notification.dto.NotificationResponse;

import com.backend.fitchallenge.domain.post.dto.PostCreateVO;
import com.backend.fitchallenge.domain.post.dto.PostResponse;
import com.backend.fitchallenge.domain.post.dto.PostUpdateVO;
import com.backend.fitchallenge.domain.post.dto.SimplePostResponse;
import com.backend.fitchallenge.domain.post.entity.Post;

import com.backend.fitchallenge.domain.postcomment.dto.CommentResponse;
import com.backend.fitchallenge.domain.postcomment.dto.CommentUpdateResponse;
import com.backend.fitchallenge.domain.postcomment.entity.PostComment;
import com.backend.fitchallenge.domain.tag.dto.TagDto;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
public class Builder {

    public static Member dummyMember(String email, String userName, String password) {

        return Member.createBuilder()
                .email(email)
                .username(userName)
                .password(password)
                .build();
    }

    public static RankingResponse dummyRanking(Long memberId, String userName, String profileImage, Integer height, Integer weight, Integer period, Boolean challengeStatus, LocalDate challengeEndDate) {

        return RankingResponse.builder()
                .memberId(memberId)
                .userName(userName)
                .profileImage(profileImage)
                .height(height)
                .weight(weight)
                .period(period)
                .challengeStatus(challengeStatus)
                .challengeEndDate(challengeEndDate)
                .build();
    }

    public static ChallengeResponse dummyChallenge(Long applicantId, String applicantName, String applicantImage, Integer applicantHeight, Integer applicantWeight,
                                                   Long counterpartId, String counterpartName, String counterpartImage, Integer counterpartHeight, Integer counterpartWeight) {
      return  ChallengeResponse.builder()
                .applicantId(applicantId)
                .applicantName(applicantName)
                .applicantImage(applicantImage)
                .applicantHeight(applicantHeight)
                .applicantWeight(applicantWeight)
                .counterpartId(counterpartId)
                .counterpartName(counterpartName)
                .counterpartImage(counterpartImage)
                .counterpartHeight(counterpartHeight)
                .counterpartWeight(counterpartWeight)
                .build();
    }

    public static PostComment dummyComment(String content, Post post, Member member) {
        return PostComment.builder()
                .content(content)
                .post(post)
                .member(member)
                .build();
    }

    public static CommentResponse dummyCommentResponse(Long commentId, String content, Long memberId, String userName, String profileImage) {

     return   CommentResponse.builder()
                .commentId(commentId)
                .content(content)
                .memberId(memberId)
                .userName(userName)
                .profileImage(profileImage)
                .build();
    }
    public static CommentUpdateResponse dummyCommentUpdate(Long commentId, String content) {
        return CommentUpdateResponse.builder()
                .commentId(commentId)
                .content(content)
                .build();
    }

    public static SimplePostMemberResponse dummyPostMemberResponse(Long userId, String profileImage, String username) {

        return SimplePostMemberResponse.builder()
                .userId(userId)
                .profileImage(profileImage)
                .username(username)
                .build();
    }

    public static SimplePostResponse dummySimplePostResponse(Long postId, LocalDateTime createdAt, String content, Integer commentCount, Integer likeCount) {

       return SimplePostResponse.builder()
                .postId(postId)
                .createdAt(createdAt)
                .content(content)
                .commentCount(commentCount)
                .likeCount(likeCount)
                .build();
    }

    public static PostResponse dummyPostResponse( List<String> tags, SimplePostMemberResponse member, SimplePostResponse post, List<String>  pictures, Boolean likeState) {

        return   PostResponse.builder()
                .tags(tags)
                .member(member)
                .post(post)
                .pictures(pictures)
                .likeState(likeState)
                .build();
    }

    public static PostUpdateVO dummyPostUpdate(String content, List<TagDto> tagDtos) {

       return PostUpdateVO.builder()
                .content(content)
                .tagDtos(tagDtos)
                .build();
    }

    public static NotificationResponse dummyNotification(Long id, String content, String url, LocalDateTime createdAt, Boolean read) {
        return NotificationResponse.builder()
                .id(id)
                .content(content)
                .url(url)
                .createdAt(createdAt)
                .read(read)
                .build();
    }

    public static Post dummyPost(Member member, String content) {
       return   Post.builder()
                .member(member)
                .content(content)
                .build();
    }

    public static PostCreateVO dummyPostCreate(String content, List<TagDto> tagDtos) {
        return   PostCreateVO.builder()
                .content(content)
                .tagDtos(tagDtos)
                .build();
    }

}
