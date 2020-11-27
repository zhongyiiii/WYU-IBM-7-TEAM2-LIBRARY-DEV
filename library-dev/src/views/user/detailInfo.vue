<template>
<body>
    <div class="detail-info">
        <div class="info-form" id="infoForm">
            <h2>用户信息</h2>
            <el-form :inline="true" :model="formInline" status-icon :rules="rules" ref="formInline" class="demo-forminline">
                <el-form-item label="姓名：" prop="userName"  maxlength="20"
                >
                    <el-input v-model="formInline.userName" placeholder="姓名" maxlength="20" show-word-limit></el-input>
                </el-form-item>
                <el-form-item label="性别：" prop="gender">
                    <el-input v-model="formInline.gender" placeholder="性别" maxlength="5" show-word-limit></el-input>
                </el-form-item>
                <el-form-item label="年龄：" prop="age">
                    <el-input v-model.number="formInline.age" placeholder="年龄" maxlength="5" show-word-limit></el-input>
                </el-form-item>
                <br />
                <el-form-item label="邮箱：" prop="email">
                    <el-input v-model="formInline.email" placeholder="邮箱" maxlength="30" show-word-limit></el-input>
                </el-form-item>
                <el-form-item label="联系：" prop="contact">
                    <el-input v-model="formInline.contact" placeholder="电话" maxlength="30" show-word-limit></el-input>
                </el-form-item>
                <el-form-item label="借阅：" prop="remainingBooks">
                    <el-input v-model="formInline.remainingBooks" placeholder="借阅" readonly="readonly"></el-input>
                </el-form-item>
                <br />
                <el-form-item label="地址：" prop="address">
                    <el-input style="width: 320px" v-model="formInline.address" placeholder="地址" maxlength="100" show-word-limit></el-input>
                </el-form-item>

                <el-form-item prop="birthday" label="出生时间：">
                    <el-date-picker type="date" placeholder="选择日期" v-model="formInline.birthday" style="width: 325px"></el-date-picker>
                </el-form-item>

                <br />
                <el-form-item style="margin-left:-30px;" label="自我介绍：" prop="description">
                    <el-input type="textarea" :rows="3" style="width: 740px" v-model="formInline.description" placeholder="自我介绍" maxlength="100" show-word-limit></el-input>
                </el-form-item>
                <br />
                <el-form-item v-show="submitAndReset">
                    <el-button class="submit-btn" type="primary" @click="submitForm('formInline')">提交</el-button>
                    <el-button class="reset-btn" @click="resetForm('formInline')">重置</el-button>
                </el-form-item>
                <el-form-item v-show="!editOrNot" class="edit-btn">
                    <el-button type="info" @click="editable">编辑</el-button>
                </el-form-item>

            </el-form>
        </div>
        <div style="width: 320px; margin: 0 auto">
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" class="demo-ruleForm">
                <el-form-item label="新密码:" prop="pass">
                    <el-input type="password" v-model="ruleForm.pass" placeholder="请输入8个字符以上的密码" maxlength="30" show-word-limit></el-input>
                </el-form-item>
                <el-form-item label="确认新密码:" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" placeholder="确保匹配" maxlength="30" show-word-limit></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button class="pwd-btn" type="primary" @click="submitForm1('ruleForm')">更改密码</el-button>
                    <el-button class="pwd-btn" type="info" @click="resetForm2('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</body>
</template>

<script>
import axios from 'axios'
export default {
    data() {
        var checkuserName = (rule, value, callback) => {
            if (!value) {
                return callback(new Error("姓名不能为空"));
            } else {
                // callback();
                this.formInline.userName = value;
                callback();
            }
        };
        var checkgender = (rule, value, callback) => {
            if (!value) {
                return callback(new Error("性别不能为空"));
            } else {
                this.formInline.gender = value;
                callback();
            }
        };
        var checkage = (rule, value, callback) => {
            if (!value) {
                return callback(new Error("年龄不能为空"));
            }
            setTimeout(() => {
                if (!Number.isInteger(value)) {
                    callback(new Error("请输入数字值"));
                } else {
                    this.formInline.age = value;
                    callback();
                }
            });
        };

        var checkaddress = (rule, value, callback) => {
            if (!value) {
                return callback(new Error("地址不能为空"));
            } else {
                this.formInline.address = value;
                callback();
            }
        };
        var checkemail = (rule, value, callback) => {
            if (!value) {
                return callback(new Error("邮箱不能为空"));
            } else {
                this.formInline.email = value;

                callback();
            }
        };
        var checkcontact = (rule, value, callback) => {
            if (!value) {
                return callback(new Error("电话不能为空"));
            }else {
                this.formInline.contact = value;

                callback();
            }
            
              
        };

        var checkbirthday = (rule, value, callback) => {
            if (!value) {
                return callback(new Error("出生时间不能为空"));
            } else {
                this.formInline.birthday = value;
                callback();
            }
        };
        var checkdescription = (rule, value, callback) => {
            if (!value) {
                return callback(new Error("自我介绍不能为空"));
            } else {
                this.formInline.description = value;
                callback();
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
            } else if (value !== this.ruleForm.pass) {
                callback(new Error("两次输入密码不一致!"));
            } else {
                this.ruleForm.checkPass = value;
                callback();
            }
        };

        return {
            formInline: {},
            ruleForm: {
                pass: "",
                checkPass: "",
            },
            rules: {
                gender: [{
                    validator: checkgender,
                    trigger: "blur"
                }],
                userName: [{
                    validator: checkuserName,
                    trigger: "blur"
                }],
                contact: [{
                    validator: checkcontact,
                    trigger: "blur"
                }],
                address: [{
                    validator: checkaddress,
                    trigger: "blur"
                }],
                age: [{
                    validator: checkage,
                    trigger: "blur"
                }],
                email: [{
                    validator: checkemail,
                    trigger: "blur"
                }],
                birthday: [{
                    validator: checkbirthday,
                    trigger: "blur"
                }],
                description: [{
                    validator: checkdescription,
                    trigger: "blur"
                }],
                pass: [{
                    validator: validatePass,
                    trigger: "blur"
                }],
                checkPass: [{
                    validator: validatePass2,
                    trigger: "blur"
                }],
            },
            submitAndReset: false,
            editOrNot: false
        };
    },
    created(){
        this.formInline = JSON.parse(localStorage.getItem('userInfo'));
    },
    mounted: function () {   
        
        this.noEditable();
    },
    methods: {
        noEditable() {
            let form = document.getElementById("infoForm");
            let allInputBox = form.getElementsByTagName("input");
            let len = allInputBox.length;
            for (let i = 0; i < len; i++) {
                allInputBox[i].setAttribute("disabled", "disabled");
            }
            let textarea = form.getElementsByTagName("textarea")[0];
            textarea.setAttribute("disabled", "disabled");

            this.submitAndReset = false;
            this.editOrNot = false;
        },
        editable() {
            let form = document.getElementById("infoForm");
            console.log(form.getElementsByTagName("input"));
            let allInputBox = form.getElementsByTagName("input");
            let len = allInputBox.length;
            for (let i = 0; i < len; i++) {
                allInputBox[i].removeAttribute("disabled");
            }
            let textarea = form.getElementsByTagName("textarea")[0];
            textarea.removeAttribute("disabled");

            this.submitAndReset = true;
            this.editOrNot = true;
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    //提交修改
                    let user = this.formInline;
                    axios({
                        url:this.GLOBAL.basePath+'/updateUser',
                        method:'post',
                        contentType:'application/json',
                        data:user
                    }).then(data=>{
                        let res = data.data;
                        console.log(res);
                        if(res == 1){
                            this.alertSuccess(this.GLOBAL.systemName,'修改成功',1200);
                            axios({
                                url:this.GLOBAL.basePath+'/showUser',
                                method:"post",
                                contentType:'application/json',
                                data:user
                            }).then(userData=>{                                                       //获取用户信息成功
                                localStorage.setItem('userInfo', JSON.stringify(userData.data));      //将用户信息存储在本地storage中
                            }).catch(getUserDateError=>{
                                console.log("修改信息后获取用户信息失败："+getUserDateError);
                            })
                        }else{
                            this.alertInfo(this.GLOBAL.systemName,'正在修改信息...服务器繁忙',1500);
                        }
                    }).catch(err=>{
                        console.log("修改信息错误:"+err)
                    })
                } else {
                    console.log("error submit!!");
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
            this.noEditable();
        },
        resetForm2(formName) {
            this.$refs[formName].resetFields();
        },
        submitForm1(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.formInline.userPwd = this.ruleForm.checkPass;
                    let user = this.formInline;
                    console.log(user)
                    axios({
                        url:this.GLOBAL.basePath+'/updatePassword',
                        method:"post",
                        contentType:'application/json',
                        data:user
                    }).then(userData=>{    //获取用户信息成功
                        if(userData.data == 1)  {
                            this.alertSuccess(this.GLOBAL.systemName,'修改密码成功',1500);
                            localStorage.setItem('userInfo', JSON.stringify(userData.data));      //将用户信息存储在本地storage中
                        } else{
                            this.alertFail(this.GLOBAL.systemName,'修改密码失败',1500);
                        }                                                  
                    }).catch(getUserDateError=>{
                        console.log("修改信息后获取用户信息失败："+getUserDateError);
                    })
                } else {
                    console.log("error submit!!");
                    return false;
                }
            });
        },
    },
};
</script>

<style scoped>
.detail-info {
    display: flex;
    justify-content: flex-start;
}

.info-form {
    width: 70%;
    height: 500px;
    background: #f5f5f5;
}

.edit-btn {
    float: right;
    margin-right: 80px;
}

.submit-btn {
    width: 300px;
    margin-right: 50px;
}

.reset-btn {

    width: 300px;
}

.pwd-btn {
    width: 155px;
}
</style>
