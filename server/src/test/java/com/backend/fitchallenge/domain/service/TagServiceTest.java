package com.backend.fitchallenge.domain.service;

import com.backend.fitchallenge.domain.tag.domain.Tag;
import com.backend.fitchallenge.domain.tag.dto.TagDto;
import com.backend.fitchallenge.domain.tag.repository.TagRepository;
import com.backend.fitchallenge.domain.tag.service.TagService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService;

    @Test
    void createTag() {
        //given
        TagDto tagDto = new TagDto("오운완");
        Tag tag = tagDto.toTag();

        given(tagRepository.save(Mockito.any(Tag.class)))
                .willReturn(tag);
        given(tagRepository.existsByContent(Mockito.anyString()))
                .willReturn(false);

        given(tagRepository.findByContent(Mockito.anyString()))
                .willReturn(Optional.of(tag));
        //when
        Tag result = tagService.createTag(tagDto);
        //then
        assertThat(result.getContent()).isEqualTo(tag.getContent());

    }
}