<template>
    <div class="user-header">
        <div class="user-header-nav">
          <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/user/index' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>我是借阅者</el-breadcrumb-item>
          </el-breadcrumb>
          <el-breadcrumb v-for="(t, index) in titleList" :key="index" separator-class="el-icon-arrow-right">
            <el-breadcrumb-item></el-breadcrumb-item>
              <el-breadcrumb-item>{{t}}</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div  class="header-exit">
            <div class="myinfo">
                <img  @click="jump2editUserInfo" src="@/assets/icons/user-icon.png" height="35" width="35" alt="">
                <p>{{loginId}}</p>
                <p>{{userName}}</p>
            </div>
            <img @click="exit" src="@/assets/icons/exit-icon.png" height="35" width="35" alt="">
        </div>
    </div>
</template>

<script>
export default {
    name:"user-header",
    props:["title"],
    data(){
      return{
        titleList:[],
        loginId:JSON.parse(localStorage.getItem('userInfo')).loginId,
        userName:JSON.parse(localStorage.getItem('userInfo')).userName
      }
    },
    created(){
      this.addTitle();
    },
    methods:{
      jump2editUserInfo(){
            this.$router.push({
                name:"editInfo"
            })
        },
      addTitle(){
        if(this.title instanceof Array){
          let len = this.title.length
          for (let i = 0;  i < len; i++) {
            this.titleList.push(this.title[i])
          }
        }else{
          this.titleList.push(this.title);
        }
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
                        localStorage.removeItem('userInfo');
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
.user-header{
    width: 100%;
    height: 60px;
}
.user-header img{
    float: right;
    margin-right: 50px;
    margin-top: 10px;
    cursor: pointer;
}
.user-header-nav{
  margin-left: 20px;
}
.user-header-nav *{
    margin-top: 10px;
    float: left;
    font-size: 18px;
}
.header-exit{
  display: flex;
  justify-content: flex-end;
}
.myinfo{
  min-width:300px;
  height: 35px;
  border-radius: 10px;
  background: #f5f5f5;
  margin-top: 10px;
  display: flex;
  justify-content: space-evenly;
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