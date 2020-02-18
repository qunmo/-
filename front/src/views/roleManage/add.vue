<template>
  <div>
    <crumbs :crumbs="crumbs"></crumbs>
    <div class="app-container">
      <div class="form-box">
        <el-form :model="ruleForm" ref="ruleForm" :rules="rules" label-width="240px">
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="ruleForm.roleName"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="note">
            <el-input v-model="ruleForm.note" ></el-input>
          </el-form-item>
          <el-form-item label="身份" prop="identity">
            <el-select
              v-model="ruleForm.identity"
              placeholder="请选择角色身份"
              @change="identityChange(ruleForm.identity)"
            >
              <el-option
                v-for="(value,key) in $global.identityTypeList"
                :key="key"
                :label="value"
                :value="parseInt(key)"
                v-if="key!=4"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item v-if="showPrivileges" label="功能权限" prop="privilegeIdList">
            <el-tree
              :data="privileges"
              ref="tree"
              :props="props"
              node-key="privilegeId"
              show-checkbox
            ></el-tree>
          </el-form-item>
          <el-form-item v-else label="功能权限" style="display:none">
            <el-tree
              :data="privileges"
              ref="tree"
              :props="props"
              node-key="privilegeId"
              show-checkbox
            ></el-tree>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="save('ruleForm')">确定</el-button>
            <el-button type="primary" @click="prev">返回</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "basic_role_add",
  data: function() {
    let ruleForm = {
      roleName: "",
      note: "",
      identity: 1,
      privilegeIdList: []
    };
    return {
      crumbs: ["基础管理", "角色管理", "角色新增"],
      props: {
        label: "privilegeName",
        children: "childList"
      },
      ruleForm: ruleForm,
      roleList: [],
      privileges: [],
      dialogVisible: false,
      dialogImageUrl: "",
      showPrivileges: true,
      rules: {
        roleName: [
          {
            required: true,
            message: "请输入角色名称",
            trigger: "change"
          }
        ],
        note: [
          {
            required: true,
            message: "请输入备注",
            trigger: "change"
          }
        ],
        identity: [
          {
            required: true,
            message: "请选择角色身份",
            trigger: "change"
          }
        ],
        privilegeIdList: [
          {
            required: true,
            message: "请设置角色功能权限",
            trigger: "blur"
          }
        ]
      }
    };
  },
  created() {
    //获取身份对应的资源树
    this.identityChange(this.ruleForm.identity);
  },
  methods: {
    //身份切换
    identityChange(identity) {
      if (identity == 4 || identity == 5 || identity == 6 || identity == 7) {
        this.showPrivileges = false;
      } else {
        this.showPrivileges = true;
        let url = this.$global.url("/member/role/queryPrivilegeByIdentity");
        this.$axios
          .get(url, {
            params: {
              identity: identity
            }
          })
          .then(res => {
            this.privileges = res.privileges;
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
      }
    },
    //提交新增角色
    save(formName) {
      var privilegeIdList = [
        ...this.$refs.tree.getCheckedKeys(false),
        ...this.$refs.tree.getHalfCheckedKeys()
      ];
      console.log(privilegeIdList);
      this.ruleForm.privilegeIdList = privilegeIdList;
      let url = this.$global.url("/member/role/addRole");
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false;
        }
        this.$axios
          .post(url, this.ruleForm)
          .then(res => {
            this.$message({
              type: "success",
              showClose: true,
              message: "添加成功",
              center: true
            });
            this.prev();
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
      });
    },
    // 返回
    prev() {
      this.$router.go(-1);
    }
  }
};
</script>

<style>
</style>