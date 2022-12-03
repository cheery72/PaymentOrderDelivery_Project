import http from "@/common/http-common.js";

const pointStore = {
  namespaced: true,
  state: {
    point: 0,
  },
  getters: {},
  mutations: {
    SET_CARD_LIST(state, paymentCard) {
      state.point = paymentCard;
    },
  },
  actions: {
    getPaymentCard({ commit }, param) {
    http.get(`/point/${param}/member-point`)
        .then((response) => {
          commit("SET_CARD_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default pointStore;
