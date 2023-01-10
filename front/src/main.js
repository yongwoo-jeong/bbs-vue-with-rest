import { createApp, VueElement } from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";

// createApp(App).use(router).mount("#app");

const app = createApp(App);
app.config.globalProperties.axios = axios;
app.use(router).mount("#app");
