package com.backend.fitchallenge.global.security.oauth2;

/**
 * 배경지식) Oauth 서버에서 유저 정보를 담아오면 OAuth2User에 담겨온다.
 *          이로부터 해야할 일 -> 필요한 정보만 빼온다.
 * 문제는 회사마다 제공하는 정보와 방법이 다르다는 것.
 * 따라서 공통적으로 빼오기 위한 메서드들만 정의한 인터페이스를 만들었다.
 *
 * 현재는 getEmail, getName만 사용하는 중.
 * todo. 추후 profileImage 추가 가능성, provider 사용 가능성(이를 member엔티티에 추가하면, 로그인 방식 간의 email 중복가입이 허용될 것)
 */
public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
}
