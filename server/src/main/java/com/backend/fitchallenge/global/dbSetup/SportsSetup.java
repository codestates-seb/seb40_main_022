package com.backend.fitchallenge.global.dbSetup;

import com.backend.fitchallenge.domain.calendar.entity.Sports;
import com.backend.fitchallenge.domain.calendar.repository.SportsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SportsSetup  implements ApplicationListener<ContextRefreshedEvent> {
    private final SportsRepository sportsRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Sports> sportsList = List.of(
                Sports.of("하체", "바벨 백스쿼트"),
                Sports.of("하체", "데드리프트"),
                Sports.of("하체", "프론트 스쿼트"),
                Sports.of("하체", "레그 프레스"),
                Sports.of("하체", "레그 컬"),
                Sports.of("하체", "레그 익스텐션"),
                Sports.of("하체", "덤벨 런지"),
                Sports.of("하체", "스모 데드리프트"),
                Sports.of("하체", "스탠딩 카프 레이즈"),
                Sports.of("하체", "이너 싸이 머신"),
                Sports.of("가슴", "바벨 벤치프레스"),
                Sports.of("가슴", "바벨 인클라인 벤치프레스"),
                Sports.of("가슴", "덤벨 벤치프레스"),
                Sports.of("가슴", "덤벨 인클라인 벤치프레스"),
                Sports.of("가슴", "딥스"),
                Sports.of("가슴", "덤벨 플라이"),
                Sports.of("가슴", "크로스오버"),
                Sports.of("가슴", "체스트 프레스 머신"),
                Sports.of("가슴", "펙덱 플라이 머신"),
                Sports.of("가슴", "푸시업"),
                Sports.of("등", "풀업"),
                Sports.of("등", "바벨 로우"),
                Sports.of("등", "덤벨 로우"),
                Sports.of("등", "펜들레이 로우"),
                Sports.of("등", "머신 로우"),
                Sports.of("등", "랫풀다운"),
                Sports.of("등", "친업"),
                Sports.of("등", "백 익스텐션"),
                Sports.of("등", "굿모닝 엑서사이즈"),
                Sports.of("등", "시티드 케이블 로우"),
                Sports.of("어깨", "바벨 오버헤드 프레스"),
                Sports.of("어깨", "덤벨 숄더 프레스"),
                Sports.of("어깨", "덤벨 레터럴 레이즈"),
                Sports.of("어깨", "덤벨 프론트 레이즈"),
                Sports.of("어깨", "덤벨 슈러그"),
                Sports.of("어깨", "비하인드 넥 프레스"),
                Sports.of("어깨", "페이스 풀"),
                Sports.of("어깨", "핸드스탠드 푸시업"),
                Sports.of("어깨", "케이블 리버스 플라이"),
                Sports.of("어깨", "바벨 업라이트 로우"),
                Sports.of("어깨", "덤벨 벤트오버 레터럴 레이즈"),
                Sports.of("팔", "바벨 컬"),
                Sports.of("팔", "덤벨 컬"),
                Sports.of("팔", "덤벨 삼두 익스텐션"),
                Sports.of("팔", "덤벨 킥백"),
                Sports.of("팔", "덤벨 리스트 컬"),
                Sports.of("팔", "덤벨 해머 컬"),
                Sports.of("팔", "케이블 푸시 다운"),
                Sports.of("팔", "클로즈그립 푸시업"),
                Sports.of("팔", "벤치 딥스"),
                Sports.of("복근", "싯업"),
                Sports.of("복근", "레그 레이즈"),
                Sports.of("복근", "러시안 트위스트"),
                Sports.of("복근", "플랭크"),
                Sports.of("복근", "덤벨 사이드 밴드"),
                Sports.of("복근", "AB 롤아웃"),
                Sports.of("복근", "AB 에어바이크"),
                Sports.of("복근", "브이업"),
                Sports.of("기타", "버피"),
                Sports.of("기타", "쓰러스터"),
                Sports.of("기타", "케틀벨 스윙"),
                Sports.of("기타", "파머스 워크"),
                Sports.of("기타", "웜볼 샷"),
                Sports.of("유산소", "러닝"),
                Sports.of("유산소", "수영"),
                Sports.of("유산소", "사이클링")
        );
        sportsRepository.saveAll(sportsList);
    }
}
