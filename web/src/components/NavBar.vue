<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <router-link class="navbar-brand" to="/">King of Bots</router-link>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <router-link :class="route_name == 'PK' ? 'nav-link active' : 'nav-link'" aria-current="page"
                            to="/pk">对战</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :class="route_name == 'Record' ? 'nav-link active' : 'nav-link'"
                            to="/record">对战记录</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :class="route_name == 'Rank' ? 'nav-link active' : 'nav-link'" to="/rank">排行榜</router-link>
                    </li>
                </ul>
                <ul class="navbar-nav" v-if="$store.state.user.is_login">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                            {{ $store.state.user.username }}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><router-link class="dropdown-item" to="/user/account">个人主页</router-link></li>
                            <li><router-link class="dropdown-item" to="/user/bot">我的bot</router-link></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="/" @click="logout">退出</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="navbar-nav" v-else-if="!$store.state.user.pulling_info">
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'Login' }" role="button">
                            登录
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'Register' }" role="button">
                            注册
                        </router-link>
                    </li>
                </ul>

            </div>
        </div>
    </nav>
</template>

<script>
import { useRoute } from 'vue-router';
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
    name: 'NavBar',
    setup() {
        const store = useStore();
        const route = useRoute();
        let route_name = computed(() => route.name)

        const logout = () => {
            store.dispatch("logout");
        }

        return {
            route_name,
            logout
        }
    }

}
</script>

<style scoped>
.dropdown-menu {
    margin: 0 !important;
}
</style>