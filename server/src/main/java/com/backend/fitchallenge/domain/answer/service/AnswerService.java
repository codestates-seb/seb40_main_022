package com.backend.fitchallenge.domain.answer.service;

import com.backend.fitchallenge.domain.answer.dto.request.AnswerCreate;
import com.backend.fitchallenge.domain.answer.dto.request.AnswerUpdate;
import com.backend.fitchallenge.domain.answer.entity.Answer;
import com.backend.fitchallenge.domain.answer.repository.AnswerRepository;
import com.backend.fitchallenge.domain.answer.exception.AnswerException;
import com.backend.fitchallenge.domain.member.Member;
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

    public Long createAnswer(Long memberId, Long id, AnswerCreate answerCreate) {

        Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionException(ExceptionCode.QUESTION_NOT_FOUND));
        Member member = getStubMember(memberId);

        return answerRepository.save(Answer.createAnswer(answerCreate, question, member)).getId();
    }

    public Long updateAnswer(Long answerId, Long memberId, AnswerUpdate answerUpdate) {

        Answer findAnswer = answerRepository.findById(answerId).orElseThrow(() -> new AnswerException(ExceptionCode.ANSWER_NOT_FOUND));

        verifyWriter(memberId, findAnswer);

        findAnswer.updateAnswer(answerUpdate);

        return answerRepository.save(findAnswer).getId();
    }

    public Long deleteAnswer(Long memberId, Long answerId) {

        Answer findAnswer = answerRepository.findById(answerId).orElseThrow(() -> new AnswerException(ExceptionCode.ANSWER_NOT_FOUND));

        verifyWriter(memberId, findAnswer);

        answerRepository.delete(findAnswer);

        return answerId;
    }

    @Transactional(readOnly = true)
    private void verifyWriter(Long memberId, Answer findAnswer) {
        Long writerId = answerRepository.findMemberIdByAnswerId(findAnswer.getId());

        if (!writerId.equals(memberId)) {
            throw new AnswerException(ExceptionCode.NOT_ANSWER_WRITER);
        }
    }

    private Member getStubMember(Long memberId) {
        return Member.builder()
                .id(memberId)
                .email("hgd@email.com")
                .password("ghdrlfehd")
                .username("홍길동")
                .point(0L)
                .build();
    }
}
