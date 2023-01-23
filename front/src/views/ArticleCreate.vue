<template>
  <main>
    <HeaderTitle title="게시판 - 등록" />
    <form>
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
          <td><input name="writer" type="text" /></td>
        </tr>
        <tr>
          <th>비밀번호*</th>
          <td>
            <input name="password" type="password" placeholder="비밀번호" />
            <input
              name="passwordConfirm"
              type="password"
              placeholder="비밀번호 확인"
            />
          </td>
        </tr>
        <tr>
          <th>제목*</th>
          <td><input name="title" type="text" /></td>
        </tr>
        <tr>
          <th>내용*</th>
          <td class="table-data__content">
            <input name="content" type="text" />
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

export default {
  name: "articleCreate",
  components: { HeaderTitle },
  created() {},
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
