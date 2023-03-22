## 丁香医生疫情 
    移动端的疫情数据信息介绍 

## 技术点
1. 组件拆分
2. 网络请求axios 
3. vue中使用echarts图表
4. Vue中封装的tabs组件-自定义封装组件
5. Vue中使用swiper 
6. Vue中使用vant库


## 项目初始化
1. 创建项目 vue create vue-yiqing
2. 安装：npm i axios  vant -S
3. 删除无用的组件
4. css初始化
5. 移动端适配方案： 
   1. phone.js 引入main.js   / 媒体查询设置 各各屏幕的css 
   2. vw布局    100vw=750px 
6. 配置一个公共的接口文档



## 疫情信息
1. 处理接口地址
2. http://api.tianapi.com/ncov/index?key=b5919dd6f573907e378d0a6be7a78ff3


## Vant库使用
1. 安装：npm i vant -S 
2. 自动按需引入组件 (推荐):npm i babel-plugin-import -D
3. 对于使用 babel7 的用户，可以在 babel.config.js 中配置
        module.exports = {
            plugins: [
                ['import', {
                libraryName: 'vant',
                libraryDirectory: 'es',
                style: true
                }, 'vant']
            ]
        };

4. 创建一个vant.js插件使用 vant.js
5. main.js 引入vant库
    import './plugins/vant'


## Echarts
1. 安装echarts: npm i echarts@4.x -S 
2. 使用方式
   1. 方式1：组件内部使用单独使用
      1. 导入：import echarts from 'echarts'
      2. 直接使用

   2. 方式2：挂载到vue原型上 所有组件都可以使用
      1. 导入：import echarts from 'echarts'
      2. main.js挂载：Vue.prototype.$echarts = echarts

   3. 方式3：开发成vue插件 echarts
      1. 创建js文件：const install = function (Vue, options) {}
      2. 导入main.js 
        //使用开发的插件 
        import echarts from './plugins/echarts'
        Vue.use(echarts)

## 地图点击城市--切换城市界面
1. 创建一个城市界面  
2. 配置动态路由 接受城市名称：city
   


## md5加密
1. 安装:  npm i md5-js -S  
2. 引入md5模块  
    import md5 from 'md5-js'
3. 使用加密处理 
   let sign =md5('加密规则')

4. 规则：md5('appid6938city_name北京formatjson秘钥')


## vant库-轮播图


## swiper使用
1. 参考网址：https://github.com/surmon-china/vue-awesome-swiper
2. 使用步骤：
    1. 安装： npm install swiper@5.x vue-awesome-swiper --save
    2. main.js 导入文件
        import VueAwesomeSwiper from 'vue-awesome-swiper'
        import 'swiper/css/swiper.css'
        Vue.use(VueAwesomeSwiper)
     3. 组件使用
        <template>
            <swiper ref="mySwiper" :options="swiperOptions">
                <swiper-slide>Slide 1</swiper-slide>
                <swiper-slide>Slide 2</swiper-slide>
                <swiper-slide>Slide 3</swiper-slide>
                <swiper-slide>Slide 4</swiper-slide>
                <swiper-slide>Slide 5</swiper-slide>
                <div class="swiper-pagination" slot="pagination"></div>
            </swiper>
        </template>  

    4. 参数配置
        ```
        <script>
        export default {
            name: 'carrousel',
            data() {
            return {
                swiperOptions: {
                pagination: {
                    el: '.swiper-pagination'
                },
                // Some Swiper option/callback...
                }
            }
            },
            computed: {
            swiper() {
                return this.$refs.mySwiper.$swiper
            }
            },
            mounted() {
            console.log('Current Swiper instance object', this.swiper)
            this.swiper.slideTo(3, 1000, false)
            }
        }
        </script>
        ``` 

## 城市出行-聚合数据
接口来源（聚合数据）：https://www.juhe.cn/docs/api/id/566 

注册流程：
    注册后   实名认证
    需要等待一两分钟，刷新页面  ，认证成功
    请求示例：http://apis.juhe.cn/springTravel/citys?key=keyid
    参数 ： key   ，获取位置 ： 右上角个人中心—数据中心—我的api 
    
接口：key 
1. key=171e165a7d991c5f6ecd5194c54778ef
2. key=6abf107dc48af5c2745ae63e8c125342
3. key=25309d1d363848790921460a73866c0c
4. key=ea3295121216b176e1a6b8af7fcd3dcf
5. key=674d6ec65afc51339176339dc899e16e
6. key=f3e50a7c0bce34a492c291db993d0c04


## Vue组件之间的数据传递方式
1. 父传子： prop传递数据   子传父：自定义事件
2. 父组件实例：$parent
3. 子组件实例：$chldren 
4. 兄弟元素：子组件数据给父组件---另外的子组件 
5. dom元素和子组件实例：$refs 
6. 获取根组件：$root 
7. 深层传递：provide / inject 
8. 原型链数据： Vue.prototype.xx =xx
9. 本地存储：localStorage.setItem()/getItem() 
10. eventBus 中央系统 - 跨组件之间传递数据 
11. $attrs 和 $listeners
12. Vuex 

### eventBus 中央系统 - 跨组件之间传递数据  
1. 定义eventBus.js
2. 创建eventbus
    import Vue from 'vue'
    var Bus=new Vue();

     export default Bus

3. main.js引入文件：
    import Bus from './utils/eventBus.js'
    Vue.prototype.$bus=Bus

4. 使用语法：
    传递数据：
       this.$bus.$emit("sun", "哈哈哈");

    接受数据：
            this.$bus.$on("sun",val=>{
                    val
              });
    解绑事件：
        this.$bus.$off("sun");


    缺点：不支持响应式  维护的“灾难” 

