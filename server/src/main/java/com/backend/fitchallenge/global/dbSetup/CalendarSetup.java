package com.backend.fitchallenge.global.dbSetup;

import com.backend.fitchallenge.domain.calendar.entity.Calendar;
import com.backend.fitchallenge.domain.calendar.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static java.text.DateFormat.DEFAULT;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CalendarSetup implements ApplicationListener<ContextRefreshedEvent> {
    /**
     * [캘린더 초기화]
     * ApplicationListener<ContextRefreshedEvent>: ApplicationContext가 초기화된 후 onApplicationEvent 메서드를 실행합니다.
     * 특정 년도와 월에 해당하는 일수만큼 Calendar 객체를 생성하고 repository에 저장합니다.
     */
    private final CalendarRepository calendarRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (int year = 2020; year <= 2030; year++) {
            for (int month = 1; month <=12; month++) {
                addCalendar(year, month);
            }
        }
    }

    public void addCalendar(int year, int month) {
        if(year == DEFAULT){
            year = LocalDateTime.now().getYear();
            month = LocalDateTime.now().getMonth().getValue();
        }

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
