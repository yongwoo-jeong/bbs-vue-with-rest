<template>
  <body>
    <HeaderTitle title="게시판 - 보기" />
    <main>
      <div class="writer">
        <span>{{ articleDetail.writer }}</span>
      </div>
      <div class="title">
        <div>
          <span class="title__category">[{{ categoryName }}]</span>
          <span>{{ articleDetail.title }}</span>
        </div>
      </div>
      <div class="content">
        <a>{{ articleDetail.content }}</a>
      </div>
      <div class="files" @click="onClickFile">
        <a v-for="file in fileList" :key="file.fileUuid" :id="file.fileUuid">
          {{ file.nameOriginal }}{{ file.fileUuid }}
        </a>
      </div>
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
      <div class="buttons">
        <div class="button__back">
          <RouterLink
            :to="{
              name: 'articleList',
              query: {
                startDate,
                endDate,
                categoryId,
                keyword,
                currentPage,
              },
            }"
            >목록</RouterLink
          >
        </div>
        <div class="button__modify" @click="isModalOpen = !isModalOpen">
          수정
        </div>
        <PasswordModal
          v-if="isModalOpen === true"
          pageDestination="modify"
          :password="articleDetail.password"
        />
        <div class="button__delete">삭제</div>
        <PasswordModal
          v-if="isModalOpen === true"
          pageDestination="delete"
          :password="articleDetail.password"
        />
      </div>
    </main>
  </body>
</template>

<script>
import HeaderTitle from "@/components/HeaderTitle.vue";
import PasswordModal from "@/components/PasswordModal.vue";
import { articleAPI, commentAPI, fileAPI } from "@/api/api";
import axios from "axios";

export default {
  name: "ArticleDetail",
  components: {
    HeaderTitle,
    PasswordModal,
  },

  data() {
    return {
      /**
       * 게시글 상세정보
       */
      articleDetail: {},
      /**
       * 게시글에 따른 댓글 리스트
       */
      commentList: [],
      /**
       * 게시글에 따른 파일리스트
       */
      fileList: [],
      /**
       * 새 댓글 내용 for v-model
       */
      newComment: "",
      isModalOpen: false,
    };
  },

  /**
   * 게시글 ID를 path에서 가져와 데이터 페칭 후 등록
   */
  async created() {
    this.fetchArticleDetail();
  },

  methods: {
    /**
     * 게시글, 댓글 리스트 반환하는 API 에 요청 보내는메서드
     */
    fetchArticleDetail: async function () {
      // path variable 획득
      const articleId = this.$route.params.id;
      const articleDetailResult = await articleAPI.getArticleDetail(articleId);
      this.articleDetail = articleDetailResult.data.articleDetail;
      this.commentList = articleDetailResult.data.commentList;
      this.fileList = articleDetailResult.data.fileList;
    },

    /**
     * input 입력된 댓글을 this.newComment 에 등록해주는 메서드
     * @param 자바스크립트 이벤트객체
     */
    onChagneCommentInput: function (e) {
      this.newComment = e.target.value;
    },

    /**
     * 댓글 입력 확인을 눌렀을 때 새 댓글을 POST 요청 보내는 메서드
     */
    onClickSubmit: async function () {
      if (this.newComment.replaceAll(" ", "") == "") {
        alert("댓글을 입력해야합니다.");
        return;
      }
      const formData = new FormData();
      formData.append("content", this.newComment);
      await commentAPI.postComment(this.$route.params.id, formData);
      // 댓글 입력 후 input value 초기화
      this.newComment = "";
      // 새 댓글 입력후엔 게시글/댓글 정보를 새롭게 받아옴
      // 게시글, 댓글 받아오는 API 분리 후 댓글만 따로 페치하는게 맞지않나..
      this.fetchArticleDetail();
    },

    /**
     * 파일 다운로드 메서드
     * @param {*} e
     */
    onClickFile: async function (e) {
      const res = await axios.get(
        `http://localhost:8080/api/v1/file/${e.target.id}`,
        {
          responseType: "blob",
        }
      );
      console.log(res);
      const url = window.URL.createObjectURL(new Blob([res.data]));
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", "구직.xlsx");
      document.body.appendChild(link);
      link.click();
      link.parentNode.removeChild(link);
    },

    /**
     * 모달창 닫기
     */
    closeModal: function () {
      this.isModalOpen = false;
    },
  },

  /**
   * 댓글 리스트 리렌더를 위한 와쳐
   */
  watch: {
    commentList() {},
  },

  /**
   * state로부터 적절한 카테고리명 리턴
   */
  computed: {
    categoryName: function () {
      return this.$store.state.categoryObject[this.articleDetail.categoryId];
    },
    startDate: function () {
      return this.$route.query.startDate;
    },
    endDate: function () {
      return this.$route.query.endDate;
    },
    categoryId: function () {
      return this.$route.query.categoryId;
    },
    keyword: function () {
      return this.$route.query.keyword;
    },
    currentPage: function () {
      return this.$route.query.currentPage;
    },
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

.buttons {
  display: flex;
}

.button__back,
.button__modify,
.button__delete {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 25px;
  color: black;
  text-decoration: none;
  cursor: pointer;
  background-color: rgba(0, 0, 0, 0.25);
  margin: 5px;
}

.button__back * {
  padding: 30px;
  color: black;
  text-decoration: none;
}
</style>
