<template>
    <div class="register">
        <div class="register-head">
            <span>用户注册</span>
        </div>
        <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-width="80px"
        class="demo-ruleForm"
        >
            <el-form-item label="登陆ID" prop="loginId">
                <el-input
                v-model="ruleForm.loginId"
                maxlength="30"
                show-word-limit
                ></el-input>
            </el-form-item>
            <el-form-item label="用户名" prop="userName">
                <el-input
                type="text"
                maxlength="20"
                show-world-limit
                v-model="ruleForm.userName"
                ></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="userPwd">
                <el-input
                type="password"
                v-model="ruleForm.userPwd"
                placeholder="请输入密码"
                maxlength="8"
                show-word-limit
                ></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
                <el-input
                type="password"
                v-model="ruleForm.checkPass"
                placeholder="确保匹配"
                maxlength="8"
                show-word-limit
                ></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input
                v-model="ruleForm.email"
                maxlength="30"
                show-word-limit
                ></el-input>
            </el-form-item>
            <el-form-item class="register-btn">
                <el-button style="width:105px" type="primary" @click="submitForm('ruleForm')"
                >提交</el-button
                >
                <el-button style="width:105px" @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>


<script>
import axios from 'axios'
export default {
  data() {
    var checkId = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("ID不能为空"));
      }
      setTimeout(() => {
        if (!Number.isInteger(parseInt(value))) {
          callback(new Error("请输入数字值"));
        } else {
          this.ruleForm.loginId = value;
          callback();
        }
      });
    };
    

    var checkName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("用户名不能为空"));
      } else {
        this.ruleForm.userName = value;
        callback();
      }
    };

    var checkEmail = (rule, value, callback) => {
      const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
      if (!value) {
        return callback(new Error("邮箱不能为空"));
      } else {
        if (mailReg.test(value)) {
          this.ruleForm.email = value;
          callback();
        } else {
          callback(new Error('请输入正确的邮箱格式'))
        } 
      }
    };

    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.userPwd) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        this.ruleForm.userPwd = value;
        callback();
      }
    };
    return {
      ruleForm: {
        loginId: "",
        userName: "",
        userPwd: "",
        checkPass: "",
        email: "",
      },
      rules: {
        loginId: [{ validator: checkId, trigger: "blur" }],
        userName: [{ validator: checkName, trigger: "blur" }],
        userPwd: [{ validator: validatePass, trigger: "blur" }],
        checkPass: [{ validator: validatePass2, trigger: "blur" }],
        email: [{ validator: checkEmail, trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var user = {
            loginId:this.ruleForm.loginId,
            userName:this.ruleForm.userName,
            userPwd:this.ruleForm.userPwd,
            email:this.ruleForm.email
          }
          console.log(user)
          axios({
              url:this.GLOBAL.basePath+'/addUser',
              method:"post",
              contentType:'application/json',
              data:user
          }).then(data=>{
            let res = data.data;
            if(res == 1){
              this.alertSuccess(this.GLOBAL.systemName,'注册成功',1500);
            }else{
              this.alertInfo(this.GLOBAL.systemName,'服务器繁忙',1500);
            }
          }).catch(err=>{
            console.log("注册错误:"+err);
          })
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>
<style scoped>
.register{
    margin:0 auto;
    margin-top: 30px;
    width:300px;
    height: 400px;
    padding: 30px 50px;
    border-radius: 10px;
    background-color: #ffffff;
}
.register-head {
    text-align: center;
    margin-bottom: 10px;
    font-size: 20px;
    font-weight: 600;
  }

</style>