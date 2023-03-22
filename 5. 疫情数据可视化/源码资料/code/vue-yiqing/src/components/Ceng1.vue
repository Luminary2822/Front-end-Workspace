<template>
  <div>
      <h3>第一层</h3>
      <p>获取父组件数据：{{msg }}</p>
      <button @click="send">发送数据</button>
      <button @click="getParent">获取父组件实例</button>
      <hr>
      <Ceng2 />
  </div>
</template>

<script>
import Ceng2 from './Ceng2.vue'
export default {
    props:['msg'],
    data(){
        return{
            son:'儿子'
        }
    },
    components:{
        Ceng2
    },
    methods:{
        send(){
            this.$emit('sendData',this.son)
        },
        getParent(){
            console.log(this.$parent);
        }
    }
}
</script>

<style>

</style>