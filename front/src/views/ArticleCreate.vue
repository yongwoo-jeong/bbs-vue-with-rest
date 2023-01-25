<template>
  <main>
    <HeaderTitle title="게시판 - 등록" />
    <form enctype="multipart/form-data" @submit.prevent="onSumbit">
      <table style="border-collapse: collapse">
        <tr>
          <th>카테고리*</th>
          <td>
            <label for="category"></label>
            <select name="categoryId" id="category" @change="selectCategory">
              <option
                v-for="category in this.$store.state.categoryList"
                :key="category.categoryId"
                :value="category.categoryId"
              >
                {{ category.categoryName }}
              </option>
            </select>
          </td>
        </tr>
        <tr>
          <th>작성자*</th>
          <td>
            <input :value="writer" @input="changeWriter" />
          </td>
        </tr>
        <tr>
          <th>비밀번호*</th>
          <td>
            <input
              :value="password"
              @input="changePassword"
              type="password"
              placeholder="비밀번호"
            />
            <input
              :value="passwordConfirm"
              @input="changePasswordConfirm"
              type="password"
              placeholder="비밀번호 확인"
            />
          </td>
        </tr>
        <tr>
          <th>제목*</th>
          <td><input :value="title" @input="changeTitle" /></td>
        </tr>
        <tr>
          <th>내용*</th>
          <td class="table-data__content">
            <input :value="content" @input="changeContent" />
          </td>
        </tr>
        <tr>
          <th>파일 첨부</th>
          <td>
            <input type="file" id="file1" />
            <input type="file" id="file2" />
            <input type="file" id="file3" />
          </td>
        </tr>
      </table>
      <div class="footer__button-containers">
        <div class="footer__button__cancel">
          <RouterLink
            :to="{
              name: 'articleList',
              query: {
                startDate: queryString.startDate,
                endDate: queryString.endDate,
                categoryId: queryString.categoryId,
                keyword: queryString.keyword,
                currentPage: queryString.currentPage,
              },
            }"
            ><a>취소</a></RouterLink
          >
        </div>
        <div class="footer__button__submit">
          <input type="submit" value="저장" />
        </div>
      </div>
    </form>
  </main>
</template>

<script>
import HeaderTitle from "@/components/HeaderTitle.vue";
import { api, articleAPI } from "@/api/api";
import router from "@/router";

export default {
  name: "newArticleForm",
  components: { HeaderTitle },
  data() {
    return {
      writer: "",
      title: "",
      password: "",
      content: "",
      categoryId: 1,
      files: [],
      passwordConfirm: "",
      queryString: {
        startDate: "",
        endDate: "",
        categoryId: "",
        keyword: "",
        currentPage: "",
      },
    };
  },

  mounted() {
    this.setQueryString();
  },

  methods: {
    /**
     * 필드 길이를 클라이언트에서 검증을 위한 메서드
     */
    validateForm: function () {
      if (this.writer.length != 3 && this.writer.length != 4) {
        alert("작성자 길이는 3~4 자입니다.");
        return -1;
      }
      if (this.password.length < 4 || this.password.length > 15) {
        alert("비밀번호 길이는 4~15 자입니다.");
        return -1;
      }
      if (
        this.passwordConfirm.length < 4 ||
        this.passwordConfirm.length > 15 ||
        this.password !== this.passwordConfirm
      ) {
        alert("비밀번호가 일치하지 않습니다.");
        return -1;
      }
      if (this.title.length < 4 || this.title.length > 99) {
        alert("제목은 4~100 자입니다.");
        return -1;
      }
      if (this.content.length < 4 || this.content.length > 1999) {
        alert("본문내용 길이는 4~1999 자입니다.");
        return -1;
      }
      return 1;
    },

    onSumbit: async function (event) {
      event.preventDefault();
      let isValidForm = this.validateForm();
      if (isValidForm === -1) {
        return;
      }
      const formData = new FormData();
      formData.append("title", this.title);
      formData.append("writer", this.writer);
      formData.append("password", this.password);
      formData.append("passwordConfirm", this.passwordConfirm);
      formData.append("content", this.content);
      formData.append("categoryId", this.categoryId);
      formData.append("passwordConfirm", this.passwordConfirm);

      // 파일
      const fileOne = document.getElementById("file1");
      const fileTwo = document.getElementById("file2");
      const fileThree = document.getElementById("file3");

      await articleAPI.postNewArticle(formData);
      router.push("/articles");
    },

    selectCategory(e) {
      this.categoryId = e.target.value;
    },
    changeWriter(e) {
      this.writer = e.target.value;
    },
    changeTitle(e) {
      this.title = e.target.value;
    },
    changePassword(e) {
      this.password = e.target.value;
    },
    changePasswordConfirm(e) {
      this.passwordConfirm = e.target.value;
    },
    changeContent(e) {
      this.content = e.target.value;
    },

    /**
     * 검색조건 유지를 위한 쿼리스트링 설정해주는 메서드
     */
    setQueryString() {
      this.queryString.startDate = this.$route.query.startDate ?? "";
      this.queryString.endDate = this.$route.query.endDate ?? "";
      this.queryString.categoryId = this.$route.query.categoryId ?? "";
      this.queryString.keyword = this.$route.query.keyword ?? "";
      this.queryString.currentPage = this.$route.query.currentPage ?? "";
    },
  },
};
</script>

<style scoped>
table {
  width: 1200px;
  border-top: 1px solid black;
  border-bottom: 1px solid black;
}

th {
  background-color: rgba(0, 0, 0, 0.15);
  border-bottom: 1px solid black;
  width: 100px;
  height: 30px;
}

td {
  width: 500px;
  border-bottom: 1px solid black;
}

td * {
  width: 300px;
  align-self: baseline;
}

.table-data__content * {
  width: 95%;
  height: 150px;
  margin: 10px 20px;
}

.footer__button-containers {
  width: 1000px;
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
}

.footer__button-containers > div {
  width: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.footer__button-containers input {
  padding-left: 30px;
  padding-right: 30px;
  background: none;
  border: none;
  outline: none;
}

.footer__button__cancel {
  background-color: rgba(0, 0, 0, 0.15);
  cursor: pointer;
}

.footer__button__submit {
  background-color: rgba(0, 0, 0, 0.65);
}

.footer__button__submit * {
  color: white;
  cursor: pointer;
}

.filebox label {
  font-size: 12px;
  width: 100px;
  height: 30px;
  display: inline-block;
  padding: 10px 20px;
  vertical-align: middle;
  cursor: pointer;
  margin-left: 10px;
  border: 1px dotted black;
  text-align: center;
}

.filebox input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}
</style>
