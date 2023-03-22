<template>
  <div>
      <h2>第二层</h2>
      <p>----获取爷爷的数据-------------</p>
      <p>info:{{info}}</p>
      <p>msg:{{msg}}</p>
      <h4>第二层的数据通过event-bus传递给层3</h4>
      <button @click="send">点击发送数据</button>
  </div>
</template>

<script>
export default {
    //接受数据
    inject:['msg','info'],
    methods:{
        send(){
            //调用eventBus 
            this.$bus.$emit('id',123456)
        }
    }
}
</script>

<style>

</style>