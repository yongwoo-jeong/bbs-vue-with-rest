<template>
  <!-- 부모컴포넌트 -->
  <div>
    <PasswordModal
      v-if="isModalOpen === true"
      pageDestination="modify"
      @closeModal="closeModal"
    />
  </div>
</template>
// 부모 메서드 methods: { /** * 모달창 닫기 */ closeModal: function () {
console.log("clicked"); this.isModalOpen = false; },

<script>
export default {
  name: "Test",
  created() {},
  data() {
    return {};
  },
  props: {},
  methods: {},
};
</script>

<style lang="scss" scoped></style>
