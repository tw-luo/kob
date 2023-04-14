<template>
    <div id="game_area" ref="parent">
        <canvas ref="canvas">

        </canvas>
    </div>
</template>

<script>

import { GameMap } from '@/assets/script/GameMap';
import { ref, onMounted } from 'vue';


export default {
    name: 'GameArea',
    setup: () => {
        let parent = ref(null);
        let canvas = ref(null);

        onMounted(() => {
            let ctx = canvas.value.getContext('2d');
            new GameMap(ctx, parent.value);
        });

        return {
            parent,
            canvas
        }
    }

}

</script>

<style scoped>
#game_area {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}


</style>