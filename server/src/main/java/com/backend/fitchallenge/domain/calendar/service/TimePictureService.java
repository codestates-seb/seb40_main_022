/*
package com.backend.fitchallenge.domain.calendar.service;

import com.backend.fitchallenge.domain.calendar.dto.request.TimePictureVO;
import com.backend.fitchallenge.domain.calendar.exception.NotImageFile;
import com.backend.fitchallenge.domain.calendar.exception.PictureDateMismatch;
import com.backend.fitchallenge.domain.post.service.AwsS3Service;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Directory;
import org.hibernate.boot.Metadata;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class TimePictureService {

    private final AwsS3Service awsS3Service;


    public LocalTime getTimeInfo(TimePictureVO timePictureVO) throws IOException {

        //MultipartFile을 readAttributes의 매개변수 타입인 File로 변환합니다.
        MultipartFile mFile = timePictureVO.getFile();

//        File file = new File(Objects.requireNonNull(mFile.getOriginalFilename()));
//        file.createNewFile();
//        FileOutputStream fos = new FileOutputStream(file);
//        fos.write(mFile.getBytes());
//        fos.close();
//        log.info("file: {}", file);

    */
/*    //오픈소스 api를 사용한 코드입니다. 추후 활용할 수 있을 것 같아 기록해두었습니다.
        Metadata metadata = ImageMetadataReader.readMetadata(mFile.getInputStream());

        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.format("[%s] - %s = %s",
                        directory.getName(), tag.getTagName(), tag.getDescription());
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }

        ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
//*//*

//        Date date = directory.getDateOriginal(TimeZone.getTimeZone("Asia/Seoul"));
//        verifyIfFileIsImage(file);
//
//        LocalDateTime localDateTime = date.toInstant()
//                .atZone(ZoneId.of("Asia/Tokyo"))
//                .toLocalDateTime();
//
//
//
//        ZonedDateTime time = null;
//        try {
//            time = Files.readAttributes(file.toPath(), BasicFileAttributes.class)
//                    .creationTime().toInstant().atZone(ZoneId.of("Asia/Tokyo"));
//        } catch(IOException e) {
//            e.printStackTrace();
//            throw e;
//        }
//       LocalDateTime localDateTime = time.toLocalDateTime();
//
//        log.info("localDateTime: {}", localDateTime);
//
//        //LocalDateTime에서 추출하고자 하는 날짜 정보의 형식을 지정합니다.
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        //사진의 날짜가 운동기록을 등록하려는 날짜와 같은지 검증합니다.
//        verifyDateOfPicture(localDateTime.toLocalDate(), LocalDate.parse(timePictureVO.getDate(), formatter));
//
//        //localDateTime에서 시간 정보만을 추출한 이후 반환합니다.
//        return localDateTime.toLocalTime().truncatedTo(ChronoUnit.SECONDS);
        return null;
    }

    public static void main(String[] args) throws ImageProcessingException, IOException {
        File file = new File("IMG_2395.jpg");
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.format("[%s] - %s = %s",
                        directory.getName(), tag.getTagName(), tag.getDescription());
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }
    }

    //파일이 이미지 형식인지 확인합니다.
    private void verifyIfFileIsImage(File file) throws IOException {
        //파일 형식을 확인합니다.
        //  예를 들어, png파일의 경우 mimeType이 "image/png"로 시작할 것입니다.
        String mimeType = Files.probeContentType(file.toPath());
        log.info("File type: {}", mimeType);

        if (!mimeType.startsWith("image/")) {
            throw new NotImageFile();
        }
    }

    private void verifyDateOfPicture(LocalDate pictureDate, LocalDate recordDate) {
        if (!pictureDate.equals(recordDate)) {
            throw new PictureDateMismatch();
        }
    }
}
*/
