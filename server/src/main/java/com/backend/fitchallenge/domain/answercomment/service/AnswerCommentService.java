package com.backend.fitchallenge.domain.answercomment.service;

import com.backend.fitchallenge.domain.answer.entity.Answer;
import com.backend.fitchallenge.domain.answer.exception.AnswerException;
import com.backend.fitchallenge.domain.answer.repository.AnswerRepository;
import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentCreate;
import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentUpdate;
import com.backend.fitchallenge.domain.answercomment.entity.AnswerComment;
import com.backend.fitchallenge.domain.answercomment.exception.AnswerCommentException;
import com.backend.fitchallenge.domain.answercomment.repository.AnswerCommentRepository;
import com.backend.fitchallenge.domain.member.Member;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.exception.QuestionException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AnswerCommentService {

    private final AnswerCommentRepository answerCommentRepository;
    private final AnswerRepository answerRepository;

    public Long createAnswerComment(Long memberId, Long answerId, AnswerCommentCreate answerCommentCreate) {

        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new AnswerException(ExceptionCode.ANSWER_NOT_FOUND));
        Member member = getStubMember(memberId);

        return answerCommentRepository.save(AnswerComment.createAnswerComment(answerCommentCreate, answer, member)).getId();
    }

    public Long updateAnswerComment(Long memberId, Long answerCommentId, AnswerCommentUpdate answerCommentUpdate) {

        AnswerComment findAnswerComment = answerCommentRepository.findById(answerCommentId)
                .orElseThrow(() -> new AnswerCommentException(ExceptionCode.COMMENT_NOT_FOUND));
        verifyWriter(memberId, findAnswerComment);

        findAnswerComment.updateAnswerComment(answerCommentUpdate);

        return answerCommentRepository.save(findAnswerComment).getId();
    }

    public Long deleteAnswerComment(Long memberId, Long answerCommentId) {

        AnswerComment findAnswerComment = answerCommentRepository.findById(answerCommentId)
                .orElseThrow(() -> new AnswerCommentException(ExceptionCode.COMMENT_NOT_FOUND));
        verifyWriter(memberId, findAnswerComment);

        answerCommentRepository.delete(findAnswerComment);

        return answerCommentId;
    }

    @Transactional(readOnly = true)
    private void verifyWriter(Long memberId, AnswerComment findAnswerComment) {

        Long writerId = answerCommentRepository.findMemberIdByAnswerCommentId(findAnswerComment.getId());

        if (!writerId.equals(memberId)) {
            throw new AnswerCommentException(ExceptionCode.NOT_COMMENT_WRITER);
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
