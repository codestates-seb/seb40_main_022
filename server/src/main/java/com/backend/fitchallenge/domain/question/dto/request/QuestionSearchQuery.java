package com.backend.fitchallenge.domain.question.dto.request;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionSearchQuery {

    private String q;

    public QuestionSearchQuery(String q) {
        this.q = q;
    }

    public QuestionSearch queryParsing() {

        Pattern p = Pattern.compile("\\#([ㄱ-ㅎ가-핳a-zA-Z\\d]+)");

        // 검색할 문자열 패턴 : 숫자
        Matcher m = p.matcher(q);// 문자열 설정

        List<String> tag = new ArrayList<>();

        while (m.find()) {
            tag.add(m.group().substring(1));
        }

        String noTagOneSpaceString = m.replaceAll("").replaceAll("\\s+", " ");

        return QuestionSearch.of(noTagOneSpaceString, tag);
    }
}
