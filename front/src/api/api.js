import service from "./service";

const API_URL = {
  articleList: "api/v1/articles",
  articleDetail: (args) => `/api/v1/articles/${args}`,
};

export const api = {
  getBoardInfo() {
    return service.get(`${API_URL.articleList}`);
  },
  getBoardDetail(articleId) {
    return service.get(`${API_URL.articleDetail(articleId)}`);
  },
};
