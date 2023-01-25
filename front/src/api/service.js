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
   * TODO 에러 발생한 경우 에러페이지로 유도
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

  /**
   * 새 게시글 등록 POST 요청을 보내는 메서드
   * TODO 에러 발생한 경우 에러페이지로 유도
   * @param {FormData} 새 게시글 정보가 담긴 폼데이터
   * @param {string} POST 요청 할 API URL
   * @returns
   */
  async post(url, formData) {
    try {
      const res = await axiosInstance.post(url, formData, {
        headers: {
          "Content-Type": `multipart/form-data`,
        },
      });
      return res;
    } catch (e) {
      console.log(e);
      return e;
    }
  },
};
