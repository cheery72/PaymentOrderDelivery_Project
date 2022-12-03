<template>
  <div>
    <b-form-input v-model="text" placeholder="Enter your name"></b-form-input>
    <div class="mt-2">Value: {{ value }}</div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const pointStore = "pointStore";

export default {
  name: "PaymentPoint",
  data() {
    return {
      value: 0,
    };
  },
  computed: {
    ...mapState(pointStore, ["paymentCardList"]),
  },
  methods: {
    ...mapActions(pointStore, ["getPaymentCard"]),
  },
};
</script>

<style></style>
