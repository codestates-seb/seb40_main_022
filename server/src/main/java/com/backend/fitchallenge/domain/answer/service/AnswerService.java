package com.backend.fitchallenge.domain.answer.service;

import com.backend.fitchallenge.domain.answer.dto.request.AnswerCreate;
import com.backend.fitchallenge.domain.answer.dto.request.AnswerUpdate;
import com.backend.fitchallenge.domain.answer.entity.Answer;
import com.backend.fitchallenge.domain.answer.repository.AnswerRepository;
import com.backend.fitchallenge.domain.answer.exception.AnswerException;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.exception.QuestionException;
import com.backend.fitchallenge.domain.question.repository.QuestionRepository;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    /**
     * 요청을 보낸 사용자 조회하는 로직 필요
     */
    public Long createAnswer(Long id, AnswerCreate answerCreate) {

        // 요청 사용자 조회 로직 적용시 수정
        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionException(ExceptionCode.QUESTION_NOT_FOUND));

        return answerRepository.save(Answer.createAnswer(answerCreate, question, member)).getId();
    }

    /**
     * 요청을 보낸 사용자 조회하는 로직 필요
     */
    public Long updateAnswer(Long answerId, AnswerUpdate answerUpdate) {

        Answer findAnswer = findVerifiedAnswer(answerId);

        // 요청 사용자 조회 로직 적용시 수정
        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        verifyWriter(member.getId(), findAnswer);

        findAnswer.updateAnswer(answerUpdate);

        return answerRepository.save(findAnswer).getId();
    }

    /**
     * 요청을 보낸 사용자 조회하는 로직 필요
     */
    public Long deleteAnswer(Long answerId) {

        Answer findAnswer = findVerifiedAnswer(answerId);

        // 요청 사용자 조회 로직 적용시 수정
        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        verifyWriter(member.getId(), findAnswer);

        answerRepository.delete(findAnswer);

        return answerId;
    }

    /**
     * 1. 요청을 보낸 사용자 조회하는 로직 필요
     * 2. Member 클래스에 Community Point 늘리는 메서드 추가되면 반영
     */
    public Long accept(Long id, Long answerId) {

        Answer findAnswer = findVerifiedAnswer(answerId);

        // 요청 사용자 조회 로직 적용시 수정
        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionException(ExceptionCode.QUESTION_NOT_FOUND));

        if (member.getId().equals(question.getMember().getId())) {
            findAnswer.accept();
            if (!findAnswer.getMember().getId().equals(member.getId())) {
                // 채택자의 Community Point가 증가
            }
        } else {
            throw new QuestionException(ExceptionCode.NOT_QUESTION_WRITER);
        }

        return answerId;
    }

    @Transactional(readOnly = true)
    private void verifyWriter(Long memberId, Answer findAnswer) {
        Long writerId = answerRepository.findMemberIdByAnswerId(findAnswer.getId());

        if (!writerId.equals(memberId)) {
            throw new AnswerException(ExceptionCode.NOT_ANSWER_WRITER);
        }
    }

    @Transactional(readOnly = true)
    private Answer findVerifiedAnswer(Long answerId) {

        return answerRepository.findById(answerId)
                .orElseThrow(() -> new AnswerException(ExceptionCode.ANSWER_NOT_FOUND));
    }
}
