package com.backend.fitchallenge.domain.member.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.backend.fitchallenge.domain.member.exception.NoProfileImage;
import com.backend.fitchallenge.domain.post.exception.NoImage;
import com.backend.fitchallenge.domain.post.exception.UploadFailed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberAwsS3Service {

    private final AmazonS3 amazonS3;

    //S3 버킷
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    //CloudFront
    @Value("${cloud.aws.cloudFront.distributionDomain}")
    private String cloudFront;

    public String storeFile(MultipartFile file) {

        //파일 유무 체크
        validateFileExists(file);

        //반환할 이미지 저장경로 리스트
        String imagePath;

        String originalName = file.getOriginalFilename();
        String storeName = createStoreFileName(originalName);
        long size = file.getSize();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(size);

        try (InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucketName, storeName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new UploadFailed(); // 업로드 실패 예외
        }
            // CloudFront 도메인명 + 저장한 파일명
        imagePath = cloudFront + "/" + storeName;

        return imagePath;
    }

    public String updateFile(MultipartFile file, String imagePath) {

        validateFileExists(file);

        deleteFile(imagePath);

        return storeFile(file);
    }


    // S3에서 이미지 파일 삭제
    public void deleteFile(String imagePath) {

        boolean isExistObject = amazonS3.doesObjectExist(bucketName, imagePath);

        if (isExistObject == true) {
            amazonS3.deleteObject(bucketName, imagePath);
        }
    }

    // 파일 유무 체크
    private void validateFileExists(MultipartFile file) {
        if (file == null) {
            throw new NoProfileImage();
        }
    }

    /**
     * 저장되는 파일이름 중복이 되지 않게 하기 위해서
     * UUID로 생성한 랜덤값 + 파일확장명으로 업로드
     */
    private String createStoreFileName(String originalName) {
        String ext = extractExt(originalName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    //파일 확장명 추출
    private static String extractExt(String originalName) {
        int pos = originalName.lastIndexOf(".");
        return originalName.substring(pos + 1);

    }
}
