<template>
  <div>
    <HeaderTitle title="게시판 보기" />
    <!-- SearchCriteriaVO 
        keyword
        categoryId
        startDate
        endDate
        currentPage -->
    <form method="get" action="/articles">
      <input name="startDate" type="date" />
      <input name="endDate" type="date" />
      <select name="categoryId">
        <option value="">전체 카테고리</option>
        <option
          v-for="category in categoryList"
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
        :value="this.$route.query.currentPage"
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
        <td>{{ categoryObject[article.categoryId] }}</td>
        <td></td>
        <td>
          <RouterLink
            :to="{
              name: 'articleDetail',
              params: { id: article.articleId },
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
    <Pagination :totalArticles="searchedCount" />
    <a class="create__button">
      <RouterLink :to="{ name: 'articleCreate' }"> 등록 </RouterLink>
    </a>
  </div>
</template>

<script>
import HeaderTitle from "@/components/HeaderTitle.vue";
import Pagination from "@/components/Pagination.vue";
import { api } from "@/api/api";

export default {
  name: "ArticleList",
  components: {
    HeaderTitle,
    Pagination,
  },

  /**
   * 게시글리스트, 검색된게시글 수, 카테고리리스트, 카테고리객체
   */
  data() {
    return {
      articleList: [],
      categoryList: [],
      categoryObject: {},
      searchedCount: 0,
    };
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
      const axiosResult = await api.getBoardInfo(searchQueryString);
      // TO KNOW ) await 라인에서 data 프로퍼티 불러오면 undefined?
      const boardInfo = axiosResult.data;
      this.articleList = boardInfo.articleList;
      this.searchedCount = boardInfo.searchedArticleCount;
      return boardInfo;
    },
  },

  async mounted() {
    const boardInfo = await this.fetchArticleList();
    this.categoryList = boardInfo.categoryList;
    // 객체 리스트 형태 카테고리 리스트를 객체로 만들어주기 위한 리듀스
    this.categoryObject = boardInfo.categoryList.reduce((newObj, obj) => {
      newObj[obj.categoryId] = obj.categoryName;
      return newObj;
    }, {});
  },

  watch: {
    /**
     * 페이징, 검색을 위해 라우트 쿼리가 바뀌면 리렌더하기 위한 와쳐메서드
     */
    $route(to, from) {
      if (to !== from) {
        this.fetchArticleList();
      }
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
  right: 15px;
  background-color: rgba(0, 0, 0, 0.75);
}

.create__button * {
  padding: 0px 55px;
  color: white;
  text-decoration: none;
}
</style>
