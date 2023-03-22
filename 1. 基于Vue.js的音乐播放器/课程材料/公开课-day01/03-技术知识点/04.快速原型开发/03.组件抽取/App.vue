<template>
  <div>
    <h2>我是APP</h2>
    <player></player>
    <player></player>
  </div>
</template>

<script>
// 导入组件
import player from './components/player.vue'
export default {
  // 注册
  // 属性名 不要用关键字即可
  components:{
    player// player:player
  }
}
</script>

<style>

</style>