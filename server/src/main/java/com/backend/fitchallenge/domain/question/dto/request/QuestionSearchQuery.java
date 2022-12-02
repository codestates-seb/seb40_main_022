package com.backend.fitchallenge.domain.question.dto.request;

import com.backend.fitchallenge.domain.question.entity.Question;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionSearchQuery {

    private String q;

    public QuestionSearchQuery(String q) {
        this.q = q;
    }

    public QuestionSearch queryParsing() {

        Pattern p = Pattern.compile("\\#([ㄱ-ㅎ가-힣]*)");

        // 검색할 문자열 패턴 : 숫자
        Matcher m = p.matcher(q);// 문자열 설정

        String tagName = "";

        while (m.find()) {
            tagName = m.group().substring(1);
        }

        String noTagOneSpaceString = m.replaceAll("").replaceAll("\\s+", " ");

        return QuestionSearch.of(noTagOneSpaceString, Question.QuestionTag.valueToConstant(tagName));
    }

    public QuestionSearch elasticQueryParsing() {

        Pattern p = Pattern.compile("\\#([ㄱ-ㅎ가-힣]*)");

        // 검색할 문자열 패턴 : 숫자
        Matcher m = p.matcher(q);// 문자열 설정

        String tagName = "";

        while (m.find()) {
            tagName = m.group().substring(1);
        }

        String noTagOneSpaceString = m.replaceAll("").replaceAll("\\s+", " ");

        return QuestionSearch.of(noTagOneSpaceString, tagName);
    }
}
