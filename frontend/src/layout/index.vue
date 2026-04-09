<template>
  <el-container class="app-wrapper">
    <el-aside width="210px" class="sidebar-container">
      <div class="logo-container">
        <span class="logo-text">🏥 HIS System</span>
      </div>
      
      <el-menu
        :default-active="route.path"
        class="el-menu-vertical"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
        router
      >
        <el-menu-item index="/dashboard">
          <el-icon><DataLine /></el-icon>
          <span>数据看板</span>
        </el-menu-item>

        <el-menu-item index="/department" v-if="userRole === 'ADMIN'">
          <el-icon><OfficeBuilding /></el-icon>
          <span>科室管理</span>
        </el-menu-item>
        
        <el-menu-item index="/doctor" v-if="userRole === 'ADMIN'">
          <el-icon><User /></el-icon>
          <span>医生管理</span>
        </el-menu-item>

        <el-menu-item index="/consultation">
          <el-icon><Document /></el-icon>
          <span>{{ userRole === 'PATIENT' ? '我的挂号' : '就诊工作台' }}</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container class="main-container">
      <el-header class="header-container">
        <div class="left-header">
          </div>
        
        <div class="right-header">
          <el-tag :type="roleTagType" effect="dark" class="role-tag">
            {{ roleDisplayName }}
          </el-tag>
          
          <el-dropdown trigger="click" @command="handleCommand">
            <span class="avatar-wrapper">
              <el-avatar :size="36" :src="defaultAvatar" />
              <span class="username">{{ username }}</span>
              <el-icon class="el-icon--right"><arrow-down /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="profile">
                  <el-icon><UserFilled /></el-icon>个人中心
                </el-dropdown-item>
                <el-dropdown-item divided command="logout">
                  <el-icon><SwitchButton /></el-icon>退出登录
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>

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
import { ref, computed, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'
import { DataLine, OfficeBuilding, User, Document, ArrowDown, UserFilled, SwitchButton } from '@element-plus/icons-vue'

const router = useRouter()
const route = useRoute()

// 状态定义
const username = ref('加载中...')
const userRole = ref('')
const defaultAvatar = 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'

// 解析 JWT Token 的简易方法 (获取 payload)
const parseJwt = (token) => {
  try {
    const base64Url = token.split('.')[1]
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/')
    const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
    }).join(''))
    return JSON.parse(jsonPayload)
  } catch (error) {
    return null
  }
}

// 初始化：从 localStorage 获取 Token 并解析用户信息
onMounted(() => {
  const token = localStorage.getItem('token')
  if (token) {
    const decoded = parseJwt(token)
    if (decoded) {
      // 这里的 sub 或 roleName 取决于你后端 JwtUtils 是怎么塞入数据的
      username.value = decoded.sub || decoded.username || '未知用户'
      userRole.value = decoded.role || decoded.roleName || 'PATIENT'
    }
  }
})

// 计算属性：根据角色显示不同的 Tag 颜色和文字
const roleDisplayName = computed(() => {
  const map = {
    'ADMIN': '👑 管理员',
    'DOCTOR': '👨‍⚕️ 医生',
    'PATIENT': '🤒 患者'
  }
  return map[userRole.value] || '未知身份'
})

const roleTagType = computed(() => {
  const map = {
    'ADMIN': 'danger',
    'DOCTOR': 'primary',
    'PATIENT': 'success'
  }
  return map[userRole.value] || 'info'
})

// 处理下拉菜单点击事件
const handleCommand = (command) => {
  if (command === 'logout') {
    // 退出登录逻辑
    localStorage.removeItem('token')
    ElMessage.success('已安全退出系统')
    router.push('/login')
  } else if (command === 'profile') {
    // 跳转到个人中心，真正的动态履历/病史展示将在那个页面完成！
    router.push('/profile')
  }
}
</script>

<style scoped>
/* 充满全屏 */
.app-wrapper {
  height: 100vh;
  width: 100%;
}

/* 侧边栏样式 */
.sidebar-container {
  background-color: #304156;
  transition: width 0.28s;
  overflow: hidden;
}

.logo-container {
  height: 60px;
  line-height: 60px;
  text-align: center;
  background: #2b3643;
}

.logo-text {
  color: #fff;
  font-weight: 600;
  font-size: 18px;
}

.el-menu-vertical {
  border-right: none; /* 去除多余边框 */
}

/* Header 样式 */
.header-container {
  height: 60px;
  background-color: #ffffff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  z-index: 10;
}

.right-header {
  display: flex;
  align-items: center;
}

.role-tag {
  margin-right: 20px;
}

.avatar-wrapper {
  display: flex;
  align-items: center;
  cursor: pointer;
  padding: 0 8px;
}

.username {
  margin: 0 8px;
  font-size: 14px;
  color: #333;
}

/* 主干内容区 */
.app-main {
  background-color: #f0f2f5;
  padding: 20px;
  overflow-y: auto;
}

/* 页面切换动画 */
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