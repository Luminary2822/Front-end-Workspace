<template>
  <div class="test">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
  export default {
    name: 'Test',
    data () {
      return {
        msg: '这是一个默认渲染的测试组件'
      }
    }
  }
</script>
