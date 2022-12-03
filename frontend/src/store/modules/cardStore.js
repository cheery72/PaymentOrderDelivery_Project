import http from "@/common/http-common.js";

const cardStore = {
  namespaced: true,
  state: {
    paymentCardList: [],
  },
  getters: {},
  mutations: {
    SET_CARD_LIST(state, paymentCard) {
      state.paymentCardList.push(paymentCard);
    },
  },
  actions: {
    getPaymentCard({ commit }, param) {
    http.get(`/card/${param}/member-card`)
        .then((response) => {
          commit("SET_CARD_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default cardStore;
