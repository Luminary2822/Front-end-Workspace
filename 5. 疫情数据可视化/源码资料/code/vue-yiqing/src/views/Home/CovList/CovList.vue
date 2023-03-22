<template>
  <div class="cov-list">
    <div class="title">国内疫情列表数据</div>
    <van-row gutter="1" class="cov-row">
      <van-col span="4">
        <div class="cov-title">地区</div>
      </van-col>
      <van-col span="4">
        <div class="cov-title">现存确诊</div>
      </van-col>
      <van-col span="4">
        <div class="cov-title">累计确诊</div>
      </van-col>
      <van-col span="4">
        <div class="cov-title">死亡</div>
      </van-col>
      <van-col span="4">
        <div class="cov-title">治愈</div>
      </van-col>
      <van-col span="4">
        <div class="cov-title">疫情</div>
      </van-col>
    </van-row>
    <van-row
      gutter="2"
      v-for="item in retdata"
      :key="item.xArea"
      class="cov-row cov-bg"
    >
      <van-col span="4">
        <div class="item">{{ item.xArea }}</div>
      </van-col>
      <van-col span="4">
        <div class="item">{{ item.curConfirm }}</div>
      </van-col>
      <van-col span="4">
        <div class="item">{{ item.confirm }}</div>
      </van-col>
      <van-col span="4">
        <div class="item">{{ item.died }}</div>
      </van-col>
      <van-col span="4">
        <div class="item">{{ item.heal }}</div>
      </van-col>
      <van-col span="4">
        <div class="item"><a href="##" style="color: #029cdb">详情</a></div>
      </van-col>
    </van-row>
  </div>
</template>

<script>
import api from "../../../api/index";
export default {
  data() {
    return {
      retdata: [],
      activeNames:[],//默认展开的位置
    };
  },
  created() {
    api.getChina().then((res) => {
      console.log(res.data);
      this.retdata = res.data.retdata;
    });
  },
};
</script>

<style lang='less' scoped>
.cov-list {
  padding: 0.16rem;
  margin-top: 0.2rem;
  .title {
    margin-bottom: 0.3rem;
    font-size: 0.3rem;
    font-weight: bold;
    color: #666;
  }
  .title::before {
    content: "";
    border-left: 0.1rem solid rgb(9, 60, 202);
    font-size: 0.26rem;
    margin-right: 0.1rem;
  }
  .cov-row {
    text-align: center;
    line-height: 0.6rem;
    font-size: 0.26rem;
    .van-col:nth-child(1) .cov-title {
      background: #e3e7f3;
    }
    .van-col:nth-child(2) .cov-title {
      background: #f3bab0;
    }
    .van-col:nth-child(3) .cov-title {
      background: #e69a8d;
    }
    .van-col:nth-child(4) .cov-title {
      background: #b4c0d5;
    }
    .van-col:nth-child(5) .cov-title {
      background: #95db9a;
    }
    .van-col:nth-child(6) .cov-title {
      background: #e3e7f3;
    }
  }
  .cov-bg {
    .item {
      background: #f7f7f7;
    }
  }
}
</style>