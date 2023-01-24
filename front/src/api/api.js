import service from "./service";

/**
 * API SERVER PATH
 */
const API_URL = {
  articleList: (args) => `api/v1/articles${args}`,
  articleDetail: (args) => `/api/v1/articles/${args}`,
  insertArticle: "/api/v1/articles",
};

/**
 * API 호출 메서드
 */
export const api = {
  getBoardInfo(queryParam) {
    return service.get(`${API_URL.articleList(queryParam)}`);
  },
  getBoardDetail(articleId) {
    return service.get(`${API_URL.articleDetail(articleId)}`);
  },
  postNewArticle(newArticle) {
    return service.post(newArticle, API_URL.insertArticle);
  },
};
