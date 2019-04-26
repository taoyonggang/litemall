<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.username" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户名"/>
      <el-input v-model="listQuery.mobile" clearable class="filter-item" style="width: 200px;" placeholder="请输入手机号"/>
      <el-date-picker v-model="listQuery.beginTime" class="filter-item" style="width: 200px;" type="datetime" placeholder="请输入开始时间" value-format="yyyy-MM-dd HH:mm:ss"/>
      <el-date-picker v-model="listQuery.endTime" class="filter-item" style="width: 200px;" type="datetime" placeholder="请输入结束时间" value-format="yyyy-MM-dd HH:mm:ss"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" width="100px" label="用户ID" prop="id" sortable/>

      <el-table-column align="center" label="用户名" prop="username"/>

      <el-table-column align="center" label="真实姓名" prop="memberUsername"/>

      <el-table-column align="center" label="昵称" prop="nickname"/>

      <el-table-column align="center" label="城市" prop="address"/>

      <el-table-column align="center" label="手机号码" prop="mobile"/>

      <el-table-column align="center" label="性别" prop="gender">
        <template slot-scope="scope">
          <el-tag >{{ genderDic[scope.row.gender] }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="生日" prop="birthday"/>

      <el-table-column align="center" label="宝宝性别(一胎)" prop="babysex">
        <template slot-scope="scope">
          <el-tag >{{ genderDic[scope.row.babysex] }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="宝宝生日(一胎)" prop="babybirthday"/>

      <el-table-column align="center" label="宝宝性别(二胎)" prop="babysex2">
        <template slot-scope="scope">
          <el-tag >{{ genderDic[scope.row.babysex2] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="宝宝生日(二胎)" prop="babybirthday2"/>

      <el-table-column align="center" label="来源" prop="fromsouce"/>

      <el-table-column align="center" label="注册时间" prop="addTime"/>

      <el-table-column align="center" label="推荐人" prop="memberId"/>

      <el-table-column align="center" label="积分" prop="integral"/>

      <el-table-column align="center" label="用户等级" prop="userLevel" width="100px">
        <template slot-scope="scope">
          <el-tag >{{ levelDic[scope.row.userLevel] }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="状态" prop="status">
        <template slot-scope="scope">
          <el-tag>{{ statusDic[scope.row.status] }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="dataForm.username"/>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="dataForm.password" type="password" auto-complete="off"/>
        </el-form-item>
        <el-form-item label="手机号码" prop="mobile">
          <el-input v-model="dataForm.mobile"/>
        </el-form-item>
        <el-form-item label="城市" prop="address">
          <el-input v-model="dataForm.address"/>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="dataForm.gender">
            <el-option :value="0" label="未知"/>
            <el-option :value="1" label="男"/>
            <el-option :value="2" label="女"/>
          </el-select>
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker v-model="dataForm.birthday" type="date" value-format="yyyy-MM-dd"/>
        </el-form-item>
        <el-form-item label="宝宝性别(一胎)" prop="babysex">
          <el-select v-model="dataForm.babysex">
            <el-option :value="0" label="未知"/>
            <el-option :value="1" label="男"/>
            <el-option :value="2" label="女"/>
          </el-select>
        </el-form-item>
        <el-form-item label="宝宝生日(一胎)" prop="babybirthday">
          <el-date-picker v-model="dataForm.babybirthday" type="date" value-format="yyyy-MM-dd"/>
        </el-form-item>
        <el-form-item label="宝宝性别(二胎)" prop="babysex2">
          <el-select v-model="dataForm.babysex2">
            <el-option :value="0" label="未知"/>
            <el-option :value="1" label="男"/>
            <el-option :value="2" label="女"/>
          </el-select>
        </el-form-item>
        <el-form-item label="宝宝生日(二胎)" prop="babybirthday2">
          <el-date-picker v-model="dataForm.babybirthday2" type="date" value-format="yyyy-MM-dd"/>
        </el-form-item>
        <el-form-item label="来源" prop="fromsouce">
          <el-input v-model="dataForm.fromsouce"/>
        </el-form-item>
        <el-form-item label="用户等级" prop="userLevel">
          <el-select v-model="dataForm.userLevel">
            <el-option :value="0" label="普通用户"/>
            <el-option :value="1" label="VIP用户"/>
            <el-option :value="2" label="高级VIP用户"/>
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="dataForm.status">
            <el-option :value="0" label="可用"/>
            <el-option :value="1" label="禁用"/>
            <el-option :value="2" label="注销"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { fetchList, createUser, updateUser } from '@/api/user'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'User',
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        username: undefined,
        address: undefined,
        babysex: undefined,
        babysex2: undefined,
        babybirthday: undefined,
        babybirthday2: undefined,
        memberUsername: undefined,
        addTime: undefined,
        mobile: undefined,
        beginTime: undefined,
        endTime: undefined,
        memberId: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      dataForm: {
        id: undefined,
        username: '',
        mobile: '',
        password: undefined,
        gender: 0,
        userLevel: 0,
        birthday: '',
        address: '',
        babysex: '',
        babysex2: '',
        babybirthday: '',
        babybirthday2: '',
        memberUsername: '',
        addTime: '',
        memberId: '',
        fromsouce: '',
        status: 0
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      rules: {
        username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
        memberUsername: [{ required: true, message: '真实姓名不能为空', trigger: 'blur' }],
        mobile: [{ required: true, message: '手机号码不能为空', trigger: 'blur' }],
        password: [{ required: true, message: '密码不能为空', trigger: 'blur' }],
        address: [{ required: true, message: '地址不能为空', trigger: 'blur' }],
        babysex: [{ required: true, message: '宝宝性别（一胎）不能为空', trigger: 'blur' }],
        babybirthday: [{ required: true, message: '宝宝生日（一胎）不能为空', trigger: 'blur' }],
        fromsouce: [{ required: true, message: '来源不能为空', trigger: 'blur' }]
      },
      downloadLoading: false,
      genderDic: ['未知', '男', '女'],
      levelDic: ['普通用户', 'VIP用户', '高级VIP用户'],
      statusDic: ['可用', '禁用', '注销']
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.data.items
        this.total = response.data.data.total
        this.listLoading = false
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    resetForm() {
      this.dataForm = {
        id: undefined,
        username: '',
        mobile: '',
        pass: undefined,
        checkPass: undefined,
        gender: 0,
        userLevel: 0,
        birthday: '',
        address: '',
        babysex: '',
        babysex2: '',
        babybirthday: '',
        babybirthday2: '',
        memberUsername: '',
        addTime: '',
        memberId: '',
        fromsouce: '',
        status: 0
      }
    },
    handleCreate() {
      this.resetForm()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          createUser(this.dataForm).then(response => {
            this.list.unshift(response.data.data)
            this.dialogFormVisible = false
            this.$notify.success({
              title: '成功',
              message: '添加用户成功'
            })
          }).catch(response => {
            this.$notify.error({
              title: '失败',
              message: response.data.errmsg
            })
          })
        }
      })
    },
    handleUpdate(row) {
      this.dataForm = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          updateUser(this.dataForm).then(() => {
            for (const v of this.list) {
              if (v.id === this.dataForm.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.dataForm)
                break
              }
            }
            this.dialogFormVisible = false
            this.$notify.success({
              title: '成功',
              message: '更新成功'
            })
          }).catch(response => {
            this.$notify.error({
              title: '失败',
              message: response.data.errmsg
            })
          })
        }
      })
    },
    handleDelete(row) {
      this.$notify.error({
        title: '警告',
        message: '用户删除操作不支持！'
      })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['用户名', '真实姓名', '手机号码', '性别', '生日', '地址', '宝宝性别（一胎）', '宝宝生日（一胎）', '宝宝性别（二胎）', '宝宝生日（二胎）', '来源', '注册时间', '推荐人', '积分', '用户等级', '状态']
        const filterVal = ['username', 'memberUsername', 'mobile', 'gender', 'birthday', 'address', 'babysex', 'babybirthday', 'babysex2', 'babybirthday2', 'fromsouce', 'addTime', 'memberId', 'integral', 'userLevel', 'status']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '用户信息')
        this.downloadLoading = false
      })
    }
  }
}
</script>
