package com.backend.fitchallenge.builder;

import com.backend.fitchallenge.domain.member.dto.request.MemberCreate;
import com.backend.fitchallenge.domain.member.dto.request.MemberUpdateVO;
import com.backend.fitchallenge.domain.member.dto.response.DetailsMemberResponse;
import com.backend.fitchallenge.domain.member.dto.response.UpdateResponse;
import com.backend.fitchallenge.domain.member.dto.response.extract.DailyPost;
import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractActivity;
import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractMember;
import com.backend.fitchallenge.domain.member.entity.Authority;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.entity.MemberActivity;
import com.backend.fitchallenge.domain.member.entity.ProfileImage;
import com.backend.fitchallenge.global.dto.request.LoginDto;
import com.backend.fitchallenge.global.dto.response.SliceMultiResponse;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class MemberRelatedBuilder {

    //member
    public static Member dummyMember(String email, String password, String username){
        return Member.createBuilder()
                .email(email)
                .password(password)
                .username(username)
                .build();
    }

    public static Member dummyMember(Long memberId, String email, String password, String username){
        return Member.createBuilder()
                .memberId(memberId)
                .email(email)
                .password(password)
                .username(username)
                .build();
    }

    public static Member dummyMember(Long memberId, String email, String password, String username, ProfileImage profileImage){
        return Member.createBuilder()
                .memberId(memberId)
                .email(email)
                .password(password)
                .username(username)
                .profileImage(profileImage)
                .build();
    }

    public static Member dummyMember(Long memberId, String email, String password, String username,
                                     String job, String address, String gender, Integer age,
                                     Integer height, Integer weight, Integer split, Integer period,
                                     ProfileImage profileImage, Authority authority){

        return Member.dummyBuilder()
                .id(memberId)
                .email(email)
                .password(password)
                .username(username)
                .job(job)
                .address(address)
                .gender(gender)
                .age(age)
                .height(height)
                .weight(weight)
                .split(split)
                .period(period)
                .profileImage(profileImage)
                .authority(authority)
                .build();
    }


    //memberDetails
    public static MemberDetails dummyMemberDetails(Long memberId, String email, String password, Authority authority){
        return MemberDetails.builder()
                .memberId(memberId)
                .email(email)
                .password(password)
                .roles(List.of(authority.toString()))
                .build();
    }

    public static MemberDetails dummyOauth2MemberDetails(Long memberId, String email, String password, Authority authority,
                                                         Map<String, Object> attributes){
        return MemberDetails.builder()
                .memberId(memberId)
                .email(email)
                .password(password)
                .roles(List.of(authority.toString()))
                .attributes(attributes)
                .build();
    }


    //request
    public static LoginDto dummyLoginDto(String email, String password){
        return LoginDto.builder()
                .username(email)
                .password(password)
                .build();
    }

    public static MemberCreate dummyMemberCreate(String email, String password, String username){
        return MemberCreate.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
    }

    public static MemberUpdateVO dummyMemberUpdateVO(String password, String username, String job,
                                                     String address, String gender, Integer age,
                                                     Integer height, Integer weight, Integer kilogram,
                                                     Integer split, MultipartFile profileImage,
                                                     Integer period){
        return MemberUpdateVO.builder()
                .password(password)
                .username(username)
                .job(job)
                .address(address)
                .gender(gender)
                .age(age)
                .height(height)
                .weight(weight)
                .kilogram(kilogram)
                .split(split)
                .profileImage(profileImage)
                .period(period)
                .build();
    }




    //response

    public static ExtractMember dummyExtractMember(String userName, String imagePath, Integer height, Integer weight){
        return ExtractMember.builder()
                .userName(userName)
                .profileImage(imagePath)
                .height(height)
                .weight(weight)
                .build();
    }

    public static ExtractActivity dummyExtractActivity(Integer kilogram, Integer dayCount, Double point){
        return ExtractActivity.builder()
                .kilogram(kilogram)
                .dayCount(dayCount)
                .point(point)
                .build();
    }

    public static DailyPost dummyDailyPost(Long postId, String image){
        return DailyPost.builder()
                .postId(postId)
                .image(image)
                .build();
    }

    public static SliceMultiResponse<DailyPost> dummySliceDailyPost(DailyPost dailyPost){
        Pageable pageable = PageRequest.of(1,3);
        return SliceMultiResponse.createSliceDailyPost(new SliceImpl<>(List.of(dailyPost), pageable, false));
    }

    public static UpdateResponse dummyUpdaterResponse(String password, String username , String job, String address,
                                                      String gender, Integer age, Integer height, Integer weight,
                                                      Integer kilogram, Integer split, String profileImage){
        return UpdateResponse.builder()
                .password(password)
                .username(username)
                .job(job)
                .address(address)
                .gender(gender)
                .address(address)
                .gender(gender)
                .weight(weight)
                .kilogram(kilogram)
                .split(split)
                .profileImage(profileImage)
                .build();
    }
}
