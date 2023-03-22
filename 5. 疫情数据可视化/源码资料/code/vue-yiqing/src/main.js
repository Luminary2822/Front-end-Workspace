import Vue from 'vue'
import App from './App.vue'
import router from './router'
//引入css初始化
import './assets/css/base.css'
//引入js适配移动端
// import './assets/js/phone'
//引入vant库
import './plugins/vant'
//挂载原型上 
// import echarts from 'echarts'
// Vue.prototype.$echarts = echarts;

//使用开发echarts插件 
import echarts from './plugins/echarts'
Vue.use(echarts)
//引入swiper
import VueAwesomeSwiper from 'vue-awesome-swiper'
import 'swiper/css/swiper.css'
Vue.use(VueAwesomeSwiper)

//中央系统
import Bus from './utils/event-bus.js'
Vue.prototype.$bus = Bus;

Vue.config.productionTip = false

new Vue({
  router,

  render: h => h(App)
}).$mount('#app')
