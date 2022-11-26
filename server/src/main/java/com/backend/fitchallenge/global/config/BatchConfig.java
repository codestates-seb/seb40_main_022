package com.backend.fitchallenge.global.config;

import com.backend.fitchallenge.global.batch.RankTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final RankTasklet rankTasklet;

    @Bean
    public Job calRankJob(){
        return jobBuilderFactory.get("calRankJob")
                .start(dailyPointStep())
                .build();
    }

    @Bean
    @JobScope
    public Step dailyPointStep(){
        return stepBuilderFactory.get("dailyPointStep")
                .tasklet(rankTasklet)
                .build();
    }
}
