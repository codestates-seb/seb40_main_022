package com.backend.fitchallenge.domain.question.service;

import com.backend.fitchallenge.domain.answer.dto.response.AnswerResponse;

import com.backend.fitchallenge.domain.answer.repository.QueryAnswerRepository;
import com.backend.fitchallenge.domain.answercomment.dto.response.AnswerCommentResponse;

import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.post.service.AwsS3Service;
import com.backend.fitchallenge.domain.question.dto.request.QuestionCreateVO;
import com.backend.fitchallenge.domain.question.dto.request.QuestionSearch;
import com.backend.fitchallenge.domain.question.dto.request.QuestionUpdateVO;
import com.backend.fitchallenge.domain.question.dto.response.DetailQuestionResponse;
import com.backend.fitchallenge.domain.question.dto.response.SimpleQuestionResponse;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.entity.QuestionPicture;
import com.backend.fitchallenge.domain.question.exception.NotQuestionWriter;
import com.backend.fitchallenge.domain.question.exception.QuestionNotFound;
import com.backend.fitchallenge.domain.question.repository.QuestionRepository;
import com.backend.fitchallenge.domain.question.dto.request.PageRequest;
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
    private final AwsS3Service awsS3Service;

    public Long createQuestion(Long memberId, QuestionCreateVO questionCreateVO, List<String> imagePathList) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Question question = Question.create(questionCreateVO, member, imagePathList);

        return questionRepository.save(question).getId();
    }


    public DetailQuestionResponse getQuestion(Long id) {

        //질문 조회수 증가
        Question findQuestion = questionRepository.findQuestionWithWriter(id).orElseThrow(QuestionNotFound::new);
        findQuestion.addView();

        //해당 질문 작성자 community point 증가
        Member questionWriter = findQuestion.getMember();
        questionWriter.getMemberActivity().updatePoint(0.01);
        memberRepository.save(questionWriter);

        List<AnswerResponse> answerResponses = queryAnswerRepository.findAnswersAndCommentsWithWriters(id).stream()
                .map(answer ->
                        AnswerResponse.of(answer, answer.getComments().stream().map(AnswerCommentResponse::of).collect(Collectors.toList())))
                .collect(Collectors.toList());

        MemberResponse memberResponse = MemberResponse.of(questionWriter);

        return DetailQuestionResponse.of(findQuestion, memberResponse, answerResponses);
    }


    @Transactional(readOnly = true)
    public MultiResponse<?> getQuestionList(PageRequest pageable) {

        Long total = questionRepository.pagingCount();

        Page<SimpleQuestionResponse> questionResponses = new PageImpl<>(questionRepository.findList(pageable).stream()
                .map(questionTuple -> SimpleQuestionResponse.builder()
                        .question(Objects.requireNonNull(questionTuple.get(question)))
                        .picture(questionTuple.get(question).getQuestionPictures().stream().findFirst()
                                .orElse(QuestionPicture.createWithEmptyPath()).getPath())
                        .member(MemberResponse.of(questionTuple.get(question).getMember()))
                        .answerCount(questionTuple.get(question.answers.size()))
                        .build()).collect(Collectors.toList()), pageable.of(), total);

        return MultiResponse.of(questionResponses);
    }


    @Transactional(readOnly = true)
    public MultiResponse<?> getQuestionList(PageRequest pageable, QuestionSearch questionSearch) {
        log.info("repository query: {}", questionSearch.getQuery());
        Long total = questionRepository.pagingCount();

        Page<SimpleQuestionResponse> questionResponses = new PageImpl<>(questionRepository.findList(pageable, questionSearch).stream()
                .map(questionTuple -> SimpleQuestionResponse.builder()
                        .question(Objects.requireNonNull(questionTuple.get(question)))
                        .member(MemberResponse.of(questionTuple.get(question).getMember()))
                        .answerCount(questionTuple.get(question.answers.size()))
                        .build()).collect(Collectors.toList()), pageable.of(), total);

        return MultiResponse.of(questionResponses);
    }


    public Long updateQuestion(Long memberId, Long id, QuestionUpdateVO questionUpdateVO) {

        Question findQuestion = findQuestion(id);

        isWriter(memberId, findQuestion);

        if (!questionUpdateVO.getFiles().isEmpty()) {
            List<String> paths = findQuestion.getQuestionPictures().stream()
                    .map(questionPicture -> {
                        int index = questionPicture.getPath().lastIndexOf("/");
                        return questionPicture.getPath().substring(index + 1);
                    }).collect(Collectors.toList());

            List<String> imagePaths = awsS3Service.UpdateFile(paths, questionUpdateVO.getFiles());
        }

        findQuestion.update(questionUpdateVO);

        return questionRepository.save(findQuestion).getId();
    }


    public Long deleteQuestion(Long memberId, Long id) {

        Question findQuestion = findQuestion(id);

        isWriter(memberId, findQuestion);

        List<String> paths = findQuestion.getQuestionPictures().stream()
                        .map(questionPicture -> {
                            int index = questionPicture.getPath().lastIndexOf("/");
                            return questionPicture.getPath().substring(index + 1);
                        }).collect(Collectors.toList());

        if (!paths.isEmpty()) {
            awsS3Service.DeleteFile(paths);
        }

        questionRepository.delete(findQuestion);

        return id;
    }


    @Transactional(readOnly = true)
    private Question findQuestion(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        return optionalQuestion.orElseThrow(QuestionNotFound::new);
    }


    @Transactional(readOnly = true)
    private void isWriter(Long memberId, Question findQuestion) {

        Long writerId = questionRepository.findMemberIdByQuestionId(findQuestion.getId());

        if (!writerId.equals(memberId)) {
            throw new NotQuestionWriter();
        }
    }
}
