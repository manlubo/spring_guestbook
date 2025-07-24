## 2025-07-23

- QueryDSL 설정 완료 (`querydsl-jpa:5.1.0:jakarta` 기반)
- BooleanBuilder, JPQLQuery, PathBuilder 등 core 클래스 사용 확인
- `querydsl-core` 생략 가능 여부 테스트 → 정상 작동
- `jakarta.annotation-api` 생략 가능 여부 검토 → Q클래스 생성 이상 없음
- `GuestbookServiceImpl`에서 `GuestbookService` 주입 실패 오류 발생
  - 원인: 테스트 클래스에서 구현체를 직접 대상으로 지정하여 ApplicationContext 생성 실패
  - 해결: `@Service` 누락 여부 확인 및 테스트 클래스 명확히 분리 (`GuestbookServiceTest` 등)

## 2025-07-24

- PageRequestDTO, PageRequestDTO 생성
- 검색 기능 구현
