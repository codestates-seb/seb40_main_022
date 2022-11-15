package com.backend.fitchallenge.global.config;

import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.member.service.MemberService;
import com.backend.fitchallenge.domain.refreshtoken.RefreshTokenRepository;
import com.backend.fitchallenge.global.security.filter.JwtAuthenticationFilter;
import com.backend.fitchallenge.global.security.filter.JwtVerificationFilter;
import com.backend.fitchallenge.global.security.handler.Oauth2SuccessHandler;
import com.backend.fitchallenge.global.security.jwt.JwtTokenProvider;
import com.backend.fitchallenge.global.security.utils.MemberAuthorityUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberAuthorityUtils authorityUtils;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity http){
        http
                //h2 사용하려면 - h2가 태그기반
                .headers().frameOptions().sameOrigin()
                .and()

                //일반적인 로그인, 폼 로그인, 세션 사용 안함
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                //추후 설정 가능한 부분
                .cors(withDefaults())
                .csrf().disable()

                //커스텀 필터 적용
                .apply(new CustomFilterConfigurer())
                .and()

                //추후 권한설정
                .authorizeHttpRequests()
                .antMatchers("/**").permitAll()
                .and()

                //oAuth2 로그인. 성공시 핸들러로 넘어간다.
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(new Oauth2SuccessHandler(jwtTokenProvider, authorityUtils,
                                memberRepository, refreshTokenRepository)));


        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //추후 설정들이 필요할 시 cors filter에 적용할 예정
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();  // Cors설정 객체생성(정책 설정)

        //여기에 추가설정
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH","DELETE"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 해당 인터페이스 구현 객체에 넣어준다.

        return source;
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager =
                    builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter =
                    new JwtAuthenticationFilter(authenticationManager, jwtTokenProvider);

            JwtVerificationFilter jwtVerificationFilter =
                    new JwtVerificationFilter(jwtTokenProvider, authorityUtils);

            jwtAuthenticationFilter.setFilterProcessesUrl("/members/login");

            //핸들러 작성 전
//            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthSuccessHandler());
//            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthFailureHandler());

            builder
//                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtAuthenticationFilter, OAuth2LoginAuthenticationFilter.class)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
