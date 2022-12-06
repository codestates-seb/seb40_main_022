package com.backend.fitchallenge.domain.service;

import com.amazonaws.services.s3.AmazonS3;
import com.backend.fitchallenge.domain.config.AwsS3MockConfig;
import com.backend.fitchallenge.domain.post.service.AwsS3Service;
import io.findify.s3mock.S3Mock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(AwsS3MockConfig.class)
class AwsS3ServiceTest {


    @Autowired
    private S3Mock s3Mock;

    @Autowired
    private AwsS3Service awsS3Service;

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @AfterEach
    public void tearDown() {
        s3Mock.stop();
    }

    @Test
    void storeFile() {
        //given
        String path = "test.png";
        String contentType = "image/png";
        String dirName = "test";

        MockMultipartFile file = new MockMultipartFile("test", path, contentType, "test".getBytes());

        //when
        List<String> paths = awsS3Service.StoreFile(List.of(file));

        //then
        assertThat(paths.get(0)).contains("cloudfront");
        assertThat(paths.get(0)).contains(".png");
        assertThat(paths.get(0)).isNotEqualTo(path);

    }

    @Test
    void deleteFile() {
        //given
        String path = "test.png";
        String contentType = "image/png";
        String dirName = "test";

        MockMultipartFile file = new MockMultipartFile("test", path, contentType, "test".getBytes());
        List<String> paths = awsS3Service.StoreFile(List.of(file));

        int index = paths.get(0).lastIndexOf("/");
        String imagePath = paths.get(0).substring(index + 1);
        //when
        boolean isExist = amazonS3.doesObjectExist(bucketName, imagePath);
        amazonS3.deleteObject(bucketName,imagePath);
        boolean afterDelete = amazonS3.doesObjectExist(bucketName, imagePath);
        //then
        assertThat(isExist).isTrue();
        assertThat(afterDelete).isFalse();
    }
}