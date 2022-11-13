package com.backend.fitchallenge.domain.post.service;

import com.backend.fitchallenge.domain.picture.challenge.Member;
import com.backend.fitchallenge.domain.post.dto.PostCreateVO;
import com.backend.fitchallenge.domain.post.dto.TagDto;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.post.repository.PostRepository;
import com.backend.fitchallenge.domain.tag.domain.Tag;
import com.backend.fitchallenge.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {


    private final PostRepository postRepository;
    private final TagService tagService;

    /**
     * toPostWithTag - Tag가 있을경우 tagRepository에 저장후 태그를 포함한 Post,PostTag 생성
     * toPost - Tag가 없을경우, Tag를 포함하지 않는 Post 생성
     */
    public Long createPost(Long memberId, PostCreateVO postCreateVO, List<String> imagePathList) {

        // 현재 사용자 가져오는 로직으로 수정 필요
        Member member = init();
        //Tag가 있는 경우
        if (postCreateVO.getTagDtos() != null) {
            List<Tag> tags = createPostTag(postCreateVO.getTagDtos());
            Post postWithTag = Post.toPostWithTag(postCreateVO, tags, member, imagePathList);
            return postRepository.save(postWithTag).getId();
        }else {
        //Tag가 없는 경우
            Post post = Post.toPost(postCreateVO, member, imagePathList);
            return postRepository.save(post).getId();
        }

    }

    private List<Tag> createPostTag(List<TagDto> tagDtos) {
        return tagDtos.stream()
                .map(dto -> {
                    return  tagService.createTag(dto);
                })
                .collect(Collectors.toList());
    }

    /**
     *  무한 스크롤 페이지네이션
     *  postId에 해당하는 사진들을 picture 테이블에 저장한 image path로 불러온다.
     */
    public void getPostList() {


    }

    public Object updatePost() {
        return null;
    }

    public Member init() {
       return  new Member(new Random().nextLong(), "abc@gmail.com", "1234", "잉스기", "https://unsplash.com/photos/yMSecCHsIBc",
                "남", "개발자", "경기도 성남시 판교로", 25L, 180L, 75L, "3분할", "user");
    }


}
