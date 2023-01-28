import service from "./service";

/**
 * API SERVER PATH
 */
const API_URL = {
  articles: (args) => process.env.VUE_APP_API_VERSION + `articles${args}`,
  articleId: (args) => process.env.VUE_APP_API_VERSION + `articles/${args}`,
  // 댓글
  insertComment: (args) =>
    process.env.VUE_APP_API_VERSION + `articles/${args}/comments`,

  fileDownload: (args) => process.env.VUE_APP_API_VERSION + `file/${args}`,
};

/**
 * API 호출 메서드
 */
export const articleAPI = {
  /**
   * 검색조건에 따른 게시글리스트, 검색된 게시글 수, 카테고리 리스트 반환하는 API 호출
   * @param {string} 검색조건 queryString
   * @returns BoardVO
   */
  getBoardInfo(queryParam) {
    return service.get(`${API_URL.articles(queryParam)}`);
  },

  /**
   * 게시글 정보 반환하는 API 호출
   * @param {number} 게시글 id
   * @returns ArticleVO
   */
  getArticleDetail(articleId) {
    return service.get(`${API_URL.articleId(articleId)}`);
  },

  /**
   * 새 게시글 정보 POST 요청
   * @param {FormData} newArticle FormData
   * @returns
   */
  postNewArticle(newArticle) {
    return service.post(API_URL.articles(""), newArticle);
  },

  deleteArticle(articleId, formData) {
    return service.delete(`${API_URL.articleId(articleId)}`, formData);
  },
};

export const commentAPI = {
  /**
   *
   * @param {string} articleId
   * @param {string} newComment
   * @returns
   */
  postComment(articleId, newComment) {
    return service.post(API_URL.insertComment(articleId), newComment);
  },
};

export const fileAPI = {
  getFileDownload(fileUuid) {
    return service.get(API_URL.fileDownload(fileUuid));
  },
};
