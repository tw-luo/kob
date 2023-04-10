import HomeView from "@/views/HomeView.vue";
import NotFoundView from "@/views/error/404/NotFoundView.vue";
import PkIndexView from "@/views/pk/PkIndexView.vue";
import RankIndexView from "@/views/rank/RankIndexView.vue";
import RecordIndexView from "@/views/record/RecordIndexView.vue";
import UserProfileView from "@/views/user/profile/UserProfileView.vue";
import UserBotView from "@/views/user/bot/UserBotView.vue";
import { createRouter, createWebHistory } from "vue-router";

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
    path: "/user/profile",
    name: "UserProfile",
    component: UserProfileView,
  },
  {
    path: "/user/bot",
    name: "UserBot",
    component: UserBotView,
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
