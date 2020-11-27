<template>
<div>
    <el-container>
        <el-header>Header</el-header>
        <el-main>Main</el-main>
    </el-container>
</div>
</template>

<script>
export default {
    name: "HelloWorld",
    data() {
        return {
            msg: "hello library"
        }
    }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style scoped>
.el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
}

.el-main {
    background-color: #E9EEF3;
    color: #333;
    line-height: 160px;
}
</style>
