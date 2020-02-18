<style>
    .login_main{
        width:50%;
        margin:0 auto;
        margin-top:150px;
    }
   .el-input{
       margin-top:15px;
   }
</style>
<template>
    <div>
        <div class="login_main">
            <template>
                <el-input v-model="username" placeholder="请输入名称"></el-input>
                <el-input v-model="password" placeholder="请输入密码"></el-input>
            </template>
            <el-button type="primary" round style="display:block;margin:0 auto;margin-top:15px;" @click="getMessage">登录</el-button>
        </div>
    </div>
</template>
<script>
export default {
    data(){
        return {
            //用户名
            username: '',
            //密码
            password:''
        }
    },
     beforeCreate: function () {
        console.log("执行");
        let myName=sessionStorage.getItem("keyName"); 
        console.log(myName);
        this.$axios({
            // A项目进行token验证
            // url:'/lb/member/check',
            url:'/test/test/check',
            method: 'post',
            headers:{
                'Content-Type':'application/x-www-form-urlencoded',
                'access_token':myName
            }
            
        })
        .then(response=>{
            console.log(response.data);
             if(response.data.msg == "走了拦截器,您已登陆"){
                this.$router.push({
                    path:'success'
                })
            }   
        })

    },
    methods:{
        getMessage(){
            var that = this;
            console.log(this.username,this.password);
            // this.$axios.post('/test/pass/login', {
            //     accountNumber: '20200212',
            //     password: '123456'
            // })
            // .then(function (response) {
            // console.log(response);
            
            // })
            // .catch(function (error) {
            // console.log(error);
            // });

            this.$axios.post('/lb/login/frontLogin', {
                // accountNumber: '20200212',
                // password: '123456'
                accountNumber:that.username,
                password:that.password
            })
            .then(function (response) {
            console.log(response);
            console.log(response.data.data.data.accessToken);
            var sessionDate = response.data.data.data.accessToken;
            sessionStorage.setItem("keyName", sessionDate);

            if(response.status == 200){
                that.$router.push({
                    path:'success'
                })
            }

            })
            .catch(function (error) {
            console.log(error);
            });
        }
    }
}
</script>

