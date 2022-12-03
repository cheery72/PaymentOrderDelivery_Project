<template>
  <div>
    <member-badge></member-badge>
    <b-form-textarea
      v-model="text"
      placeholder="내용을 입력해주세요."
    ></b-form-textarea>
    <b-button @click="regist" style="display: block; float: right"
      >등록하기</b-button
    >
    <br />
    <br />
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";

const orderStore = "orderStore";

export default {
  name:"OrderRegister",
  data() {
    return {};
  },
};
</script>

<style></style>
