package com.backend.fitchallenge.domain.service;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.post.service.PostService;
import com.backend.fitchallenge.domain.postcomment.dto.request.CommentCreate;
import com.backend.fitchallenge.domain.postcomment.dto.request.CommentUpdate;
import com.backend.fitchallenge.domain.postcomment.dto.response.CommentResponse;
import com.backend.fitchallenge.domain.postcomment.entity.PostComment;
import com.backend.fitchallenge.domain.postcomment.repository.CommentRepository;
import com.backend.fitchallenge.domain.postcomment.repository.QueryCommentRepository;
import com.backend.fitchallenge.domain.postcomment.service.CommentService;
import com.backend.fitchallenge.global.dto.response.SliceMultiResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.backend.fitchallenge.domain.util.Builder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Transactional
class CommentServiceTest {
    @Mock
   private PostService postService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private QueryCommentRepository queryCommentRepository;

    @Mock
   private MemberRepository memberRepository;

    @InjectMocks
    private CommentService commentService;

    private Member member1;

    private Member member2;

    private Post post1;

    @BeforeAll
    public void setup() {

        member1 = dummyMember("abc@gmail.com", "페트르", "1234");

        member2 = dummyMember("bcd@gmail.com", "하울", "1233");

        memberRepository.save(member1);
        memberRepository.save(member2);

        post1 = dummyPost(member1, "3대 운동 기록 측정");
    }

    @Test
    void createComment() {
        //given
        CommentCreate commentCreate = new CommentCreate("멋있으세요 화이팅");

        //when
        PostComment comment = commentCreate.toEntity(post1, member1);

        //then
        assertThat(comment.getContent()).isEqualTo(commentCreate.getContent());
        assertThat(comment.getPost().getContent()).isEqualTo(post1.getContent());
        assertThat(comment.getMember().getUsername()).isEqualTo(member1.getUsername());
    }

    @Test
    void getCommentList() {
        List<PostComment> postComments = Arrays.asList(
                dummyComment("입고 계신 헬스나시 멋지시네요 정보 공유좀", post1, member1),
                dummyComment("식단 어떻게 하고 있으세요?", post1, member2));

        given(queryCommentRepository.findPostComments(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(PageRequest.class)))
                .willReturn(postComments);

        SliceMultiResponse<CommentResponse> response = commentService.getCommentList(1L, 4L, PageRequest.of(0, 5));

        assertThat(response.getItems().size()).isEqualTo(2);
        assertThat(response.getItems().get(0).getContent()).isEqualTo("입고 계신 헬스나시 멋지시네요 정보 공유좀");

    }

    @Test
    void updateComment() {
        //given
        PostComment postComment = dummyComment("입고 계신 헬스나시 멋지시네요 정보 공유좀", post1, member1);

        CommentUpdate commentUpdate = new CommentUpdate("3분할 운동 추천해주세요");

        //when
        postComment.patch(commentUpdate);
        //then
        assertThat(postComment.getContent()).isEqualTo(commentUpdate.getContent());

    }

    @Test
    void deleteComment() {
        PostComment postComment = dummyComment("입고 계신 헬스나시 멋지시네요 정보 공유좀", post1, member1);

        given(commentRepository.findById(Mockito.anyLong()))
                .willReturn(Optional.of(postComment));

        //when
        commentService.deleteComment(1L,1L);
        verify(commentRepository, times(1))
                .delete(Mockito.any(PostComment.class));

    }
}