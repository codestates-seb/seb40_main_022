package com.backend.fitchallenge.domain.tag.dto;


import com.backend.fitchallenge.domain.tag.domain.Tag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagDto {


    private String name;


    public TagDto(String name) {
        this.name = name;
    }

    public  Tag toTag() {
        return Tag.of(name);
    }
}
