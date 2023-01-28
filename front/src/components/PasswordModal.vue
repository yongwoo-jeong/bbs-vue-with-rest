<template>
  <div class="modal__fade">
    <div class="modal__content">
      <h3>비밀번호 확인</h3>
      <div class="modal__table-container">
        <form @submit.prevent="submitPassword">
          <table>
            <tr>
              <th>비밀번호*</th>
              <td>
                <input
                  type="password"
                  placeholder="비밀번호를 입력해주세요"
                  :value="userInputPassword"
                  @input="userInputPasswordChange"
                />
              </td>
            </tr>
          </table>
          <div>
            <button @click="$emit('closeModal')">취소</button>
            <input type="submit" value="확인" />
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { articleAPI } from "@/api/api";

export default {
  name: "PassworCheckModal",
  created() {},
  data() {
    return {
      userInputPassword: "",
    };
  },
  props: {
    pageDestination: String,
    password: String,
  },
  methods: {
    userInputPasswordChange(e) {
      this.userInputPassword = e.target.value;
    },
    /**
     * 수정 혹은은
     */
    submitPassword() {
      if (this.userInputPassword !== this.password) {
        alert("비밀번호가 일치하지 않습니다");
        return;
      }
      const articleId = this.$route.params.id;
      // 서버에서 비밀번호 불러와서 비교검증 X
      // 서버로 비밀번호 보내서 검증한 뒤 status 코드 확인 후 리디렉션..
      if (this.pageDestination === "modify") {
        router.push(`/articles/${articleId}/edit`);
      }
      if (this.pageDestination === "delete") {
        const formData = new FormData();
        formData.append("_method", "delete");
        formData.append("articleId", articleId);
        articleAPI.deleteArticle(articleId, formData);
        // window location 보다 더 나은 방법..
        // 페이지 refresh 없이 article List 만 업데이트 할 수 있는 방법으로..
        window.location.href = process.env.VUE_APP_ROOT_URL;
      }
    },
  },
};
</script>

<style scoped>
.modal__fade {
  position: absolute;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: rgba(255, 255, 255, 0.55);
}

.modal__content {
  width: 500px;
  height: 200px;
  background-color: white;
  border: 1px solid black;
  box-shadow: 5px 5px 3px #666;
}
</style>
