<template>
    <div class="container content-field">
        <div class="card">
            <div class="card-body">
                <!-- 显示所有子元素 -->
                <slot></slot>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: 'ContentBase',
}

</script>

<style scoped>
.content-field {
    margin-top: 20px;
}

</style>