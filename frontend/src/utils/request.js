import axios from 'axios'
import router from '../router'
import { ElMessage } from 'element-plus'

const service = axios.create({
  baseURL: 'http://localhost:8080/api', // backend base url
  timeout: 10000 // request timeout
})

// Request Interceptor
service.interceptors.request.use(
  config => {
    // Inject the token from localStorage if it exists
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// Response Interceptor
service.interceptors.response.use(
  response => {
    const res = response.data
    // Optional: You could intercept specific business logic code errors here if needed
    // Assuming backend returns Result<T> structure correctly
    return res
  },
  error => {
    if (error.response) {
      if (error.response.status === 401) {
        // Token missing or expired or invalid
        ElMessage.error('登录状态已过期，请重新登录')
        localStorage.removeItem('token')
        localStorage.removeItem('user')
        router.push('/login')
      } else {
        ElMessage.error(error.response.data?.message || '服务器请求异常')
      }
    } else {
      ElMessage.error('网络异常，请检查您的连接')
    }
    return Promise.reject(error)
  }
)

export default service
