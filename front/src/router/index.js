import { createRouter, createWebHistory } from "vue-router";
/**
 * 앱 라우팅
 * - /api/버전{v1}/
 * GET /articles - 전체 게시글 리스트 페이지 
 * POST /articles - 새 게시글 삽입  ( articleId는 autoIncrement) // 파일은 어떻게 처리할것인가
 * GET /articles/{id} - id 값에 해당하는 게시글 상세보기
 * PUT /articles/:id - id 값에 해당하는 게시글 수정 // 파일도 수정가능
 * DELETE /articles/:id - id 값에 해당하는 게시글 삭제. 관련 파일, 댓글도 삭제
 * GET /articles/:id/comments - id 값에 해당하는 게시글 댓글 조회
 * POST /articles/:id/comments - 새 댓글 등록

 */
const routes = [
  {
    path: "/",
    redirect: "/articles",
  },
  {
    path: "/articles",
    component: () => import("@/views/ArticleList"),
    name: "articleList",
  },
  {
    path: "/articles/:id",
    component: () => import("@/views/ArticleDetail"),
    name: "articleDetail",
    props: true,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
