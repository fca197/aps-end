<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="88px" size="small">
      <el-form-item label="工厂ID" prop="factoryId">
        <el-input v-model="queryParams.data.factoryId" clearable placeholder="请输入工厂ID" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="年份" prop="year">
        <el-input v-model="queryParams.data.year" clearable placeholder="请输入年份" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="月份" prop="month">
        <el-input v-model="queryParams.data.month" clearable placeholder="请输入月份" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="01日期产量" prop="day01">
        <el-input v-model="queryParams.data.day01" clearable placeholder="请输入01日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="02日期产量" prop="day02">
        <el-input v-model="queryParams.data.day02" clearable placeholder="请输入02日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="03日期产量" prop="day03">
        <el-input v-model="queryParams.data.day03" clearable placeholder="请输入03日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="04日期产量" prop="day04">
        <el-input v-model="queryParams.data.day04" clearable placeholder="请输入04日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="05日期产量" prop="day05">
        <el-input v-model="queryParams.data.day05" clearable placeholder="请输入05日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="06日期产量" prop="day06">
        <el-input v-model="queryParams.data.day06" clearable placeholder="请输入06日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="07日期产量" prop="day07">
        <el-input v-model="queryParams.data.day07" clearable placeholder="请输入07日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="08日期产量" prop="day08">
        <el-input v-model="queryParams.data.day08" clearable placeholder="请输入08日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="09日期产量" prop="day09">
        <el-input v-model="queryParams.data.day09" clearable placeholder="请输入09日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="10日期产量" prop="day10">
        <el-input v-model="queryParams.data.day10" clearable placeholder="请输入10日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="11日期产量" prop="day11">
        <el-input v-model="queryParams.data.day11" clearable placeholder="请输入11日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="12日期产量" prop="day12">
        <el-input v-model="queryParams.data.day12" clearable placeholder="请输入12日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="13日期产量" prop="day13">
        <el-input v-model="queryParams.data.day13" clearable placeholder="请输入13日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="14日期产量" prop="day14">
        <el-input v-model="queryParams.data.day14" clearable placeholder="请输入14日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="15日期产量" prop="day15">
        <el-input v-model="queryParams.data.day15" clearable placeholder="请输入15日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="16日期产量" prop="day16">
        <el-input v-model="queryParams.data.day16" clearable placeholder="请输入16日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="17日期产量" prop="day17">
        <el-input v-model="queryParams.data.day17" clearable placeholder="请输入17日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="18日期产量" prop="day18">
        <el-input v-model="queryParams.data.day18" clearable placeholder="请输入18日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="19日期产量" prop="day19">
        <el-input v-model="queryParams.data.day19" clearable placeholder="请输入19日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="20日期产量" prop="day20">
        <el-input v-model="queryParams.data.day20" clearable placeholder="请输入20日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="21日期产量" prop="day21">
        <el-input v-model="queryParams.data.day21" clearable placeholder="请输入21日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="22日期产量" prop="day22">
        <el-input v-model="queryParams.data.day22" clearable placeholder="请输入22日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="23日期产量" prop="day23">
        <el-input v-model="queryParams.data.day23" clearable placeholder="请输入23日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="24日期产量" prop="day24">
        <el-input v-model="queryParams.data.day24" clearable placeholder="请输入24日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="25日期产量" prop="day25">
        <el-input v-model="queryParams.data.day25" clearable placeholder="请输入25日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="26日期产量" prop="day26">
        <el-input v-model="queryParams.data.day26" clearable placeholder="请输入26日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="27日期产量" prop="day27">
        <el-input v-model="queryParams.data.day27" clearable placeholder="请输入27日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="28日期产量" prop="day28">
        <el-input v-model="queryParams.data.day28" clearable placeholder="请输入28日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="29日期产量" prop="day29">
        <el-input v-model="queryParams.data.day29" clearable placeholder="请输入29日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="30日期产量" prop="day30">
        <el-input v-model="queryParams.data.day30" clearable placeholder="请输入30日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="31日期产量" prop="day31">
        <el-input v-model="queryParams.data.day31" clearable placeholder="请输入31日期产量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="容量" prop="capacity">
        <el-input v-model="queryParams.data.capacity" clearable placeholder="请输入容量" @keyup.enter.native="handleQuery"/>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button icon="el-icon-plus" plain size="mini" type="primary" @click="handleAdd"></el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button :disabled="multiple" icon="el-icon-delete" plain size="mini" type="danger" @click="handleDelete"></el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="apsRollingForecastFactoryCapacityList" @selection-change="handleSelectionChange">
      <el-table-column align="center" label="全选" prop="id" type="selection" width="50"/>
      <el-table-column v-for="(item,index) in  tableHeaderList" :key="index" :label="item.showName" :prop="item.fieldName" align="center" :width="item.width+'px'"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button icon="el-icon-edit" size="mini" type="text" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button icon="el-icon-delete" size="mini" type="text" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total>0"
        :limit.sync="queryParams.pageSize"
        :page.sync="queryParams.pageNum"
        :total="total"
        @pagination="getList"
    />

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">

        <el-form-item label="工厂ID" prop="factoryId">
          <el-input v-model="form.factoryId" clearable placeholder="请输入工厂ID"/>
        </el-form-item>
        <el-form-item label="年份" prop="year">
          <el-input v-model="form.year" clearable placeholder="请输入年份"/>
        </el-form-item>
        <el-form-item label="月份" prop="month">
          <el-input v-model="form.month" clearable placeholder="请输入月份"/>
        </el-form-item>
        <el-form-item label="01日期产量" prop="day01">
          <el-input v-model="form.day01" clearable placeholder="请输入01日期产量"/>
        </el-form-item>
        <el-form-item label="02日期产量" prop="day02">
          <el-input v-model="form.day02" clearable placeholder="请输入02日期产量"/>
        </el-form-item>
        <el-form-item label="03日期产量" prop="day03">
          <el-input v-model="form.day03" clearable placeholder="请输入03日期产量"/>
        </el-form-item>
        <el-form-item label="04日期产量" prop="day04">
          <el-input v-model="form.day04" clearable placeholder="请输入04日期产量"/>
        </el-form-item>
        <el-form-item label="05日期产量" prop="day05">
          <el-input v-model="form.day05" clearable placeholder="请输入05日期产量"/>
        </el-form-item>
        <el-form-item label="06日期产量" prop="day06">
          <el-input v-model="form.day06" clearable placeholder="请输入06日期产量"/>
        </el-form-item>
        <el-form-item label="07日期产量" prop="day07">
          <el-input v-model="form.day07" clearable placeholder="请输入07日期产量"/>
        </el-form-item>
        <el-form-item label="08日期产量" prop="day08">
          <el-input v-model="form.day08" clearable placeholder="请输入08日期产量"/>
        </el-form-item>
        <el-form-item label="09日期产量" prop="day09">
          <el-input v-model="form.day09" clearable placeholder="请输入09日期产量"/>
        </el-form-item>
        <el-form-item label="10日期产量" prop="day10">
          <el-input v-model="form.day10" clearable placeholder="请输入10日期产量"/>
        </el-form-item>
        <el-form-item label="11日期产量" prop="day11">
          <el-input v-model="form.day11" clearable placeholder="请输入11日期产量"/>
        </el-form-item>
        <el-form-item label="12日期产量" prop="day12">
          <el-input v-model="form.day12" clearable placeholder="请输入12日期产量"/>
        </el-form-item>
        <el-form-item label="13日期产量" prop="day13">
          <el-input v-model="form.day13" clearable placeholder="请输入13日期产量"/>
        </el-form-item>
        <el-form-item label="14日期产量" prop="day14">
          <el-input v-model="form.day14" clearable placeholder="请输入14日期产量"/>
        </el-form-item>
        <el-form-item label="15日期产量" prop="day15">
          <el-input v-model="form.day15" clearable placeholder="请输入15日期产量"/>
        </el-form-item>
        <el-form-item label="16日期产量" prop="day16">
          <el-input v-model="form.day16" clearable placeholder="请输入16日期产量"/>
        </el-form-item>
        <el-form-item label="17日期产量" prop="day17">
          <el-input v-model="form.day17" clearable placeholder="请输入17日期产量"/>
        </el-form-item>
        <el-form-item label="18日期产量" prop="day18">
          <el-input v-model="form.day18" clearable placeholder="请输入18日期产量"/>
        </el-form-item>
        <el-form-item label="19日期产量" prop="day19">
          <el-input v-model="form.day19" clearable placeholder="请输入19日期产量"/>
        </el-form-item>
        <el-form-item label="20日期产量" prop="day20">
          <el-input v-model="form.day20" clearable placeholder="请输入20日期产量"/>
        </el-form-item>
        <el-form-item label="21日期产量" prop="day21">
          <el-input v-model="form.day21" clearable placeholder="请输入21日期产量"/>
        </el-form-item>
        <el-form-item label="22日期产量" prop="day22">
          <el-input v-model="form.day22" clearable placeholder="请输入22日期产量"/>
        </el-form-item>
        <el-form-item label="23日期产量" prop="day23">
          <el-input v-model="form.day23" clearable placeholder="请输入23日期产量"/>
        </el-form-item>
        <el-form-item label="24日期产量" prop="day24">
          <el-input v-model="form.day24" clearable placeholder="请输入24日期产量"/>
        </el-form-item>
        <el-form-item label="25日期产量" prop="day25">
          <el-input v-model="form.day25" clearable placeholder="请输入25日期产量"/>
        </el-form-item>
        <el-form-item label="26日期产量" prop="day26">
          <el-input v-model="form.day26" clearable placeholder="请输入26日期产量"/>
        </el-form-item>
        <el-form-item label="27日期产量" prop="day27">
          <el-input v-model="form.day27" clearable placeholder="请输入27日期产量"/>
        </el-form-item>
        <el-form-item label="28日期产量" prop="day28">
          <el-input v-model="form.day28" clearable placeholder="请输入28日期产量"/>
        </el-form-item>
        <el-form-item label="29日期产量" prop="day29">
          <el-input v-model="form.day29" clearable placeholder="请输入29日期产量"/>
        </el-form-item>
        <el-form-item label="30日期产量" prop="day30">
          <el-input v-model="form.day30" clearable placeholder="请输入30日期产量"/>
        </el-form-item>
        <el-form-item label="31日期产量" prop="day31">
          <el-input v-model="form.day31" clearable placeholder="请输入31日期产量"/>
        </el-form-item>
        <el-form-item label="容量" prop="capacity">
          <el-input v-model="form.capacity" clearable placeholder="请输入容量"/>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>

import {add, deleteByIdList, getById, queryPageList, updateById} from '@/api/common'
import {getFactoryList} from '@/api/factory'

export default {
  name: 'tenantName',
  data() {

    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: false,
      // 总条数
      total: 0,
      apsRollingForecastFactoryCapacityList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        data: {}
      },
      // 表单参数
      form: {
        factoryId: undefined,
        year: undefined,
        month: undefined,
        day01: undefined,
        day02: undefined,
        day03: undefined,
        day04: undefined,
        day05: undefined,
        day06: undefined,
        day07: undefined,
        day08: undefined,
        day09: undefined,
        day10: undefined,
        day11: undefined,
        day12: undefined,
        day13: undefined,
        day14: undefined,
        day15: undefined,
        day16: undefined,
        day17: undefined,
        day18: undefined,
        day19: undefined,
        day20: undefined,
        day21: undefined,
        day22: undefined,
        day23: undefined,
        day24: undefined,
        day25: undefined,
        day26: undefined,
        day27: undefined,
        day28: undefined,
        day29: undefined,
        day30: undefined,
        day31: undefined,
        capacity: undefined,
        id: undefined
      },
      // 表单校验
      rules: {},
      tableHeaderList: []
    }
  },
  created() {
    document['pagePath'] = '/apsRollingForecastFactoryCapacity'
    this.getList()
  },
  methods: {
    /** 查询公告列表 */
    getList() {
      this.loading = true
      queryPageList(this.queryParams).then(response => {
        response = response.data
        this.tableHeaderList = response.headerList
        this.apsRollingForecastFactoryCapacityList = response.dataList
        this.total = parseInt(response.total)
        this.loading = false
      })
    },
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      let fid = this.form.id
      this.form = {
        factoryId: undefined,
        year: undefined,
        month: undefined,
        day01: undefined,
        day02: undefined,
        day03: undefined,
        day04: undefined,
        day05: undefined,
        day06: undefined,
        day07: undefined,
        day08: undefined,
        day09: undefined,
        day10: undefined,
        day11: undefined,
        day12: undefined,
        day13: undefined,
        day14: undefined,
        day15: undefined,
        day16: undefined,
        day17: undefined,
        day18: undefined,
        day19: undefined,
        day20: undefined,
        day21: undefined,
        day22: undefined,
        day23: undefined,
        day24: undefined,
        day25: undefined,
        day26: undefined,
        day27: undefined,
        day28: undefined,
        day29: undefined,
        day30: undefined,
        day31: undefined,
        capacity: undefined,
        id: fid
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.title = '添加滚动预测'
      this.open = true
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      let req = {idList: [row.id], pageSize: 1, pageNum: 1}
      getById(req).then(response => {
        this.form = response.data.dataList[0]
        this.title = '修改滚动预测'
        this.open = true
      })

    },

    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id !== undefined) {
            updateById(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            add(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const idList = row.id ? [row.id] : this.ids
      this.$modal.confirm('是否确认删序号为 <span style="color:red">' + idList + '</span> 的数据项？', '删除提示').then(function () {
        let req = {
          idList: idList
        }
        return deleteByIdList(req)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      })
      document.getElementsByClassName('el-message-box')[0].style.width = '520px'
    }
  }

}


</script>

