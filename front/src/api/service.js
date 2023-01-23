import axios from "axios";

/**
 * .env 에 존재하는 API 서버 패스를 base로 하는 axios 객체
 */
const service = axios.create({
  baseURL: process.env.VUE_APP_SERVER_URL,
  timeout: 1000,
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
});

export default {
  /**
   * GET 요청 메서드
   * @param  {String} GET 요청을 보낼 주소
   * @returns
   */
  async get(...options) {
    try {
      const res = await service.get(...options);
      return res;
    } catch (e) {
      return e;
    }
  },
  /**
   *
   * @param  {...any} options
   * @returns
   */
  async post(newArticle, ...options) {
    try {
      const res = await service.post(...options, newArticle);
      return res;
    } catch (e) {
      return e;
    }
  },
};
