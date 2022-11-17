package com.backend.fitchallenge.domain.workout.service;

import com.backend.fitchallenge.domain.workout.entity.Calendar;
import com.backend.fitchallenge.domain.workout.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static java.text.DateFormat.DEFAULT;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarService {

    private final CalendarRepository calendarRepository;

    /**
     * Calendar 추가하는 방식 결정 필요
     * - 추천: 스케쥴링
     */
    public void addCalendar(int year, int month) {
        if(year == DEFAULT){
            year = LocalDateTime.now().getYear();
            month = LocalDateTime.now().getMonth().getValue();
        }

        if(calendarRepository.getCount(year, month) == 0){
            List<Calendar> calendarList = new ArrayList<>();
            int lengthOfMonth = YearMonth.of(year, month).lengthOfMonth();

            for(int day = 1; day <= lengthOfMonth; day++){
                Calendar calendar = Calendar.builder()
                        .year(year)
                        .month(month)
                        .day(day)
                        .build();

                calendarList.add(calendar);
            }
            calendarRepository.saveAll(calendarList);
        }
    }
}
