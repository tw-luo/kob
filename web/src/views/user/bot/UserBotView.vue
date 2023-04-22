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

        //测试添加bot
        // $.ajax({
        //     url: "http://127.0.0.1:8088/user/bot/add/",
        //         type: "post",
        //         data: {
        //             bot_name: "bot name",
        //             description: "bot description",
        //             content: "bot content",
        //         },
        //         headers:{
        //             Authorization: "Bearer "+store.state.user.token,
        //         },
        //         success(resp) {
        //             console.log(resp);
        //             if (resp.message === "success") {
        //                 alert("添加成功");
        //             } else {
        //                 alert("添加失败");
        //             }
        //         },
        //         error(resp){
        //             alert("添加失败");
        //             console.log(resp);
        //         }
        // });


        //测试删除bot
        // $.ajax({
        //     url: "http://127.0.0.1:8088/user/bot/remove/",
        //         type: "post",
        //         data: {
        //             bot_id: 9,
        //         },
        //         headers:{
        //             Authorization: "Bearer "+store.state.user.token,
        //         },
        //         success(resp) {
        //             console.log(resp);
        //             if (resp.message === "success") {
        //                 alert("删除成功");
        //             } else {
        //                 alert("删除失败\n"+resp.message);
        //             }
        //         },
        //         error(resp){
        //             alert("删除失败,请检查网络连接");
        //             console.log(resp);
        //         }
        // });

        //测试修改bot
        $.ajax({
            url: "http://127.0.0.1:8088/user/bot/update/",
                type: "post",
                data: {
                    bot_id: 3,
                    bot_name: "new bot name",
                    description: "new bot description",
                    content: "new bot content",
                },
                headers:{
                    Authorization: "Bearer "+store.state.user.token,
                },
                success(resp) {
                    console.log(resp);
                    if (resp.message === "success") {
                        alert("更新成功");
                    } else {
                        alert("更新失败\n"+resp.message);
                    }
                },
                error(resp){
                    alert("更新失败,请检查网络连接");
                    console.log(resp);
                }
        });


    }
}

</script>

<style scoped></style>