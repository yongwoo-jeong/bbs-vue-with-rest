export const SERVER_URL = "http://localhost:8080/";

export const API_URL = {
  articleList: "api/v1/articles",
  articleDetail: (args) => `/api/v1/articles/${args}`,
};
