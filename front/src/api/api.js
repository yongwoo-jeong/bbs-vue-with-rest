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
  /**
   * 검색조건에 따른 게시글리스트, 검색된 게시글 수, 카테고리 리스트 반환하는 API 호출
   * @param {String} 검색조건 queryString
   * @returns BoardVO
   */
  getBoardInfo(queryParam) {
    return service.get(`${API_URL.articleList(queryParam)}`);
  },

  /**
   * 게시글 정보 반환하는 API 호출
   * @param {Number} 게시글 id
   * @returns ArticleVO
   */
  getArticleDetail(articleId) {
    return service.get(`${API_URL.articleDetail(articleId)}`);
  },

  /**
   * 새 게시글 정보 POST 요청
   * @param {FormData} newArticle FormData
   * @returns
   */
  postNewArticle(newArticle) {
    return service.post(newArticle, API_URL.insertArticle);
  },
};
