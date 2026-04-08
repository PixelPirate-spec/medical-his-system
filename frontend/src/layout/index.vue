<template>
  <el-container class="layout-container">
    <!-- Sidebar -->
    <el-aside :width="isCollapse ? '64px' : '220px'" class="sidebar-container">
      <div class="logo-container">
        <el-icon v-if="isCollapse" class="logo-icon"><Platform /></el-icon>
        <span v-else class="logo-text">🏥 医疗 HIS 系统</span>
      </div>
      <el-menu
        :default-active="activeMenu"
        class="el-menu-vertical"
        :collapse="isCollapse"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
        :collapse-transition="false"
        router
      >
        <el-menu-item index="/dashboard">
          <el-icon><Odometer /></el-icon>
          <template #title>数据看板</template>
        </el-menu-item>
        <el-menu-item index="/department">
          <el-icon><OfficeBuilding /></el-icon>
          <template #title>科室管理</template>
        </el-menu-item>
        <el-menu-item index="/doctor">
          <el-icon><User /></el-icon>
          <template #title>医生排班</template>
        </el-menu-item>
        <el-menu-item index="/consultation">
          <el-icon><Document /></el-icon>
          <template #title>就诊记录</template>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <!-- Main Content Area -->
    <el-container class="main-container">
      <!-- Header -->
      <el-header class="header-container">
        <div class="header-left" @click="toggleCollapse">
          <el-icon class="collapse-icon">
            <Fold v-if="!isCollapse" />
            <Expand v-else />
          </el-icon>
        </div>
        <div class="header-right">
          <el-dropdown trigger="click">
            <span class="user-dropdown">
              <el-avatar :size="32" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" />
              <span class="username">管理员</span>
              <el-icon class="el-icon--right"><arrow-down /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item divided>退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>

      <!-- Main Body -->
      <el-main class="app-main">
        <router-view v-slot="{ Component }">
          <transition name="fade-transform" mode="out-in">
            <component :is="Component" />
          </transition>
        </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import {
  Odometer, OfficeBuilding, User, Document,
  Fold, Expand, ArrowDown, Platform
} from '@element-plus/icons-vue'

const route = useRoute()
const isCollapse = ref(false)

const activeMenu = computed(() => {
  return route.path
})

const toggleCollapse = () => {
  isCollapse.value = !isCollapse.value
}
</script>

<style scoped>
.layout-container {
  height: 100vh;
  width: 100%;
}

.sidebar-container {
  background-color: #304156;
  transition: width 0.3s;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.logo-container {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #2b3643;
  color: #fff;
  font-size: 18px;
  font-weight: 600;
  white-space: nowrap;
}

.logo-icon {
  font-size: 24px;
}

.el-menu-vertical {
  border-right: none;
  flex: 1;
}

.el-menu-vertical:not(.el-menu--collapse) {
  width: 220px;
}

.header-container {
  height: 60px;
  background-color: #ffffff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  z-index: 10;
}

.header-left {
  cursor: pointer;
  display: flex;
  align-items: center;
}

.collapse-icon {
  font-size: 24px;
  color: #606266;
}

.collapse-icon:hover {
  color: #409EFF;
}

.header-right {
  display: flex;
  align-items: center;
}

.user-dropdown {
  display: flex;
  align-items: center;
  cursor: pointer;
  color: #606266;
}

.username {
  margin-left: 8px;
  font-size: 14px;
}

.app-main {
  background-color: #f0f2f5;
  padding: 20px;
  position: relative;
  overflow: auto;
}

/* fade-transform transition */
.fade-transform-leave-active,
.fade-transform-enter-active {
  transition: all 0.3s;
}

.fade-transform-enter-from {
  opacity: 0;
  transform: translateX(-30px);
}

.fade-transform-leave-to {
  opacity: 0;
  transform: translateX(30px);
}
</style>
