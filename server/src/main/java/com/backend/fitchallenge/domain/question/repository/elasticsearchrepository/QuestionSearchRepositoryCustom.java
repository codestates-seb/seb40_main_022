package com.backend.fitchallenge.domain.question.repository.elasticsearchrepository;

import com.backend.fitchallenge.domain.question.dto.request.PageRequest;
import com.backend.fitchallenge.domain.question.dto.request.QuestionSearch;
import com.backend.fitchallenge.domain.question.dto.request.QuestionUpdateVO;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.entity.QuestionDocument;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface QuestionSearchRepositoryCustom {

    List<QuestionDocument> searchByQuery(PageRequest pageable, QuestionSearch questionSearch);

    void updateView(Long id, Long updatedView) throws IOException;

    void updateQuestion(Long id, Question findQuestion) throws IOException;

    void deleteQuestion(Long id) throws IOException;
}
