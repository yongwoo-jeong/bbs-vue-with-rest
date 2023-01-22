import service from "./service";

const API_URL = {
  articleList: (args) => `api/v1/articles${args}`,
  articleDetail: (args) => `/api/v1/articles/${args}`,
};

export const api = {
  getBoardInfo(queryParam) {
    return service.get(`${API_URL.articleList(queryParam)}`);
  },
  getBoardDetail(articleId) {
    return service.get(`${API_URL.articleDetail(articleId)}`);
  },
};
