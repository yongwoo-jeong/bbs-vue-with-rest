<template>
  <div>
    <HeaderTitle title="게시판 보기" />
    <form method="get" action="/articles">
      <input name="startDate" type="date" />
      <input name="endDate" type="date" />
      <select name="categoryId">
        <option value="">전체 카테고리</option>
        <option
          v-for="category in this.$store.state.categoryList"
          :key="category.categoryId"
          :value="category.categoryId"
        >
          {{ category.categoryName }}
        </option>
      </select>
      <input name="keyword" type="text" />
      <input
        name="currentPage"
        type="hidden"
        :value="
          typeof this.$route.query.currentPage === Number
            ? this.$route.query.currentPage
            : 1
        "
      />
      <input type="submit" value="검색" />
    </form>
    <div>
      <span>검색된 게시글: {{ searchedCount }}</span>
    </div>
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
      <tr
        class="article"
        v-for="article in articleList"
        :key="article.articleId"
      >
        <td>{{ this.$store.state.categoryObject[article.categoryId] }}</td>
        <td></td>
        <td>
          <RouterLink
            :to="{
              name: 'articleDetail',
              params: { id: article.articleId },
              query: { startDate, endDate, categoryId, keyword, currentPage },
            }"
          >
            {{ article.title }}
          </RouterLink>
        </td>
        <td>{{ article.writer }}</td>
        <td>{{ article.view }}</td>
        <td>{{ article.createdAt }}</td>
        <td>{{ article.modifiedAt }}</td>
      </tr>
    </table>
    <Pagination
      :totalArticles="searchedCount"
      :startDate="startDate"
      :endDate="endDate"
      :categoryId="categoryId"
      :keyword="keyword"
      :currentPage="currentPage"
    />
    <a class="create__button">
      <RouterLink
        :to="{
          name: 'newArticleForm',
          query: { startDate, endDate, categoryId, keyword, currentPage },
        }"
      >
        등록
      </RouterLink>
    </a>
  </div>
</template>

<script>
import HeaderTitle from "@/components/HeaderTitle.vue";
import Pagination from "@/components/Pagination.vue";
import { articleAPI } from "@/api/api";

export default {
  name: "ArticleList",
  components: {
    HeaderTitle,
    Pagination,
  },

  /**
   * 게시글리스트, 검색된게시글 수
   */
  data() {
    return {
      articleList: [],
      searchedCount: 0,
      // 검색조건 쿼리 파라미터
      startDate: "",
      endDate: "",
      categoryId: "",
      keyword: "",
      currentPage: "",
    };
  },

  /**
   * API 서버로부터 게시글 리스트 페치
   */
  async created() {
    await this.fetchArticleList();
  },

  watch: {
    /**
     * 페이징, 검색을 위해 라우트 쿼리가 바뀌면 리렌더하기 위한 와쳐메서드
     */
    articleList() {},
    $route(to, from) {
      if (to !== from) {
        this.fetchArticleList();
      }
    },
  },

  methods: {
    /**
     * API 통해서 articleList, category List, searchedArticleCount 받아오는 메서드
     */
    async fetchArticleList() {
      const searchQueryString = this.$route.fullPath.replace(
        this.$route.path,
        ""
      );
      const axiosResult = await articleAPI.getBoardInfo(searchQueryString);
      const boardInfo = axiosResult.data;
      this.articleList = boardInfo.articleList;
      this.searchedCount = boardInfo.searchedArticleCount;
      this.setDataFromQuery();
      return boardInfo;
    },

    /**
     * 쿼리스트링으로 가져온 검색조건을 뷰 인스턴스 데이터에 주입
     */
    setDataFromQuery() {
      this.startDate = this.$route.query.startDate ?? "";
      this.endDate = this.$route.query.endDate ?? "";
      this.categoryId = this.$route.query.categoryId ?? "";
      this.keyword = this.$route.query.keyword ?? "";
      this.currentPage = this.$route.query.currentPage ?? "1";
    },
  },
};
</script>

<style scoped>
table {
  width: 100%;
  border-bottom: 1px solid black;
}

th {
  border-bottom: 2px solid black;
  height: 30px;
}

td * {
  color: black;
}

.article {
  height: 30px;
  border-bottom: 1px solid black;
}

.create__button {
  border: 1px solid black;
  height: 25px;
  position: absolute;
  right: 5px;
  background-color: rgba(0, 0, 0, 0.75);
}

.create__button * {
  padding: 0px 55px;
  color: white;
  text-decoration: none;
}
</style>
