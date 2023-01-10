<template>
  <HeaderTitle title="게시글 보기" />
  <div></div>
</template>

<script>
import HeaderTitle from "@/components/HeaderTitle.vue";

export default {
  name: "ArticleDetail",
  components: {
    HeaderTitle,
  },
  data() {
    return {
      article: {
        articleId: Number,
        categoryId: Number,
        writer: String,
        password: String,
        title: String,
        content: String,
        createdAt: String,
        modifiedAt: String,
      },
    };
  },
  async created() {
    const articleId = this.$route.params.id;
    const articleDetail = await this.axios.get(`/api/v1/articles/${articleId}`);
    this.article = articleDetail;
    console.log(this.article);
  },
  computed: {
    articleId() {
      return parseInt(this.$route.params.id);
    },
  },
};
</script>
