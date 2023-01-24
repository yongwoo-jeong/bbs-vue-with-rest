## 스터디 4주차  :

#### 뷰, 스프링부트를 이용해 REST 아키텍쳐 스타일의 게시판 만들기
#### 뷰, 스프링부트, 마이바티스, 마리아디비, 깃

명세
- API_BASE_URL/api/v1/
- **GET /articles** - 전체 게시글 리스트 페이지 
- **POST /articles** - 새 게시글 삽입  ( articleId는 autoIncrement) // 파일은 어떻게 처리할것인가
- **GET /articles/create** - 새 게시글 등록 FORM 페이지 
- **GET /articles/:id** - id 값에 해당하는 게시글 상세보기
- **PUT /articles/:id** - id 값에 해당하는 게시글, 파일 수정
- **DELETE /articles/:id** - id 값에 해당하는 게시글 삭제. 관련 파일, 댓글도 삭제
- **GET /articles/:id/comments** - id 값에 해당하는 게시글 댓글 조회
- **POST /articles/:id/comments** - 새 댓글 등록


- [ ] 기본 게시판 동작은 2주차와 동일하게 구현 (CRUD)
  - [x] 게시판 전체 리스트 보기(루트)
  - [x] 게시판 상세페이지 
  - [ ] 검색 기능 (페이지 이동, 게시글 삽입, 삭제, 수정해도 유지)
  - [x] 게시글 등록
  - [ ] 댓글 등록 
  - [ ] 게시글 수정 (비밀번호검증)
  - [ ] 게시글 삭제 (비밀번호검증)

유의사항

- [ ] **[웹 API 디자인 모범사례](https://learn.microsoft.com/ko-kr/azure/architecture/best-practices/api-design)** 참고하여 설계하기
  - [ ] 리소스 URI는 동사(리소스에 대한 작업)가 아닌 명사(리소스)를 기반 
  - [ ] URI에 일관적인 명명 규칙을 적용. 컬렉션을 참조하는 URI에 대해 복수 명사를 사용
  - [ ] URI는 우선 직관적이도록 설계
  - [ ] HTTP 응답 코드 준수
  - [ ]



