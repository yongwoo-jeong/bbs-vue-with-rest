import axios from "axios";

/**
 * .env 에 존재하는 API 서버 패스를 base로 하는 axios 객체
 */
const axiosInstance = axios.create({
  baseURL: process.env.VUE_APP_SERVER_URL,
  timeout: 1000,
});

export default {
  /**
   * GET 요청 메서드
   * @param  {String} GET 요청을 보낼 주소
   * @returns
   */
  async get(url) {
    try {
      const res = await axiosInstance.get(url);
      return res;
    } catch (e) {
      return e;
    }
  },

  async post(newArticle, url) {
    try {
      const res = await axiosInstance.post(url, newArticle, {});
      return res;
    } catch (e) {
      console.log(e);
      return e;
    }
  },
};
