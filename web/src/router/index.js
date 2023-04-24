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
import store from "@/store";
import UserProfileView from "@/views/user/account/UserProfileView.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: HomeView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/pk",
    name: "PK",
    component: PkIndexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path:"/pk/play",
    name:"PKPlay",
    component: PlayView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/record",
    name: "Record",
    component: RecordIndexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/rank",
    name: "Rank",
    component: RankIndexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/error/404",
    name: "404NotFound",
    component: NotFoundView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/user/bot",
    name: "UserBot",
    component: UserBotView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/account/profile",
    name: "UserProfile",
    component: UserProfileView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/account/login",
    name: "Login",
    component: LoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/user/account/register",
    name: "Register",
    component: RegisterView,
    meta: {
      requestAuth: false,
    }
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

router.beforeEach((to, from, next) => {
  if (to.meta.requestAuth && !store.state.user.is_login) {
    next({name: "Login"});
  } else {
    next();
  }
})


export default router;
