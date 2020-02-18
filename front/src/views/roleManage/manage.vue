<template>
  <div class="app-container">
    <el-form @submit.native.prevent :inline="true" :model="ruleForm" label-width="120px">
      <el-form-item label="角色名称">
        <el-input
          v-model.trim="ruleForm.RealName"
          @keyup.enter.native="search"
          placeholder="请输入真实姓名"
        ></el-input>
      </el-form-item>
      <el-form-item label="角色Id">
        <el-input
          v-model.trim="ruleForm.RealName"
          @keyup.enter.native="search"
          placeholder="请输入真实姓名"
        ></el-input>
      </el-form-item>
      <el-form-item label="角色所属身份">
        <el-select v-model="ruleForm.cashState" clearable placeholder="请选择">
          <el-option label="待提现" value="0">待提现</el-option>
          <el-option label="成功" value="1">成功</el-option>
          <el-option label="失败" value="2">失败</el-option>
        </el-select>
      </el-form-item>
      <!-- <el-form-item>
        <div class="block">
          <el-date-picker
            v-model="time"
            @change="search"
            type="datetimerange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </div>
      </el-form-item> -->
      <el-button class="mb19" type="primary" icon="el-icon-search" @click="search">查询</el-button>
      <el-button class="mb19" type="primary" icon="el-icon-search" @click="search">新增</el-button>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" type="index" :index="indexMethod" width="50" align="center"></el-table-column>
      <el-table-column prop="urlName" label="角色id" align="center" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="urlName" label="角色名称" align="center" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="urlName" label="备注" align="center" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="urlName" label="所属身份" align="center" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="urlName" label="创建时间" align="center" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-link :underline="false" type="primary" :href="$global.url(scope.row.zipUrl)">预览</el-link>
          <el-link :underline="false" type="danger" @click="info(scope.row.templateId)">删除</el-link>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination">
      <el-pagination
        background
        :page-sizes="$global.pSizes"
        :layout="$global.pLayout"
        :page-size="ruleForm.pageSize"
        :current-page="ruleForm.pageNum"
        @size-change="sizeChange"
        @current-change="currentChange"
        :total="total"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
// 按需引入方法名
// import { queryTbStatic } from "@/api/template";

export default {
  data() {
    let ruleForm = {
      pageNum: this.$global.pageNum,
      pageSize: this.$global.pageSize
    };
    return {
      list: [],
      listLoading: true,
      ruleForm: ruleForm,
      total: this.$global.total
    };
  },
  created() {
    this.getData();
  },
  methods: {
    search() {
      this.ruleForm.pageNum = 1;
      this.getData();
    },
    currentChange(val) {
      this.ruleForm.pageNum = val;
      this.getData();
    },
    sizeChange(val) {
      this.ruleForm.pageSize = val;
      this.ruleForm.pageNum = 1;
      this.getData();
    },

    getData() {
      let url = this.$global.url("/template/queryTemplateList");
      this.$axios
        .post(url, this.ruleForm)
        .then(res => {
          this.list = res.data.data.list;
          console.log(res.data.data.list);
          this.total = res.data.data.count;
          this.listLoading = false;
        })
        .catch(error => {
          this.$tools.log(error, url);
          this.$message({
            type: "error",
            showClose: true,
            message: error.message,
            center: true
          });
        });
    },

    //删除
    info(Id) {
      let url = this.$global.url("/template/delTemplate");
      this.$confirm("此操作将删除该条数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$axios
            .post(url, {
              templateId: Id
            })
            .then(res => {
              this.$message({
                type: "success",
                message: "删除成功!"
              });
              this.getData();
            })
            .catch(error => {
              this.$tools.log(error, this.url);
              this.$message({
                type: "error",
                showClose: true,
                message: error.message,
                center: true
              });
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    //列表序号
    indexMethod(index) {
      return (this.ruleForm.pageNum - 1) * this.ruleForm.pageSize + index + 1;
    }
  }
};
</script>
