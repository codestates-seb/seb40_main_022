package com.backend.fitchallenge.domain.controller;

import com.backend.fitchallenge.domain.config.TestMemberDetailService;
import com.backend.fitchallenge.domain.member.SimplePostMemberResponse;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.post.dto.request.PostCreateVO;
import com.backend.fitchallenge.domain.post.dto.request.PostGet;
import com.backend.fitchallenge.domain.post.dto.request.PostSearch;
import com.backend.fitchallenge.domain.post.dto.request.PostUpdateVO;
import com.backend.fitchallenge.domain.post.dto.response.MultiResponse;
import com.backend.fitchallenge.domain.post.dto.response.PostResponse;
import com.backend.fitchallenge.domain.post.dto.response.PostUpdateResponse;
import com.backend.fitchallenge.domain.post.dto.response.SimplePostResponse;
import com.backend.fitchallenge.domain.post.service.AwsS3Service;
import com.backend.fitchallenge.domain.post.service.PostService;
import com.backend.fitchallenge.domain.tag.dto.TagDto;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.backend.fitchallenge.domain.util.Builder.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @MockBean
    private PostService postService;

    @MockBean
    AwsS3Service awsS3Service;

    @Autowired
    private Gson gson;

    private final TestMemberDetailService testMemberDetailService = new TestMemberDetailService();

    private MemberDetails memberDetails;

    private Member member1;

    private Member member2;

    @Value("${cloud.aws.cloudFront.distributionDomain}")
    private String cloudFront;

    private static final String USER_DEFAULT_IMAGE = "imagesForS3Test/botobo-default-profile.png";


    @BeforeAll
    public void setup() {

        member1 = dummyMember(TestMemberDetailService.UserName, "프랭크", "123456");

        member2 = dummyMember("abc@gmail.com", "페트르", "123456");

        memberRepository.save(member1);
        memberRepository.save(member2);

        memberDetails = testMemberDetailService.loadUserByUsername(TestMemberDetailService.UserName);

    }

    @Test
    void create() throws Exception {
        //given
        MockMultipartFile firstFile = new MockMultipartFile("attachments", "Image.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                "image".getBytes());

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.put("tagDtos", List.of("오운완","헬스"));

        List<TagDto> tagDtos = Arrays.asList(new TagDto("오운완"), new TagDto("헬스"));

        PostCreateVO createVO = PostCreateVO.builder()
                .content("3대 500 운동 측정")
                .tagDtos(tagDtos)
                .build();

        String content = gson.toJson(createVO);

        List<String> imagePathList = Arrays.asList(cloudFront + "/" + "weujksahu12351.png");

        given(awsS3Service.StoreFile(Mockito.anyList()))
                .willReturn(imagePathList);

        given(postService.createPost(Mockito.anyLong(), Mockito.any(PostCreateVO.class), Mockito.anyList()))
                .willReturn(1L);

        //when & then
        MvcResult result = mockMvc.perform(
                        multipart("/dailyPosts")
                                .file(firstFile)
                                .with(user(memberDetails))
                                .params(multiValueMap)
                                .param("content", "3대 500 운동 측정"))
                .andExpect(status().isCreated())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    void getList() throws Exception {
        //given
        PostGet postGet = new PostGet(4L);

        String content = gson.toJson(postGet);

        SimplePostMemberResponse member1 = dummyPostMemberResponse(2L, USER_DEFAULT_IMAGE, "페트르");
        SimplePostMemberResponse member2 = dummyPostMemberResponse(3L, USER_DEFAULT_IMAGE, "프랭크");

        SimplePostResponse post1 = dummySimplePostResponse(2L, LocalDateTime.now().minusDays(1), "3대 400 달성", 3, 5);
        SimplePostResponse post2 = dummySimplePostResponse(3L, LocalDateTime.now(), "챌린지 승리", 2, 10);

        PostResponse response1 = dummyPostResponse(Arrays.asList("오운완", "헬스"), member1, post1, Arrays.asList(cloudFront + "/" + "weujksahu12351.png"), false);
        PostResponse response2 = dummyPostResponse(Arrays.asList("3대 500", "챌린지"), member2, post2, Arrays.asList(cloudFront + "/" + "sdafkj1231512.png"), true);

        List<PostResponse> responses = Arrays.asList(response1, response2);

        given(postService.getPostList(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(PageRequest.class)))
                .willReturn(MultiResponse.of(new SliceImpl<>(responses, PageRequest.of(0, 3), false)));
        //when
        ResultActions actions = mockMvc.perform(
                get("/dailyPosts")
                        .with(user(memberDetails))
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content));

        MvcResult mvcResult = actions.andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    void getSearchList() throws Exception {

        PostSearch postSearch = new PostSearch("#오운완",4L);

        String content = gson.toJson(postSearch);

        SimplePostMemberResponse member1 = dummyPostMemberResponse(2L, USER_DEFAULT_IMAGE, "페트르");
        SimplePostMemberResponse member2 = dummyPostMemberResponse(3L, USER_DEFAULT_IMAGE, "프랭크");

        SimplePostResponse post1 = dummySimplePostResponse(2L, LocalDateTime.now().minusDays(1), "3대 400 달성", 3, 5);
        SimplePostResponse post2 = dummySimplePostResponse(3L, LocalDateTime.now(), "챌린지 승리", 2, 10);

        PostResponse response1 = dummyPostResponse(Arrays.asList("오운완", "헬스"), member1, post1, Arrays.asList(cloudFront + "/" + "weujksahu12351.png"), false);
        PostResponse response2 = dummyPostResponse(Arrays.asList("3대 500", "챌린지"), member2, post2, Arrays.asList(cloudFront + "/" + "sdafkj1231512.png"), true);

        List<PostResponse> responses = Arrays.asList(response1, response2);

        given(postService.getSearchList(Mockito.anyLong(), Mockito.any(PageRequest.class), Mockito.anyLong(),Mockito.anyList()))
                .willReturn(MultiResponse.of(new SliceImpl<>(responses, PageRequest.of(0, 4), false)));

        mockMvc.perform(
                        get("/dailyPosts/search")
                                .with(user(memberDetails))
                                .accept(MediaType.APPLICATION_JSON)
                                .param("lastPostId", "4")
                                .param("tag", "#오운완"))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        //given
        Long postId = 1L;

        MockMultipartFile secondFile = new MockMultipartFile("attachments", "Sports.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                "image".getBytes());

        List<TagDto> tagDtos = Arrays.asList(new TagDto("3대500"), new TagDto("바프"));

        PostUpdateVO updateVO = dummyPostUpdate("바디프로필 D-100", tagDtos);

        String content = gson.toJson(updateVO);

        List<String> imagePathList = Arrays.asList(cloudFront + "/" + "sdafweds12421.png");

        PostUpdateResponse response = PostUpdateResponse.builder()
                .postId(1L)
                .content("바디프로필 D-100")
                .tags(Arrays.asList("3대500", "바프"))
                .imagePaths(imagePathList)
                .build();


        given(postService.updatePost(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(PostUpdateVO.class)))
                .willReturn(response);
        //when & then
        mockMvc.perform(
                        multipart("/dailyPosts/{id}", postId)
                                .file(secondFile)
                                .with(user(memberDetails))
                                .content(content))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.content").value(response.getContent()));
    }

    @Test
    void delete() throws Exception {
        Long postId = 1L;

        willDoNothing().given(postService).deletePost(Mockito.anyLong(), Mockito.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/dailyPosts/{id}", postId)
                                .with(user(memberDetails)))
                .andExpect(status().isOk());

    }
}