import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/index.vue'

const routes = [
  {
    path: '/',
    redirect: '/dashboard'
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

export default router
