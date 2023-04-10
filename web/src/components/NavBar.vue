<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="/">King of Bots</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/pk">对战</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/record">对战列表</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/rank">排行榜</a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                            luo
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/user/profile">个人主页</a></li>
                            <li><a class="dropdown-item" href="/user/bot">我的bot</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="/logout">退出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script>
    export default {
        name: 'NavBar',
    }
</script>

<style scoped></style>