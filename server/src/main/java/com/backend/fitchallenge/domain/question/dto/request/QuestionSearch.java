package com.backend.fitchallenge.domain.question.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class QuestionSearch {

    private String query;
    private List<String> tag;

    private QuestionSearch(String query, List<String> tag) {
        this.query = query;
        this.tag = tag;
    }

    static public QuestionSearch of(String query, List<String> tag){
        return new QuestionSearch(query, tag);
    }
}
