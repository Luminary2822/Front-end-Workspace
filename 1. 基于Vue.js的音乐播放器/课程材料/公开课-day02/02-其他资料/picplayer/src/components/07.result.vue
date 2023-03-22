<!--
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-09 13:32:32
 * @LastEditTime: 2022-04-09 14:36:42
-->
<template>
<div>
    <!-- tab切换 -->
    <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="用户管理" name="first">
            <!-- <img src="../assets/03.discovery.png"> -->
            <h2>页面1</h2> 
        </el-tab-pane>
        <el-tab-pane label="配置管理" name="second">
            <!-- <img src="../assets/04.playlist.png"> -->
            <h2>页面2</h2> 
        </el-tab-pane>
        <el-tab-pane label="角色管理" name="third">
            <!-- <img src="../assets/05.songs.png"> -->
            <h2>页面3</h2> 
        </el-tab-pane>
        <el-tab-pane label="定时任务补偿" name="fourth">
            <!-- <img src="../assets/06.mvs.png"> -->
            <h2>页面4</h2> 
        </el-tab-pane>
  </el-tabs>
  <el-pagination
   background
   layout="prev, pager, next"
   :total="1000">
</el-pagination>
  <img src="../assets/07.result.png">
</div>
  
</template>

<script>
export default {
    data() {
        return {
            activeName: 'second',
        };
    },
    // 生命周期钩子 created
    // 回调函数，添加之后自动执行
    // 内部可以通过this访问到vue实例
    created() {
        console.log(this.$route.query.q)
    },
    

}
</script>

<style>

</style>