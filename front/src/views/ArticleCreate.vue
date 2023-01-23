<template>
  <main>
    <HeaderTitle title="게시판 - 등록" />
    <form enctype="multipart/form-data" @submit.prevent="submit">
      <table style="border-collapse: collapse">
        <tr>
          <th>카테고리*</th>
          <td>
            <label for="category"></label>
            <select name="categoryId" id="category">
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
          <td></td>
        </tr>
      </table>
      <div class="footer__button-containers">
        <div class="footer__button__cancel">
          <a>취소</a>
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
import { api } from "@/api/api";

export default {
  name: "articleCreate",
  components: { HeaderTitle },
  data() {
    return {
      postUrl: `${process.env.VUE_APP_SERVER_URL}api/v1/articles/create`,
      writer: "",
      title: "",
      password: "",
      passwordConfirm: "",
      content: "",
    };
  },
  methods: {
    submit: async function (event, newArticle) {
      event.preventDefault();
      await api.postNewArticle(newArticle);
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
      this.Content = e.target.value;
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
}

.footer__button__submit {
  background-color: rgba(0, 0, 0, 0.65);
}

.footer__button__submit * {
  color: white;
}
</style>
