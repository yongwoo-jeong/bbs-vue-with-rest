<template>
  <main>
    <HeaderTitle title="게시판 - 등록" />
    <form>
      <table>
        <tr>
          <th>카테고리*</th>
          <td>
            <label for="category"></label>
            <select name="categoryId" id="category">
              <option
                v-for="category in categoryList"
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
  prop: ["categoryList"],
  mounted() {
    console.log(this.categoryList);
  },
};
</script>

<style lang="scss" scoped></style>
