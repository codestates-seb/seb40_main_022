package com.backend.fitchallenge.domain.answercomment.service;

import com.backend.fitchallenge.domain.answer.entity.Answer;
import com.backend.fitchallenge.domain.answer.exception.AnswerException;
import com.backend.fitchallenge.domain.answer.repository.AnswerRepository;
import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentCreate;
import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentUpdate;
import com.backend.fitchallenge.domain.answercomment.entity.AnswerComment;
import com.backend.fitchallenge.domain.answercomment.exception.AnswerCommentException;
import com.backend.fitchallenge.domain.answercomment.repository.AnswerCommentRepository;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    /**
     * 요청을 보낸 사용자 조회하는 로직 필요
     */
    public Long createAnswerComment(Long answerId, AnswerCommentCreate answerCommentCreate) {

        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new AnswerException(ExceptionCode.ANSWER_NOT_FOUND));

        return answerCommentRepository.save(AnswerComment.createAnswerComment(answerCommentCreate, answer, member)).getId();
    }

    /**
     * 요청을 보낸 사용자 조회하는 로직 필요
     */
    public Long updateAnswerComment(Long answerCommentId, AnswerCommentUpdate answerCommentUpdate) {

        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        AnswerComment findAnswerComment = answerCommentRepository.findById(answerCommentId)
                .orElseThrow(() -> new AnswerCommentException(ExceptionCode.COMMENT_NOT_FOUND));
        verifyWriter(member.getId(), findAnswerComment);

        findAnswerComment.updateAnswerComment(answerCommentUpdate);

        return answerCommentRepository.save(findAnswerComment).getId();
    }

    /**
     * 요청을 보낸 사용자 조회하는 로직 필요
     */
    public Long deleteAnswerComment(Long answerCommentId) {

        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        AnswerComment findAnswerComment = answerCommentRepository.findById(answerCommentId)
                .orElseThrow(() -> new AnswerCommentException(ExceptionCode.COMMENT_NOT_FOUND));
        verifyWriter(member.getId(), findAnswerComment);

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
}
