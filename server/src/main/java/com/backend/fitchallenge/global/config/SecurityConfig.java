package com.backend.fitchallenge.global.config;

import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.refreshtoken.RefreshTokenRepository;
import com.backend.fitchallenge.global.redis.RedisService;
import com.backend.fitchallenge.global.security.filter.JwtAuthenticationFilter;
import com.backend.fitchallenge.global.security.filter.JwtVerificationFilter;
import com.backend.fitchallenge.global.security.handler.Oauth2SuccessHandler;
import com.backend.fitchallenge.global.security.jwt.JwtTokenProvider;
import com.backend.fitchallenge.global.security.userdetails.MemberDetailsService;
import com.backend.fitchallenge.global.security.userdetails.OAuth2DetailsService;
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
@EnableWebSecurity(debug = false)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberAuthorityUtils authorityUtils;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisService redisService;
    private final MemberDetailsService memberDetailsService;
    private final OAuth2DetailsService oAuth2DetailsService;


    @Bean
    @SneakyThrows // <- 해당하는 에러를 찾아서 알아서 던져준다.
    public SecurityFilterChain filterChain(HttpSecurity http){
        http
                /**
                 * h2 사용을 위함. h2가 태그기반이기에.
                 */
                .headers().frameOptions().sameOrigin()
                .and()

                /**
                 * 토큰 방식이기 때문에 session - stateless
                 */
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                //todo. API연결시 쿠키 사용한다면 수정해야 할 부분.
                .cors(withDefaults())
                .csrf().disable()

                /**
                 * 커스텀 필터 적용 : JwtAuthenticationFilter, JwtVerificationFilter
                 */
                .apply(new CustomFilterConfigurer())
                .and()

                /**
                 * uri 별 권한관리의 시작점.  todo. 추후 uri마다의 권한구조 설정하기
                 */
                .authorizeHttpRequests()
                .antMatchers("/**").permitAll()
                .and()

                /**
                1. OAuth서버에서 유저정보를 가지고 왔을때의 지점 -> userInfoEndPoint
                2. 그 이후에 유저정보로 MemberDetails를 생성, authentication을 생성하는 로직을 커스텀하였음(oAuth2DetailsService)
                3. atk, rtk를 발행하는 로직 실행 (Oauth2SuccessHandler)
                 */
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint().userService(oAuth2DetailsService)
                        .and()
                        .successHandler(new Oauth2SuccessHandler(jwtTokenProvider, authorityUtils,
                                refreshTokenRepository, redisService)));


        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    // todo. 추후 사용 가능성 높음. 위 filterchain의 cors설정의 파라미터로 사용할 가능성.
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();  // Cors설정 객체생성(정책 설정)

        //여기에 추가설정
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH","DELETE"));
        configuration.setAllowedOriginPatterns(Arrays.asList(""));
        configuration.setAllowedHeaders(Arrays.asList(""));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH","DELETE"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 해당 인터페이스 구현 객체에 넣어준다.

        return source;
    }

    /**
     * filter단에 만든 customFilter들의 흐름 설정  todo. 인증 성공, 실패에 따른 핸들러 작성시 여기서 추가하도록 하자.
     */
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {

            AuthenticationManager authenticationManager =
                    builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter =
                    new JwtAuthenticationFilter(authenticationManager, jwtTokenProvider, redisService);

            JwtVerificationFilter jwtVerificationFilter =
                    new JwtVerificationFilter(jwtTokenProvider, authorityUtils, redisService, memberDetailsService);

            jwtAuthenticationFilter.setFilterProcessesUrl("/members/login");

            builder
                    .addFilterAfter(jwtAuthenticationFilter, OAuth2LoginAuthenticationFilter.class)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
