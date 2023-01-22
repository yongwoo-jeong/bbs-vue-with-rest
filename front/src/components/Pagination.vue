<template>
  <div>
    <a v-for="pageNumber in totalPages" :key="pageNumber">{{ pageNumber }}</a>
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
