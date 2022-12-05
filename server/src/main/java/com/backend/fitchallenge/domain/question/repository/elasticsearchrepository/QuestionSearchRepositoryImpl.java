package com.backend.fitchallenge.domain.question.repository.elasticsearchrepository;

import com.backend.fitchallenge.domain.question.dto.request.PageRequest;
import com.backend.fitchallenge.domain.question.dto.request.PageRequestTemp;
import com.backend.fitchallenge.domain.question.dto.request.QuestionSearch;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.entity.QuestionDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@RequiredArgsConstructor
@Component
@Slf4j
public class QuestionSearchRepositoryImpl implements QuestionSearchRepositoryCustom {

    private final ElasticsearchOperations elasticsearchOperations;
    private final RestHighLevelClient client;

    @Override
    public List<QuestionDocument> getQuestionsOrderByIdOrView(PageRequestTemp pageable, QuestionSearch questionSearch) {

        //Bool Query 는 쿼리들을 조합하는 데 사용
        BoolQueryBuilder boolQuery = boolQuery();

        //태그가 검색어에 포함된 경우
        if(questionSearch.getTag() != null && !questionSearch.getTag().isBlank()) {
            //bool query 에 필터 추가
            boolQuery.filter(termQuery("tag", questionSearch.getTag()));
        }

        QueryBuilder defaultQueryBuilder;
        QueryBuilder andMatchBuilder;

        //(태그를 제외한) 검색어가 없는 경우
        if(questionSearch.getQuery() == null || questionSearch.getQuery().isBlank()) {

            defaultQueryBuilder = QueryBuilders.matchAllQuery();
            boolQuery.should(defaultQueryBuilder);
        }

        //검색어가 있는 경우
        else {
            String query = questionSearch.getQuery();

            andMatchBuilder = QueryBuilders.multiMatchQuery(query)
                    .field("title")
                    .field("title.nori")
                    .field("title.ngram")
                    .field("content")
                    .field("content.nori")
                    .field("content.ngram")
                    .prefixLength(3)
                    .operator(Operator.AND);

            boolQuery.must(andMatchBuilder);
        }

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .withPageable(pageable.of())
                .withSorts(SortBuilders.fieldSort(pageable.getSort()).order(SortOrder.DESC))
                .build();

        SearchHits<QuestionDocument> hits = elasticsearchOperations.search(query, QuestionDocument.class);

        return hits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDocument> getQuestionsOrderByAccuracy(PageRequestTemp pageable, QuestionSearch questionSearch) {

        //Bool Query 는 쿼리들을 조합하는 데 사용
        BoolQueryBuilder boolQuery = boolQuery();

        log.info("questionSearch query: {}", questionSearch.getQuery());
        log.info("questionSearch tag: {}", questionSearch.getTag());

        //태그가 검색어에 포함된 경우
        if(!questionSearch.getTag().isEmpty()) {
            //bool query 에 필터 추가
            boolQuery.filter(termQuery("tag", questionSearch.getTag()));
        }

        QueryBuilder defaultQueryBuilder;
        QueryBuilder andMatchBuilder;
        QueryBuilder titleMatchPhraseBuilder;
        QueryBuilder contentMatchPhraseBuilder;

        //(태그를 제외한) 검색어가 없는 경우
        if(questionSearch.getQuery().isEmpty()) {

            defaultQueryBuilder = QueryBuilders.matchAllQuery();
            boolQuery.should(defaultQueryBuilder);
        }

        //검색어가 있는 경우
        else {
            String query = questionSearch.getQuery();
            defaultQueryBuilder = QueryBuilders.multiMatchQuery(query)
                    .field("title", 3)
                    .field("title.nori", 3)
                    .field("title.ngram", 3)
                    .field("content")
                    .field("content.nori")
                    .field("content.ngram")
                    .prefixLength(3);
            andMatchBuilder = QueryBuilders.multiMatchQuery(query)
                    .field("title", 3)
                    .field("title.nori", 3)
                    .field("title.ngram", 3)
                    .field("content")
                    .field("content.nori")
                    .field("content.ngram")
                    .prefixLength(3)
                    .operator(Operator.AND);
            titleMatchPhraseBuilder = QueryBuilders.matchPhraseQuery("title", query).boost(6);
            contentMatchPhraseBuilder = QueryBuilders.matchPhraseQuery("content", query).boost(3);

            //bool query의 should를 통해 검색 점수를 조정
            boolQuery.should(defaultQueryBuilder)
                    .should(andMatchBuilder)
                    .should(titleMatchPhraseBuilder)
                    .should(contentMatchPhraseBuilder);
        }

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .withPageable(pageable.of())
                .build();

        SearchHits<QuestionDocument> hits = elasticsearchOperations.search(query, QuestionDocument.class);

        return hits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public void updateView(Long id, Long updatedView) throws IOException {
        UpdateByQueryRequest updateRequest = new UpdateByQueryRequest("question");

        Map<String, Object> params = new HashMap<>();
        params.put("view", updatedView);

        updateRequest.setQuery(new TermQueryBuilder("id", String.valueOf(id)));
        updateRequest.setScript(new Script(
                ScriptType.INLINE, "painless",
                "ctx._source.view = params.view;",  params
        ));
        client.updateByQuery(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void updateQuestion(Long id, Question question) throws IOException {
        UpdateByQueryRequest updateRequest = new UpdateByQueryRequest("question");

        Map<String, Object> params = new HashMap<>();
        params.put("title", question.getTitle());
        params.put("content", question.getContent());
        params.put("tag", question.getQuestionTag().getValue());

        updateRequest.setQuery(new TermQueryBuilder("id", String.valueOf(id)));
        updateRequest.setScript(new Script(
                ScriptType.INLINE, "painless",
                   "ctx._source.title = params.title; " +
                        "ctx._source.content = params.content; " +
                        "ctx._source.tag = params.tag", params
        ));
        client.updateByQuery(updateRequest, RequestOptions.DEFAULT);
    }


    @Override
    public void deleteQuestion(Long id) throws IOException {
        DeleteByQueryRequest deleteRequest = new DeleteByQueryRequest("question");

        deleteRequest.setQuery(new TermQueryBuilder("id", String.valueOf(id)));
        client.deleteByQuery(deleteRequest, RequestOptions.DEFAULT);
    }
}
