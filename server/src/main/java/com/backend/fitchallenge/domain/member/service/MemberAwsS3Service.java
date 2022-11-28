package com.backend.fitchallenge.domain.member.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.backend.fitchallenge.domain.member.exception.NoProfileImage;
import com.backend.fitchallenge.domain.post.exception.UploadFailed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberAwsS3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.cloudFront.distributionDomain}")
    private String cloudFront;

    public String storeFile(MultipartFile file) {

        isFileExist(file);

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
            throw new UploadFailed();
        }
        imagePath = cloudFront + "/" + storeName; // CloudFront 도메인명 + 저장한 파일명

        return imagePath;
    }

    public String updateFile(MultipartFile file, String imagePath) {

        isFileExist(file);

        deleteFile(imagePath);

        return storeFile(file);
    }

    public void deleteFile(String imagePath) {

        if(imagePath !="https://pre-project-bucket-seb40-017.s3.ap-northeast-2.amazonaws.com/00398f65-51c3-4c1d-baac-38070910c5b3.png"){
            boolean isExistObject = amazonS3.doesObjectExist(bucketName, imagePath);

            if (isExistObject == true) {
                amazonS3.deleteObject(bucketName, imagePath);
            }
        }
    }

    private void isFileExist(MultipartFile file) {
        if (file == null) {
            throw new NoProfileImage();
        }
    }

    private String createStoreFileName(String originalName) {
        String ext = extractExt(originalName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private static String extractExt(String originalName) {
        int pos = originalName.lastIndexOf(".");
        return originalName.substring(pos + 1);

    }
}
