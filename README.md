## 주요 기능

## 해당 프로젝트는 학습 및 구조 연습을 목적으로 하기 때문에, 복잡도를 줄이고 가독성을 높이기 위해 일부 인터페이스 구현을 생략하였으며, DTO 등의 단순한 데이터 구조는 record 형태로 컨트롤러 내부에 정의하였습니다.

### Account (계정)
- `GET /api/accounts/{id}` : ID로 계정 조회
- 테스트 클래스: `AccountControllerTest`

### Board (게시판)
- `GET /boards/{id}` : 게시물 단건 조회
- `GET /boards/creator/{accountId}` : 계정 기준 게시물 목록
- `POST /boards` : 게시물 생성
- 테스트 클래스: `BoardControllerTest`

### Auth (인증)
- `GET /auth/token?email={email}` : 이메일 기반 JWT 토큰 발급
- 테스트 클래스: `AuthControllerTest`

### 인증 & 보안

- `JwtTokenProvider` 기반 JWT 발급 및 필터 적용
- 테스트 환경에서는 `TestJwtConfig`로 테스트용 JWT 처리
- 인증 필요시 Authorization 헤더에 Bearer 토큰 전달

### 권한 처리

- 계정 도메인에 `isAdmin: boolean` 필드 존재
- Spring Security의 `@PreAuthorize`를 통해 관리자 전용 기능 제어

### 예외 처리

- 전역 예외 처리기를 통해 `ApiResponse<T>` 형식으로 일관된 에러 응답 제공
- 커스텀 예외 클래스(`BusinessException`)를 기반으로 도메인별 예외 처리 가능


### 테스트

- `@SpringBootTest + @AutoConfigureMockMvc` 사용
- 주요 테스트 클래스:
- `AuthControllerTest`
- `AccountControllerTest`
- `BoardControllerTest`
- `TestJwtConfig`로 통합 테스트 환경 구성

### 기술 스택

- Java 21
- Spring Boot 3.2.5
- Spring Web, Spring Security, Spring Data JPA
- H2 In-Memory DB (테스트용)
- Gradle 8.14
- JUnit 5, Mockito, MockMvc
