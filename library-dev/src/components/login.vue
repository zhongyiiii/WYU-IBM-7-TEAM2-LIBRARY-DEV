<template>
<div class="login-in">
  <div class="login">
    <div class="login-head">
      <span>帐号登录</span>
    </div>
    <el-form :model="loginForm" :rules="rules" ref="loginForm"   class="demo-ruleForm" >
      <el-form-item prop="username" >
        <el-input placeholder="登录ID" type="text" v-model="loginForm.username" maxlength="30" show-word-limit ></el-input>
      </el-form-item>
      <el-form-item  prop="password">
        <el-input type="password" placeholder="密码" v-model="loginForm.password" maxlength="30"></el-input>
      </el-form-item>
      <el-form-item  prop="validateCode">
        <el-input
          style="width:150px;float:left;"
          placeholder="验证码"
          v-model="validateCode"
          clearable>
        </el-input>
        <img @click="getValidateCode" :src="validateImage" alt="" width="115" height="35" style="float:right;">
      </el-form-item>
      <div class="login-btn">
        <el-button style="width:140px;margin-right:10px;" @click="change2Register" >注册</el-button>
        <el-button style="width:140px" @click="sumbit('loginForm')" type="primary" >登录</el-button>
      </div>
    </el-form>
  </div>
</div>
</template>

<script>
import axios from 'axios'
import qs from 'qs'
export default {
  name: 'login',
  components: {

  },
  created(){
    this.getValidateCode();
  },
  data: function () {
    
    let validateName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('用户名不能为空'))
      } else {
        this.loginForm.username = value;
        callback()
      }
    }
    let validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        this.loginForm.password = value
        callback()
      }
    }
    return {
      validateImage:'',
      validateCode:'',
      loginForm: { // 登录用户名密码
        username: '',
        password: '',
        
      },
      rules: {
        username: [
          { validator: validateName, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { validator: validatePassword, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    //得到验证码
    getValidateCode(){
      let getValidataCodeUrl = this.GLOBAL.basePath+"/getValidateCode";

      let time = qs.stringify({
        time:new Date().getTime()
      })
      this.validateImage = getValidataCodeUrl+"?"+time;
    },
      change2Register(){
          this.$emit("toRegister")
      },
      sumbit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let user = {  //封装用户信息为json
            loginId:this.loginForm.username.toString(),
            userPwd:this.loginForm.password.toString(),
            validateCode:this.validateCode
          }
          axios({
              url:this.GLOBAL.basePath+"/loginUser",
              method:"post",
              contentType:'application/json',
              data:user
          }).then(data=>{
            let res = data.data;
            if(res == 0){
              this.alertFail(this.GLOBAL.systemName,'用户名或密码错误！',1500);
            }else if( res == 3){
              this.alertFail(this.GLOBAL.systemName,'验证码错误',1500);
            }else if(res == 1 || res == 2){       //用户登录
              axios({
                  url:this.GLOBAL.basePath+"/showUser",
                  method:"post",
                  contentType:'application/json',
                  data:user
              }).then(userData=>{                                                       //获取用户信息成功
                  //跳转到用户主页
                  if(res == 1){
                      localStorage.setItem('userInfo', JSON.stringify(userData.data));      //将用户信息存储在本地storage中
                      this.$router.push({
                        name:"index"
                    })
                  }else{
                    let admin = {"userName":"admin", "userPwd":"admin"}
                      localStorage.setItem('admin', JSON.stringify(admin) );      //将用户信息存储在本地storage中
                      this.$router.push({
                        name:"adminIndex"
                    })
                  }
                  
              }).catch(getUserDateError=>{
                console.log("获取用户信息失败："+getUserDateError);
              })
            }
          }).catch(err=>{
            console.log("登录错误:"+err);
          })
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  }
}
</script>

<style scoped>
.login-in{
    margin:0 auto;
    margin-top: 80px;
}
.login {
  width: 300px;
  height: 260px;
  padding: 30px 50px;
  border-radius: 10px;
  background-color: #ffffff;
}
.login-head {
    text-align: center;
    margin-bottom: 10px;
    font-size: 20px;
    font-weight: 600;
  }
</style>
