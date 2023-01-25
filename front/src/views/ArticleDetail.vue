<template>
  <body>
    <HeaderTitle title="게시판 - 보기" />
    <main>
      <div class="writer">
        <span>{{ articleDetail.writer }}</span>
        <div class="dateInfo">
          <span>등록일시 {{ articleDetail.createdAt }}</span>
          <span>수정일시 {{ articleDetail.modifiedAt ?? "-" }}</span>
        </div>
      </div>
      <div class="title">
        <div>
          <span class="title__category">[{{ categoryName }}]</span>
          <span>{{ articleDetail.title }}</span>
        </div>
        <span>조회수: {{ articleDetail.view }}</span>
      </div>
      <div class="content">
        <a>{{ articleDetail.content }}</a>
      </div>
      <div class="files"></div>
      <div class="comments">
        <div
          class="comments__content"
          v-for="comment in commentList"
          :key="comment.commentId"
        >
          <a>{{ comment.createdAt }}</a>
          <a>{{ comment.content }}</a>
        </div>
        <div>
          <input
            class="comments__input"
            type="text"
            :value="newComment"
            @input="onChagneCommentInput"
            placeholder="댓글을 입력해주세요"
          />
          <input type="submit" value="등록" @click.prevent="onClickSubmit" />
        </div>
      </div>
      <div class="buttons"></div>
    </main>
  </body>
</template>

<script>
import HeaderTitle from "@/components/HeaderTitle.vue";
import { articleAPI, commentAPI } from "@/api/api";

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
      commentList: {},
      newComment: "",
    };
  },

  /**
   * 게시글 ID를 path에서 가져와 데이터 페칭 후 등록
   */
  async created() {
    this.fetchArticleDetail();
  },

  /**
   * state로 부터 적절한 카테고리명 리턴
   */
  computed: {
    categoryName: function () {
      return this.$store.state.categoryObject[this.articleDetail.categoryId];
    },
  },

  methods: {
    fetchArticleDetail: async function () {
      // path variable 획득
      const articleId = this.$route.params.id;
      const articleDetailResult = await articleAPI.getArticleDetail(articleId);
      this.articleDetail = articleDetailResult.data.articleDetail;
      this.commentList = articleDetailResult.data.commentList;
    },

    onChagneCommentInput: function (e) {
      this.newComment = e.target.value;
    },
    onClickSubmit: async function () {
      if (this.newComment.replaceAll(" ", "") == "") {
        alert("댓글을 입력해야합니다.");
        return;
      }
      const formData = new FormData();
      formData.append("content", this.newComment);
      await commentAPI.postComment(this.$route.params.id, formData);
      this.fetchArticleDetail();
    },
  },

  watch: {
    commentList() {},
  },
};
</script>

<style scoped>
.writer {
  display: flex;
  justify-content: space-between;
}

.dateInfo *:first-child {
  margin-right: 15px;
}

.title {
  margin-top: 10px;
  display: flex;
  border-bottom: 1px solid black;
  justify-content: space-between;
}

.title__category {
  margin-right: 10px;
}

.content {
  margin-top: 10px;
  padding: 10px 10px;
  border: 1px dotted black;
  height: 150px;
  text-align: left;
}

.comments {
  width: 90%;
  background-color: rgba(0, 0, 0, 0.25);
}

.comments__content {
  display: flex;
  flex-direction: column;
  padding: 5px 10px;
  align-items: flex-start;
  border-bottom: 1px dotted black;
}

.comments__input {
  margin-top: 10px;
  width: 80%;
  height: 30px;
}
</style>
