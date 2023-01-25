<template>
  <div>
    <a
      v-for="pageNumber in totalPages"
      :key="pageNumber"
      :class="{ active: pageNumber === parseInt(currentPage) }"
    >
      <RouterLink
        :to="{
          name: 'articleList',
          query: {
            startDate,
            endDate,
            categoryId,
            keyword,
            currentPage: pageNumber,
          },
        }"
      >
        {{ pageNumber }}
      </RouterLink>
    </a>
  </div>
</template>

<script>
export default {
  name: "ListPagination",
  props: [
    "totalArticles",
    "queryString",
    "startDate",
    "endDate",
    "categoryId",
    "keyword",
    // 현재 페이지 표시를 위한 prop
    "currentPage",
  ],
  computed: {
    /**
     * 검색된 게시글 통해 총 페이지 개수 계산
     */
    totalPages: function () {
      return Math.ceil(this.totalArticles / 10);
    },
  },
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
}
.active * {
  color: red;
}
</style>
