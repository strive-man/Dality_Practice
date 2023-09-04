<template>
  <el-dialog
      :title="!dataForm.staffId ? '新增' : '修改'"
      :close-on-click-modal="false"
      :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="员工姓名" prop="staffName">
        <el-input v-model="dataForm.staffName" placeholder="员工姓名"></el-input>
      </el-form-item>
      <el-form-item label="员工所属校区" prop="staffSchool">
        <el-input v-model="dataForm.staffSchool" placeholder="员工所属校区"></el-input>
      </el-form-item>
      <el-form-item label="员工所属部门" prop="staffDepartment">
        <el-input v-model="dataForm.staffDepartment" placeholder="员工所属部门"></el-input>
      </el-form-item>
      <el-form-item label="员工角色" prop="staffRole">
        <el-input v-model="dataForm.staffRole" placeholder="员工角色"></el-input>
      </el-form-item>
      <el-form-item label="员工登录账户" prop="staffAccount">
        <el-input v-model="dataForm.staffAccount" placeholder="员工登录账户"></el-input>
      </el-form-item>
      <el-form-item label="用户密码，RSA加密" prop="staffPassword">
        <el-input v-model="dataForm.staffPassword" placeholder="用户密码，RSA加密"></el-input>
      </el-form-item>
      <el-form-item label="乐观锁" prop="version">
        <el-input v-model="dataForm.version" placeholder="乐观锁"></el-input>
      </el-form-item>
      <el-form-item label="写入时间" prop="addTime">
        <el-input v-model="dataForm.addTime" placeholder="写入时间"></el-input>
      </el-form-item>
      <el-form-item label="修改时间" prop="updateTime">
        <el-input v-model="dataForm.updateTime" placeholder="修改时间"></el-input>
      </el-form-item>
      <el-form-item label="逻辑删除状态" prop="deleted">
        <el-input v-model="dataForm.deleted" placeholder="逻辑删除状态"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      visible: false,
      dataForm: {
        staffId: 0,
        staffName: '',
        staffSchool: '',
        staffDepartment: '',
        staffRole: '',
        staffAccount: '',
        staffPassword: '',
        version: '',
        addTime: '',
        updateTime: '',
        deleted: ''
      },
      dataRule: {
        staffName: [
          {required: true, message: '员工姓名不能为空', trigger: 'blur'}
        ],
        staffSchool: [
          {required: true, message: '员工所属校区不能为空', trigger: 'blur'}
        ],
        staffDepartment: [
          {required: true, message: '员工所属部门不能为空', trigger: 'blur'}
        ],
        staffRole: [
          {required: true, message: '员工角色不能为空', trigger: 'blur'}
        ],
        staffAccount: [
          {required: true, message: '员工登录账户不能为空', trigger: 'blur'}
        ],
        staffPassword: [
          {required: true, message: '用户密码，RSA加密不能为空', trigger: 'blur'}
        ],
        version: [
          {required: true, message: '乐观锁不能为空', trigger: 'blur'}
        ],
        addTime: [
          {required: true, message: '写入时间不能为空', trigger: 'blur'}
        ],
        updateTime: [
          {required: true, message: '修改时间不能为空', trigger: 'blur'}
        ],
        deleted: [
          {required: true, message: '逻辑删除状态不能为空', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    init(id) {
      this.dataForm.staffId = id || 0
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.staffId) {
          this.$http({
            url: this.$http.adornUrl(`/member/staff/info/${this.dataForm.staffId}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm.staffName = data.staff.staffName
              this.dataForm.staffSchool = data.staff.staffSchool
              this.dataForm.staffDepartment = data.staff.staffDepartment
              this.dataForm.staffRole = data.staff.staffRole
              this.dataForm.staffAccount = data.staff.staffAccount
              this.dataForm.staffPassword = data.staff.staffPassword
              this.dataForm.version = data.staff.version
              this.dataForm.addTime = data.staff.addTime
              this.dataForm.updateTime = data.staff.updateTime
              this.dataForm.deleted = data.staff.deleted
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/member/staff/${!this.dataForm.staffId ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'staffId': this.dataForm.staffId || undefined,
              'staffName': this.dataForm.staffName,
              'staffSchool': this.dataForm.staffSchool,
              'staffDepartment': this.dataForm.staffDepartment,
              'staffRole': this.dataForm.staffRole,
              'staffAccount': this.dataForm.staffAccount,
              'staffPassword': this.dataForm.staffPassword,
              'version': this.dataForm.version,
              'addTime': this.dataForm.addTime,
              'updateTime': this.dataForm.updateTime,
              'deleted': this.dataForm.deleted
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      })
    }
  }
}
</script>
