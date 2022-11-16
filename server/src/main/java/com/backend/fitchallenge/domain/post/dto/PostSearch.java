package com.backend.fitchallenge.domain.post.dto;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Slf4j
public class PostSearch {

    private String tag;

    public PostSearch(String tag) {this.tag = tag;}


    public List<String> queryParsing(String tag) {

        log.info("queryParsing tag ={}", tag);

        //검색어에서 '#~~' 형식의 pattern 하나씩분리
        Pattern p = Pattern.compile("\\#([ㄱ-ㅎ가-힣a-zA-Z\\d]+)");

        Matcher m = p.matcher(tag); //문자열 설정

        List<String> tagNames = new ArrayList<>();

        while (m.find()) {
            tagNames.add(m.group().substring(1));
        }

        return tagNames;

    }

}
