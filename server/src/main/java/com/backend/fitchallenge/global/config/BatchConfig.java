//package com.backend.fitchallenge.global.config;
//
//import com.backend.fitchallenge.domain.member.entity.Member;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.database.JpaItemWriter;
//import org.springframework.batch.item.database.JpaPagingItemReader;
//import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
//import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.persistence.EntityManagerFactory;
//
//@Configuration
//@RequiredArgsConstructor
//public class BatchConfig {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final EntityManagerFactory entityManagerFactory;
//
//    @Bean
//    public Job calDailyRankJob() throws Exception{
//        return jobBuilderFactory.get("calDailyRankJob")
//                .start()
//                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step calDailyRankStep() throws Exception{
//        return stepBuilderFactory.get("calDailyRankStep")
//                .<Member, Member>chunk(10)
//                .reader()
//                .processor()
//                .writer()
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public JpaPagingItemReader<Member> memberPointReader() throws Exception{
//
//        return new JpaPagingItemReaderBuilder<Member>()
//                .pageSize(10)
//                .queryString("SELECT p.memberActivity.point FROM Member p") // jpql 사용해서 멤버 포인트 값 가져오기
//                .entityManagerFactory(entityManagerFactory)
//                .name("MemberPointReader")
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public JpaPagingItemReader<Member> challengeResultReader() throws Exception{
//
//        return new JpaPagingItemReaderBuilder<Member>()
//                .pageSize(10)
//                .queryString("SELECT p.")
//                .entityManagerFactory(entityManagerFactory)
//                .name("");
//    }
//
//
//
//    @Bean
//    @StepScope
//    public JpaItemWriter<Member> writer(){
//        return new JpaItemWriterBuilder<Member>()
//                .entityManagerFactory(entityManagerFactory)
//                .build();
//    }
//}
///*
//1. 운동 여부를 가져온다. -> + 1점여부
//2. 하루단위 시작시간과 끝시간 가져와서 -
//3. 챌린지에서 오늘이 마지막날인 챌린지. 필터링 -> 승패여부.  7일단위
//
// */