<template>
    <ContentBase>
        <div class="row justify-content-md-center">
            <div class="col-3">
                <form @submit.prevent="register">
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">确认密码</label>
                        <input v-model="confirmPassword" type="password" class="form-control" id="confirmPassword" placeholder="确认密码">
                    </div>
                    <div class="error-message">{{ error_message }}</div>
                    <button type="submit" class="btn btn-primary">提交</button>
                </form>
            </div>
        </div>
    </ContentBase>
</template>

<script>

import ContentBase from '@/components/ContentBase.vue';
import { ref } from 'vue';
import router from '../../../router/index';
import $ from 'jquery';

export default {
    name: 'RegisterView',
    components: {
        ContentBase
    },
    setup(){
        let username = ref('');
        let password = ref('');
        let confirmPassword = ref('');
        let error_message= ref('');

        const register=()=>{
            $.ajax({
                url: "http://127.0.0.1:8088/user/account/register/",
                type: "post",
                data: {
                    username: username.value,
                    password: password.value,
                    confirmPassword: confirmPassword.value,
                },
                success(resp) {
                    console.log(resp);
                    if (resp.message === "success") {
                        alert("注册成功");
                        router.push({name: "Login"});
                    } else {
                        error_message.value = resp.message;
                    }
                },
                error(resp){
                    alert("注册失败");
                    console.log(resp);
                }

            });
        }

        return {
            username,
            password,
            confirmPassword,
            error_message,
            register,
        }
    }
}

</script>

<style scoped>
button {
    width: 100%;
}

div.error-message {
    color: red;
}
</style>