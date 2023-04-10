<template>
    <div class="card">
        <div class="card-body">
            <!-- 显示所有子元素 -->
            <slot></slot>
        </div>
    </div>
</template>

<script>

export default {
    name: 'ContentBase',
}

</script>

<style scoped>
    .card{
        margin: 10px;
    }

</style>