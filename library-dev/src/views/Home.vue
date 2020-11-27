<template>
<div>
    <title>图书馆管理系统</title>
    <el-container>
        <el-header  height="60">
            <div class="header-wrap">
                <div class="header-logo">
                    <img src="@/assets/logo.png" height="60" width="60">
                </div>
                <p>耗子尾汁<br>图书馆管理系统</p>
                <div id="login-div" class="header-login">
                    <a @click="login" >登陆</a>
                </div>
                <div id="register-div" class="header-register">
                    <a @click="register" >注册</a>
                </div>
            </div>
        </el-header>
        <el-main style="height:530px;">
            <div class="content-left">
                <div>
                    <div class="swiper-container">
                        <el-carousel class="swiper-img" :interval="4000" height="500px" indicator-position="outside">
                            <el-carousel-item  v-for="(item, index) in noticeList" :key="index">
                                <div class="ql-editor body-content" v-html="item.body"></div>    
                            </el-carousel-item>
                        </el-carousel>
                    </div>
                </div>
            </div>
            <div class="content-right">
                <login @toRegister="change2Register" v-show="isLogin"/>
                <register v-show="isRegister"></register>
            </div>
        </el-main>
        <el-footer height="30">
            <div class="footer">
                <div class="copy-right">
                    &copy;&nbsp;&nbsp;IBM 7 班 第二组
                </div>
            </div>
        </el-footer>
    </el-container>

</div>
</template>

<script>
// import { swiperList } from '../assets/data/swiper'
import axios from "axios"
import login from "@/components/login.vue"
import register from '@/components/register'
export default {
    name: 'home',
    components:{
        login,
        register
    },
    data() {
        return {
            isLogin:true,
            isRegister:false,
            noticeList:[]
        }
    },
    created() {
        this.getNoticeList();
    },
    methods: {
        getNoticeList(){
            axios({
                url:'http://localhost:9090/showGonggao',
                method:'get'
            }).then(data=>{
                console.log(data.data)
                let notices = data.data;
                let noticeList = this.noticeList;
                let len = notices.length;
                for (let i = 0; i < len; i++) {
                    if(!(notices[i].body == null || notices[i].body == "")){
                        notices[i].body = this.escapeStringHTML(notices[i].body);
                        noticeList.push(notices[i]);
                    }
                }
                this.noticeList = noticeList;

            }).catch(err=>{
                console.log("获取公告失败:"+err);
            })
        },
        login(){
            this.isLogin = true;
            this.isRegister = false;
            document.getElementById("login-div").style.borderBottom = "4px solid #95d2f6";
            document.getElementById("register-div").style.border = 0;
        },
        register(){
            this.isLogin = false;
            this.isRegister = true;
            document.getElementById("register-div").style.borderBottom = "4px solid #95d2f6";
            document.getElementById("login-div").style.border = 0;
        },
        change2Register(){
            this.register();
        },
         //把实体格式字符串转义成HTML格式的字符串
         escapeStringHTML(str) {
            str = str.replace(/&lt;/g,'<');
            str = str.replace(/&gt;/g,'>');
            return str;
        },
    }

}
</script>

<style scoped>
.body-content{
    width:100%;
    height: 500px;
    background: white;
    /* border:1px solid red; */
    overflow-x: hidden; 
    overflow-y: auto; 
}
.header-login{
    margin-left: 680px;
    width: 60px;
    height: 56px;
    line-height: 60px;
    vertical-align: middle;
    border-bottom: 4px solid #95d2f6;
}

.header-register{
    margin-left: 50px;
    width: 60px;
    height: 56px;
    line-height: 60px;
    vertical-align: middle;
}
.header-wrap a:hover{
    color: #95d2f6 ;
}
.header-wrap a{
    text-decoration: none;
    color: #67757f;
    font-size: 22px;
    cursor: pointer;
    font-family: cursive;
}
.header-wrap{
    display: flex;
    justify-content: flex-start;
    background: whitesmoke;
}
.header-wrap p{
    width: 250px;
    font-size:25px;
    margin-left: 20px;
    margin-top: 0;
    margin-bottom: 0;
    font-family: cursive;
}
.header-logo{
    margin-left: 50px;
    width: 60px;
    height: 60px;
}
.content-left {
    width: 65%;
    height: 450px;
    background: #f5f5f5;
}

/* .swiper-img-item img {
    width: 100%;
    height: 500px;
} */

.el-header {
    padding:0;
    color: #333;
}

.el-main {
    padding: 0;
    margin-top: 5px;
    color: #333;
    display: flex;
}

.content-right{
    background: azure;
    width: 35%;
    display: flex;
    justify-content: flex-start;
}

.footer{
    width: 100%;
    height:30px;
    line-height: 30px;
    vertical-align: middle;
}

.copy-right{
    font-weight: 600;
}
</style>
