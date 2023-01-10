import { createRouter, createWebHistory } from "vue-router";
const routes = [
  {
    path: "/",
    redirect: "/articles",
  },
  {
    path: "/articles",
    component: () => import("@/views/MainBoard"),
  },
  {
    path: "/articles/:id",
    component: () => import("@/views/ArticleDetail"),
    name: "articleDetail",
    props: true,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
