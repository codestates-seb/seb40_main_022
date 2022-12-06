package com.backend.fitchallenge.service;

import com.backend.fitchallenge.builder.MemberRelatedBuilder;
import com.backend.fitchallenge.domain.member.dto.request.MemberCreate;
import com.backend.fitchallenge.domain.member.dto.request.MemberUpdateVO;
import com.backend.fitchallenge.domain.member.dto.response.UpdateResponse;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.entity.ProfileImage;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.member.service.MemberAwsS3Service;
import com.backend.fitchallenge.domain.member.service.MemberService;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import com.backend.fitchallenge.util.TestMemberUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static com.backend.fitchallenge.builder.MemberRelatedBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


public class MemberServiceTest extends TestMemberUtil {

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MemberAwsS3Service memberAwsS3Service;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("로그인 유저의 정보 얻어오기 - 이메일로")
    void isMemberExist(){
        //given
        MemberDetails memberDetails = dummyMemberDetails(1L, EMAIL, PASSWORD, AUTHORITY);

        //when, then
        when(memberRepository.findByEmail(memberDetails.getEmail()))
                .thenReturn(Optional.of(dummyMember(1L, EMAIL, PASSWORD, USERNAME)))
                .thenThrow(new MemberNotExist())
                .thenReturn(Optional.empty());

    }

    @Test
    @DisplayName("로그인 유저의 정보 얻어오기 - ID값으로")
    void findMember(){
        //given
        MemberDetails memberDetails = dummyMemberDetails(1L, EMAIL, PASSWORD, AUTHORITY);

        //when, then
        when(memberRepository.findById(memberDetails.getId()))
                .thenReturn(Optional.of(dummyMember(1L, EMAIL, PASSWORD, USERNAME)))
                .thenThrow(new MemberNotExist())
                .thenReturn(Optional.empty());

    }

    @Test
    @DisplayName("멤버 정보 업데이트하기 - DTO -> ENTITY")
    void deleteMember(){
        //given
        MemberUpdateVO memberUpdateVO = dummyMemberUpdateVO(PASSWORD,USERNAME,JOB,ADDRESS,GENDER,AGE,HEIGHT,
        WEIGHT,KILOGRAM,SPLIT,FILE,PERIOD);

        Member member = dummyMember(1L,EMAIL, PASSWORD, USERNAME, PROFILE_IMAGE);

        Member updatedMember = dummyMember(1L,EMAIL,PASSWORD,USERNAME,JOB,ADDRESS,
                GENDER,AGE,HEIGHT,WEIGHT,SPLIT,PERIOD,PROFILE_IMAGE,AUTHORITY);

        //when
        member.update(memberUpdateVO, passwordEncoder);

        //then
        assertThat(updatedMember, samePropertyValuesAs(member));
    }
//
//    @Test
//    @DisplayName("memberCreate 를 멤버 객체로 변환하기")
//    void memberCreateToMember(){
//        //given
//        Member member = Member.createBuilder()
//                .email(testEmail)
//                .password(testPassword)
//                .username(testUsername)
//                .build();
//
//        MemberCreate memberCreate = MemberCreate.builder()
//                .email(testEmail)
//                .password(testPassword)
//                .username(testUsername)
//                .build();
//
//        //when
//        Member newMember = memberCreate.toEntity();
//        //then
//        assertThat(newMember, samePropertyValuesAs(member));
//    }
//
//    @Test
//    @DisplayName("프로필 이미지 객체 생성하기")
//    void createProfileImage(){
//        //given
//        String path = testImagePath;
//        ProfileImage profileImage = ProfileImage.builder().path(path).build();
//        //when
//        ProfileImage createdProfileImage = ProfileImage.createWithPath(path);
//        //then
//        assertThat(createdProfileImage, samePropertyValuesAs(profileImage));
//    }
//
//    @Test
//    @DisplayName("memberUpdate를 바탕으로 Member 업데이트하기")
//    void memberUpdateToMember(){
//        //given
//        Member member = testMember;
//        MemberUpdateVO memberUpdateVO = testMemberUpdateVO;
//
//        //when
//        member.update(memberUpdateVO, passwordEncoder);
//
//        //then
//        assertThat(member, samePropertyValuesAs(updatedTestMember));
//    }

}
