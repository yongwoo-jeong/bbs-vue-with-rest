<template>
  <HeaderTitle title="게시판 보기" />
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
      <tr v-for="article in articleList" :key="article['articleId']">
        <th>{{ category[article["categoryId"]] }}</th>
        <th></th>
        <th>{{ article["title"] }}</th>
        <th>{{ article["writer"] }}</th>
        <th>{{ article["view"] }}</th>
        <th>{{ article["createdAt"] }}</th>
        <th>{{ article["modifiedAt"] }}</th>
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
      articleList: [],
      category: {},
    };
  },
  async created() {
    const boardJson = await axios.get("/api/v1/articles");
    this.articleList = boardJson.data["articleList"];
    // 카테고리 표현을 위해 카테고리 리스트를 맵형태로 교체
    this.category = await boardJson.data["categoryList"].reduce(
      (newObj, obj) => {
        newObj[obj["categoryId"]] = obj["categoryName"];
        console.log(obj.key, obj.value);
        return newObj;
      },
      {}
    );

    console.log(this.category);
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
