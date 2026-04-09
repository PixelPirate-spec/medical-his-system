<template>
  <div class="profile-page" v-loading="pageLoading">
    <section class="hero-card">
      <div>
        <p class="eyebrow">Profile Center</p>
        <h1 class="page-title">个人中心</h1>
        <p class="page-subtitle">
          根据当前登录身份展示最关键的个人信息与业务摘要。
        </p>
      </div>

      <div class="hero-meta">
        <el-tag size="large" :type="roleTagType" effect="light">
          {{ roleLabel }}
        </el-tag>
        <span class="welcome-text">{{ username }}</span>
      </div>
    </section>

    <el-alert
      v-if="usingMockData"
      title="当前展示的是前端预置数据，后端接口联通后会自动切换为真实数据。"
      type="warning"
      show-icon
      :closable="false"
      class="page-alert"
    />

    <el-alert
      v-if="tokenMissing"
      title="未检测到有效 Token，页面已回退为默认展示。"
      type="info"
      show-icon
      :closable="false"
      class="page-alert"
    />

    <section class="summary-grid">
      <el-card
        v-for="item in summaryCards"
        :key="item.label"
        shadow="hover"
        class="summary-card"
      >
        <p class="summary-label">{{ item.label }}</p>
        <p class="summary-value">{{ item.value }}</p>
        <p class="summary-hint">{{ item.hint }}</p>
      </el-card>
    </section>

    <template v-if="currentRole === 'PATIENT'">
      <section class="content-grid patient-grid">
        <el-card shadow="never" class="panel-card">
          <template #header>
            <div class="card-header">
              <span>基础信息</span>
              <el-tag type="success" effect="light">患者档案</el-tag>
            </div>
          </template>

          <el-form label-position="top" class="profile-form">
            <el-row :gutter="16">
              <el-col :xs="24" :sm="12">
                <el-form-item label="姓名">
                  <el-input v-model="patientProfile.name" readonly />
                </el-form-item>
              </el-col>
              <el-col :xs="24" :sm="12">
                <el-form-item label="年龄">
                  <el-input v-model="patientProfile.age" readonly />
                </el-form-item>
              </el-col>
              <el-col :xs="24" :sm="12">
                <el-form-item label="性别">
                  <el-input v-model="patientProfile.gender" readonly />
                </el-form-item>
              </el-col>
              <el-col :xs="24" :sm="12">
                <el-form-item label="联系电话">
                  <el-input v-model="patientProfile.phone" readonly />
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>

        <el-card shadow="never" class="panel-card">
          <template #header>
            <div class="card-header">
              <span>健康摘要</span>
              <el-tag type="primary" effect="light">近期概览</el-tag>
            </div>
          </template>

          <el-descriptions :column="1" border class="soft-descriptions">
            <el-descriptions-item label="血型">
              {{ patientProfile.bloodType }}
            </el-descriptions-item>
            <el-descriptions-item label="过敏史">
              {{ patientProfile.allergyHistory }}
            </el-descriptions-item>
            <el-descriptions-item label="慢病管理">
              {{ patientProfile.chronicCondition }}
            </el-descriptions-item>
            <el-descriptions-item label="最近一次就诊">
              {{ patientProfile.lastVisit }}
            </el-descriptions-item>
          </el-descriptions>
        </el-card>
      </section>

      <el-card shadow="never" class="panel-card table-card">
        <template #header>
          <div class="card-header">
            <span>近期挂号 / 病史记录</span>
            <el-tag type="info" effect="light">{{ patientRecords.length }} 条</el-tag>
          </div>
        </template>

        <el-table :data="patientRecords" stripe>
          <el-table-column prop="date" label="日期" min-width="120" />
          <el-table-column prop="department" label="科室" min-width="120" />
          <el-table-column prop="doctor" label="医生" min-width="120" />
          <el-table-column prop="type" label="类型" min-width="110" />
          <el-table-column prop="diagnosis" label="诊断 / 事项" min-width="220" />
          <el-table-column label="状态" min-width="110">
            <template #default="{ row }">
              <el-tag :type="recordStatusType(row.status)" effect="light">
                {{ row.status }}
              </el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </template>

    <template v-else-if="currentRole === 'DOCTOR'">
      <section class="content-grid doctor-grid">
        <el-card shadow="never" class="panel-card">
          <template #header>
            <div class="card-header">
              <span>职业信息</span>
              <el-tag type="primary" effect="light">医生档案</el-tag>
            </div>
          </template>

          <el-descriptions :column="1" border class="soft-descriptions">
            <el-descriptions-item label="姓名">
              {{ doctorProfile.name }}
            </el-descriptions-item>
            <el-descriptions-item label="所属科室">
              {{ doctorProfile.department }}
            </el-descriptions-item>
            <el-descriptions-item label="职称">
              {{ doctorProfile.title }}
            </el-descriptions-item>
            <el-descriptions-item label="专长">
              {{ doctorProfile.specialty }}
            </el-descriptions-item>
          </el-descriptions>
        </el-card>

        <el-card shadow="never" class="panel-card">
          <template #header>
            <div class="card-header">
              <span>排班状态</span>
              <el-tag :type="doctorScheduleTagType" effect="light">
                {{ doctorProfile.scheduleStatus }}
              </el-tag>
            </div>
          </template>

          <div class="schedule-panel">
            <div class="schedule-stat">
              <span>本周门诊</span>
              <strong>{{ doctorProfile.weeklySessions }} 场</strong>
            </div>
            <div class="schedule-stat">
              <span>待接诊</span>
              <strong>{{ doctorProfile.pendingAppointments }} 人</strong>
            </div>
          </div>

          <div class="schedule-days">
            <el-tag
              v-for="item in doctorProfile.scheduleDays"
              :key="item.day"
              :type="item.active ? 'primary' : 'info'"
              effect="light"
              class="schedule-day"
            >
              {{ item.day }} · {{ item.period }}
            </el-tag>
          </div>
        </el-card>
      </section>
    </template>

    <template v-else>
      <section class="admin-grid">
        <el-card shadow="hover" class="panel-card admin-stat-card">
          <p class="summary-label">系统已运行</p>
          <p class="admin-stat-value">{{ adminStats.runningDays }} 天</p>
          <p class="summary-hint">平台稳定运行状态</p>
        </el-card>

        <el-card shadow="hover" class="panel-card admin-stat-card">
          <p class="summary-label">注册用户总数</p>
          <p class="admin-stat-value">{{ adminStats.userCount }}</p>
          <p class="summary-hint">包含管理员、医生与患者</p>
        </el-card>

        <el-card shadow="hover" class="panel-card admin-stat-card">
          <p class="summary-label">医生数量</p>
          <p class="admin-stat-value">{{ adminStats.doctorCount }}</p>
          <p class="summary-hint">可提供排班服务的医师</p>
        </el-card>

        <el-card shadow="hover" class="panel-card admin-stat-card">
          <p class="summary-label">患者数量</p>
          <p class="admin-stat-value">{{ adminStats.patientCount }}</p>
          <p class="summary-hint">累计注册患者账户</p>
        </el-card>
      </section>

      <el-card shadow="never" class="panel-card">
        <template #header>
          <div class="card-header">
            <span>系统概览</span>
            <el-tag type="danger" effect="light">管理员视角</el-tag>
          </div>
        </template>

        <el-descriptions :column="2" border class="soft-descriptions admin-descriptions">
          <el-descriptions-item label="当前登录管理员">
            {{ username }}
          </el-descriptions-item>
          <el-descriptions-item label="系统版本">
            {{ adminStats.version }}
          </el-descriptions-item>
          <el-descriptions-item label="最近部署时间">
            {{ adminStats.lastDeployTime }}
          </el-descriptions-item>
          <el-descriptions-item label="服务健康度">
            {{ adminStats.serviceStatus }}
          </el-descriptions-item>
        </el-descriptions>
      </el-card>
    </template>
  </div>
</template>

<script setup>
import { computed, onMounted, reactive, ref } from 'vue'
import request from '../utils/request'

const pageLoading = ref(false)
const tokenMissing = ref(false)
const usingMockData = ref(false)
const currentRole = ref('PATIENT')
const username = ref('未登录用户')

const patientProfile = reactive({
  name: '',
  age: '',
  gender: '',
  phone: '',
  bloodType: '',
  allergyHistory: '',
  chronicCondition: '',
  lastVisit: ''
})

const patientRecords = ref([])

const doctorProfile = reactive({
  name: '',
  department: '',
  title: '',
  specialty: '',
  scheduleStatus: '',
  weeklySessions: 0,
  pendingAppointments: 0,
  scheduleDays: []
})

const adminStats = reactive({
  runningDays: 0,
  userCount: 0,
  doctorCount: 0,
  patientCount: 0,
  version: '',
  lastDeployTime: '',
  serviceStatus: ''
})

const roleMap = {
  ADMIN: '管理员',
  DOCTOR: '医生',
  PATIENT: '患者'
}

const roleLabel = computed(() => roleMap[currentRole.value] || '未知角色')

const roleTagType = computed(() => {
  const map = {
    ADMIN: 'danger',
    DOCTOR: 'primary',
    PATIENT: 'success'
  }
  return map[currentRole.value] || 'info'
})

const doctorScheduleTagType = computed(() => {
  return doctorProfile.scheduleStatus === '出诊中' ? 'success' : 'warning'
})

const summaryCards = computed(() => {
  if (currentRole.value === 'PATIENT') {
    return [
      { label: '当前身份', value: roleLabel.value, hint: '根据 JWT 自动识别' },
      { label: '最近科室', value: patientRecords.value[0]?.department || '暂无', hint: '最近一次挂号记录' },
      { label: '就诊次数', value: `${patientRecords.value.length} 次`, hint: '近期待处理记录' }
    ]
  }

  if (currentRole.value === 'DOCTOR') {
    return [
      { label: '所属科室', value: doctorProfile.department || '-', hint: '当前执业归属' },
      { label: '职称', value: doctorProfile.title || '-', hint: '专业等级信息' },
      { label: '排班状态', value: doctorProfile.scheduleStatus || '-', hint: '今日门诊状态' }
    ]
  }

  return [
    { label: '系统运行', value: `${adminStats.runningDays} 天`, hint: '稳定运行时长' },
    { label: '注册总量', value: `${adminStats.userCount}`, hint: '全平台账户数量' },
    { label: '服务状态', value: adminStats.serviceStatus || '-', hint: '核心服务健康度' }
  ]
})

const normalizeRole = (role) => {
  const value = String(role || '').toUpperCase()
  return ['ADMIN', 'DOCTOR', 'PATIENT'].includes(value) ? value : 'PATIENT'
}

const parseJwt = (token) => {
  try {
    const base64Url = token.split('.')[1]
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/')
    const jsonPayload = decodeURIComponent(
      window.atob(base64).split('').map((char) => {
        return `%${(`00${char.charCodeAt(0).toString(16)}`).slice(-2)}`
      }).join('')
    )
    return JSON.parse(jsonPayload)
  } catch (error) {
    return null
  }
}

const getUserContext = () => {
  const token = localStorage.getItem('token')
  const cachedUser = localStorage.getItem('user')
  const decoded = token ? parseJwt(token) : null
  const localUser = cachedUser ? JSON.parse(cachedUser) : null

  return {
    token,
    decoded,
    localUser
  }
}

const getProfileDetail = async () => {
  return await request.get('/profile/me')
}

const getPatientProfile = async () => {
  return await request.get('/patient/profile')
}

const getPatientRecords = async () => {
  return await request.get('/patient/records')
}

const getDoctorProfile = async () => {
  return await request.get('/doctor/profile')
}

const getAdminSummary = async () => {
  return await request.get('/admin/summary')
}

const assignPatientProfile = (data = {}) => {
  patientProfile.name = data.name || data.username || username.value
  patientProfile.age = data.age ?? '29'
  patientProfile.gender = data.gender || '女'
  patientProfile.phone = data.phone || '138****1024'
  patientProfile.bloodType = data.bloodType || 'A'
  patientProfile.allergyHistory = data.allergyHistory || '无明显药物过敏史'
  patientProfile.chronicCondition = data.chronicCondition || '无'
  patientProfile.lastVisit = data.lastVisit || '2026-04-06'
}

const assignDoctorProfile = (data = {}) => {
  doctorProfile.name = data.name || username.value
  doctorProfile.department = data.department || '心内科'
  doctorProfile.title = data.title || '主治医师'
  doctorProfile.specialty = data.specialty || '高血压、冠心病管理'
  doctorProfile.scheduleStatus = data.scheduleStatus || '出诊中'
  doctorProfile.weeklySessions = data.weeklySessions ?? 6
  doctorProfile.pendingAppointments = data.pendingAppointments ?? 18
  doctorProfile.scheduleDays = data.scheduleDays || [
    { day: '周一', period: '上午', active: true },
    { day: '周三', period: '下午', active: true },
    { day: '周五', period: '上午', active: true }
  ]
}

const assignAdminStats = (data = {}) => {
  adminStats.runningDays = data.runningDays ?? 188
  adminStats.userCount = data.userCount ?? 1268
  adminStats.doctorCount = data.doctorCount ?? 84
  adminStats.patientCount = data.patientCount ?? 1172
  adminStats.version = data.version || 'v1.0.0'
  adminStats.lastDeployTime = data.lastDeployTime || '2026-04-08 22:10'
  adminStats.serviceStatus = data.serviceStatus || '正常'
}

const assignPatientRecords = (records = []) => {
  patientRecords.value = records.length
    ? records
    : [
        {
          date: '2026-04-06',
          department: '呼吸内科',
          doctor: '李医生',
          type: '复诊',
          diagnosis: '上呼吸道感染复查',
          status: '已完成'
        },
        {
          date: '2026-03-29',
          department: '全科门诊',
          doctor: '周医生',
          type: '初诊',
          diagnosis: '发热问诊与血常规检查',
          status: '已完成'
        },
        {
          date: '2026-03-15',
          department: '皮肤科',
          doctor: '王医生',
          type: '挂号',
          diagnosis: '过敏性皮疹复查',
          status: '待随访'
        }
      ]
}

const applyCommonProfile = (data = {}) => {
  username.value = data.name || data.username || username.value
}

const hydrateFromLocal = () => {
  const { token, decoded, localUser } = getUserContext()
  tokenMissing.value = !token

  username.value =
    decoded?.sub ||
    decoded?.username ||
    localUser?.username ||
    localUser?.name ||
    '未登录用户'

  currentRole.value = normalizeRole(
    decoded?.role ||
    decoded?.roleName ||
    localUser?.role ||
    localUser?.roleName
  )
}

const loadPatientData = async () => {
  const [profileResponse, recordsResponse] = await Promise.all([
    getPatientProfile(),
    getPatientRecords()
  ])

  assignPatientProfile(profileResponse?.data)
  assignPatientRecords(recordsResponse?.data || recordsResponse?.data?.records || [])
}

const loadDoctorData = async () => {
  const response = await getDoctorProfile()
  assignDoctorProfile(response?.data)
}

const loadAdminData = async () => {
  const response = await getAdminSummary()
  assignAdminStats(response?.data)
}

const loadProfilePage = async () => {
  pageLoading.value = true
  usingMockData.value = false
  hydrateFromLocal()

  try {
    const commonResponse = await getProfileDetail()
    if (commonResponse?.code === 200) {
      applyCommonProfile(commonResponse.data)
    }

    if (currentRole.value === 'PATIENT') {
      await loadPatientData()
    } else if (currentRole.value === 'DOCTOR') {
      await loadDoctorData()
    } else {
      await loadAdminData()
    }
  } catch (error) {
    usingMockData.value = true

    if (currentRole.value === 'PATIENT') {
      assignPatientProfile()
      assignPatientRecords()
    } else if (currentRole.value === 'DOCTOR') {
      assignDoctorProfile()
    } else {
      assignAdminStats()
    }
  } finally {
    pageLoading.value = false
  }
}

const recordStatusType = (status) => {
  const map = {
    已完成: 'success',
    待随访: 'warning',
    已取消: 'info'
  }
  return map[status] || 'info'
}

onMounted(() => {
  loadProfilePage()
})
</script>

<style scoped>
.profile-page {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.hero-card {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 20px;
  padding: 28px 32px;
  border-radius: 18px;
  background: linear-gradient(135deg, #ffffff 0%, #f7faff 100%);
  box-shadow: 0 10px 30px rgba(26, 115, 232, 0.08);
}

.eyebrow {
  margin: 0 0 8px;
  font-size: 12px;
  letter-spacing: 0.12em;
  color: #5f6368;
  text-transform: uppercase;
}

.page-title {
  margin: 0;
  font-size: 30px;
  font-weight: 500;
  color: #202124;
}

.page-subtitle {
  margin: 10px 0 0;
  color: #5f6368;
  font-size: 14px;
}

.hero-meta {
  display: flex;
  align-items: center;
  gap: 12px;
  flex-wrap: wrap;
}

.welcome-text {
  color: #202124;
  font-size: 14px;
  font-weight: 500;
}

.page-alert {
  border-radius: 14px;
}

.summary-grid,
.admin-grid {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 16px;
}

.admin-grid {
  grid-template-columns: repeat(4, minmax(0, 1fr));
}

.summary-card,
.panel-card {
  border: none;
  border-radius: 18px;
  box-shadow: 0 8px 24px rgba(15, 23, 42, 0.06);
}

.summary-label {
  margin: 0;
  font-size: 13px;
  color: #5f6368;
}

.summary-value,
.admin-stat-value {
  margin: 14px 0 8px;
  font-size: 28px;
  line-height: 1.2;
  font-weight: 600;
  color: #202124;
}

.summary-hint {
  margin: 0;
  font-size: 13px;
  color: #80868b;
}

.content-grid {
  display: grid;
  gap: 20px;
}

.patient-grid,
.doctor-grid {
  grid-template-columns: minmax(0, 1.2fr) minmax(0, 0.8fr);
}

.card-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  font-size: 16px;
  font-weight: 500;
  color: #202124;
}

.profile-form :deep(.el-input__wrapper) {
  box-shadow: none;
  background-color: #f7f8fa;
  border-radius: 10px;
}

.soft-descriptions :deep(.el-descriptions__body) {
  background-color: #fff;
}

.soft-descriptions :deep(.el-descriptions__label) {
  width: 120px;
  color: #5f6368;
  font-weight: 500;
}

.schedule-panel {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 12px;
  margin-bottom: 18px;
}

.schedule-stat {
  padding: 18px;
  border-radius: 14px;
  background: #f7faff;
}

.schedule-stat span {
  display: block;
  margin-bottom: 8px;
  color: #5f6368;
  font-size: 13px;
}

.schedule-stat strong {
  font-size: 24px;
  color: #202124;
}

.schedule-days {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.schedule-day {
  margin-right: 0;
}

.table-card :deep(.el-table) {
  --el-table-border-color: transparent;
  --el-table-header-bg-color: #f8fafc;
  --el-table-row-hover-bg-color: #f6f9ff;
  border-radius: 12px;
}

.admin-descriptions :deep(.el-descriptions__table) {
  table-layout: fixed;
}

@media (max-width: 1100px) {
  .summary-grid,
  .admin-grid,
  .patient-grid,
  .doctor-grid {
    grid-template-columns: 1fr 1fr;
  }
}

@media (max-width: 768px) {
  .hero-card,
  .summary-grid,
  .admin-grid,
  .patient-grid,
  .doctor-grid {
    grid-template-columns: 1fr;
    flex-direction: column;
    align-items: flex-start;
  }

  .hero-card {
    padding: 24px;
  }

  .page-title {
    font-size: 24px;
  }

  .schedule-panel {
    grid-template-columns: 1fr;
  }
}
</style>
