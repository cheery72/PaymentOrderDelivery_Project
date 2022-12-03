import Vue from "vue";
import VueRouter from "vue-router";
import MainBody from "@/views/MainBody.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "MainBody",
    component: MainBody,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
