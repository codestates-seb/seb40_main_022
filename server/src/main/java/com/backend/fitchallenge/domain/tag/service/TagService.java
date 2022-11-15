package com.backend.fitchallenge.domain.tag.service;

import com.backend.fitchallenge.domain.tag.dto.TagDto;
import com.backend.fitchallenge.domain.tag.domain.Tag;
import com.backend.fitchallenge.domain.tag.exception.TagNotFound;
import com.backend.fitchallenge.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag createTag(TagDto tagDto) {
        Tag tag = tagDto.toTag();

        if (!isTagExist(tag)) {
            tagRepository.save(tag);
        }
        return tagRepository.findByContent(tag.getContent()).orElseThrow(TagNotFound::new);
    }

    private boolean isTagExist(Tag tag) {
        return tagRepository.existsByContent(tag.getContent());
    }
}
