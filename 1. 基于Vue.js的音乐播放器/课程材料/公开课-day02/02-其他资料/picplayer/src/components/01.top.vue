<!--
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-08 19:26:45
 * @LastEditTime: 2022-04-09 14:22:06
-->
<template>
<div class="top">
    <img src="../assets/01.top.png">
    <input type="text" v-model = "inputValue" @keyup.enter = "toSearch">
</div>
</template>

<script>
export default {
    data() {
        return {
            inputValue: "",
        };
    },
    methods: {
        toSearch() {
            // 如果为空，提示
            if(this.inputValue == ''){
                alert('请输入内容')
            }else{
                // 否则，跳转
                // 编程式导航
                this.$router.push('/result')
                // 携带数据
                // this.$router.push("/result?q=" + this.inputValue)
                // ES6模板字符串
                this.$router.push(`/result?q=${this.inputValue}`)
            }


            
            
        },
    },
}
</script>

<style>
.top{
    /* 开启弹性布局 */
    display: flex;
    align-items: center;
    background-color:#F9F9F9;
}
.top img{
    width: 88%;

}
</style>
