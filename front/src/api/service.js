import axios from "axios";
import { SERVER_URL } from "./config";

const service = axios.create({
  baseURL: SERVER_URL,
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
