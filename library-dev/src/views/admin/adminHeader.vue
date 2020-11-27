<template>
  <div class="admin-header">
    <div class="header-wrap">
        <div class="header-logo">
            <img @click="jump3AdminIndex" src="@/assets/logo.png" height="60" width="60">
        </div>
        <p>耗子尾汁<br>图书馆管理系统</p>
    </div>
    <div @click="menuItemClick(0,$event)" @mouseleave="menuItemLeave($event)"  @mouseenter="menuItemHover($event)" class="admin-header-menu">
      <p>用户列表</p>
    </div>
    <div @click="menuItemClick(1,$event)" @mouseleave="menuItemLeave($event)"  @mouseenter="menuItemHover($event)" class="admin-header-menu">
      <p>图书借阅与归还</p>
    </div>
    <!-- <div @click="menuItemClick(2,$event)" @mouseleave="menuItemLeave($event)"  @mouseenter="menuItemHover($event)" class="admin-header-menu">
      <p>注销用户</p>
    </div> -->
    <div @click="menuItemClick(2,$event)" @mouseleave="menuItemLeave($event)"  @mouseenter="menuItemHover($event)" class="admin-header-menu">
      <p>书库</p>
    </div>
    <div @click="menuItemClick(3,$event)" @mouseleave="menuItemLeave($event)"  @mouseenter="menuItemHover($event)" class="admin-header-menu">
      <p>首页公告管理</p>
    </div>
    <div @click="menuItemClick(4,$event)" @mouseleave="menuItemLeave($event)"  @mouseenter="menuItemHover($event)" class="admin-header-menu">
      <p>图书借阅统计</p>
    </div>
    <div class="header-exit">
        <img @click="exit"  src="@/assets/icons/exit-icon.png" height="35" width="35" alt="">
    </div>
  </div>
</template>

<script>
export default {
  name:"admin-header",
  props:["index"],
  components:{

  },
  mounted() {
    console.log(this.index);
    if(this.index != null){
      let menuItemList = document.getElementsByClassName("admin-header-menu");
      menuItemList[this.index].style.borderBottom = "4px solid pink";
    }
  },
  methods:{
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
            }).then(() => {
                this.$message({
                    type: 'info',
                    message: '退出成功 '
                });
            });
        },
        menuItemHover(event){
          let element = event.currentTarget;
          if(element.style.borderBottom == "0px" || element.style.borderBottom == ""){
            element.style.borderBottom = "4px solid #95d2f6";
          }
        },
        menuItemClick(index){
          // var element = event.currentTarget;
          // let menuItemList = document.getElementsByClassName("admin-header-menu");
          // let len = menuItemList.length;
          // for (let i = 0; i < len; i++) {
          //   if(i != index){
          //     menuItemList[i].style.borderBottom = 0;
          //   }
          // }
          // element.style.borderBottom = "4px solid pink";
          var menuList = ["userList","bookBorrow","library","notice","borrowSummary"]
          var name = menuList[index]
          this.$router.push({
                name: name,
                params: {
                    index: index
                }
            })
        },
        menuItemLeave(event){
          let element = event.currentTarget; 
          if(element.style.borderBottom == "4px solid pink"){
            return false;
          }
          element.style.borderBottom = 0;
        },
        jump3AdminIndex(){
          this.$router.push({
                name: "adminIndex",
            })
        }
  }
}
</script>

<style scoped>
.admin-header{
  width:100%;
  height: 60px;
  background: #f5f5f5;
  display: flex;
  justify-content: flex-start;
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
    min-width: 30%;
    /*background: rgb(218, 230, 255);*/
    background: #f5f5f5;
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
    cursor: pointer;
}

.admin-header-menu{
  min-width:50px;
  height: 56px;
  line-height: 60px;
  vertical-align: middle;
  margin-right: 40px;
  text-align: center;
  cursor: pointer;
}
.admin-header-menu p{
  margin:0;
  padding:0;
  font-family: cursive;
  font-size: 18px;
  font-weight: 550;
}
.header-exit{
  margin-left: 50px;
  width:70px;
  padding-top:12.5px;
  cursor: pointer;
}

</style>