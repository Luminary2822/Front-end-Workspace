<!--
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-08 20:04:44
 * @LastEditTime: 2022-04-08 22:08:05
-->
<template>
  <div class="index">
      <!-- 左侧导航 -->
      <div class="nav">
          <ul>
            <li>
                <router-link to="/discovery">发现音乐</router-link>
            </li>
            <li>
                <router-link to="/playlist">推荐歌单</router-link>
            </li>
            <li>
                <router-link to="/songs">最新音乐</router-link>
            </li>
            <li>
                <router-link to="/mvs">最新MV</router-link>
            </li>
          </ul>
      </div>
      <!-- 右侧容器 -->
      <div class="main">
          <!-- <discovery></discovery> -->
          <!-- 路由的出口 地址命中之后组件显示的位置 -->
          <router-view></router-view>

      </div>
  </div>
</template>

<script>
// 导入发现音乐组件
import discovery from './03.discovery.vue'
export default {
    // components:{
    //     discovery
    // }
}
</script>

<style>
    .index{
        display: flex;
        height: 610px;
    }
    .index .nav{
        width: 200px;
        background-color: #ededed;
    }
    .index .nav li{ 
        text-align: center;
        margin: 10px;
    }
    .main{
        background-color: orange;
        /* 使用剩余尺寸 */
        flex: 1;
    }
    /* 点击高亮效果 */
    /* 声明式导航router-link可以自动实现类名的切换，解析成a标签，点击哪个都会有相同的class */
    a.router-link-active{
        color: white;
        background-color: orange;
    }


</style>
