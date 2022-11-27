package com.backend.fitchallenge.global.config;

import com.backend.fitchallenge.global.batch.tasklet.DailyPointTasklet;
import com.backend.fitchallenge.global.batch.tasklet.NotificationTasklet;
import com.backend.fitchallenge.global.batch.tasklet.WeeklyPointTasklet;
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
    private final DailyPointTasklet dailyPointTasklet;
    private final WeeklyPointTasklet weeklyPointTasklet;
    private final NotificationTasklet notificationTasklet;

    @Bean
    public Job calRankJob(){
        return jobBuilderFactory.get("calRankJob")
                .start(dailyPointStep())
                .next(weeklyPointStep())
                .build();
    }
    @Bean
    @JobScope
    public Step dailyPointStep(){
        return stepBuilderFactory.get("dailyPointStep")
                .tasklet(dailyPointTasklet)
                .build();
    }

    @Bean
    @JobScope
    public Step weeklyPointStep(){
        return stepBuilderFactory.get("weeklyPointStep")
                .tasklet(weeklyPointTasklet)
                .build();
    }

    @Bean
    public Job notificationJob() {
        return jobBuilderFactory.get("notificationJob")
                .start(notificationStep())
                .build();
    }
    @Bean
    @JobScope
    public Step notificationStep() {
        return stepBuilderFactory.get("notificationStep")
                .tasklet(notificationTasklet)
                .build();
    }
}
