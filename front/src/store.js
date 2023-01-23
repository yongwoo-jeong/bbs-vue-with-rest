import Vuex from "vuex";

export const store = new Vuex.Store({
  state: {
    /**
     * 카테고리 오브젝트 리스트
     */
    categoryList: [
      { categoryId: 1, categoryName: "JAVA" },
      { categoryId: 2, categoryName: "Javascript" },
      { categoryId: 3, categoryName: "Database" },
    ],
    /**
     * 카테고리 오브젝트
     */
    categoryObject: { 1: "JAVA", 2: "Javascript", 3: "Database" },
  },

  //   mutations: {
  //     /**
  //      * 게시글리스트, 오브젝트 state 설정하는 메서드
  //      * @param {state} state
  //      * @param {Object[]} 게시글 Object List
  //      */
  //     updateCategoryList(state, payload) {
  //       state.categoryList = payload;
  //       // 카테고리 리스트를 객체로 만들어주기 위한 리듀스(해체)
  //       state.categoryObject = payload.reduce((newObj, obj) => {
  //         newObj[obj.categoryId] = obj.categoryName;
  //         return newObj;
  //       }, {});
  //     },
  //   },
});
