<!--
 * @Description: 
 * @Author: Luminary
 * @Date: 2020-03-05 19:23:52
 * @LastEditTime: 2022-04-05 20:06:59
-->
<!--单文件vue组件无法直接在浏览器运行-->
<template>
  <!-- 结构 -->
  <div class="app">
      <h2 @click="sayHello">标题</h2>
  </div>
</template>

<script>
// 写逻辑
export default {
    methods: {
        sayHello(){
            alert("你好吗！！！！")
        }
    },
}
</script>

<style>

/* 写样式 */
.app{
    background-color: skyblue;
}

</style>