package com.backend.fitchallenge.domain.question.repository.elasticsearchrepository;

import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.entity.QuestionDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface QuestionSearchRepository extends ElasticsearchRepository<QuestionDocument, Long>, QuestionSearchRepositoryCustom {
}
