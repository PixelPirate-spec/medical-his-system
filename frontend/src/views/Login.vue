<template>
  <div class="login-container">
    <el-card class="login-card">
      <div class="logo-header">
        <el-icon class="logo-icon"><Platform /></el-icon>
        <h2 class="title">医疗 HIS 系统</h2>
        <p class="subtitle">{{ isLogin ? '登录您的账号' : '创建新账号' }}</p>
      </div>

      <!-- 登录表单 -->
      <transition name="fade-transform" mode="out-in">
        <div v-if="isLogin" key="login" class="form-wrapper">
          <el-form :model="loginForm" :rules="loginRules" ref="loginFormRef" size="large">
            <el-form-item prop="username">
              <el-input v-model="loginForm.username" placeholder="账号 / 邮箱" prefix-icon="User" />
            </el-form-item>
            <el-form-item prop="password">
              <el-input v-model="loginForm.password" type="password" placeholder="密码" prefix-icon="Lock" show-password @keyup.enter="handleLogin" />
            </el-form-item>
            <el-form-item class="action-item">
              <el-button type="primary" class="submit-btn" :loading="loading" @click="handleLogin">
                登录
              </el-button>
            </el-form-item>
            <div class="switch-mode">
              <span>还没有账号？</span>
              <el-link type="primary" :underline="false" @click="toggleMode">创建账号</el-link>
            </div>
          </el-form>
        </div>

        <!-- 注册表单 -->
        <div v-else key="register" class="form-wrapper">
          <el-form :model="registerForm" :rules="registerRules" ref="registerFormRef" size="large">
            <el-form-item prop="username">
              <el-input v-model="registerForm.username" placeholder="选择账号" prefix-icon="User" />
            </el-form-item>
            <el-form-item prop="password">
              <el-input v-model="registerForm.password" type="password" placeholder="设置密码" prefix-icon="Lock" show-password />
            </el-form-item>
            <el-form-item prop="roleName">
              <el-select v-model="registerForm.roleName" placeholder="选择角色" style="width: 100%;">
                <el-option label="患者 (PATIENT)" value="PATIENT" />
                <el-option label="医生 (DOCTOR)" value="DOCTOR" />
                <el-option label="管理员 (ADMIN)" value="ADMIN" />
              </el-select>
            </el-form-item>
            <el-form-item class="action-item">
              <el-button type="primary" class="submit-btn" :loading="loading" @click="handleRegister">
                注册
              </el-button>
            </el-form-item>
            <div class="switch-mode">
              <span>已有账号？</span>
              <el-link type="primary" :underline="false" @click="toggleMode">直接登录</el-link>
            </div>
          </el-form>
        </div>
      </transition>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { Platform } from '@element-plus/icons-vue'
import request from '../utils/request'

const router = useRouter()
const isLogin = ref(true)
const loading = ref(false)

const loginFormRef = ref(null)
const registerFormRef = ref(null)

const loginForm = reactive({
  username: '',
  password: ''
})

const registerForm = reactive({
  username: '',
  password: '',
  roleName: 'PATIENT'
})

const loginRules = {
  username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
}

const registerRules = {
  username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  roleName: [{ required: true, message: '请选择角色', trigger: 'change' }]
}

const toggleMode = () => {
  isLogin.value = !isLogin.value
  // Reset forms on toggle
  if (isLogin.value) {
    if (loginFormRef.value) loginFormRef.value.resetFields()
  } else {
    if (registerFormRef.value) registerFormRef.value.resetFields()
  }
}

const handleLogin = async () => {
  if (!loginFormRef.value) return
  await loginFormRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true
      try {
        const response = await request.post('/auth/login', {
          username: loginForm.username,
          password: loginForm.password
        })

        if (response.code === 200) {
          ElMessage.success('登录成功')
          const { token, user } = response.data
          localStorage.setItem('token', token)
          localStorage.setItem('user', JSON.stringify(user))
          router.push('/')
        } else {
          ElMessage.error(response.message || '登录失败')
        }
      } catch (error) {
        // error handled in interceptor globally, but can catch here if needed
      } finally {
        loading.value = false
      }
    }
  })
}

const handleRegister = async () => {
  if (!registerFormRef.value) return
  await registerFormRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true
      try {
        const response = await request.post('/auth/register', {
          username: registerForm.username,
          password: registerForm.password,
          roleName: registerForm.roleName
        })

        if (response.code === 200) {
          ElMessage.success('注册成功，请登录')
          // Auto switch to login
          toggleMode()
          loginForm.username = registerForm.username
        } else {
          ElMessage.error(response.message || '注册失败')
        }
      } catch (error) {
        // global interceptor handles it
      } finally {
        loading.value = false
      }
    }
  })
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5; /* 极其干净的浅灰色 */
}

.login-card {
  width: 100%;
  max-width: 440px;
  border-radius: 12px;
  border: none; /* 无边框 */
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04); /* 柔和阴影 */
  padding: 40px 32px;
  background: #ffffff; /* 纯白色 */
}

.logo-header {
  text-align: center;
  margin-bottom: 32px;
}

.logo-icon {
  font-size: 48px;
  color: #1a73e8; /* 借鉴 Google 蓝色 */
  margin-bottom: 16px;
}

.title {
  font-size: 24px;
  font-weight: 400;
  color: #202124;
  margin: 0 0 8px 0;
}

.subtitle {
  font-size: 16px;
  color: #5f6368;
  margin: 0;
  font-weight: 400;
}

.form-wrapper {
  margin-top: 10px;
}

/* 覆盖 Element Plus input 默认样式以增加现代感 */
:deep(.el-input__wrapper) {
  box-shadow: 0 0 0 1px #dadce0 inset; /* 默认浅灰色边框 */
  border-radius: 6px;
  padding: 4px 12px;
  transition: box-shadow 0.2s ease;
}

:deep(.el-input__wrapper.is-focus) {
  box-shadow: 0 0 0 2px #1a73e8 inset !important; /* 聚焦时 Google 蓝呼吸感 */
}

:deep(.el-input__inner) {
  font-size: 15px;
  color: #202124;
}

.action-item {
  margin-top: 28px;
  margin-bottom: 16px;
}

.submit-btn {
  width: 100%;
  border-radius: 6px;
  font-size: 15px;
  font-weight: 500;
  letter-spacing: 0.5px;
  background-color: #1a73e8;
  border-color: #1a73e8;
  height: 44px;
}

.submit-btn:hover {
  background-color: #1b66c9;
  border-color: #1b66c9;
}

.switch-mode {
  text-align: center;
  font-size: 14px;
  color: #5f6368;
  margin-top: 16px;
}

.switch-mode .el-link {
  font-weight: 500;
  color: #1a73e8;
  margin-left: 4px;
}

/* 过渡动画 */
.fade-transform-leave-active,
.fade-transform-enter-active {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.fade-transform-enter-from {
  opacity: 0;
  transform: translateX(-15px);
}

.fade-transform-leave-to {
  opacity: 0;
  transform: translateX(15px);
}
</style>
