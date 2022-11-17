package com.backend.fitchallenge.domain.question.service;

import com.backend.fitchallenge.domain.answer.dto.response.AnswerResponse;
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

    public Long createQuestion(Long memberId, QuestionCreate questionCreate) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Question question = Question.createQuestion(questionCreate, member);

        return questionRepository.save(question).getId();
    }

    public DetailQuestionResponse getQuestion(Long id) {

        List<Question> questions = questionRepository.findQuestionAndAnswersWithWriters(id);
        Question question = questions.stream().findAny().orElseThrow(QuestionNotFound::new);
        question.addView();

        List<AnswerResponse> answerResponses = question.getAnswers().stream().map(AnswerResponse::of).collect(Collectors.toList());

        MemberResponse memberResponse = MemberResponse.of(question.getMember());

        return DetailQuestionResponse.of(question, memberResponse, answerResponses);
    }

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

    public Long updateQuestion(Long memberId, Long id, QuestionUpdate questionUpdate) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Question findQuestion = findVerifiedQuestion(id);

        verifyWriter(member.getId(), findQuestion);

        findQuestion.updateQuestion(questionUpdate);

        return questionRepository.save(findQuestion).getId();
    }

    public Long deleteQuestion(Long memberId, Long id) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
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
