<template>
  <HeaderTitle title="게시판 보기" />
  <div>{{ boardData["articleList"] }}</div>
  <div>
    <table style="border-collapse: collapse">
      <tr>
        <th>카테고리</th>
        <th></th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>조회수</th>
        <th>등록일시</th>
        <th>수정일시</th>
      </tr>
    </table>
  </div>
</template>

<script>
import HeaderTitle from "@/components/HeaderTitle.vue";
import axios from "axios";
// @ is an alias to /src

export default {
  name: "MainBoard",
  components: {
    HeaderTitle,
  },
  data() {
    return {
      boardData: [],
    };
  },
  async created() {
    const boardJson = await axios.get("/api");
    this.boardData = boardJson.data;
  },
};
</script>

<style scoped>
table {
  width: 100%;
  border-bottom: 1px solid black;
}

th {
  border-bottom: 1px solid black;
}
</style>
