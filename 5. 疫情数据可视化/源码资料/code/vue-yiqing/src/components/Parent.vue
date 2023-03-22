<template>
  <div>
      <h2>parent</h2>
      <p>msg:{{msg}}</p>
      <p ref='p'>获取dom</p>
      <button @click="getDom">获取dom</button>
      <button @click="getChild">获取children</button>
      <hr>
      <Ceng1 :msg='msg' @sendData='sendData'/>
      <hr>
      <Ceng3 ref='ceng' />
  </div>
</template>

<script>
//父子组件嵌套：生命周期函数顺序：beforeCreate -- created -- beforeMount -- 子组件4个 --父组件的mounted 
import Ceng1 from './Ceng1.vue'
import Ceng3 from './Ceng3.vue'
export default {
    data(){
        return{
            msg:'父组件数据'
        }
    },
    //深层传递数据
    provide(){
        return {
            info:'啊啦啦啦',
            msg:this.msg
        }
    },
    methods:{
        sendData(val){
            console.log('子组件的数据:',val);
        },
        getChild(){
            console.log(this.$children);
        },
        getDom(){
            console.log(this.$refs);
        }
    },
    components:{
        Ceng1,
        Ceng3
    },

}
</script>

<style>

</style>