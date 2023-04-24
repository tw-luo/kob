<template>
  <div id="game_area">
    <GameArea v-if="$store.state.pk.status === 'playing'"></GameArea>
    <MatchGround v-if="$store.state.pk.status === 'matching'"></MatchGround>
  </div>
</template>

<script>
import MatchGround from "@/components/MatchGround.vue";
import GameArea from "@/components/GameArea.vue";
import { onMounted, onUnmounted } from "vue";
import { useStore } from "vuex";

export default {
  name: "PlayView",
  components: {
    GameArea,
    MatchGround,
  },
  setup() {
    const store = useStore();
    const socketUrl = `ws://127.0.0.1:8088/websocket/${store.state.user.token}`;

    let socket = null;
    onMounted(() => {
      store.commit("updateOpponent", {
        username: "我的对手",
        photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
      });

      socket = new WebSocket(socketUrl);
      socket.onopen = function () {
        console.log("connect success");
        store.commit("updateSocket", socket);
      };
      socket.onmessage = function (e) {
        const data=JSON.parse(e.data);

        console.log(data);

        if(data.event==="match-success"){
          store.commit("updateOpponent", {
            username: data.opponent_username,
            photo: data.opponent_photo,
          });

          store.commit("updateGameMap", data.map);

          setTimeout(() => {
            store.commit("updateStatus", "playing");
            store.commit("updateOpponent", {
              username: "",
              photo: "",
            });
          }, 2000);

        }

      };
      socket.onclose = function () {
        console.log("connect close");
        store.commit("updateSocket", null);
        store.commit("updateStatus", "matching");
      };
    });

    onUnmounted(() => {
      socket.close();
    });

    return {};
  },
};
</script>



<style scoped>
#game_area {
  width: 70vw;
  height: 70vh;
  margin: 40px auto;
}
</style>
