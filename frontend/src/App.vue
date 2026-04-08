<template>
  <div style="padding: 40px; font-family: sans-serif;">
    <h1>🏥 医疗 HIS 系统 (前端权限联调测试)</h1>
    
    <div style="background: #f0f2f5; padding: 20px; border-radius: 8px; margin-bottom: 20px;">
      <strong style="margin-right: 10px;">👤 当前模拟身份：</strong>
      <select v-model="mockRole" @change="fetchData" style="padding: 5px 10px; font-size: 16px;">
        <option value="ADMIN">👑 管理员 (能看全院所有记录)</option>
        <option value="DOCTOR">🩺 医生 (仅看医生ID为 2 的接诊记录)</option>
        <option value="PATIENT">🤒 患者 (仅看患者ID为 2 的看病记录)</option>
      </select>
      <p style="color: #666; font-size: 0.9em; margin-top: 10px;">
        💡 提示：试着切换下拉框，观察下方的列表会不会根据角色自动过滤！
      </p>
    </div>

    <h2>📋 就诊记录实时数据：</h2>
    
    <p v-if="loading" style="color: gray;">正在呼叫后端 8080 端口请求数据...</p>
    <p v-else-if="records.length === 0" style="color: red;">当前角色下没有查到任何数据哦~</p>
    
    <ul v-else>
      <li v-for="record in records" :key="record.id" style="margin-bottom: 10px;">
        <strong>就诊记录 ID: {{ record.id || record.consultationId }}</strong> 
        - 👨‍⚕️ 医生ID: {{ record.doctorId }} 
        - 🤒 患者ID: {{ record.patientId }}
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

// 定义响应式变量
const records = ref([])
const loading = ref(true)

// 默认模拟医生角色，假设当前登录的 ID 是 2
const mockRole = ref('DOCTOR')
const mockUserId = ref('2')

// 核心逻辑：把请求后端的动作封装成一个函数
const fetchData = async () => {
  loading.value = true
  records.value = [] // 切换时先清空旧数据
  
  try {
    // 1. 注意这里！URL 换成了获取“就诊记录”的接口
    const response = await axios.get('http://localhost:8080/api/consultations/list', {
      
      // 2. 魔法就在这里！通过 headers 把当前选中的角色偷偷传给后端
      headers: {
        'X-User-Id': mockUserId.value,
        'X-Role': mockRole.value
      }
      
    })
    
    // 兼容处理：如果 Jules 已经加了 Result 统一响应体，数据就在 response.data.data 里
    // 如果还没加，就是在 response.data 里
    records.value = response.data.data || response.data
    
  } catch (error) {
    console.error('请求后端失败！请检查后端是否报错：', error)
  } finally {
    loading.value = false
  }
}

// 页面第一次加载时，自动调用一次
onMounted(() => {
  fetchData()
})
</script>