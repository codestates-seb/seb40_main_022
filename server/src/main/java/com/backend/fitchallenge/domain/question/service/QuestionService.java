package com.backend.fitchallenge.domain.question.service;

import com.backend.fitchallenge.domain.answer.dto.response.AnswerResponse;
import com.backend.fitchallenge.domain.answer.repository.AnswerRepository;
import com.backend.fitchallenge.domain.member.Member;
import com.backend.fitchallenge.domain.member.dto.MemberResponse;
import com.backend.fitchallenge.domain.question.dto.request.QuestionCreate;
import com.backend.fitchallenge.domain.question.dto.request.QuestionUpdate;
import com.backend.fitchallenge.domain.question.dto.response.DetailQuestionResponse;
import com.backend.fitchallenge.domain.question.dto.response.SimpleQuestionResponse;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.exception.QuestionException;
import com.backend.fitchallenge.domain.question.repository.QuestionRepository;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public Long createQuestion(Long memberId, QuestionCreate questionCreate) {

        // Member stub 데이터로 대체
        Member member = getStubMember(memberId);

        Question question = Question.createQuestion(questionCreate, member);

        return questionRepository.save(question).getId();
    }

    @Transactional(readOnly = true)
    public DetailQuestionResponse getQuestion(Long id) {

        Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionException(ExceptionCode.QUESTION_NOT_FOUND));

        List<AnswerResponse> answerResponses = question.getAnswers().stream().map(AnswerResponse::of).collect(Collectors.toList());

        // Member stub 데이터로 대체
        MemberResponse memberResponse = MemberResponse.of(getStubMember(0L));

        return DetailQuestionResponse.of(question, memberResponse, answerResponses);
    }

    @Transactional(readOnly = true)
    public MultiResponse<?> getQuestionList(Pageable pageable) {

        Long total = questionRepository.pagingCount();

        // Member stub 데이터로 대체
        Page<SimpleQuestionResponse> questionResponses = new PageImpl<>(questionRepository.findAll(pageable).stream()
                .map(question -> SimpleQuestionResponse.builder()
                        .question(question)
                        .memberResponse(MemberResponse.of(getStubMember(0L)))
                        .answerCount(question.getAnswers().size())
                        .build()).collect(Collectors.toList()), pageable, total);

        return MultiResponse.of(questionResponses);
    }

    public Long updateQuestion(Long memberId, Long id, QuestionUpdate questionUpdate) {

        Question findQuestion = questionRepository.findById(id).orElseThrow(() -> new QuestionException(ExceptionCode.QUESTION_NOT_FOUND));

        verifyWriter(memberId, findQuestion);

        findQuestion.updateQuestion(questionUpdate);

        return questionRepository.save(findQuestion).getId();
    }

    public Long deleteQuestion(Long memberId, Long id) {

        Question findQuestion = questionRepository.findById(id).orElseThrow(() -> new QuestionException(ExceptionCode.QUESTION_NOT_FOUND));

        verifyWriter(memberId, findQuestion);

        questionRepository.delete(findQuestion);

        return id;
    }

    @Transactional(readOnly = true)
    private void verifyWriter(Long memberId, Question findQuestion) {

        Long writerId = questionRepository.findMemberIdByQuestionId(findQuestion.getId());

        if (!writerId.equals(memberId)) {
            throw new QuestionException(ExceptionCode.NOT_QUESTION_WRITER);
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
