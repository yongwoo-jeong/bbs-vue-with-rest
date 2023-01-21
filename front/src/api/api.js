import { API_URL } from "./config";
import service from "./service";

export const api = {
  getBoardInfo() {
    return service.get(`${API_URL.articleList}`);
  },
  getBoardDetail(articleId) {
    return service.get(`${API_URL.articleDetail(articleId)}`);
  },
};
