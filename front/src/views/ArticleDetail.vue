<template>
  <HeaderTitle title="게시글 보기" />
  <div class="about">
    <h1>This is an about page{{ $route.params.id }}</h1>
  </div>
</template>

<script>
import HeaderTitle from "@/components/HeaderTitle.vue";

export default {
  name: "ArticleDetail",
  components: {
    HeaderTitle,
  },
  computed: {
    articleId() {
      return parseInt(this.$route.params.id);
    },
  },
};
</script>
