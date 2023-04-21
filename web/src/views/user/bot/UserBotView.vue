<template>
    <ContentBase>
        <div>
            User Bot
        </div>
    </ContentBase>
</template>

<script>

import ContentBase from '@/components/ContentBase.vue';
import $ from 'jquery';
import {useStore} from 'vuex';

export default {
    name: 'UserBotView',
    components: {
        ContentBase
    },
    setup(){
        const store=useStore();
        $.ajax({
            url: "http://127.0.0.1:8088/user/bot/add/",
                type: "post",
                data: {
                    bot_name: "bot name",
                    description: "bot description",
                    content: "bot content",
                },
                headers:{
                    Authorization: "Bearer "+store.state.user.token,
                },
                success(resp) {
                    console.log(resp);
                    if (resp.message === "success") {
                        alert("添加成功");
                    } else {
                        alert("添加失败");
                    }
                },
                error(resp){
                    alert("添加失败");
                    console.log(resp);
                }
        });
    }
}

</script>

<style scoped></style>