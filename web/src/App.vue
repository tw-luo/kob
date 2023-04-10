<template>
  <NavBar></NavBar>

  <router-view>
  </router-view>
</template>

<script>
import NavBar from '@/components/NavBar.vue';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'

export default {
  name: 'App',
  components: {
    NavBar
  },

}

</script>

<style>
body {
  width: 100%;
  height: 100%;
  background-image: url('@/assets/background.png');
  background-size: cover;
}
</style>

