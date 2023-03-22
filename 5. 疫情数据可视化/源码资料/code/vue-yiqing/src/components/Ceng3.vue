<template>
  <div>
      <h3>第三层</h3>
      <p>啊啦啦啦啦</p>
      <h4>这是层2的数据----</h4>
      <p>id:{{id}}</p>
  </div>
</template>

<script>
export default {
    data(){
        return {
            id:'',
        }
    },
    created(){
        //接受eventBus提供的数据
        this.$bus.$on('id',val=>{
            console.log('接受的层2的数据',val);
            this.id = val;
        })
    }
}
</script>

<style>

</style>