import http from "@/common/http-common.js";

const orderStore = {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {
    SET_ORDER_REGST(state, order) {
      state.orderList.push(order);
    },
  },
  actions: {
    regOrder({ commit }, param) {
      const params = {
        product_id: param.product_id,
        purchaser: param.purchaser,
        total_price: param.total_price,
        use_point: param.use_point,
        purchaser_memo: param.purchaser_memo,
        pay_type: param.pay_type,
        card_id: param.card_id,
        coupon_id: param.coupon_id,
      };
      http
        .post(`/order/create`, params)
        .then((response) => {
          commit("SET_ORDER_REGST", response.data);
          alert("주문 완료");
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default orderStore;
