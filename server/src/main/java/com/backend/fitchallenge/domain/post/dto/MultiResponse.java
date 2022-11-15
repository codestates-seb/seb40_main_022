package com.backend.fitchallenge.domain.post.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;
@Getter
public class MultiResponse<T> {
    private List<T> items;


    private MultiResponse(Slice<T> items) {
        this.items = items.getContent();
    }

    public static MultiResponse<?> of(Slice<?> items){
        return new MultiResponse<>(items);

    }


    }


