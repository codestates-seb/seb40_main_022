package com.backend.fitchallenge.domain.answercomment.service;

import com.backend.fitchallenge.domain.answer.entity.Answer;
import com.backend.fitchallenge.domain.answer.exception.AnswerNotFound;
import com.backend.fitchallenge.domain.answer.repository.AnswerRepository;
import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentCreate;
import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentUpdate;
import com.backend.fitchallenge.domain.answercomment.entity.AnswerComment;
import com.backend.fitchallenge.domain.answercomment.exception.CommentNotFound;
import com.backend.fitchallenge.domain.answercomment.exception.NotCommentWriter;
import com.backend.fitchallenge.domain.answercomment.repository.AnswerCommentRepository;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AnswerCommentService {

    private final AnswerCommentRepository answerCommentRepository;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;

    public Long createAnswerComment(Long memberId, Long answerId, AnswerCommentCreate answerCommentCreate) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(AnswerNotFound::new);

        return answerCommentRepository.save(AnswerComment.toEntity(answerCommentCreate, answer, member)).getId();
    }

    public Long updateAnswerComment(Long memberId, Long answerCommentId, AnswerCommentUpdate answerCommentUpdate) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        AnswerComment answerComment = findAnswerComment(answerCommentId);
        verifyWriter(member.getId(), answerComment);

        answerComment.update(answerCommentUpdate);

        return answerCommentRepository.save(answerComment).getId();
    }

    public Long deleteAnswerComment(Long memberId, Long answerCommentId) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        AnswerComment answerComment = findAnswerComment(answerCommentId);
        verifyWriter(member.getId(), answerComment);

        answerCommentRepository.delete(answerComment);

        return answerCommentId;
    }

    private AnswerComment findAnswerComment(Long answerCommentId) {
        Optional<AnswerComment> optionalAnswerComment = answerCommentRepository.findById(answerCommentId);

        return optionalAnswerComment.orElseThrow(CommentNotFound::new);
    }

    @Transactional(readOnly = true)
    private void verifyWriter(Long memberId, AnswerComment findAnswerComment) {

        Long writerId = answerCommentRepository.findMemberIdByAnswerCommentId(findAnswerComment.getId());

        if (!writerId.equals(memberId)) {
            throw new NotCommentWriter();
        }
    }
}
