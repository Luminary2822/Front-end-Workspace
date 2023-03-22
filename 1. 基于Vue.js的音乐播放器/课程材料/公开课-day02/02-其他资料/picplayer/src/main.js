/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2020-03-06 14:45:19
 * @LastEditTime: 2022-04-09 14:17:52
 */
import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false
// 导入
import VueRouter from 'vue-router'
// use
Vue.use(VueRouter)
// 导入 需要通过路由管理的组件
import discovery from './components/03.discovery.vue'
import playlist from './components/04.playlist.vue'
import songs from './components/05.songs.vue'
import mvs from './components/06.mvs.vue'
import result from './components/07.result.vue'
// 创建路由
let router = new VueRouter({
  routes:[
    // 配置地址和组件的对应关系
    {
      // 空地址
      path:"/",
      // 组件
      component:discovery
    },
    {
      // 地址
      path:"/discovery",
      // 组件
      component:discovery
    },
    {
      // 地址
      path:"/playlist",
      // 组件
      component:playlist
    },
    {
      // 地址
      path:"/songs",
      // 组件
      component:songs
    },
    {
      // 地址
      path:"/mvs",
      // 组件
      component:mvs
    },
    {
      // 地址
      path:"/result",
      // 组件
      component:result
    }
    

  ]

})

// 导入 element-ui
import ElementUI from 'element-ui'
// 导入 element-ui 的样式
import 'element-ui/lib/theme-chalk/index.css';
// use一下
Vue.use(ElementUI)



new Vue({
  render: h => h(App),
  // 挂载到Vue实例
  router // router:router
}).$mount('#app')
