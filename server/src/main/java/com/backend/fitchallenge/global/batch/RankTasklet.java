package com.backend.fitchallenge.global.batch;

import com.backend.fitchallenge.domain.calendar.entity.Record;
import com.backend.fitchallenge.domain.calendar.repository.RecordRepository;
import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.backend.fitchallenge.domain.challenge.repository.ChallengeRepository;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.backend.fitchallenge.domain.calendar.entity.QRecord.record;
import static com.backend.fitchallenge.domain.member.entity.QMember.member;

@RequiredArgsConstructor
@Component
@StepScope
public class RankTasklet implements Tasklet, StepExecutionListener {

    private final ChallengeRepository challengeRepository;
    private final MemberRepository memberRepository;

    List<Challenge> ongoingChallenges;
    List<Record> records;
    Map<Long, Record> recordMap = new HashMap<>();


    @Override
    public void beforeStep(StepExecution stepExecution){
        //진행중인 챌린지들을 가져온다.
        ongoingChallenges = challengeRepository.ongoingChallenges();

        //챌린지가 진행중인 레코드들을 가져온다.
        records = memberRepository.findMemberRecords().stream()
                .map(tuple -> tuple.get(record))
                .collect(Collectors.toList());

        // 레코드들을 id값으로 조회하기 위해 map에 넣어준다.
        records.stream().forEach(recordEntry -> recordMap.put(recordEntry.getMember().getId(), recordEntry));
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext){
        // challenge 아이디값들끼리 비교한다.

        ongoingChallenges.forEach(challenge -> {

            int apPoint = 0;
            int cpPoint = 0;

            //챌린지의 applicantId, counterpartId를 바탕으로 운동기록을 불러온다.
            Record apRecord = recordMap.get(challenge.getApplicantId());
            Record cpRecord = recordMap.get(challenge.getCounterpartId());

            //볼륨을 기준으로 포인트를 계산한다.
            if(apRecord.getVolume() < cpRecord.getVolume()){
                cpPoint++;
            }
            else if(apRecord.getVolume() > cpRecord.getVolume()){
                apPoint++;
            }
            else{
                apPoint++;
                cpPoint++;
            }

            //시간을 기준으로 포인트를 계산한다.
            checkTimeLogic(apRecord.getStartTime(), apRecord.getEndTime());
            checkTimeLogic(cpRecord.getStartTime(), cpRecord.getEndTime());

            long apWorkOutTime = calWorkOutTime(apRecord.getStartTime(), apRecord.getEndTime());
            long cpWorkOutTime = calWorkOutTime(cpRecord.getStartTime(), cpRecord.getEndTime());

            if(apWorkOutTime < cpWorkOutTime){
                cpPoint++;
            }
            else if(apWorkOutTime > cpWorkOutTime){
                apPoint++;
            }
            else{
                apPoint++;
                cpPoint++;
            }

            //총점 계산
            if(apPoint < cpPoint){
                cpRecord.getMember().getMemberActivity().updatePoint(3);
            }
            else if(apPoint > cpPoint){
                apRecord.getMember().getMemberActivity().updatePoint(3);
            }
            else{
                cpRecord.getMember().getMemberActivity().updatePoint(1);
                apRecord.getMember().getMemberActivity().updatePoint(1);
            }
        });
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution){

        return ExitStatus.COMPLETED;
    }

    private void checkTimeLogic(LocalTime startTime, LocalTime endTime){
        if(endTime.isBefore(startTime))
            throw new RuntimeException(); //todo 시간 에러 바꾸기
    }

    private long calWorkOutTime(LocalTime startTime, LocalTime endTime){
        return startTime.until(endTime, ChronoUnit.SECONDS);
    }
}


