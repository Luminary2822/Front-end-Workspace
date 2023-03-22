<template>
  <!-- 主体区域 -->
  <section id="player">
    <!-- 输入框 -->
    <header class="header">
      <h1>
        <a href="http://www.itheima.com/">
          <img src="./assets/img/black.png" alt="" />
        </a>
        小黑播放器
      </h1>
      <input
        autofocus="autofocus"
        autocomplete="off"
        placeholder="请输入歌名"
        class="new-todo"
        v-model="keywords"
        @keyup.enter="search"
      />
    </header>
    <!-- 列表区域 -->
    <section class="main">
      <ul class="music-list">
        <li class="music" v-for="(item, index) in results" :key="index">
          <div class="view">
            <span class="index">{{ index + 1 }}.</span>
            <label>{{ item.name }}</label>
            <button class="play" @click="play(item.id)"></button>
          </div>
        </li>
      </ul>
    </section>
    <!-- 统计和清空 -->
    <footer class="footer">
      <audio :src="songUrl" controls autoplay></audio>
    </footer>
  </section>
</template>

<script>
  /*
    接口1:歌曲搜索
      地址:https://autumnfish.cn/search
      方法:get
      参数:keywords  搜索关键字

    接口2:获取歌曲播放地址
      地址:https://autumnfish.cn/song/url
      方法:get
      参数:id  歌曲id
  */
  // 导入样式
  import './assets/css/player.css'
  // 导入下载的 axios
  import axios from 'axios'
  export default {
    data() {
      return {
        keywords: '',
        // 搜索结果
        results: [],
        // 歌曲地址
        songUrl: ''
      }
    },
    methods: {
      search() {
        axios({
          url: 'https://autumnfish.cn/search',
          method: 'get',
          params: {
            keywords: this.keywords
          }
        }).then(res => {
          this.results = res.data.result.songs
        })
      },
      play(id) {
        axios({
          url: 'https://autumnfish.cn/song/url',
          method: 'get',
          params: {
            id
          }
        }).then(res => {
          this.songUrl = res.data.data[0].url
        })
      }
    }
  }
</script>

<style></style>
