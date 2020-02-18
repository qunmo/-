<template>
    <div>
        <div class="app-container">
            <div class="form-box">
                <el-form :model="ruleForm" ref="ruleForm" :rules="rules" label-width="240px">
                    <el-form-item label="角色名称" prop="roleName">
                        <el-input v-model="ruleForm.roleName"></el-input>
                    </el-form-item>
                    <el-form-item label="备注" prop="note">
                        <el-input v-model="ruleForm.note"></el-input>
                    </el-form-item>
                    <el-form-item label="身份" prop="identity">
                        <el-select v-model="ruleForm.identity" placeholder="请选择角色身份"
                            @change="identityChange(ruleForm.identity)">
                            <el-option v-for="(value,key) in $global.identityTypeList" :key="key" :label="value"
                                :value="key-0" v-if="key!=4">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="功能权限" prop="privilegeIdList">
                        <el-tree :data="privileges" :check-strictly="isChecked" :default-expanded-keys="privilegeIdList"
                            :default-checked-keys="privilegeIdList" ref="tree" :props="props" node-key="privilegeId"
                            show-checkbox></el-tree>
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
        name: 'basic_role_edit',
        data: function () {
            let ruleForm = {
                roleName: "",
                note: "",
                identity: "",
                privilegeIdList: []
            }

            return {
                props: {
                    label: 'privilegeName',
                    children: 'childList',
                },
                isChecked: true,
                ruleForm: ruleForm,
                roleList: [],
                privileges: [],
                privilegeIdList: [],
                rules: {
                    roleName: [{
                        required: true,
                        message: '请输入角色名称',
                        trigger: 'change'
                    }],
                    note: [{
                        required: true,
                        message: '请输入备注',
                        trigger: 'change'
                    }],
                    identity: [{
                        required: true,
                        message: '请选择角色身份',
                        trigger: 'change'
                    }],
                    privilegeIdList: [{
                        required: true,
                        message: '请设置角色功能权限',
                        trigger: 'blur'
                    }]
                }
            }
        },
        created() {
            let id = this.$route.params.id;
            this.getData(id);
        },
        watch: {
            '$route'(to, from) {
                if (to.meta.path == '/basic/role/edit/') {
                    let id = this.$route.params.id;
                    this.getData(id);
                }
            }
        },
        methods: {
            //修改角色，回显
            getData(id) {
                let url = this.$global.url('/member/role/queryRoleByRoleId');
                this.$axios.get(url, {
                    params: {
                        roleId: id
                    }
                }).then((res) => {
                    Object.assign(this.ruleForm, res.role);
                    this.ruleForm.roleId = this.$route.params.id;
                    this.privilegeIdList = res.role.privilegeIdList;
                    this.identityChange(this.ruleForm.identity);
                }).catch((error) => {
                    this.$tools.log(error, url)
                    this.$message({
                        type: 'error',
                        showClose: true,
                        message: error.message,
                        center: true
                    });
                })
            },
            //身份切换
            identityChange(identity) {
                let url = this.$global.url('/member/role/queryPrivilegeByIdentity');
                this.$axios.get(url, {
                    params: {
                        identity: identity
                    }
                }).then((res) => {
                    this.privileges = res.privileges;
                    this.isChecked = false;
                }).catch((error) => {
                    this.$tools.log(error, url)
                    this.$message({
                        type: 'error',
                        showClose: true,
                        message: error.message,
                        center: true
                    });
                })
            },
            //保存修改
            save(formName) {
                var privilegeIdList = [...this.$refs.tree.getCheckedKeys(false), ...this.$refs.tree
                .getHalfCheckedKeys()];
                this.ruleForm.privilegeIdList = privilegeIdList;
                let url = this.$global.url('/member/role/updateRoleByRoleId');
                this.$refs[formName].validate((valid) => {
                    if (!valid) {
                        return false;
                    }
                    this.$axios.post(url, this.ruleForm)
                        .then((res) => {
                            this.$message({
                                type: 'success',
                                showClose: true,
                                message: "修改成功",
                                center: true
                            })
                            this.prev();
                        }).catch((error) => {
                            this.$tools.log(error, url);
                            this.$message({
                                type: 'error',
                                showClose: true,
                                message: error.message,
                                center: true
                            });
                        })
                });
            },
            // 返回
            prev() {
                this.$router.go(-1);
            }
        }
    }
</script>

<style>
.container{
  width: 60%;
  height: 80%;
  margin: 50px auto 0;
}
</style>