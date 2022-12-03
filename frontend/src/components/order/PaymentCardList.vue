<template>
  <div>
    <div v-if="check">
      <div v-for="(item, index) in cardList" :key="index">
        <payment-card :card="item" :idx="index" />
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import PaymentCard from "@/components/order/PaymentCard.vue";

const orderStore = "orderStore";

export default {
  name: "PaymentCardList",
  components: {
    PaymentCard,
  },
  data() {
    return {
      check: true,
    };
  },
  // watch: {
  //     apt(info) {
  //         this.check = true;
  //         this.getBoard(info);
  //     },
  // },
  computed: {
    ...mapState(orderStore, ["paymentCardList"]),
  },
  methods: {
    ...mapActions(orderStore, ["getPaymentCard"]),
  },
};
</script>

<style></style>
