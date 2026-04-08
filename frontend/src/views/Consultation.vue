<template>
  <div class="consultation-container">
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
      <h2>📋 就诊记录管理</h2>
      <el-tag type="primary">路由页面联调测试</el-tag>
    </div>
    
    <div style="background: #f8f9fa; padding: 20px; border-radius: 8px; margin-bottom: 20px; border: 1px solid #ebeef5;">
      <strong style="margin-right: 10px;">👤 接口权限模拟：</strong>
      <select v-model="mockRole" @change="fetchData" style="padding: 5px 10px; font-size: 16px; border-radius: 4px;">
        <option value="ADMIN">👑 管理员 (能看全院所有记录)</option>
        <option value="DOCTOR">🩺 医生 (仅看医生ID为 2 的接诊记录)</option>
        <option value="PATIENT">🤒 患者 (仅看患者ID为 2 的看病记录)</option>
      </select>
      <p style="color: #909399; font-size: 0.9em; margin-top: 10px; margin-bottom: 0;">
        💡 提示：此下拉框用于测试后端 ThreadLocal 拦截器的数据隔离效果。
      </p>
    </div>

    <div v-if="loading" style="color: #909399; padding: 20px 0;">正在呼叫后端 8080 端口请求数据...</div>
    <div v-else-if="records.length === 0" style="color: #f56c6c; padding: 20px 0;">当前角色下没有查到任何数据哦~</div>
    
    <ul v-else style="padding-left: 20px;">
      <li v-for="record in records" :key="record.id || record.consultationId" style="margin-bottom: 12px; line-height: 1.6;">
        <strong style="color: #303133;">就诊记录 ID: {{ record.id || record.consultationId }}</strong> 
        <span style="color: #606266; margin-left: 10px;">- 👨‍⚕️ 医生ID: {{ record.doctorId }}</span> 
        <span style="color: #606266; margin-left: 10px;">- 🤒 患者ID: {{ record.patientId }}</span>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const records = ref([])
const loading = ref(true)
const mockRole = ref('DOCTOR')
const mockUserId = ref('2')

const fetchData = async () => {
  loading.value = true
  records.value = [] 
  
  try {
    const response = await axios.get('http://localhost:8080/api/consultations/list', {
      headers: {
        'X-User-Id': mockUserId.value,
        'X-Role': mockRole.value
      }
    })
    records.value = response.data.data || response.data
  } catch (error) {
    console.error('请求后端失败！请检查后端是否报错：', error)
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchData()
})
</script>

<style scoped>
.consultation-container {
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}
</style>