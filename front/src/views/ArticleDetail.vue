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
      articleDetail: {},
      categoryName: String,
    };
  },
  async created() {
    // path variable 획득
    const articleId = this.$route.params.id;
    // api 요청
    const axiosResult = await api.getBoardDetail(articleId);
    this.articleDetail = axiosResult.data;
    // 카테고리 리스트 env에서 관리, 인덱싱으로 이름 잡아주기
    const categoryList = process.env.VUE_APP_CATEGORY_LIST.split(",");
    this.categoryName = categoryList[this.articleDetail.categoryId - 1];
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
