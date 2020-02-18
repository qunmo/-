<template>
  <el-form :model="form" class="form-css">
    <el-form-item label="上传文件" :label-width="formLabelWidth">
      <el-upload
        ref="uploadExcel"
        :action="$global.url('/template/uplaodZip')"
        :limit="limitNum"
        :auto-upload="true"
        accept=".zip"
        :before-upload="beforeUploadFile"
        :on-change="fileChange"
        :on-exceed="exceedFile"
        :on-remove="removeFile"
        :on-success="handleSuccess"
        :on-error="handleError"
        :file-list="fileList"
      >
        <el-button size="small" plain style="margin-left:54px;">选择文件</el-button>
        <div
          slot="tip"
          class="el-upload__tip"
          style="margin-left:54px;"
        >只能上传zip文件，且不超过10M, 模板只能命名为中文</div>
      </el-upload>
    </el-form-item>
    <el-form-item label="上传模板名称" style="margin-left:10px;">
      <el-input
        v-model="form.name"
        style="display: inline-block;width:150px;margin-left:30px;"
        @change="changeInput()"
      ></el-input>
    </el-form-item>
    <el-button
      size="small"
      type="primary"
      @click="uploadFile"
      style="margin-left:136px;"
      :disabled="idAble"
    >立即上传</el-button>
    <!-- <el-button size="small" >取消</el-button> -->
  </el-form>
</template>

<script>
import axios from 'axios'
export default {  
  data() {
    //新增模板时提交的url 和模板名称
    let TemplateMessage = {
      urlName:'',
      zipUrl:''
    };
    return {
      limitNum: 1,
      formLabelWidth: '80px',
      form: {
        file: '',
        name:'',
      },
      fileList: [],
      //按钮是否启用
      idAble:true,
      //判断文件是否上传成功
      fillSuccess:false,
      //判断输入框中是否全是汉字
      chapterSuccess:false,
      //新增模板时提交的url 和模板名称
      TemplateMessage:TemplateMessage,
    }
  },
  methods: {
    // 文件超出个数限制时的钩子
    exceedFile(files, fileList) {
      this.$notify.warning({
        title: '警告',
        message: `只能选择 ${this.limitNum} 个文件，当前共选择了 ${files.length + fileList.length} 个`
      });
    },
    // 文件状态改变时的钩子
    fileChange(file, fileList) {
      console.log('change')
      console.log(file)
      this.form.file = file.raw
      console.log(this.form.file)
      console.log(fileList)
    },
    // 上传文件之前的钩子, 参数为上传的文件,若返回 false 或者返回 Promise 且被 reject，则停止上传
    beforeUploadFile(file) {
      console.log('before upload')
      console.log(file)
      let extension = file.name.substring(file.name.lastIndexOf('.')+1)
      let size = file.size / 1024 / 1024
      if(extension !== 'zip') {
        this.$notify.warning({
          title: '警告',
          message: `只能zip文件`
        });
      }
      if(size > 10) {
        this.$notify.warning({
          title: '警告',
          message: `文件大小不得超过10M`
        });
      }
    },
    //删除上传的zip文件的时候触发
    removeFile(file,fileList){
      console.log("文件被移除了");
      this.idAble = true;
    },
    // 文件上传成功时的钩子
    handleSuccess(res, file, fileList) {
      this.$notify.success({
        title: '成功',
        message: `文件上传成功`
      });
      console.log(res);
      console.log("上传成功之后打印的消息");
      this.TemplateMessage.zipUrl = res.data.ZipUrl;
      console.log(this.TemplateMessage.zipUrl);
      this.fillSuccess = true;
      if(this.fillSuccess == true && this.chapterSuccess == true ){
        this.idAble = false;
      }
    },
    // 文件上传失败时的钩子
    handleError(err, file, fileList) {
      this.$notify.error({
        title: '错误',
        message: `文件上传失败`
      });
    },
    //input框失去焦点时候进行输入内容校验，只能是中文，通过可以点击提交，不通过不可以提交
    changeInput(){
      console.log(this.form.name);
      this.TemplateMessage.urlName = this.form.name;
      const IsTure = this.form.name.replace(/[^\u4E00-\u9FA5]/g,'');
      if(IsTure){
        this.$notify.success({
          title:'成功',
          message:'成功输入中文名称'
        })
        this.chapterSuccess = true;
      }else{
        this.$notify.error({
          title: '错误',
          message: `只能输入中文名称`
        });
        this.chapterSuccess = false;
      }
      if(this.fillSuccess == true && this.chapterSuccess == true ){
        this.idAble = false;
      }
      
    },
    //点击提交按钮触发的事件
    uploadFile() {
      let url = this.$global.url("/template/addTemplate");
      this.$refs.uploadExcel.submit();

      this.$axios.post(url,this.TemplateMessage)
      .then(res => {
        if(res.status == 200){
          this.$notify.success({
            title:'成功',
            message:'插入队列成功'
          })
        };
        this.$router.go(-1);
      }).catch(error => {
          this.$tools.log(error, url);
          this.$message({
            type: "error",
            showClose: true,
            message: error.message,
            center: true
          });
        })
    }
  }
}
</script>

<style lang="scss" scoped>
.form-css {
  margin-top: 30px;
}
</style>
