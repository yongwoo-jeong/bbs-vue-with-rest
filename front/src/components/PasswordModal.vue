<template>
  <div class="modal__fade">
    <div class="modal__content">
      <h3>비밀번호 확인</h3>
      <div class="modal__table-container">
        <table>
          <tr>
            <th>비밀번호*</th>
            <td>
              <form @submit.prevent="submitPassword">
                <input
                  type="password"
                  placeholder="비밀번호를 입력해주세요"
                  :value="userInputPassword"
                  @input="userInputPasswordChange"
                />
                <input type="submit" value="확인" />
              </form>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
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
  },
  methods: {
    userInputPasswordChange(e) {
      this.userInputPassword = e.target.value;
      console.log(this.userInputPassword);
    },

    /**
     * 수정 혹은은
     */
    submitPassword() {
      // 서버에서 비밀번호 불러와서 비교검증 X
      // 서버로 비밀번호 보내서 검증한 뒤 status 코드 확인 후 리디렉션..
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
  background-color: rgba(0, 0, 0, 0.5);
}

.modal__content {
  width: 500px;
  height: 200px;
  background-color: white;
}
</style>
