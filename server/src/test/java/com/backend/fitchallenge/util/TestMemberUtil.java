package com.backend.fitchallenge.util;

import com.backend.fitchallenge.domain.member.entity.Authority;
import com.backend.fitchallenge.domain.member.entity.ProfileImage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TestMemberUtil {

    //member
    protected static final String EMAIL = "test@gmail.com";
    protected static final String PASSWORD = "1234";
    protected static final String USERNAME = "test";
    protected static final String GENDER = "male";
    protected static final String JOB = "student";
    protected static final String ADDRESS = "seoul";
    protected static final Integer AGE = 19;
    protected static final Integer HEIGHT = 185;
    protected static final Integer WEIGHT = 78;
    protected static final Integer SPLIT = 4;
    protected static final Integer PERIOD = 20;
    protected static final Authority AUTHORITY = Authority.ROLE_USER;

    //profileImage
    protected static final String IMAGE_PATH = "testImage.jpg";
    protected static final ProfileImage PROFILE_IMAGE = ProfileImage.createWithPath(IMAGE_PATH);
    protected static final MockMultipartFile FILE  = new MockMultipartFile(
                                "attachments","Image.png",
                                        MediaType.MULTIPART_FORM_DATA_VALUE,
                                        "image".getBytes());
    //memberActivity
    protected static final Integer KILOGRAM = 300;
    protected static final Integer DAY_COUNT = 30;
    protected static final Double POINT = 100.0D;

    //Token
    protected static final String ACCESS_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6IlJPTEVfVVNFUiIsInVzZXJuYW1lIjoidGVzdEBnbWFpbC5jb20iLCJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY2OTI3NzIwMiwiZXhwIjoxNjY5Mjc4MTAyfQ.a06NGzKD_9bp-XcyvhBREldrz9NTEEqKpXsdVq5edkMCYuHW0CWWTk6y0VVO5ZnVyVcUMRUldtUp1uUWBiM4Pw";
    protected static final String REFRESH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY2OTI3NzIwMiwiZXhwIjoxNjY5Mjc5MDAyfQ.VQed7kB3ZJbGMInmerRwq29iAmLUl6Rt8DC_ziQaWUHAR7upjhX4KXZPkhqS20aYznFr7mx63-3GmLuR6Sm68g";
    protected static final String NEW_ACCESS_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6IlJPTEVfVVNFUiIsInVzZXJuYW1lIjoidGVzdEBnbWFpbC5jb20iLCJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY2OTYxMDgyNCwiZXhwIjoxNjY5NjExNzI0fQ.OWrysPTXdb85s47JQZMhRb_gFi9B8STiVIdqxzZZ5azeMnFSpuluGY219LscK1_5l7eNalEk_Rxn72ZuR7Wsww";
    protected static final String NEW_REFRESH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY2OTYxMDgyNCwiZXhwIjoxNjY5NjEyNjI0fQ.igQ-66oXlOPLminVnSZGqOEpooruWIevfnonXtb8TMaiH0Q4HwtaUMQWZmtPGeKUOlP-dEg9jVkUcoidmyfJlA";

}
