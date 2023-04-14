<template>
    <div id="game_area">
        <GameArea></GameArea>
    </div>
</template>

<script>

import GameArea from '@/components/GameArea.vue';

export default {
    name: 'PlayView',
    components: {
        GameArea
    },
}

</script>

<style scoped>

#game_area{
    width: 70vw;
    height: 70vh;
    margin:40px auto;
}

</style>