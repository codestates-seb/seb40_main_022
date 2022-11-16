package com.backend.fitchallenge.domain.question.service;

import com.backend.fitchallenge.domain.answer.dto.response.AnswerResponse;
import com.backend.fitchallenge.domain.answer.repository.AnswerRepository;
import com.backend.fitchallenge.domain.member.dto.response.MemberResponse;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.question.dto.request.QuestionCreate;
import com.backend.fitchallenge.domain.question.dto.request.QuestionUpdate;
import com.backend.fitchallenge.domain.question.dto.response.DetailQuestionResponse;
import com.backend.fitchallenge.domain.question.dto.response.SimpleQuestionResponse;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.exception.NotQuestionWriter;
import com.backend.fitchallenge.domain.question.exception.QuestionNotFound;
import com.backend.fitchallenge.domain.question.repository.QuestionRepository;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    /**
     * 요청을 보낸 사용자 조회하는 로직 필요
     */
    public Long createQuestion(QuestionCreate questionCreate) {

        // 요청 사용자 조회 로직 적용시 수정
        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        Question question = Question.createQuestion(questionCreate, member);

        return questionRepository.save(question).getId();
    }

    @Transactional(readOnly = true)
    public DetailQuestionResponse getQuestion(Long id) {

        Question question = findVerifiedQuestion(id);

        List<AnswerResponse> answerResponses = question.getAnswers().stream().map(AnswerResponse::of).collect(Collectors.toList());

        // Member stub 데이터로 대체
        MemberResponse memberResponse = MemberResponse.of(question.getMember());

        return DetailQuestionResponse.of(question, memberResponse, answerResponses);
    }

    @Transactional(readOnly = true)
    public MultiResponse<?> getQuestionList(Pageable pageable) {

        Long total = questionRepository.pagingCount();

        Page<SimpleQuestionResponse> questionResponses = new PageImpl<>(questionRepository.findAll(pageable).stream()
                .map(question -> SimpleQuestionResponse.builder()
                        .question(question)
                        .memberResponse(MemberResponse.of(question.getMember()))
                        .answerCount(question.getAnswers().size())
                        .build()).collect(Collectors.toList()), pageable, total);

        return MultiResponse.of(questionResponses);
    }

    /**
     * 요청을 보낸 사용자 조회하는 로직 필요
     */
    public Long updateQuestion(Long id, QuestionUpdate questionUpdate) {

        // 요청 사용자 조회 로직 적용시 수정
        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        Question findQuestion = findVerifiedQuestion(id);

        verifyWriter(member.getId(), findQuestion);

        findQuestion.updateQuestion(questionUpdate);

        return questionRepository.save(findQuestion).getId();
    }

    /**
     * 요청을 보낸 사용자 조회하는 로직 필요
     */
    public Long deleteQuestion(Long id) {

        // 요청 사용자 조회 로직 적용시 수정
        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        Question findQuestion = findVerifiedQuestion(id);

        verifyWriter(member.getId(), findQuestion);

        questionRepository.delete(findQuestion);

        return id;
    }

    @Transactional(readOnly = true)
    private Question findVerifiedQuestion(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        return optionalQuestion.orElseThrow(QuestionNotFound::new);
    }

    @Transactional(readOnly = true)
    private void verifyWriter(Long memberId, Question findQuestion) {

        Long writerId = questionRepository.findMemberIdByQuestionId(findQuestion.getId());

        if (!writerId.equals(memberId)) {
            throw new NotQuestionWriter();
        }
    }
}
