<template>
  <div id="content">
    <div>bot name:{{ botName }}</div>
    <div>bot level: {{ botLevel }}</div>
  </div>
  <router-view>

  </router-view>
</template>

<script>
import $ from 'jquery';
import {ref} from 'vue';

let botName = ref('');
let botLevel = ref('');

export default{
  name: 'App',
  setup(){
    $.ajax({
      url: 'http://localhost:8088/pk/api/get_bot_info',
      type: 'GET',
      dataType: 'json',
      success: function(data){
        botName.value = data.name;
        botLevel.value = data.level;
      }
    });
    return {
      botName,
      botLevel
    }
  }
}

</script>

<style>
  body{
    width: 100%;
    height: 100%;
    background-image: url('@/assets/background.png');
    background-size: cover;
  }
</style>

