<template>
  <el-config-provider :locale="zhCn">
    <router-view />
  </el-config-provider>
</template>

<script setup>
// 引入 Element Plus 的官方中文语言包
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
</script>

<style>
/* 全局基础样式重置 */
body {
  margin: 0;
  padding: 0;
  /* 优化了字体栈，优先使用系统默认的无衬线字体，显示更现代 */
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
  /* 设置一个和 Layout 统一的浅色底色，防止切换页面时边缘闪现白光 */
  background-color: #f0f2f5; 
}

/* 消除所有原生 a 标签的下划线和颜色干扰 */
a {
  text-decoration: none;
}
</style>