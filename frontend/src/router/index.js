import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/index.vue'

const routes = [
  {
    path: '/',
    redirect: '/dashboard'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/',
    component: Layout,
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('../views/Dashboard.vue'),
        meta: { title: '数据看板', icon: 'Odometer' }
      },
      {
        path: 'department',
        name: 'Department',
        component: () => import('../views/Department.vue'),
        meta: { title: '科室管理', icon: 'OfficeBuilding' }
      },
      {
        path: 'doctor',
        name: 'Doctor',
        component: () => import('../views/Doctor.vue'),
        meta: { title: '医生排班', icon: 'User' }
      },
      {
        path: 'consultation',
        name: 'Consultation',
        component: () => import('../views/Consultation.vue'),
        meta: { title: '就诊记录', icon: 'Document' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 全局前置守卫
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')

  if (to.path === '/login') {
    if (token) {
      // 如果已经登录，直接跳转到首页
      next('/')
    } else {
      next()
    }
  } else {
    // 访问非登录页面，校验 token
    if (token) {
      next()
    } else {
      // 没登录，拦截跳转到登录页
      next('/login')
    }
  }
})

export default router
