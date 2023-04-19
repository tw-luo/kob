import HomeView from "@/views/HomeView.vue";
import NotFoundView from "@/views/error/404/NotFoundView.vue";
import PkIndexView from "@/views/pk/PkIndexView.vue";
import RankIndexView from "@/views/rank/RankIndexView.vue";
import RecordIndexView from "@/views/record/RecordIndexView.vue";
import UserBotView from "@/views/user/bot/UserBotView.vue";
import { createRouter, createWebHistory } from "vue-router";
import PlayView from "@/views/pk/play/PlayView.vue";
import LoginView from "@/views/user/account/LoginView.vue";
import RegisterView from "@/views/user/account/RegisterView.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: HomeView,
  },
  {
    path: "/pk",
    name: "PK",
    component: PkIndexView,
  },
  {
    path:"/pk/play",
    name:"PKPlay",
    component: PlayView,
  },
  {
    path: "/record",
    name: "Record",
    component: RecordIndexView,
  },
  {
    path: "/rank",
    name: "Rank",
    component: RankIndexView,
  },
  {
    path: "/error/404",
    name: "404NotFound",
    component: NotFoundView,
  },
  {
    path: "/user/bot",
    name: "UserBot",
    component: UserBotView,
  },
  {
    path: "/user/account/login",
    name: "Login",
    component: LoginView,
  },
  {
    path: "/user/account/register",
    name: "Register",
    component: RegisterView,
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/error/404",
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
