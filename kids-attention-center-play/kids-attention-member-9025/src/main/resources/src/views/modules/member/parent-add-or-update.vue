<template>
  <el-dialog
      :title="!dataForm.parentId ? '新增' : '修改'"
      :close-on-click-modal="false"
      :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="对应孩子的ID" prop="parentKidId">
        <el-input v-model="dataForm.parentKidId" placeholder="对应孩子的ID"></el-input>
      </el-form-item>
      <el-form-item label="家长姓名" prop="parentName">
        <el-input v-model="dataForm.parentName" placeholder="家长姓名"></el-input>
      </el-form-item>
      <el-form-item label="家长与学员的关系" prop="parentRelationship">
        <el-input v-model="dataForm.parentRelationship" placeholder="家长与学员的关系"></el-input>
      </el-form-item>
      <el-form-item label="家长登录登录账户" prop="parentAccount">
        <el-input v-model="dataForm.parentAccount" placeholder="家长登录登录账户"></el-input>
      </el-form-item>
      <el-form-item label="家长登录密码" prop="parentPassword">
        <el-input v-model="dataForm.parentPassword" placeholder="家长登录密码"></el-input>
      </el-form-item>
      <el-form-item label="家长年龄" prop="parentAge">
        <el-input v-model="dataForm.parentAge" placeholder="家长年龄"></el-input>
      </el-form-item>
      <el-form-item label="手机号码" prop="parentPhone">
        <el-input v-model="dataForm.parentPhone" placeholder="手机号码"></el-input>
      </el-form-item>
      <el-form-item label="邮箱地址" prop="parentEmail">
        <el-input v-model="dataForm.parentEmail" placeholder="邮箱地址"></el-input>
      </el-form-item>
      <el-form-item label="QQ账号" prop="parentQqAccount">
        <el-input v-model="dataForm.parentQqAccount" placeholder="QQ账号"></el-input>
      </el-form-item>
      <el-form-item label="微信账号" prop="parentWeixinAccount">
        <el-input v-model="dataForm.parentWeixinAccount" placeholder="微信账号"></el-input>
      </el-form-item>
      <el-form-item label="乐观锁" prop="version">
        <el-input v-model="dataForm.version" placeholder="乐观锁"></el-input>
      </el-form-item>
      <el-form-item label="入库时间" prop="addTime">
        <el-input v-model="dataForm.addTime" placeholder="入库时间"></el-input>
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
        parentId: 0,
        parentKidId: '',
        parentName: '',
        parentRelationship: '',
        parentAccount: '',
        parentPassword: '',
        parentAge: '',
        parentPhone: '',
        parentEmail: '',
        parentQqAccount: '',
        parentWeixinAccount: '',
        version: '',
        addTime: '',
        updateTime: '',
        deleted: ''
      },
      dataRule: {
        parentKidId: [
          {required: true, message: '对应孩子的ID不能为空', trigger: 'blur'}
        ],
        parentName: [
          {required: true, message: '家长姓名不能为空', trigger: 'blur'}
        ],
        parentRelationship: [
          {required: true, message: '家长与学员的关系不能为空', trigger: 'blur'}
        ],
        parentAccount: [
          {required: true, message: '家长登录登录账户不能为空', trigger: 'blur'}
        ],
        parentPassword: [
          {required: true, message: '家长登录密码不能为空', trigger: 'blur'}
        ],
        parentAge: [
          {required: true, message: '家长年龄不能为空', trigger: 'blur'}
        ],
        parentPhone: [
          {required: true, message: '手机号码不能为空', trigger: 'blur'}
        ],
        parentEmail: [
          {required: true, message: '邮箱地址不能为空', trigger: 'blur'}
        ],
        parentQqAccount: [
          {required: true, message: 'QQ账号不能为空', trigger: 'blur'}
        ],
        parentWeixinAccount: [
          {required: true, message: '微信账号不能为空', trigger: 'blur'}
        ],
        version: [
          {required: true, message: '乐观锁不能为空', trigger: 'blur'}
        ],
        addTime: [
          {required: true, message: '入库时间不能为空', trigger: 'blur'}
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
      this.dataForm.parentId = id || 0
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.parentId) {
          this.$http({
            url: this.$http.adornUrl(`/member/parent/info/${this.dataForm.parentId}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm.parentKidId = data.parent.parentKidId
              this.dataForm.parentName = data.parent.parentName
              this.dataForm.parentRelationship = data.parent.parentRelationship
              this.dataForm.parentAccount = data.parent.parentAccount
              this.dataForm.parentPassword = data.parent.parentPassword
              this.dataForm.parentAge = data.parent.parentAge
              this.dataForm.parentPhone = data.parent.parentPhone
              this.dataForm.parentEmail = data.parent.parentEmail
              this.dataForm.parentQqAccount = data.parent.parentQqAccount
              this.dataForm.parentWeixinAccount = data.parent.parentWeixinAccount
              this.dataForm.version = data.parent.version
              this.dataForm.addTime = data.parent.addTime
              this.dataForm.updateTime = data.parent.updateTime
              this.dataForm.deleted = data.parent.deleted
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
            url: this.$http.adornUrl(`/member/parent/${!this.dataForm.parentId ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'parentId': this.dataForm.parentId || undefined,
              'parentKidId': this.dataForm.parentKidId,
              'parentName': this.dataForm.parentName,
              'parentRelationship': this.dataForm.parentRelationship,
              'parentAccount': this.dataForm.parentAccount,
              'parentPassword': this.dataForm.parentPassword,
              'parentAge': this.dataForm.parentAge,
              'parentPhone': this.dataForm.parentPhone,
              'parentEmail': this.dataForm.parentEmail,
              'parentQqAccount': this.dataForm.parentQqAccount,
              'parentWeixinAccount': this.dataForm.parentWeixinAccount,
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
