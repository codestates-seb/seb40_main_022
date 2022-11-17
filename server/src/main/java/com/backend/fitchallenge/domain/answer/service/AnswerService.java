package com.backend.fitchallenge.domain.answer.service;

import com.backend.fitchallenge.domain.answer.dto.request.AnswerCreate;
import com.backend.fitchallenge.domain.answer.dto.request.AnswerUpdate;
import com.backend.fitchallenge.domain.answer.entity.Answer;
import com.backend.fitchallenge.domain.answer.exception.AnswerNotFound;
import com.backend.fitchallenge.domain.answer.exception.NotAnswerWriter;
import com.backend.fitchallenge.domain.answer.repository.AnswerRepository;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.exception.NotQuestionWriter;
import com.backend.fitchallenge.domain.question.exception.QuestionNotFound;
import com.backend.fitchallenge.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    public Long createAnswer(Long memberId, Long id, AnswerCreate answerCreate) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFound::new);

        return answerRepository.save(Answer.createAnswer(answerCreate, question, member)).getId();
    }

    public Long updateAnswer(Long memberId, Long answerId, AnswerUpdate answerUpdate) {

        Answer findAnswer = findVerifiedAnswer(answerId);

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        verifyWriter(member.getId(), findAnswer);

        findAnswer.updateAnswer(answerUpdate);

        return answerRepository.save(findAnswer).getId();
    }

    public Long deleteAnswer(Long memberId, Long answerId) {

        Answer findAnswer = findVerifiedAnswer(answerId);

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        verifyWriter(member.getId(), findAnswer);

        answerRepository.delete(findAnswer);

        return answerId;
    }

    public Long accept(Long memberId, Long id, Long answerId) {

        Answer findAnswer = findVerifiedAnswer(answerId);

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFound::new);

        if (member.getId().equals(question.getMember().getId())) {
            findAnswer.accept();
            if (!findAnswer.getMember().getId().equals(member.getId())) {
                // 채택자의 Community Point가 증가
            }
        } else {
            throw new NotQuestionWriter();
        }

        return answerId;
    }

    @Transactional(readOnly = true)
    private Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);

        return optionalAnswer.orElseThrow(AnswerNotFound::new);
    }

    @Transactional(readOnly = true)
    private void verifyWriter(Long memberId, Answer findAnswer) {
        Long writerId = answerRepository.findMemberIdByAnswerId(findAnswer.getId());

        if (!writerId.equals(memberId)) {
            throw new NotAnswerWriter();
        }
    }
}
