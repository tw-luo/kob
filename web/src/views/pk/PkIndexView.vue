<template>
    <ContentBase>
        <div style="margin-bottom: 20px;">
            PK Index
        </div>

        <router-link to="/pk/play"><button class="btn btn-danger">开始游戏</button></router-link>
    </ContentBase>
</template>

<script>

import ContentBase from '@/components/ContentBase.vue';


export default {
    name: 'PkIndexView',
    components: {
        ContentBase
    },
}

</script>

<style scoped></style>