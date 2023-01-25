<template>
  <body>
    <HeaderTitle title="게시판 - 보기" />
    <main>
      <div class="writer">
        <span>{{ articleDetail.writer }}</span>
        <div>
          <span>등록일시 {{ articleDetail.createdAt }}</span>
          <span>수정일시 {{ articleDetail.modifiedAt }}</span>
        </div>
      </div>
      <div class="title">
        <div>
          <span>[{{ categoryName }}]</span>
          <span>{{ articleDetail.title }}</span>
        </div>
        <span>조회수: {{ articleDetail.view }}</span>
      </div>
      <div>
        <a>{{ articleDetail.content }}</a>
      </div>
      <div class="files"></div>
      <div class="comments"></div>
      <div class="buttons"></div>
    </main>
  </body>
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
    return {
      /**
       * 게시글 상세정보
       */
      articleDetail: {},
    };
  },

  /**
   * 게시글 ID를 path에서 가져와 데이터 페칭 후 등록
   */
  async created() {
    // path variable 획득
    const articleId = this.$route.params.id;
    const axiosResult = await api.getArticleDetail(articleId);
    this.articleDetail = axiosResult.data;
  },

  /**
   * state로 부터 적절한 카테고리명 리턴
   */
  computed: {
    categoryName: function () {
      return this.$store.state.categoryObject[this.articleDetail.categoryId];
    },
  },
};
</script>

<style scoped>
.writer {
  display: flex;
  justify-content: space-between;
}
.title {
  display: flex;
  justify-content: space-between;
}
</style>
