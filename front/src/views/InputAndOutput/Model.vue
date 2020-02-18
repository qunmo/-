
<template>
  <div class="main">
    <template>
    <el-table :data="tableData" style="width: 30%;display: inline-block;">
        <el-table-column label="模板名称" width="180">
        <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.name }}</el-tag>
            </div>
        </template>
        </el-table-column>
        <el-table-column label="操作">  
        <template slot-scope="scope">
            <el-button size="mini" @click="handleUse(scope.$index, scope.row)">使用</el-button>
            <!-- <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button> -->
        </template>
        </el-table-column>
    </el-table>
    </template>
    <div class="putTemplate">
        <h5>您将要使用的模板</h5>
        <span v-for="date in putValue" class="putspan">
            <el-button>
                {{date.buttonName}}
            </el-button>
        </span>
    </div>
  </div>
</template>

    <script>
export default {
  data() {
    let getName =[];
    return {
      tableData: [
        {
          name: "姓名",
        },
        {
          name: "电话",
        },
        {
          name: "家庭住址",
        },
        {
          name: "是否去过武汉",
        }
      ],
      //放置模板名称的数组
      putValue:getName,
      //当选用一次模板之后就不能重复点击
    };
  },
  methods: {
    //table 中的使用按钮
    handleUse(index, row) {

      // console.log(index, row);
      // console.log(index,row.name);
      var ClickNumber = 0;
      ClickNumber++;
      for(var i = 0; i<ClickNumber;i++){
        var obj = {};
        obj.buttonName =row.name;
        obj.buttonId = index;
        this.putValue.push(obj);
      }
      console.log(this.putValue);

    },
    // table 中的删除按钮
    // handleDelete(index, row) {
    //   console.log(index, row);
    // },
  }
};

</script>
<style scoped>
.main{
  display: flex;
  flex-direction: row;
}
.putTemplate{
    display: inline-block;
    width: 45%;
    border:1px solid #ccc;
    margin-left: 10px;
}
.putTemplate h5{
    text-align: center;
    margin-top: 0px;
}

</style>