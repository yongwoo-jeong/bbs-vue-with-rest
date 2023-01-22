import axios from "axios";

const service = axios.create({
  baseURL: process.env.VUE_APP_SERVER_URL,
  timeout: 1000,
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
});

export default {
  async get(...options) {
    try {
      const res = await service.get(...options);
      return res;
    } catch (e) {
      return e;
    }
  },
};
