<template>
  <NavBar></NavBar>

  <router-view>
  </router-view>
</template>

<script>
import NavBar from '@/components/NavBar.vue';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import { useStore } from 'vuex'
import router from './router';
// import $ from 'jquery'

export default {
  name: 'App',
  components: {
    NavBar
  },
  setup() {
    const store = useStore();
    const jwt_token = localStorage.getItem("jwt_token");
    if (jwt_token) {
      store.commit("updateToken", jwt_token);
      store.dispatch("getinfo", {
        success() {
          router.push({ name: 'Home' });
          store.commit("updatePullingInfo", false);
        },
        error() {
          store.commit("updatePullingInfo", false);
        }
      })
    } else {
      store.commit("updatePullingInfo", false);
    }
    // let token = null;
    // $.ajax({
    //   url: "http://127.0.0.1:8088/user/account/token/",
    //   type: "post",
    //   data: {
    //     username: "luo",
    //     password: "123456",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //     token = resp.token;
    //     console.log(token);
    //     $.ajax({
    //       url: "http://127.0.0.1:8088/user/account/info/",
    //       type: "get",
    //       headers: {
    //         Authorization: "Bearer "+token,
    //       },
    //       success(resp) {
    //         console.log(resp);
    //       },
    //       error(resp) {
    //         console.log(resp);
    //       },
    //     });
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });


    // $.ajax({
    //   url: "http://127.0.0.1:8088/user/account/register/",
    //   type: "post",
    //   data: {
    //     username: "luo",
    //     password: "123456",
    //     confirmPassword: "123456",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });
  }
}
</script>

<style>
body {
  width: 100%;
  height: 100%;
  background-image: url('@/assets/images/background.png');
  background-size: cover;
}
</style>

