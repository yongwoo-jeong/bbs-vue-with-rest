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
          <td></td>
        </tr>
        <tr>
          <th>비밀번호*</th>
          <td></td>
        </tr>
        <tr>
          <th>제목*</th>
          <td></td>
        </tr>
        <tr>
          <th>내용*</th>
          <td></td>
        </tr>
        <tr>
          <th>파일 첨부</th>
          <td></td>
        </tr>
      </table>
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
</style>
