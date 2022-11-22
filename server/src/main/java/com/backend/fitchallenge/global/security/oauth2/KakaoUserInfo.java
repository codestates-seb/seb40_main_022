package com.backend.fitchallenge.global.security.oauth2;

import java.util.Map;

/**
 *  정보는 attributes에 담겨오고, 해당 필드들은 카카오 oauth가 제공하는 정보들
 */
public class KakaoUserInfo implements OAuth2UserInfo{
    private Map<String, Object> attributes; // getAttributes
    private Map<String, Object> attributesProperties; // getAttributes
    private Map<String, Object> attributesAccount; // getAttributes
    private Map<String, Object> attributesProfile; // getAttributes

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.attributesProperties = (Map<String, Object>) attributes.get("properties");
        this.attributesAccount = (Map<String, Object>) attributes.get("kakao_account");
        this.attributesProfile = (Map<String, Object>) attributesAccount.get("profile");
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return (String) attributesAccount.get("email");
    }

    @Override
    public String getName() {
        return (String) attributesProperties.get("nickname");
    }
}