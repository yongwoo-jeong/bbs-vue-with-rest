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
    "currentPage",
  ],
  computed: {
    totalPages: function () {
      return Math.ceil(this.totalArticles / 10);
    },
  },
  watch: {
    currentPage() {},
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
