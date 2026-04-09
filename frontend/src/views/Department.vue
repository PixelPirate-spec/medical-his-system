<template>
  <div class="department-page">
    <el-card shadow="never" class="page-card">
      <template #header>
        <div class="card-header">
          <div>
            <p class="eyebrow">Department Management</p>
            <h2 class="page-title">科室管理</h2>
          </div>
          <el-button type="primary" class="create-btn" @click="openCreateDialog">
            新增科室
          </el-button>
        </div>
      </template>

      <div class="toolbar">
        <el-input
          v-model="queryForm.keyword"
          placeholder="请输入科室名称"
          clearable
          class="search-input"
          @keyup.enter="handleSearch"
        />
        <el-button @click="handleSearch">查询</el-button>
      </div>

      <el-table v-loading="tableLoading" :data="tableData" stripe class="dept-table">
        <el-table-column prop="id" label="科室 ID" min-width="100" />
        <el-table-column prop="name" label="科室名称" min-width="160" />
        <el-table-column prop="leader" label="负责人" min-width="120" />
        <el-table-column label="状态" min-width="110">
          <template #default="{ row }">
            <el-tag :type="row.status === 'NORMAL' ? 'success' : 'info'" effect="light">
              {{ row.status === 'NORMAL' ? '正常' : '停诊' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" min-width="180" />
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="openEditDialog(row)">
              编辑
            </el-button>
            <el-button type="danger" link @click="handleDelete(row)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-wrapper">
        <el-pagination
          background
          layout="total, prev, pager, next"
          :total="pagination.total"
          :current-page="pagination.pageNum"
          :page-size="pagination.pageSize"
          @current-change="handlePageChange"
        />
      </div>
    </el-card>

    <el-dialog
      v-model="dialogVisible"
      :title="isEdit ? '编辑科室' : '新增科室'"
      width="520px"
      destroy-on-close
    >
      <el-form
        ref="formRef"
        :model="formData"
        :rules="formRules"
        label-position="top"
        class="dialog-form"
      >
        <el-form-item label="科室名称" prop="name">
          <el-input v-model="formData.name" placeholder="请输入科室名称" />
        </el-form-item>

        <el-form-item label="负责人" prop="leader">
          <el-input v-model="formData.leader" placeholder="请输入负责人姓名" />
        </el-form-item>

        <el-form-item label="科室描述" prop="description">
          <el-input
            v-model="formData.description"
            type="textarea"
            :rows="4"
            placeholder="请输入科室职责、接诊范围等说明"
          />
        </el-form-item>

        <el-form-item label="状态" prop="status">
          <el-select v-model="formData.status" placeholder="请选择状态" style="width: 100%;">
            <el-option label="正常" value="NORMAL" />
            <el-option label="停诊" value="DISABLED" />
          </el-select>
        </el-form-item>
      </el-form>

      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" :loading="submitLoading" @click="handleSubmit">
            {{ isEdit ? '保存修改' : '立即创建' }}
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import request from '../utils/request'

const tableLoading = ref(false)
const submitLoading = ref(false)
const dialogVisible = ref(false)
const isEdit = ref(false)
const formRef = ref(null)
const tableData = ref([])

const queryForm = reactive({
  keyword: ''
})

const pagination = reactive({
  pageNum: 1,
  pageSize: 5,
  total: 0
})

const formData = reactive({
  id: null,
  name: '',
  leader: '',
  description: '',
  status: 'NORMAL'
})

const formRules = {
  name: [{ required: true, message: '请输入科室名称', trigger: 'blur' }],
  leader: [{ required: true, message: '请输入负责人', trigger: 'blur' }],
  status: [{ required: true, message: '请选择状态', trigger: 'change' }]
}

const mockDepartmentStore = ref([
  {
    id: 101,
    name: '心内科',
    leader: '张主任',
    description: '负责心血管系统疾病的诊疗与慢病管理。',
    status: 'NORMAL',
    createTime: '2026-03-01 09:00:00'
  },
  {
    id: 102,
    name: '呼吸内科',
    leader: '刘主任',
    description: '负责呼吸系统疾病、感染性疾病的筛查与诊治。',
    status: 'NORMAL',
    createTime: '2026-03-03 10:30:00'
  },
  {
    id: 103,
    name: '皮肤科',
    leader: '陈医生',
    description: '负责常见皮肤病、过敏性皮炎等门诊接诊。',
    status: 'DISABLED',
    createTime: '2026-03-05 14:20:00'
  },
  {
    id: 104,
    name: '全科门诊',
    leader: '周主任',
    description: '承担基础问诊、分诊与慢病随访工作。',
    status: 'NORMAL',
    createTime: '2026-03-08 08:45:00'
  },
  {
    id: 105,
    name: '骨科',
    leader: '王主任',
    description: '负责创伤、关节与骨病相关诊疗服务。',
    status: 'NORMAL',
    createTime: '2026-03-10 11:15:00'
  },
  {
    id: 106,
    name: '口腔科',
    leader: '赵医生',
    description: '提供牙体牙髓、口腔修复等常规门诊服务。',
    status: 'NORMAL',
    createTime: '2026-03-12 16:10:00'
  }
])

const mockDelay = (handler) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(handler())
    }, 350)
  })
}

const getDeptList = async (params) => {
  try {
    return await request.get('/department/list', { params })
  } catch (error) {
    return await mockDelay(() => {
      const keyword = params.keyword?.trim() || ''
      const filtered = mockDepartmentStore.value.filter((item) => {
        return !keyword || item.name.includes(keyword)
      })
      const start = (params.pageNum - 1) * params.pageSize
      const end = start + params.pageSize

      return {
        code: 200,
        data: {
          records: filtered.slice(start, end),
          total: filtered.length
        }
      }
    })
  }
}

const addDept = async (payload) => {
  try {
    return await request.post('/department/add', payload)
  } catch (error) {
    return await mockDelay(() => {
      const nextId = Math.max(...mockDepartmentStore.value.map((item) => item.id)) + 1
      mockDepartmentStore.value.unshift({
        ...payload,
        id: nextId,
        createTime: new Date().toLocaleString('zh-CN', { hour12: false }).replace(/\//g, '-')
      })
      return {
        code: 200,
        message: '新增成功'
      }
    })
  }
}

const updateDept = async (payload) => {
  try {
    return await request.put(`/department/${payload.id}`, payload)
  } catch (error) {
    return await mockDelay(() => {
      mockDepartmentStore.value = mockDepartmentStore.value.map((item) => {
        return item.id === payload.id ? { ...item, ...payload } : item
      })
      return {
        code: 200,
        message: '更新成功'
      }
    })
  }
}

const deleteDept = async (id) => {
  try {
    return await request.delete(`/department/${id}`)
  } catch (error) {
    return await mockDelay(() => {
      mockDepartmentStore.value = mockDepartmentStore.value.filter((item) => item.id !== id)
      return {
        code: 200,
        message: '删除成功'
      }
    })
  }
}

const resetFormData = () => {
  formData.id = null
  formData.name = ''
  formData.leader = ''
  formData.description = ''
  formData.status = 'NORMAL'
}

const loadTableData = async () => {
  tableLoading.value = true
  try {
    const response = await getDeptList({
      keyword: queryForm.keyword,
      pageNum: pagination.pageNum,
      pageSize: pagination.pageSize
    })

    if (response.code === 200) {
      tableData.value = response.data.records || []
      pagination.total = response.data.total || 0
    } else {
      ElMessage.error(response.message || '获取科室列表失败')
    }
  } finally {
    tableLoading.value = false
  }
}

const handleSearch = () => {
  pagination.pageNum = 1
  loadTableData()
}

const handlePageChange = (page) => {
  pagination.pageNum = page
  loadTableData()
}

const openCreateDialog = () => {
  isEdit.value = false
  dialogVisible.value = true
  resetFormData()
}

const openEditDialog = (row) => {
  isEdit.value = true
  dialogVisible.value = true
  formData.id = row.id
  formData.name = row.name
  formData.leader = row.leader
  formData.description = row.description
  formData.status = row.status
}

const handleSubmit = async () => {
  if (!formRef.value) return

  await formRef.value.validate(async (valid) => {
    if (!valid) return

    submitLoading.value = true
    try {
      const payload = {
        id: formData.id,
        name: formData.name,
        leader: formData.leader,
        description: formData.description,
        status: formData.status
      }

      const response = isEdit.value
        ? await updateDept(payload)
        : await addDept(payload)

      if (response.code === 200) {
        ElMessage.success(isEdit.value ? '科室信息已更新' : '科室创建成功')
        dialogVisible.value = false
        loadTableData()
      } else {
        ElMessage.error(response.message || '提交失败')
      }
    } finally {
      submitLoading.value = false
    }
  })
}

const handleDelete = async (row) => {
  await ElMessageBox.confirm(
    `确认删除科室“${row.name}”吗？该操作仅用于当前演示数据。`,
    '删除确认',
    {
      type: 'warning',
      confirmButtonText: '确认删除',
      cancelButtonText: '取消'
    }
  )

  tableLoading.value = true
  try {
    const response = await deleteDept(row.id)
    if (response.code === 200) {
      ElMessage.success('删除成功')
      if (tableData.value.length === 1 && pagination.pageNum > 1) {
        pagination.pageNum -= 1
      }
      loadTableData()
    } else {
      ElMessage.error(response.message || '删除失败')
    }
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除失败，请稍后重试')
    }
  } finally {
    tableLoading.value = false
  }
}

onMounted(() => {
  loadTableData()
})
</script>

<style scoped>
.department-page {
  min-height: calc(100vh - 120px);
}

.page-card {
  border: none;
  border-radius: 18px;
  box-shadow: 0 8px 24px rgba(15, 23, 42, 0.06);
}

.card-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
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
  font-size: 28px;
  font-weight: 500;
  color: #202124;
}

.create-btn {
  border-radius: 10px;
  background-color: #1a73e8;
  border-color: #1a73e8;
}

.create-btn:hover {
  background-color: #1b66c9;
  border-color: #1b66c9;
}

.toolbar {
  display: flex;
  gap: 12px;
  margin-bottom: 20px;
  align-items: center;
}

.search-input {
  width: 280px;
}

.dept-table {
  border-radius: 12px;
}

.dept-table :deep(.el-table__inner-wrapper::before) {
  display: none;
}

.dept-table :deep(.el-table) {
  --el-table-border-color: transparent;
  --el-table-header-bg-color: #f8fafc;
  --el-table-row-hover-bg-color: #f6f9ff;
}

.pagination-wrapper {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}

.dialog-form :deep(.el-input__wrapper),
.dialog-form :deep(.el-textarea__inner),
.dialog-form :deep(.el-select__wrapper) {
  border-radius: 10px;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
}

@media (max-width: 768px) {
  .card-header,
  .toolbar {
    flex-direction: column;
    align-items: stretch;
  }

  .search-input {
    width: 100%;
  }

  .pagination-wrapper {
    justify-content: center;
  }
}
</style>
