<template>
  <HeaderTitle title="게시판 보기" />
  <div>
    <form>
      <input type="date" />
      <input type="date" />
      <select>
        <option>전체 카테고리</option>
        <option
          v-for="category in categoryList"
          :key="category['categoryId']"
          :value="category.categoryId"
        >
          {{ category["categoryName"] }}
        </option>
      </select>
      <input type="text" />
      <input type="submit" value="검색" />
    </form>
  </div>
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
        <td>{{ categoryObject[article["categoryId"]] }}</td>
        <td></td>
        <td>
          <router-link
            :to="{
              name: 'articleDetail',
              params: { id: article['articleId'] },
            }"
          >
            {{ article["title"] }}
          </router-link>
          <router-view />
        </td>
        <td>{{ article["writer"] }}</td>
        <td>{{ article["view"] }}</td>
        <td>{{ article["createdAt"] }}</td>
        <td>{{ article["modifiedAt"] }}</td>
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
      categoryList: [],
      categoryObject: {},
    };
  },
  async created() {
    const boardJson = await axios.get("/api/v1/articles");
    this.articleList = boardJson.data["articleList"];
    this.categoryList = await boardJson.data["categoryList"];
    // 카테고리 표현을 위해 카테고리 리스트를 맵형태로 교체
    this.categoryObject = await boardJson.data["categoryList"].reduce(
      (newObj, obj) => {
        newObj[obj["categoryId"]] = obj["categoryName"];
        console.log(obj.key, obj.value);
        return newObj;
      },
      {}
    );
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
