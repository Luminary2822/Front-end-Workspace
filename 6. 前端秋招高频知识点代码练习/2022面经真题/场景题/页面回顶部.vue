<!--
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-07-21 22:29:24
 * @LastEditTime: 2022-07-24 17:25:17
-->
<template>
    <div class="top" @click="toTop" v-if="showArrow">∧</div>
</template>

<script>
export default {
    name: '页面回顶部',

    data() {
        return {
            // 在达到一定高度的时候才会显示
            showArrow: false
        };
    },

    mounted() {
        // 添加事件处理程序
        window.addEventListener('scroll', this.scrollToTop)
        
    },
    destroyed () {
        // 移除事件处理程序
        window.removeEventListener('scroll', this.scrollToTop)
    },

    methods: {
        // 点击箭头回到顶部方法，加计时器是为了过渡顺滑，顺滑的不断向上回顶
        toTop () {
            const that = this
            // 设置定时器每间隔一段时间就执行
            let timer = setInterval(() => {
                // 根据当前滚动的距离设置向上回弹的速度
                let ispeed = Math.floor(-that.scrollTop / 5)
                // 设置浏览器滚动距离为当前滚动距离+速度
                document.documentElement.scrollTop = document.body.scrollTop = that.scrollTop + ispeed
                // 当回到顶部之后清楚定时器
                if (that.scrollTop === 0) {
                    clearInterval(timer)
                }
            }, 16)
        },
         // 为了计算距离顶部的高度，当高度大于60显示回顶部图标，小于60则隐藏
        scrollToTop () {
            const that = this
            let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
            that.scrollTop = scrollTop
            if (that.scrollTop > 60) {
            that.showArrow = true
            } else {
            that.showArrow = false
            }
        },
    },
};
</script>

<style lang="scss" scoped>
 .top {
      position: fixed;
      display: inline-block;
      width: 50px;
      bottom: 1rem;
      right: 1rem;
      font-size: 30px;
      border-radius: 50%;
      border: 1px solid #ccc;
      height: 50px;
      line-height: 50px;
      text-align: center;
      background: #fff;
    }
</style>