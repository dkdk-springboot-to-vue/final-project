import { createRouter, createWebHistory } from "vue-router";
// import Home from "../views/Home.vue";
import MemberLogin from "../components/Member/MemberLogin.vue";
import MemberRegister from "../components/Member/MemberRegister.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    // component: Home,
  },
  {
    path: "/MemberLogin",
    name: "MemberLogin",
    component: MemberLogin,
  },
  {
    path: "/MemberRegister",
    name: "MemberRegister",
    component: MemberRegister,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
