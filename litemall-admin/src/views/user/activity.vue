<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.title" clearable class="filter-item" style="width: 200px;" placeholder="请输入活动名称"/>
      <el-date-picker v-model="listQuery.beginTime" class="filter-item" style="width: 200px;" type="datetime" placeholder="请输入开始时间" value-format="yyyy-MM-dd HH:mm:ss"/>
      <el-date-picker v-model="listQuery.endTime" class="filter-item" style="width: 200px;" type="datetime" placeholder="请输入结束时间" value-format="yyyy-MM-dd HH:mm:ss"/>
      <el-input v-model="listQuery.orign" clearable class="filter-item" style="width: 200px;" placeholder="活动来源"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" width="100px" label="活动ID" prop="activityId" sortable/>

      <el-table-column align="center" min-width="100px" label="活动名称" prop="title"/>

      <el-table-column align="center" min-width="100px" label="活动区域" prop="subtitle"/>

      <el-table-column align="center" min-width="100px" label="用户ID" prop="userId"/>

      <el-table-column align="center" min-width="100px" label="用户昵称" prop="nickname"/>

      <el-table-column align="center" min-width="100px" label="真实姓名" prop="memberUsername"/>

      <el-table-column align="center" min-width="100px" label="手机号码" prop="mobile"/>

      <el-table-column align="center" min-width="100px" label="宝宝生日（一胎）" prop="babybirthday"/>

      <el-table-column align="center" min-width="100px" label="宝宝生日（二胎）" prop="babybirthday2"/>

      <el-table-column align="center" min-width="100px" label="推广人ID" prop="promoterId"/>

      <el-table-column align="center" min-width="100px" label="活动来源" prop="orign"/>

      <el-table-column align="center" min-width="100px" label="参与时间" prop="addTime"/>

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

  </div>
</template>

<script>
import { listActivity } from '@/api/user'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'Activity',
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        userId: undefined,
        activityId: undefined,
        promoterId: undefined,
        orign: undefined,
        title: undefined,
        beginTime: undefined,
        endTime: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      downloadLoading: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      listActivity(this.listQuery)
        .then(response => {
          this.list = response.data.data.items
          this.total = response.data.data.total
          this.listLoading = false
        })
        .catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['活动ID', '活动名称', '活动区域', '用户ID', '用户昵称', '真实姓名', '手机号码', '宝宝生日（一胎）', '宝宝生日（二胎）', '推广人ID', '活动来源', '参与时间']
        const filterVal = ['activityId', 'title', 'subtitle', 'userId', 'nickname', 'memberUsername', 'mobile', 'babybirthday', 'babybirthday2', 'promoterId', 'orign', 'addTime']
        excel.export_json_to_excel2(
          tHeader,
          this.list,
          filterVal,
          '会员参与活动信息'
        )
        this.downloadLoading = false
      })
    }
  }
}
</script>
