import Vue from "vue";
import Vuex from "vuex";
import orderStore from "@/store/modules/orderStore.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: { orderStore },
});
