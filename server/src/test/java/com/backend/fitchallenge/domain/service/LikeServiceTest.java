package com.backend.fitchallenge.domain.service;

import com.backend.fitchallenge.domain.config.TestMemberDetailService;
import com.backend.fitchallenge.domain.like.entity.Likes;
import com.backend.fitchallenge.domain.like.repository.LikeRepository;
import com.backend.fitchallenge.domain.like.service.LikeService;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.service.MemberService;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.backend.fitchallenge.domain.util.Builder.dummyMember;
import static com.backend.fitchallenge.domain.util.Builder.dummyPost;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @Mock
    private LikeRepository likeRepository;

    @InjectMocks
    private LikeService likeService;

    @Mock
    private MemberService memberService;

    @Mock
    private PostService postService;

    @Test
    void up() {
        //given
        Member member = dummyMember(TestMemberDetailService.UserName, "프랭크", "123456");

        Post post = dummyPost(member, "3대 500 달성");

        given(postService.findPostById(Mockito.anyLong()))
                .willReturn(post);
        given(memberService.findVerifiedMemberById(Mockito.anyLong()))
                .willReturn(member);
        willDoNothing().given(likeRepository).up(Mockito.any(Likes.class));

        //when
        likeService.up(1L,1L);

        //then
        assertThat(post.getMember().getMemberActivity().getPoint()).isEqualTo(0.1);
    }

    @Test
    void cancel() {
        //given
        Member member = dummyMember(TestMemberDetailService.UserName, "프랭크", "123456");

        Post post = dummyPost(member, "3대 500 달성");

        given(postService.findPostById(Mockito.anyLong()))
                .willReturn(post);
        given(likeRepository.cancel(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(1L);

        //then
        likeService.cancel(1L,1L);

        assertThat(post.getMember().getMemberActivity().getPoint()).isEqualTo(-0.1);
    }

    @Test
    void toEntity() {

        Member member = dummyMember(TestMemberDetailService.UserName, "프랭크", "123456");

        Post post = dummyPost(member, "3대 500 달성");

        Likes likes = Likes.toEntity(member, post);

        assertThat(likes.getPost().getContent()).isEqualTo(post.getContent());
        assertThat(likes.getMember().getUsername()).isEqualTo(member.getUsername());
    }
}