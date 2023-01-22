<template>
  <div>
    <a v-for="pageNumber in totalPages" :key="pageNumber">
      <RouterLink
        :to="{ name: 'articleList', query: { currentPage: pageNumber } }"
      >
        {{ pageNumber }}
      </RouterLink>
    </a>
  </div>
</template>

<script>
export default {
  name: "ListPagination",
  props: ["totalArticles"],
  data() {
    return {};
  },
  async created() {},
  computed: {
    totalPages: function () {
      return Math.ceil(this.totalArticles / 10);
    },
  },
};
</script>
