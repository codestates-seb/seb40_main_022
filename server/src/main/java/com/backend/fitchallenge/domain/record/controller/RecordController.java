package com.backend.fitchallenge.domain.record.controller;

import com.backend.fitchallenge.domain.record.dto.request.RecordCreateVO;
import com.backend.fitchallenge.domain.record.dto.request.RecordUpdateVO;
import com.backend.fitchallenge.domain.record.dto.request.TimePictureUpdateVO;
import com.backend.fitchallenge.domain.record.dto.request.TimePictureVO;
import com.backend.fitchallenge.domain.record.dto.response.TimePictureResponse;
import com.backend.fitchallenge.domain.record.service.RecordService;
import com.backend.fitchallenge.domain.record.util.Month;
import com.backend.fitchallenge.domain.post.service.AwsS3Service;
import com.backend.fitchallenge.global.annotation.AuthMember;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;
    private final AwsS3Service awsS3Service;

    @PostMapping("/records")
    public ResponseEntity<Long> create(@AuthMember MemberDetails memberDetails,
                                       RecordCreateVO recordCreateVO) {

        return ResponseEntity.ok(recordService.createRecord(memberDetails.getMemberId(), recordCreateVO));
    }

    @GetMapping("/records/{record-id}")
    public ResponseEntity<?> details(@AuthMember MemberDetails memberDetails,
                                     @PathVariable("record-id") Long recordId) {

        return ResponseEntity.ok(recordService.getDailyRecord(memberDetails.getMemberId(), recordId));
    }

    @GetMapping("/records")
    public ResponseEntity<?> list(@AuthMember MemberDetails memberDetails,
                                  @RequestParam @Month int month) {

        return ResponseEntity.ok(recordService.getMonthlyRecordList(memberDetails.getMemberId(), month));
    }

    @PostMapping("/records/{record-id}")
    public ResponseEntity<Long> update(@AuthMember MemberDetails memberDetails,
                                       @PathVariable("record-id") Long recordId,
                                       RecordUpdateVO recordUpdateVO) {

        return ResponseEntity.ok(recordService.updateRecord(memberDetails.getMemberId(), recordId, recordUpdateVO));
    }

    @DeleteMapping("/records/{record-id}")
    public ResponseEntity<Long> delete(@AuthMember MemberDetails memberDetails,
                                       @PathVariable("record-id") Long recordID) {

        return ResponseEntity.ok(recordService.deleteRecord(memberDetails.getMemberId(), recordID));
    }


   @PostMapping("/records/pictures")
    public ResponseEntity<?> createPicture(@AuthMember MemberDetails memberDetails,
                                           TimePictureVO timePictureVO) throws IOException {

        List<String> imagePathList = awsS3Service.StoreFile(List.of(timePictureVO.getFile()));

        return ResponseEntity.ok(TimePictureResponse.of(imagePathList.get(0), timePictureVO.getPoint()));
    }

    @PatchMapping("/records/pictures")
    public ResponseEntity<?> updatePicture(@AuthMember MemberDetails memberDetails,
                                            TimePictureUpdateVO timePictureUpdateVO) throws IOException {

        List<String> imagePathList = awsS3Service.UpdateFile(
                List.of(timePictureUpdateVO.getFilePath()),
                List.of(timePictureUpdateVO.getFile())
        );

        return ResponseEntity.ok(TimePictureResponse.of(imagePathList.get(0), timePictureUpdateVO.getPoint()));
    }
}
