package com.backend.fitchallenge.domain.question.service;

import com.backend.fitchallenge.domain.answer.dto.response.AnswerResponse;

import com.backend.fitchallenge.domain.answer.entity.Answer;
import com.backend.fitchallenge.domain.answer.repository.AnswerRepository;
import com.backend.fitchallenge.domain.answer.repository.QueryAnswerRepository;
import com.backend.fitchallenge.domain.answercomment.dto.response.AnswerCommentResponse;


import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
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
import com.backend.fitchallenge.global.dto.request.PageRequest;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.backend.fitchallenge.domain.question.entity.QQuestion.question;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final QueryAnswerRepository queryAnswerRepository;

    // fixme : 등록, 수정, 삭제 메서드에 간접참조 반영 고려
    /**
     * [질문 등록]
     * 1. 요청을 보낸 회원이 존재하는지 확인합니다.
     * 2. 질문을 생성한 후 id를 반환합니다.
     */
    public Long createQuestion(Long memberId, QuestionCreate questionCreate) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Question question = Question.createQuestion(questionCreate, member);

        return questionRepository.save(question).getId();
    }

    /**
     * [질문 상세 조회]
     * 1. 질문이 존재하는지 확인하고 있다면 조회수를 1 늘립니다.
     * 2. 질문의 id를 사용하여 답변과 코멘트를 조회하고 dto를 생성합니다.
     * 3. 질문을 작성한 회원의 dto를 생성합니다.
     * 4. 1,2,3의 결과를 사용해 DetailQuestionResponse를 생성하고 반환합니다.
     */
    public DetailQuestionResponse getQuestion(Long id) {

        Question findQuestion = questionRepository.findQuestionWithWriter(id).orElseThrow(QuestionNotFound::new);
        findQuestion.addView();

        List<AnswerResponse> answerResponses = queryAnswerRepository.findAnswersAndCommentsWithWriters(id).stream()
                .map(answer ->
                        AnswerResponse.of(answer, answer.getComments().stream().map(AnswerCommentResponse::of).collect(Collectors.toList())))
                .collect(Collectors.toList());

        MemberResponse memberResponse = MemberResponse.of(findQuestion.getMember());

        return DetailQuestionResponse.of(findQuestion, memberResponse, answerResponses);
    }

    /**
     * [질문 목록 조회]
     */
    @Transactional(readOnly = true)
    public MultiResponse<?> getQuestionList(PageRequest pageable) {

        Long total = questionRepository.pagingCount();

        Page<SimpleQuestionResponse> questionResponses = new PageImpl<>(questionRepository.findList(pageable).stream()
                .map(questionTuple -> SimpleQuestionResponse.builder()
                        .question(Objects.requireNonNull(questionTuple.get(question)))
                        .member(MemberResponse.of(questionTuple.get(question).getMember()))
                        .answerCount(questionTuple.get(question.answers.size()))
                        .build()).collect(Collectors.toList()), pageable.of(), total);

        return MultiResponse.of(questionResponses);
    }

    /**
     * [질문 검색 결과 조회]
     */
    // todo : "태그" '#' 붙여서 구분 고려
    @Transactional(readOnly = true)
    public MultiResponse<?> getQuestionList(PageRequest pageable, String keyword) {

        Long total = questionRepository.pagingCount();

        Page<SimpleQuestionResponse> questionResponses = new PageImpl<>(questionRepository.findList(pageable, keyword).stream()
                .map(questionTuple -> SimpleQuestionResponse.builder()
                        .question(Objects.requireNonNull(questionTuple.get(question)))
                        .member(MemberResponse.of(questionTuple.get(question).getMember()))
                        .answerCount(questionTuple.get(question.answers.size()))
                        .build()).collect(Collectors.toList()), pageable.of(), total);

        return MultiResponse.of(questionResponses);
    }

    /**
     * [질문 수정]
     * 1. 요청을 보낸 회원과 질문이 존재하는지 확인합니다.
     * 2. 회원이 질문의 작성자인지 확인합니다.
     * 3. 맞다면, 질문을 수정하고 id를 반환합니다.
     */
    public Long updateQuestion(Long memberId, Long id, QuestionUpdate questionUpdate) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Question findQuestion = findVerifiedQuestion(id);

        verifyWriter(member.getId(), findQuestion);

        findQuestion.updateQuestion(questionUpdate);

        return questionRepository.save(findQuestion).getId();
    }

    /**
     * [질문 삭제]
     * 1. 요청을 보낸 회원과 질문이 존재하는지 확인합니다.
     * 2. 회원이 질문의 작성자인지 확인합니다.
     * 3. 맞다면, 질문을 삭제하고 id를 반환합니다.
     */
    public Long deleteQuestion(Long memberId, Long id) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Question findQuestion = findVerifiedQuestion(id);

        verifyWriter(member.getId(), findQuestion);

        questionRepository.delete(findQuestion);

        return id;
    }

    //== 여기서부터 보조 기능==//
    //질문 존재하는지 확인
    @Transactional(readOnly = true)
    private Question findVerifiedQuestion(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        return optionalQuestion.orElseThrow(QuestionNotFound::new);
    }

    //요청을 보낸 회원이 질문의 작성자인지 확인
    @Transactional(readOnly = true)
    private void verifyWriter(Long memberId, Question findQuestion) {

        Long writerId = questionRepository.findMemberIdByQuestionId(findQuestion.getId());

        if (!writerId.equals(memberId)) {
            throw new NotQuestionWriter();
        }
    }
}
