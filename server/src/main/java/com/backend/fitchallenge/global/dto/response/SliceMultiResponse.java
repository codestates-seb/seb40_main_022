package com.backend.fitchallenge.global.dto.response;

import com.backend.fitchallenge.domain.member.dto.response.extract.DailyPost;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;
@Getter
public class SliceMultiResponse<T> {
    private List<T> items;
    private boolean lastPage;


    private SliceMultiResponse(Slice<T> items) {
        this.items = items.getContent();
        this.lastPage = items.isLast();
    }

    public static SliceMultiResponse<?> of(Slice<?> items){
        return new SliceMultiResponse<>(items);

    }
    public static SliceMultiResponse<DailyPost> createSliceDailyPost(Slice<DailyPost> dailyPosts){
        return new SliceMultiResponse<>(dailyPosts);
    }

}

