<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('member:parent:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('member:parent:delete')" type="danger" @click="deleteHandle()"
                   :disabled="dataListSelections.length <= 0">批量删除
        </el-button>
      </el-form-item>
    </el-form>
    <el-table
        :data="dataList"
        border
        v-loading="dataListLoading"
        @selection-change="selectionChangeHandle"
        style="width: 100%;">
      <el-table-column
          type="selection"
          header-align="center"
          align="center"
          width="50">
      </el-table-column>
      <el-table-column
          prop="parentId"
          header-align="center"
          align="center"
          label="主键ID,雪花算法生成">
      </el-table-column>
      <el-table-column
          prop="parentKidId"
          header-align="center"
          align="center"
          label="对应孩子的ID">
      </el-table-column>
      <el-table-column
          prop="parentName"
          header-align="center"
          align="center"
          label="家长姓名">
      </el-table-column>
      <el-table-column
          prop="parentRelationship"
          header-align="center"
          align="center"
          label="家长与学员的关系">
      </el-table-column>
      <el-table-column
          prop="parentAccount"
          header-align="center"
          align="center"
          label="家长登录登录账户">
      </el-table-column>
      <el-table-column
          prop="parentPassword"
          header-align="center"
          align="center"
          label="家长登录密码">
      </el-table-column>
      <el-table-column
          prop="parentAge"
          header-align="center"
          align="center"
          label="家长年龄">
      </el-table-column>
      <el-table-column
          prop="parentPhone"
          header-align="center"
          align="center"
          label="手机号码">
      </el-table-column>
      <el-table-column
          prop="parentEmail"
          header-align="center"
          align="center"
          label="邮箱地址">
      </el-table-column>
      <el-table-column
          prop="parentQqAccount"
          header-align="center"
          align="center"
          label="QQ账号">
      </el-table-column>
      <el-table-column
          prop="parentWeixinAccount"
          header-align="center"
          align="center"
          label="微信账号">
      </el-table-column>
      <el-table-column
          prop="version"
          header-align="center"
          align="center"
          label="乐观锁">
      </el-table-column>
      <el-table-column
          prop="addTime"
          header-align="center"
          align="center"
          label="入库时间">
      </el-table-column>
      <el-table-column
          prop="updateTime"
          header-align="center"
          align="center"
          label="修改时间">
      </el-table-column>
      <el-table-column
          prop="deleted"
          header-align="center"
          align="center"
          label="逻辑删除状态">
      </el-table-column>
      <el-table-column
          fixed="right"
          header-align="center"
          align="center"
          width="150"
          label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.parentId)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.parentId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="sizeChangeHandle"
        @current-change="currentChangeHandle"
        :current-page="pageIndex"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pageSize"
        :total="totalPage"
        layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from './parent-add-or-update'

export default {
  data() {
    return {
      dataForm: {
        key: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false
    }
  },
  components: {
    AddOrUpdate
  },
  activated() {
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/member/parent/list'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.pageIndex,
          'limit': this.pageSize,
          'key': this.dataForm.key
        })
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.dataList = data.page.list
          this.totalPage = data.page.totalCount
        } else {
          this.dataList = []
          this.totalPage = 0
        }
        this.dataListLoading = false
      })
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val
    },
    // 新增 / 修改
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle(id) {
      var ids = id ? [id] : this.dataListSelections.map(item => {
        return item.parentId
      })
      this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/member/parent/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      })
    }
  }
}
</script>
