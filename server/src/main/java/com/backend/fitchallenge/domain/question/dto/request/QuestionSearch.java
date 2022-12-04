package com.backend.fitchallenge.domain.question.dto.request;

import com.backend.fitchallenge.domain.question.entity.Question;
import lombok.Getter;

import java.util.List;

@Getter
public class QuestionSearch {

    private String query;
    private String tag;

    private QuestionSearch(String query, String tag) {
        this.query = query;
        this.tag = tag;
    }

    static public QuestionSearch of(String query, String tag){
        return new QuestionSearch(query, tag);
    }
}
