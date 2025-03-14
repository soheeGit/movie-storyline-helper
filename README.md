# 영화 스토리라인 헬퍼 (movie-storyline-helper)

영화 스토리라인 헬퍼는 AI를 활용하여 사용자가 입력한 장르와 아이디어를 바탕으로 영화 제목과 줄거리를 생성해주는 웹 애플리케이션입니다.

## 주요 기능

- 사용자 입력 기반 영화 제목 및 줄거리 생성
- 심층적인 스토리 분석 및 확장
- 영화 컨셉에 맞는 이미지 생성
- 직관적인 웹 인터페이스

## 기술 스택

- **Java**: 백엔드 로직 구현
- **Jakarta EE**: 서블릿 기반 웹 애플리케이션 구현
- **Maven**: 프로젝트 의존성 관리
- **Jackson**: JSON 파싱 및 처리
- **Together API**: AI 모델 호출 (텍스트 및 이미지 생성)
- **JSP**: 웹 페이지 렌더링
- **dotenv-java**: 환경 변수 관리

## 프로젝트 구조

```
movie-storyline-helper/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/moviestorylinehelper/
│   │   │       ├── controller/
│   │   │       │   ├── AnswerController.java
│   │   │       │   ├── Controller.java
│   │   │       │   └── RootController.java
│   │   │       ├── model/
│   │   │       │   ├── dto/
│   │   │       │   └── repository/
│   │   │       │       └── TogetherRepository.java
│   │   │       └── service/
│   │   │           └── TogetherService.java
│   │   ├── resources/
│   │   │   └── .env
│   │   └── webapp/
│   │       └── WEB-INF/
│   │           └── views/
│   │               ├── index.jsp
│   │               └── result.jsp
└── pom.xml
```

### 필수 조건

- Java 17 이상
- Maven
- Together API 키

### 로컬 환경 설정

1. 프로젝트 클론
```bash
git clone https://github.com/soheeGit/movie-storyline-helper.git
cd movie-storyline-helper
```

2. 환경 변수 설정
프로젝트 src/main/resources 디렉토리에 `.env` 파일을 생성하고 다음 내용을 추가하세요:
```
TOGETHER_API_KEY=your_together_api_key
```

3. 빌드 및 실행
```bash
mvn clean package
mvn tomcat7:run
```

4. 브라우저에서 `http://localhost:8080` 접속

### Render 배포

1. Render에서 새 웹 서비스 생성
2. 저장소 연결 및 Maven 설정
3. 환경 변수 설정에서 모든 `.env` 파일 변수를 추가
4. 빌드 명령어 설정: `mvn clean package`
5. 시작 명령어 설정: `java -jar target/movie-storyline-helper-1.0-SNAPSHOT.jar`

## 주의사항

- Render 배포 시 모든 환경 변수를 대시보드에 설정해야 합니다.
- 로컬 개발 환경과 Render 환경의 차이로 인해 발생할 수 있는 문제에 주의하세요.

## 예시
<img width="592" alt="스크린샷 2025-03-14 오후 9 51 12" src="https://github.com/user-attachments/assets/3f30ad34-5e29-4cab-a38f-cfbf5f8ab7c2" />
