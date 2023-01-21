<template>
  <HeaderTitle title="게시글 보기" />
  <div></div>
</template>

<script>
import HeaderTitle from "@/components/HeaderTitle.vue";
import { api } from "@/api/api";

export default {
  name: "ArticleDetail",
  components: {
    HeaderTitle,
  },
  data() {
    return {};
  },
  async created() {
    // path variable 획득
    const articleId = this.$route.params.id;
    // api 요청
    const axiosResult = await api.getBoardDetail(articleId);
    console.log(axiosResult.data);
  },
};
</script>
