package com.backend.fitchallenge.domain.service;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.post.dto.request.PostCreateVO;
import com.backend.fitchallenge.domain.post.dto.request.PostUpdateVO;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.post.exception.PostNotFound;
import com.backend.fitchallenge.domain.post.repository.PostRepository;
import com.backend.fitchallenge.domain.post.service.PostService;
import com.backend.fitchallenge.domain.tag.domain.Tag;
import com.backend.fitchallenge.domain.tag.dto.TagDto;
import com.backend.fitchallenge.domain.tag.repository.QueryTagRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.backend.fitchallenge.domain.util.Builder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostServiceTest {

//    @Mock
//    private AwsS3Service awsS3Service;
//
//    @Mock
//    private MemberRepository memberRepository;
//
    @Mock
    private QueryTagRepository queryTagRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private Member member1;

    private Member member2;

    @Value("${cloud.aws.cloudFront.distributionDomain}")
    private String cloudFront;

    @BeforeAll
    public void setup() {
         member1 = dummyMember("abc@gmail.com", "페트르", "1234");

         member2 = dummyMember("bcd@gmail.com", "하울", "1233");

    }

    @Test
    void toPost() {
        //given
        List<TagDto> tagDtos = Arrays.asList(new TagDto("오운완"), new TagDto("헬스"));

        List<Tag> tags = tagDtos.stream().map(tagDto -> tagDto.toTag()).collect(Collectors.toList());

        PostCreateVO createVO = dummyPostCreate("3대 500 운동 측정", tagDtos);

        List<String> imagePathList = Arrays.asList("text.png");

        Post postWithTag = Post.toPostWithTag(createVO, tags, member1, imagePathList);
        Post post = Post.toPost(createVO, member1, imagePathList);

        assertThat(postWithTag.getContent()).isEqualTo(createVO.getContent());
        assertThat(postWithTag.getPostTags().get(0).getTag().getContent()).isEqualTo(createVO.getTagDtos().get(0).getName());
        assertThat(post.getContent()).isEqualTo(createVO.getContent());
    }

    @Test
    void getPostList() {



    }


    @Test
    void getSearchList() {

    }

    @Test
    void findTags() {
        //given

        given(queryTagRepository.findTagsByContent(Mockito.anyList()))
                .willReturn(new ArrayList<>());

        assertThrows(PostNotFound.class, () -> postService.getSearchList(1L, Mockito.any(Pageable.class), 1L, new ArrayList<>()));

    }

    @Test
    void patch() {
        //given
        //post 생성
        List<TagDto> tagDtos = Arrays.asList(new TagDto("오운완"), new TagDto("헬스"));

        List<Tag> tags = tagDtos.stream().map(tagDto -> tagDto.toTag()).collect(Collectors.toList());

        PostCreateVO createVO = dummyPostCreate("3대 500 운동 측정", tagDtos);

        List<String> paths = Arrays.asList("text.png");
        //post 수정
        List<TagDto> tagDtoUpdate = Arrays.asList(new TagDto("3대 500"));

        List<Tag> tagUpdate = tagDtoUpdate.stream().map(tagDto -> tagDto.toTag()).collect(Collectors.toList());

        List<String> pathUpdate = Arrays.asList("image.png");

        PostUpdateVO updateVO = dummyPostUpdate("챌린지 1일차 승리!", tagDtoUpdate);

        //when
        Post postWithTag = Post.toPostWithTag(createVO, tags, member1, paths);
        Post post = Post.toPost(createVO, member1, paths);

        postWithTag.patchWithTag(updateVO.getContent(),pathUpdate,tagUpdate);
        post.patch(updateVO.getContent(),pathUpdate);

        //then
        assertThat(postWithTag.getContent()).isEqualTo(updateVO.getContent());
        assertThat(postWithTag.getPostTags().get(0).getTag().getContent()).isEqualTo(updateVO.getTagDtos().get(0).getName());
        assertThat(post.getContent()).isEqualTo(updateVO.getContent());
    }


}
