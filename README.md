![배너](https://i.postimg.cc/XJ9tJbBT/Carrier.png)

# AI 기반 일정 및 업무 관리 서비스 (Carrier)

AI가 일정, 메일, 회고, 회의를 간편화해 **업무를 간편하게 만드는 서비스**입니다.  
**사회초년생의 성장**을 돕고, **통합 플랫폼에서 모든 업무를 해결**하도록 설계되었습니다.

프로젝트 기간 - 2025.01. ~ 2025.04.

[운영중인 서비스 바로가기](https://www.jing5s.kro.kr)  
[API Docs 바로가기](https://anys34.notion.site/Carrier-API-1852a6e3eee2807ea16dedbef1691ab9?pvs=4)

## 기능

**이메일**
- 이메일 가져오기, 본문 요약 처리, 일정 자동 추가 기능

**캘린더 및 투두**
- 일정 및 할 일의 생성, 조회, 수정, 삭제 기능 제공

**사용자**
- 하루 일정 요약 제공 및 프로필 사진 등록 기능 지원

**녹음 요약**
- 녹음 음성을 텍스트로 변환하고 핵심 내용 요약

**일기**
- 일기 작성 기능 및 키워드 기반 주제 추천 기능 지원

## 기술 도입


![Java](https://img.shields.io/badge/Java-17-007396?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-6DB33F?logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql)

### CQRS 패턴 사용
CQRS 패턴을 적용하여서 조회 로직과 명령 로직을 분리하여 도메인별 책임 명확화  
→ 도메인 계층의 복잡도 감소, 유지보수 효율성 증가

```text
org.example.carrier
├── global
│   ├── annotation
│   ├── config
│   ├── entity
│   ├── error
│   ├── feign
│   ├── interceptor
│   ├── security
│   └── utils
└── domain
    └── calendar
        ├── domain
        │   ├── Schedule.java
        │   └── repository
        │       └── ScheduleRepository.java
        ├── service
        │   ├── CommandScheduleService.java
        │   └── QueryScheduleService.java
        ├── presentation
        │   ├── CommandScheduleController.java
        │   └── QueryScheduleController.java
        │   └── dto
        │       ├── request
        │       └── response
        └── exception
        └── ... (category, diary, mail, meet, todo, user)
```

@CustomService라는 annotation을 만들어 Query/Command의 의도를
명시적으로 표현 [코드 바로가기](https://github.com/Jing5s/Carrier_Server/blob/main/src/main/java/org/example/carrier/global/annotation/CustomService.java)

### record를 사용한 개발
불변 객체 record를 기반으로 REST API와 Feign 응답 DTO를 설계  
→ 가독성과 유지보수성 확보 및 클래스 대비 코드 간결화

## 트러블 슈팅

### Gmail API를 다루며 고민한 기술 선택 – MongoDB & Kotlin

#### As-Is : Gmail API의 문제들
- null 필드가 많아 정규화된 RDB 스키마로 표현이 어려움
- 메일마다 다른 구조임으로 필드 유무를 확인 해야함
- 중첩되는 JSON구조로 인해 파싱 로직 복잡도 증가

#### To-Be : MongoDB 사용 & Kotlin 사용
- 응답 받은 JSON그대로 변경 없이 저장 가능
- 조건 기반 쿼리를 사용해 필드 유무 판별 유리
- kotlin의 null-safe특성을 이용하여 로직 구성 용이

#### 회고 : data class를 활용하여 다양한 응답 타입에 유연한 모델링
- 처음 기술을 선택할 때 데이터의 구조 서비스의 특성을
  고려하여 기술을 선택하는 것이 중요하다는 것을 깨달음
- MSA 설계는 각 도메인 별로 기술 스택을 자유롭게 선택 가능  
  → 확장성과 유지보수성 면에서 유리한 구조를 체감

## ERD
![erd](https://i.postimg.cc/2S6dTNmh/image.png)

## 팀원
|                             Backend                             |                               Frontend                               |                               Frontend                               |                            Frontend                             |                            Designer                             |
|:---------------------------------------------------------------:|:--------------------------------------------------------------------:|:--------------------------------------------------------------------:|:---------------------------------------------------------------:|:---------------------------------------------------------------:|
| ![image](https://avatars.githubusercontent.com/u/127452485?v=4) | ![image](https://avatars.githubusercontent.com/u/127070837?v=4) | ![image](https://avatars.githubusercontent.com/u/126847458?v=4) | ![image](https://avatars.githubusercontent.com/u/82251632?v=4) | ![image](https://avatars.githubusercontent.com/u/119480957?v=4) |
|                [안예성](https://github.com/anys34)                 |                 [이민준](https://github.com/MinjuN07)                  |                  [추성우](https://github.com/chooseongwoo)                  |               [이승현](https://github.com/Jamkris)               |                [최성훈](https://github.com/seonghoon07)                |
