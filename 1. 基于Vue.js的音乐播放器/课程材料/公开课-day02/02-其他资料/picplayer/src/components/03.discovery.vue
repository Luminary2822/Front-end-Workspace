<!--
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-08 20:26:09
 * @LastEditTime: 2022-04-09 14:41:17
-->
<template>
 <div>
    <el-carousel :interval="4000" type="card" height="200px">
        <!-- 循环生成标签 -->
        <el-carousel-item v-for="item in 6" :key="item">
        <img src="../assets/luobo.jpg">
    </el-carousel-item>
  </el-carousel>
     <img src="../assets/03.discovery.png">
     <el-button type="success" @click="clickMe">成功按钮</el-button>
 </div>
</template>
<script>
export default {
    methods: {
        clickMe() {
            // alert('ssss')
            this.$message({
                message: '警告哦，这是一条警告消息',
                type: 'warning'
        });
            
        },
    },

}
</script>

<style>
img{
    width: 100%;
    /* height: 90%; */
}

</style>
