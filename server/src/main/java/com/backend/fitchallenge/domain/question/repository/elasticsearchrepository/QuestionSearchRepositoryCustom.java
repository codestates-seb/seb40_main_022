package com.backend.fitchallenge.domain.question.repository.elasticsearchrepository;

import com.backend.fitchallenge.domain.question.dto.request.PageRequest;
import com.backend.fitchallenge.domain.question.dto.request.QuestionSearch;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.entity.QuestionDocument;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface QuestionSearchRepositoryCustom {

    List<QuestionDocument> getQuestionsOrderByIdOrView(PageRequest pageable, QuestionSearch questionSearch);

    List<QuestionDocument> getQuestionsOrderByAccuracy(PageRequest pageable, QuestionSearch questionSearch);

    void updateView(Long id, Long updatedView) throws IOException;

    void updateQuestion(Long id, Question findQuestion) throws IOException;

    void deleteQuestion(Long id) throws IOException;
}
