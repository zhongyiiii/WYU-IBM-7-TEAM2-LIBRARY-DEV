<template>
<div class="container">
    <div class="header-wrap">
        <div class="header-logo">
            <img src="@/assets/logo.png" height="60" width="60">
        </div>
        <p>耗子尾汁<br>图书馆管理系统</p>
        <div class="header-exit">
            <div class="myinfo">
                <img @click="jump2editUserInfo"  src="@/assets/icons/user-icon.png" height="35" width="35" alt="">
                <p>{{loginId}}</p>
                <p>{{userName}}</p>
            </div>
            <img @click="exit" src="@/assets/icons/exit-icon.png" height="35" width="35" alt="">
        </div>
    </div>
    <div class="content">
        <div class="my-info" @click="jump2MyInfo($event)">
            <img id="myInfo" @mouseleave="toNormal('myInfo')" @mouseenter="magnify('myInfo')" src="@/assets/icons/I.png" width="180" height="180" alt="">
            <p>我的信息</p>
        </div>
        <div class="bookshell" @click="jump2BookShell($event)">
            <img id="bshell" @mouseleave="toNormal('bshell')" @mouseenter="magnify('bshell')" src="@/assets/icons/B.png" width="180" height="180" alt="">
            <p>我的书架</p>
        </div>
        <div class="lib" @click="jump2Library($event)">
            <img id="library" @mouseleave="toNormal('library')" @mouseenter="magnify('library')" src="@/assets/icons/L.png" width="180" height="180" alt="">
            <p>图书库</p>
        </div>
    </div>

    <div class="notice">
        <div></div>
    </div>
</div>
</template>

<script>
import axios from 'axios'
export default {
    name: "userIndex",
    data() {
        return {
            loginId:JSON.parse(localStorage.getItem('userInfo')).loginId,
            userName:JSON.parse(localStorage.getItem('userInfo')).userName
        }
    },
    created(){
        // this.getNoticeList();
    },
    methods: {
        jump2editUserInfo(){
            this.$router.push({
                name:"editInfo"
            })
        },
        getNoticeList(){
            axios({
                url:this.GLOBAL.basePath+'/showGonggao',
                method:'get'
            }).then(data=>{
                console.log(data.data)
            }).catch(err=>{
                console.log("获取公告失败:"+err);
            })
        },
        jump2MyInfo(event) {
            let element = event.currentTarget;
            let value = element.querySelector("p").innerHTML;
            this.$router.push({
                name: "myInfo",
                params: {
                    title: value
                }
            })
        },
        jump2BookShell(event){
            let element = event.currentTarget;
            let value = element.querySelector("p").innerHTML;
            this.$router.push({
                name: "myBookShell",
                params: {
                    title: value
                }
            })
        },
        jump2Library(event){
            let element = event.currentTarget;
            let value = element.querySelector("p").innerHTML;
            this.$router.push({
                name: "bookList",
                params: {
                    title: value
                }
            })
        },
        magnify(imgId) {
            let img = document.getElementById(imgId);
            img.width = 200;
            img.height = 200;
            let color = '#fd757d';
            // let fontFamily = 'serif';
            let p = img.nextElementSibling;
            p.style.color = color;
            // p.style.fontFamily = fontFamily;
        },
        toNormal(imgId) {
            let img = document.getElementById(imgId);
            img.width = 180;
            img.height = 180;
            let color = 'black';
            // let fontFamily = 'emoji';
            let p = img.nextElementSibling;
            p.style.color = color;
            // p.style.fontFamily = fontFamily;
        },
        exit() {
            var that = this;
            const h = this.$createElement;
            this.$msgbox({
                title: '温馨提示',
                message: h('p', null, [
                    h('span', null, '确认退出 '),
                    h('strong', {
                        style: 'color: red'
                    }, '登陆 '),
                    h('span', null, '?')
                ]),
                showCancelButton: true,
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                beforeClose: (action, instance, done) => {
                    if (action === 'confirm') {
                        instance.confirmButtonLoading = true;
                        instance.confirmButtonText = '执行中...';
                        //执行退出登陆操作
                        localStorage.removeItem('admin');
                        setTimeout(() => {
                            done();
                            //跳转页面
                            that.$router.push({
                              name:"Home"
                            })
                            setTimeout(() => {
                                instance.confirmButtonLoading = false;
                            }, 1500);
                        }, 1500);
                    } else {
                        done();
                    }
                }
            }).then(action => {
                console.log(action);
                this.$message({
                    type: 'info',
                    message:  '退出成功 '
                });
            });
        },
    }
}
</script>

<style scoped>
.container {
    padding: 0;
    margin: 0;
    width: 100%;
    min-height: 640px;
    background: whitesmoke;
    /*background: rgb(218, 230, 255);*/
}

.header-wrap a:hover {
    color: #95d2f6;
}

.header-wrap a {
    text-decoration: none;
    color: #67757f;
    font-size: 22px;
    cursor: pointer;
    font-family: cursive;
}

.header-wrap {
    display: flex;
    justify-content: flex-start;
    height: 60px;
    width: 100%;
    border-bottom: 1px solid white;
}

.header-wrap p {
    width: 250px;
    font-size: 22px;
    margin-left: 20px;
    margin-top: 0;
    margin-bottom: 0;
    font-family: cursive;
}

.header-logo {
    margin-left: 50px;
    width: 60px;
    height: 60px;
}

.header-exit {
    /* width: 35px;
    height: 35px;
    margin-left: 900px;
    margin-top: 10px;
    cursor: pointer; */
    width:300px;
    margin-left: 650px;
    display: flex;
    justify-content: flex-end;
}
.header-exit img{
    margin-top: 10px;
    cursor: pointer;
}
.content {
    width: 100%;
    height: 300px;
    display: flex;
    justify-content: center;
}

.content>div {
    width: 30%;
    height: 280px;
    margin-right: 30px;
    cursor: pointer;
}

.content img {
    margin-top: 40px;
}

.content p {
    font-size: 26px;
    font-family: emoji;
    font-weight: 600;
}

.notice {
    width: 90%;
    height: 260px;
    border: 1px solid white;
    border-radius: 15px;
    margin: 0 auto;
    margin-top: 10px;
    padding: 0;
}

.myinfo{
  min-width:300px;
  height: 35px;
  border-radius: 10px;
  background: white;
  margin-top: 10px;
  display: flex;
  justify-content: flex-end;
  line-height: 35px;
  vertical-align: middle;
  margin-right:20px;
}
.myinfo > p{
  padding:0;
  margin:0;
  font-size: 18px;
  font-weight: 600;
}
.myinfo > img{
  padding:0;
  margin:0;
   cursor: pointer;
}
</style>
