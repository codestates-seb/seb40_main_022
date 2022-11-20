package com.backend.fitchallenge.global.security.oauth2;

import java.util.Map;

/**
 * google은 spring 공식 oauth2 제공기업이라서 카카오보다 간단하다.
 */
public class GoogleUserInfo implements OAuth2UserInfo{
    private Map<String, Object> attributes; // getAttributes

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }
}