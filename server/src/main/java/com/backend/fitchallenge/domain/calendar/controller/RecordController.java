package com.backend.fitchallenge.domain.calendar.controller;

import com.backend.fitchallenge.domain.calendar.dto.request.RecordCreate;
import com.backend.fitchallenge.domain.calendar.dto.request.RecordUpdate;
import com.backend.fitchallenge.domain.calendar.dto.request.TimePictureUpdateVO;
import com.backend.fitchallenge.domain.calendar.dto.request.TimePictureVO;
import com.backend.fitchallenge.domain.calendar.dto.response.TimePictureResponse;
import com.backend.fitchallenge.domain.calendar.service.RecordService;
import com.backend.fitchallenge.domain.calendar.service.TimePictureService;
import com.backend.fitchallenge.domain.calendar.util.Month;
import com.backend.fitchallenge.domain.post.service.AwsS3Service;
import com.backend.fitchallenge.global.annotation.AuthMember;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import com.drew.imaging.ImageProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;
    private final TimePictureService pictureService;
    private final AwsS3Service awsS3Service;

    //운동 기록 생성
    @PostMapping("/records")
    public ResponseEntity<Long> create(@AuthMember MemberDetails memberDetails,
                                       @Valid @RequestBody RecordCreate recordCreate) {

        return ResponseEntity.ok(recordService.createRecord(memberDetails.getMemberId(), recordCreate));
    }

    @GetMapping("/records/{record-id}")
    public ResponseEntity<?> details(@AuthMember MemberDetails memberDetails,
                                     @PathVariable("record-id") Long recordId) {

        return ResponseEntity.ok(recordService.getDailyRecord(memberDetails.getMemberId(), recordId));
    }

    //특정 달의 운동기록 조회
    @GetMapping("/records")
    public ResponseEntity<?> list(@AuthMember MemberDetails memberDetails,
                                  @RequestParam @Month int month) {

        return ResponseEntity.ok(recordService.getMonthlyRecordList(memberDetails.getMemberId(), month));
    }

    //운동 기록 수정
    @PatchMapping("/records/{record-id}")
    public ResponseEntity<Long> update(@AuthMember MemberDetails memberDetails,
                                       @PathVariable("record-id") Long recordId,
                                       @Valid @RequestBody RecordUpdate recordUpdate) {

        return ResponseEntity.ok(recordService.updateRecord(memberDetails.getMemberId(), recordId, recordUpdate));
    }

    //운동 기록 삭제
    @DeleteMapping("/records/{record-id}")
    public ResponseEntity<Long> delete(@AuthMember MemberDetails memberDetails,
                                       @PathVariable("record-id") Long recordID) {

        return ResponseEntity.ok(recordService.deleteRecord(memberDetails.getMemberId(), recordID));
    }

    /**
     * [시간 메타데이터 추출하는 부분]
     * - 두 메서드 모두 날짜를 검증하고 시간 정보를 추출합니다.
     * - createPicture()는 s3에 이미지를 저장하는 반면,
     *   updatePicture()는 s3에서 기존 이미지를 제거하는 과정이 추가됩니다.
     */
    //todo : s3에 사진을 넣지 않고도 프론트엔드 단에서 사진을 띄울 수 있을까?
    //     -> 가능하다면 저장할 필요 없이 시간 값만 보내주면 됨
    @PostMapping("/records/pictures")
    public ResponseEntity<?> createPicture(@AuthMember MemberDetails memberDetails,
                                           TimePictureVO timePictureVO) throws IOException, ImageProcessingException {

        //사진의 날짜가 입력받은 값이 맞는지 검증하고 시간 정보를 추출합니다.
        LocalTime time = pictureService.getTimeInfo(timePictureVO);

        //s3에 사진을 저장하고 경로를 얻습니다.
        List<String> imagePathList = awsS3Service.StoreFile(List.of(timePictureVO.getFile()));

        //사진의 경로와 시간 정보를 반환합니다.
        return ResponseEntity.ok(TimePictureResponse.of(imagePathList.get(0), timePictureVO.getPoint(), time));
    }

    @PatchMapping("/records/pictures")
    public ResponseEntity<?> updatePicture(TimePictureUpdateVO timePictureUpdateVO) throws IOException, ImageProcessingException {

        //새로운 사진의 시간 정보를 추출합니다.
        LocalTime time = pictureService.getTimeInfo(timePictureUpdateVO);

        //s3에서 기존 사진을 지우고 새로운 사진을 저장한 후, 그 경로를 얻습니다.
        List<String> imagePathList = awsS3Service.UpdateFile(
                List.of(timePictureUpdateVO.getFilePath()),
                List.of(timePictureUpdateVO.getFile())
        );

        //사진의 경로와 시간 정보를 반환합니다.
        return ResponseEntity.ok(TimePictureResponse.of(imagePathList.get(0), timePictureUpdateVO.getPoint(), time));
    }
}
