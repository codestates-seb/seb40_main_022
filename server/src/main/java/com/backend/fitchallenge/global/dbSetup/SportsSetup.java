package com.backend.fitchallenge.global.dbSetup;

import com.backend.fitchallenge.domain.sports.entity.Sports;
import com.backend.fitchallenge.domain.sports.repository.SportsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SportsSetup  implements ApplicationListener<ContextRefreshedEvent> {
    private final SportsRepository sportsRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Sports> sportsList = List.of(
                Sports.create("하체", "바벨 백스쿼트"),
                Sports.create("하체", "데드리프트"),
                Sports.create("하체", "프론트 스쿼트"),
                Sports.create("하체", "레그 프레스"),
                Sports.create("하체", "레그 컬"),
                Sports.create("하체", "레그 익스텐션"),
                Sports.create("하체", "덤벨 런지"),
                Sports.create("하체", "스모 데드리프트"),
                Sports.create("하체", "스탠딩 카프 레이즈"),
                Sports.create("하체", "이너 싸이 머신"),
                Sports.create("가슴", "바벨 벤치프레스"),
                Sports.create("가슴", "바벨 인클라인 벤치프레스"),
                Sports.create("가슴", "덤벨 벤치프레스"),
                Sports.create("가슴", "덤벨 인클라인 벤치프레스"),
                Sports.create("가슴", "딥스"),
                Sports.create("가슴", "덤벨 플라이"),
                Sports.create("가슴", "크로스오버"),
                Sports.create("가슴", "체스트 프레스 머신"),
                Sports.create("가슴", "펙덱 플라이 머신"),
                Sports.create("가슴", "푸시업"),
                Sports.create("등", "풀업"),
                Sports.create("등", "바벨 로우"),
                Sports.create("등", "덤벨 로우"),
                Sports.create("등", "펜들레이 로우"),
                Sports.create("등", "머신 로우"),
                Sports.create("등", "랫풀다운"),
                Sports.create("등", "친업"),
                Sports.create("등", "백 익스텐션"),
                Sports.create("등", "굿모닝 엑서사이즈"),
                Sports.create("등", "시티드 케이블 로우"),
                Sports.create("어깨", "바벨 오버헤드 프레스"),
                Sports.create("어깨", "덤벨 숄더 프레스"),
                Sports.create("어깨", "덤벨 레터럴 레이즈"),
                Sports.create("어깨", "덤벨 프론트 레이즈"),
                Sports.create("어깨", "덤벨 슈러그"),
                Sports.create("어깨", "비하인드 넥 프레스"),
                Sports.create("어깨", "페이스 풀"),
                Sports.create("어깨", "핸드스탠드 푸시업"),
                Sports.create("어깨", "케이블 리버스 플라이"),
                Sports.create("어깨", "바벨 업라이트 로우"),
                Sports.create("어깨", "덤벨 벤트오버 레터럴 레이즈"),
                Sports.create("팔", "바벨 컬"),
                Sports.create("팔", "덤벨 컬"),
                Sports.create("팔", "덤벨 삼두 익스텐션"),
                Sports.create("팔", "덤벨 킥백"),
                Sports.create("팔", "덤벨 리스트 컬"),
                Sports.create("팔", "덤벨 해머 컬"),
                Sports.create("팔", "케이블 푸시 다운"),
                Sports.create("팔", "클로즈그립 푸시업"),
                Sports.create("팔", "벤치 딥스"),
                Sports.create("복근", "싯업"),
                Sports.create("복근", "레그 레이즈"),
                Sports.create("복근", "러시안 트위스트"),
                Sports.create("복근", "플랭크"),
                Sports.create("복근", "덤벨 사이드 밴드"),
                Sports.create("복근", "AB 롤아웃"),
                Sports.create("복근", "AB 에어바이크"),
                Sports.create("복근", "브이업"),
                Sports.create("기타", "버피"),
                Sports.create("기타", "쓰러스터"),
                Sports.create("기타", "케틀벨 스윙"),
                Sports.create("기타", "파머스 워크"),
                Sports.create("기타", "웜볼 샷"),
                Sports.create("유산소", "러닝"),
                Sports.create("유산소", "수영"),
                Sports.create("유산소", "사이클링")
        );
        sportsRepository.saveAll(sportsList);
    }
}
