package com.backend.fitchallenge.domain.question.service;

import com.backend.fitchallenge.domain.answer.dto.response.AnswerResponse;

import com.backend.fitchallenge.domain.answer.repository.QueryAnswerRepository;
import com.backend.fitchallenge.domain.answercomment.dto.response.AnswerCommentResponse;

import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.post.service.AwsS3Service;
import com.backend.fitchallenge.domain.question.dto.request.*;
import com.backend.fitchallenge.domain.question.dto.response.DetailQuestionResponse;
import com.backend.fitchallenge.domain.question.dto.response.SimpleQuestionResponse;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.entity.QuestionDocument;
import com.backend.fitchallenge.domain.question.entity.QuestionPicture;
import com.backend.fitchallenge.domain.question.exception.NotQuestionWriter;
import com.backend.fitchallenge.domain.question.exception.QuestionNotFound;
import com.backend.fitchallenge.domain.question.repository.elasticsearchrepository.QuestionSearchRepository;
import com.backend.fitchallenge.domain.question.repository.jparepository.QuestionRepository;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.backend.fitchallenge.domain.question.entity.QQuestion.question;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionSearchRepository questionSearchRepository;
    private final MemberRepository memberRepository;
    private final QueryAnswerRepository queryAnswerRepository;
    private final AwsS3Service awsS3Service;

    public Long createQuestion(Long memberId, QuestionCreateVO questionCreateVO, List<String> imagePathList) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Question question = Question.create(questionCreateVO, member, imagePathList);
        Question savedQuestion = questionRepository.save(question);

        questionSearchRepository.save(QuestionDocument.from(savedQuestion));

        return savedQuestion.getId();
    }


    public DetailQuestionResponse getQuestion(Long id) throws IOException {

        //질문 조회수 증가
        Question findQuestion = questionRepository.findQuestionWithWriter(id).orElseThrow(QuestionNotFound::new);
        questionSearchRepository.findById(id).orElseThrow(QuestionNotFound::new);
        findQuestion.addView();
        questionSearchRepository.updateView(findQuestion.getId(), findQuestion.getView());

        //해당 질문 작성자 community point 증가
        Member questionWriter = findQuestion.getMember();
        questionWriter.getMemberActivity().updatePoint(0.01D);
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
                .map(questionTuple -> SimpleQuestionResponse.of(
                        Objects.requireNonNull(questionTuple.get(question)),
                        questionTuple.get(question.answers.size()),
                        Objects.requireNonNull(questionTuple.get(question)).getQuestionPictures().stream().findFirst()
                            .orElse(QuestionPicture.createWithEmptyPath()).getPath(),
                        MemberResponse.of(Objects.requireNonNull(questionTuple.get(question)).getMember()))
                ).collect(Collectors.toList()), pageable.of(), total);

        return MultiResponse.of(questionResponses);
    }

    @Transactional(readOnly = true)
    public MultiResponse<?> searchQuestionList(PageRequest pageable, QuestionSearch questionSearch) {

        List<QuestionDocument> documentList;
        if (pageable.getSort().equals("accuracy")) {
            documentList = questionSearchRepository.getQuestionsOrderByAccuracy(pageable, questionSearch);
        } else {
            documentList = questionSearchRepository.getQuestionsOrderByIdOrView(pageable, questionSearch);
        }

        Page<SimpleQuestionResponse> questionResponses = new PageImpl<>(documentList.stream()
                .map(questionDocument -> SimpleQuestionResponse.of(
                        questionDocument,
                        new MemberResponse(questionDocument.getMemberId(),
                                        questionDocument.getUsername(),
                                        questionDocument.getProfileImage()))
                ).collect(Collectors.toList()), pageable.of(), documentList.size());

        return MultiResponse.of(questionResponses);
    }

    public Long updateQuestion(Long memberId, Long id, QuestionUpdateVO questionUpdateVO) throws IOException {

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
        questionSearchRepository.updateQuestion(id, findQuestion);

        return questionRepository.save(findQuestion).getId();
    }


    public Long deleteQuestion(Long memberId, Long id) throws IOException {

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
        questionSearchRepository.deleteQuestion(id);
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
