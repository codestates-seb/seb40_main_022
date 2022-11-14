package com.backend.fitchallenge.domain.post.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.backend.fitchallenge.domain.post.exception.NoImage;
import com.backend.fitchallenge.domain.post.exception.UploadFailed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsS3Service {

    private final AmazonS3 amazonS3;


    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.cloudFront.distributionDomain}")
    private String cloudFront;


    public List<String> StoreFile(List<MultipartFile> files) {

        validateFileExists(files);
        //반환할 이미지 저장경로 리스트
        List<String> imagePathList = new ArrayList<>();

        for (MultipartFile file : files) {
            String originalName = file.getOriginalFilename(); // 파일 이름
            String storeName = createStoreFileName(originalName);
            long size = file.getSize(); // 파일 크기

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(size);

            //S3에 업로드
            try (InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucketName, storeName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                throw new UploadFailed();
            }
            // CloudFront 도메인명 + 저장한 파일명
            String imagePath = cloudFront + "/" + storeName;
            imagePathList.add(imagePath);
        }
        return imagePathList;
    }
    public List<String> UpdateFile(List<String> paths,List<MultipartFile> files) {

        validateFileExists(files);

        for (String path : paths) {
            boolean isExistObject = amazonS3.doesObjectExist(bucketName, path);
            if (isExistObject == true) {
                amazonS3.deleteObject(bucketName,path);
            }
        }


        //반환할 이미지 저장경로 리스트
        List<String> imagePathList = new ArrayList<>();

        for (MultipartFile file : files) {
            String originalName = file.getOriginalFilename(); // 파일 이름
            String storeName = createStoreFileName(originalName);
            long size = file.getSize(); // 파일 크기

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(size);

            //S3에 업로드
            try (InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucketName, storeName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                throw new UploadFailed();
            }
            // CloudFront 도메인명 + 저장한 파일명
            String imagePath = cloudFront + "/" + storeName;
            imagePathList.add(imagePath);
        }
        return imagePathList;
    }


    // 파일 유무 체크
    private void validateFileExists(List<MultipartFile> files) {
        if (files.isEmpty()) {
            throw new NoImage();
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
