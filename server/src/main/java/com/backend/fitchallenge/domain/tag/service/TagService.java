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
        // Tag가 DB에 등록되 있지 않다면 저장
        if (!isTagExist(tag)) {
            tagRepository.save(tag);
        }
        // DB에 이미 있을경우 DB에서 해당내용의 Tag 불러온다.
        return tagRepository.findByContent(tag.getContent()).orElseThrow(TagNotFound::new);
    }
    //등록한 태그가 DB에 이미 등록되있는지 체크
    private boolean isTagExist(Tag tag) {
        return tagRepository.existsByContent(tag.getContent());
    }
}
