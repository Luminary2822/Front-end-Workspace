<template>
  <div id="app">
    <!-- 路由出口 -->
    <router-view/>
    <!-- <Parent/> -->
  </div>
</template>

<script>
import Parent from './components/Parent.vue'
export default {
    components:{
      Parent
    }
}
</script>

<style lang="less">
//引入公告的初始化的css 
// @import url('./assets/css/base.css');
</style>
