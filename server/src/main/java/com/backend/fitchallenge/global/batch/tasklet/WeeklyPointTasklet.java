package com.backend.fitchallenge.global.batch.tasklet;

import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.backend.fitchallenge.domain.challenge.repository.ChallengeRepository;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@StepScope
@Slf4j
public class WeeklyPointTasklet implements Tasklet, StepExecutionListener {

    private List<Tuple> result;
    private List<Challenge> challengeList;

    private final ChallengeRepository challengeRepository;
    private final MemberRepository memberRepository;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        //배치가 시작되는 시점이 자정 이후라면 가져와야 할 challenge의 endDate는 전 날
        challengeList = challengeRepository.findAllByEndDateAndStatus(LocalDate.now().minusDays(1));
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        log.info(contribution.toString());
        log.info(chunkContext.toString());
        log.info(">>>>> This is weeklyPointStep");

        if (!challengeList.isEmpty()) {
            for (Challenge challenge : challengeList) {
                Optional<Member> applicant = memberRepository.findById(challenge.getApplicantId());
                Optional<Member> counterPart = memberRepository.findById(challenge.getCounterpartId());

                //존재하지 않는 member의 id가 담긴 잘못된 challenge 기록이 있을 경우 대비
                //  ex) 챌린지 중인 멤버가 탈퇴할 경우 어떻게 처리할 것인지?
                if (applicant.isEmpty() || counterPart.isEmpty())
                    continue;

                Integer challengePoint = challenge.getChallengePoint();

                if (challengePoint > 0) {
                    applicant.get().getMemberActivity().updatePoint(10);
                }
                else if (challengePoint < 0){
                    counterPart.get().getMemberActivity().updatePoint(10);
                } {
                    applicant.get().getMemberActivity().updatePoint(5);
                    counterPart.get().getMemberActivity().updatePoint(5);
                }

                challengeRepository.delete(challenge);
            }
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        return ExitStatus.COMPLETED;
    }
}
