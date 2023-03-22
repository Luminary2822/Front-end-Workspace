<template>
  <div class="wuzi">
    <!-- 顶部logo -->
    <img width="100%" src="../../assets/images/wuzi.png" alt="" />
    <!-- 物资内容 -->
    <div class="wrapper">
      <!-- 选项卡：1. 自己写  2. vant库里面  3. 修改vant样式 /deep/ 类名{ }-->
      <!-- vant库-tabs标签 -->
      <van-tabs v-model="active" animated>
        <van-tab v-for="item in arr" :title="item.title" :key='item.title'>
            <!-- <img width="100%" src="../../assets/images/jiujing.png" alt="" /> -->
            <img width="100%" :src="item.img" alt="" />
        </van-tab>
      </van-tabs>

    </div>
  </div>
</template>

<script>
//导入图片
import img1 from '../../assets/images/jiujing.png'
import img2 from '../../assets/images/kouzhao.png'
export default {
  data() {
    return {
      active: 0,
      arr:[
          {
              img:img1,
              title:'消毒剂'
          },
          {
              img:img2,
              title:'口罩'
          }
      ]
    };
  },
};
</script>

<style lang='less' scoped>
.wuzi {
  background: #3343ff;
}
.wrapper {
  background: #3343ff;
  padding: 0.2rem;
}
</style>