<template>
  <div>
    <div class="container" style="display:flex;width: 80%;margin:0 auto;">
      <div data-force="30" class="layer block" style="top: 0; width: 50%;border:1px solid #ccc;">
        <div class="layer title" style="text-align: center;margin-top:10px;">可选模板列表</div>
        <ul id="foo" class="block__list block__list_words" style="cursor:pointer;margin-top:20px;">
          <li>住址</li>
          <li>姓名</li>
          <li>我的3</li>
          <li>我的4</li>
          <li>我的5</li>
          <li>我的6</li>
          <li>我的7</li>
          <li>我的8</li>
          <li v-for="item in modelName">{{item}}</li>
        </ul>
      </div>
      <div
        data-force="18"
        class="layer block"
        style="top: 143px; width: 50%;margin-left;15px;border:1px solid #ccc;"
      >
        <div class="layer title" style="text-align: center;margin-top:10px;">已选模板列表</div>
        <ul id="bar" class="block__list block__list_tags" style="cursor:pointer;margin-top:20px;"></ul>
        <el-button type="primary" @click="getMessageThroughDoc" style="margin-left:40%">选择该模板</el-button>
      </div>
    </div>
    <div class="handelInput">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="模板名称">
          <el-input v-model="formInline.user" placeholder="模板名称"></el-input>
        </el-form-item>
        <el-form-item label="匹配规则">
          <el-select v-model="formInline.region" placeholder="匹配规则">
            <el-option label="字符串" value="zifuchuan"></el-option>
            <el-option label="电话" value="dianhua"></el-option>
            <el-option label="数字" value="shuzi"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="自定义规则">
          <el-input v-model="formInline.Exg" placeholder="正则表达式(可选)"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<style scope>
.item {
  width: 100px;
  height: 100px;
  border: solid 1px #ccc;
}
.flex {
  display: flex;
  justify-content: space-around;
}
</style>

<script>
import Sortable from "sortablejs";
export default {
  data() {
    //可选模板出现移除效果时候将内容 push 进数组
    //当已选模板出现移除效果时 replace 出数组
    return {
      putMessage: [],

      //自定义模板中输入的内容
      formInline: {
          user: '',
          region: '',
          Exg:''
        },
      //自定义模板的名称
      modelName:[],
    };
  },
  methods: {
    getMessageThroughDoc() {
      for (var i = 0; i < document.getElementById("bar").children.length; i++) {
        var Amessage = document.getElementById("bar").children[i].innerText;
        this.putMessage.push(Amessage);
      }
      console.log(this.putMessage);
    },
    foo(str) {
      var temp = str.split(/[\n,]/g);
      for (var i = 0; i < temp.length; i++) {
        if (temp[i] == "") {
          temp.splice(i, 1);
          //删除数组索引位置应保持不变
          i--;
        }
      }
      console.log(temp);
      return temp;
    },
    //自定义模板名称的添加按钮
    onSubmit() {
        console.log(this.formInline.user);
        console.log(this.formInline.region);
        console.log(this.formInline.Exg);
        this.modelName.push(this.formInline.user);
        this.formInline.user = '';
        this.formInline.region = '';
      }
  },
  mounted() {
    var that = this;
    //用来计算移除了几次
    var i = 0;
    Sortable.create(document.getElementById("foo"), {
      group: {
        name: "words",
        pull: true,
        put: true
      }
    });

    Sortable.create(document.getElementById("bar"), {
      group: {
        name: "words",
        pull: true,
        put: true
      },
      animation: 150 //动画参数
    });
  }
};
</script>
<style>
ul,
li {
  padding: 0;
  margin: 0;
  list-style: none;
  width: 85%;
  margin: 0 auto;
  text-align: center;
  background-color:#ccc;
}
.handelInput {
  height: 300px;
  width: 80%;
  margin: 0 auto;
  border: 1px solid #ccc;
  margin-top:20px;
}
</style>