<template>
  <div>
    <div class="title">世界疫情地图</div>
    <!-- 地图容器 -->
    <div id="main" style="width: 7.5rem; height: 7rem"></div>
  </div>
</template>

<script>
import api from "../../../api/index";
export default {
  mounted() {
    //-------------------------
    api.getWorldData().then((res) => {
      console.log("--世界---", res.data);
      let world = res.data.retdata;
      if(!world){
        this.$myChart.worldMap("main", []);
        return;
      }
      let arr = []; //累计
      for (let i = 0; i < world.length; i++) {
        let obj = {};
        obj.name = world[i].xArea;
        obj.value = world[i].confirm; //累计确诊人数   curConfirm现在确诊人数
        arr.push(obj);
      }
       this.$myChart.worldMap("main", arr);
    });
   
  },
};
</script>

<style lang='less' scoped>
.title {
  margin: 0.2rem;
  padding-top: 0.2rem;
  border-top: 1px solid #eee;
}
.title::before {
  content: "";
  border-left: 0.1rem solid rgb(9, 60, 202);
  font-size: 0.26rem;
  margin-right: 0.1rem;
}
#main {
  background: #f5f5f5;
}
</style>