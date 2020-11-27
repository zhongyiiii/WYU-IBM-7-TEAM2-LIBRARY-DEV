<template>
    <div>
        <admin-header :index="index"></admin-header>
        <div class="user-list-content">
            <div class="search-borrow-history">
                <el-input placeholder="请输入内容" v-model="primaryKey" clearable>
                </el-input>
                <el-button @click="searchUsers" style="margin-left:20px;" type="primary">搜索</el-button>
            </div>
            <!-- 弹出的悬浮框 -->
            <div v-if="userDetail"  class="book-detail-head">
                <div class="close-detail" @click="userDetail=false">
                    <img src="../../assets/icons/close-icon.png" alt="">
                </div>
                <div class="detail-top">
                    <div class="detail-top-img">
                        <img src="../../assets/logo.png" alt='' width="105" height="120">
                    </div>
                    <div>
                        <div class="userName">{{selectedUser.userName}}</div>
                        <div class="borrowHistory"><a @click="jump2UserBorrowHistory" target="_blank">借阅历史</a></div>
                    </div>
                    <div>
                        <div class="gender">{{selectedUser.gender}}</div>
                    </div>
                    <div>
                        <div class="gender">{{selectedUser.age}}</div>
                    </div>
                    <div>
                        <div class="birthday">{{selectedUser.birthday}}</div>
                    </div>
                     <div>
                        <div class="contact">{{selectedUser.contact}}</div>
                    </div>
                </div>
                <div class="detail-middle">
                    <textarea maxlength="250"  readonly v-model="selectedUser.description">
                        
                    </textarea>
                </div>
                <div class="detail-bottom">
                    <el-button @click="userDetail=false"  size="small" type="info">关闭</el-button>
                    <el-button @click="logout" style="margin-right:20px;" size="small" type="primary">注销</el-button>
                </div>
            </div>
            <div class="borrow-history-list">
                <el-table :data="userList" border style="width: 100%" height="450" :header-cell-class-name="headClass" :row-class-name="rowClass">

                    <el-table-column show-overflow-tooltip align="center" prop="userName" label="姓名" width="150" style="text-align: center;" >
                    </el-table-column>
                    <el-table-column show-overflow-tooltip align="center" prop="loginId" label="ID" width="150">
                    </el-table-column>
                    <el-table-column show-overflow-tooltip  align="center" prop="email" label="邮箱" width="180">
                    </el-table-column>
                    <el-table-column show-overflow-tooltip align="center" prop="age" label="年龄" width="50">
                    </el-table-column>
                    <el-table-column show-overflow-tooltip align="center" prop="birthday" label="生日" width="100">
                    </el-table-column>
                    <el-table-column show-overflow-tooltip align="center" prop="contact" label="电话" width="100">
                    </el-table-column>
                    <el-table-column show-overflow-tooltip prop="address" label="地址" width="250">
                    </el-table-column>
                    <el-table-column align="center" prop="gender" label="性别" width="50">
                    </el-table-column>
                    <el-table-column show-overflow-tooltip  prop="description" label="描述" width="450">
                    </el-table-column>
                    <el-table-column align="center" fixed="right" label="操作" width="100">
                        <template slot-scope="scope">
                            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                            <el-button @click="logoutUser(scope.row)" type="text" size="small">注销</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>

             <div class="page-bar">
                <pagination @changeCurrentPage="changeCurrentPage" @changePageSize="changePageSize" @getCST="getCST" :bookPageBean="bookPageBean" ></pagination>
            </div>
        </div>
    </div>
</template>

<script>
import adminHeader from '@/views/admin/adminHeader'
import Pagination from '../../components/pagination.vue'
import axios from 'axios'
export default {
    name:"user-list",
    components:{
        adminHeader,
        Pagination
    },
    created(){
        this.bookPageBean = {
            currentPage:1,
            pageSize:10,
            totalCount:20
        }
        this.getBooksCondition = {
            currentPage:1,
            pageSize:5,
            totalCount:100,
            userPrimaryKey:""
        }
        this.getCST()
    },
    data(){
        return{
            bookPageBean:{},
            getBooksCondition:{},
            primaryKey:"",
            index:this.$route.params.index,
            userList:[],
            userDetail:false,
            selectedUserId:"",
            selectedUser:{}
        }
    },
    methods:{
        searchUsers(){
            this.getBooksCondition.userPrimaryKey = this.primaryKey;
            this.getCST();
        },
        changeCurrentPage(currentPage, pageSize){

            this.getBooksCondition.pageSize = pageSize;
            this.getBooksCondition.currentPage = currentPage;
            this.getCST();
        },
        changePageSize(val){
            this.getBooksCondition.pageSize = val;
            this.getBooksCondition.currentPage = 1;
            this.getCST();
        },
        getCST(){
            axios({
                url:this.GLOBAL.basePath+'/findAllUsers',
                method:'post',
                contentType:'application/json',
                data:this.getBooksCondition
            }).then(data=>{
                this.userList = data.data.users;
                this.bookPageBean.currentPage = data.data.currentPage;
                this.bookPageBean.pageSize = data.data.pageSize;
                this.bookPageBean.totalCount = data.data.totalCount;
            }).catch(err=>{
                console.log("获取失败:"+err);
            })
        },
        handleClick(row) {
            this.selectedUserId = row.userId;
            this.selectedUser = row;
            this.userDetail = true;
        },
        jump2UserBorrowHistory(){
            var selectedUserId = this.selectedUserId;
            console.log("userId:"+selectedUserId);
            let routeData = this.$router.resolve({ 
                path: '/admin/userBorrowHistory',
                query:{userId:selectedUserId}
            });
            window.open(routeData.href, '_blank');
        },
        logout(){
            this.confirm(this.GLOBAL.systemName,'确认注销该用户？',1500).then(res=>{
                if(res){
                    let userId = {
                        userId:this.selectedUserId
                    }
                    axios({
                        url:this.GLOBAL.basePath+'/deleteUser',
                        method:'post',
                        contentType:'application/json',
                        data:userId
                    }).then(data=>{
                        let res = data.data;
                        if(res == 1){
                            this.alertSuccess(this.GLOBAL.systemName,'注销用户成功',5000);
                            this.getCST();
                            this.userDetail=false;
                        }else if(res == 2){
                            this.alertWarning(this.GLOBAL.systemName,'该用户还有书没有还',3000);
                        }else{
                            this.alertInfo(this.GLOBAL.systemName,'服务器繁忙！',3000);
                        }
                    }).catch(err=>{
                        console.log("获取失败:"+err);
                    })
                }
            })
        },
        logoutUser(row){
            this.confirm(this.GLOBAL.systemName,'确认注销用户 ?', 1500).then(res=>{
                if(res){
                    let userId = {
                        userId:row.userId
                    }
                    axios({
                        url:this.GLOBAL.basePath+'/deleteUser',
                        method:'post',
                        contentType:'application/json',
                        data:userId
                    }).then(data=>{
                        let res = data.data;
                        if(res == 1){
                            this.alertSuccess(this.GLOBAL.systemName,'注销用户成功',1500);
                            this.getCST();
                        }else if(res == 2){
                            this.alertWarning(this.GLOBAL.systemName,'该用户还有书没有还',1500);
                        }else{
                            this.alertInfo(this.GLOBAL.systemName,'服务器繁忙！',1500);
                        }
                    }).catch(err=>{
                        console.log("获取失败:"+err);
                    })
                }
            })
        },

         //表格样式
        headClass() { //表头居中显示
            return "header-class"
        },
        rowClass(row){
            if(row.rowIndex % 2 == 0){
                return "row-class"
            }else{
                return 'row-class1'
            }
        }
    },
}
</script>

<style scoped>
.user-list-content{
    width:90%;
    height: 500px;
    background: whitesmoke;
    margin:0 auto;
    margin-top: 10px;
} 

.search-borrow-history {
    display: flex;
    justify-content: flex-end;
}
.borrow-history-list {
    margin-top: 20px;
    text-align: center;
}

.page-bar{
    margin-top: 20px;
}

.book-detail-head{
    width:60%;
    height: 400px;
    position: absolute;
    top: 50%;
    left: 50%;
    z-index: 100;
    -webkit-transform: translate(-50%, -50%);
    -moz-transform: translate(-50%, -50%);
    -ms-transform: translate(-50%, -50%);
    -o-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
    background: #f5f5f5;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
}

.close-detail {

    width: 20px;
    height: 20px;
    margin-left: 97%;
    margin-top:5px;
    cursor: pointer;
}
.detail-top{
    width: 100%;
    height: 130px;
    float: left;
}
.detail-top-img{
    width: 105px;
    height: 120px;
    margin-top: 5px;
    margin-left: 20px;
}
.detail-top >div{
    float: left;
}

.userName{
    border: 1px solid #DCDFE6;
    min-width: 150px;
    height: 30px;
    line-height: 30px;
    vertical-align: middle;
    border-radius: 5px;
    margin-left: 30px;
    margin-top: 20px;
}

.borrowHistory{
    border: 1px solid #DCDFE6;
    min-width: 150px;
    height: 30px;
    line-height: 30px;
    vertical-align: middle;
    border-radius: 5px;
    margin-left: 30px;
    margin-top: 20px;
}
.gender{
    border: 1px solid #DCDFE6;
    min-width: 50px;
    height: 30px;
    line-height: 30px;
    vertical-align: middle;
    border-radius: 5px;
    margin-left: 30px;
    margin-top: 20px;
}
.birthday{
    border: 1px solid #DCDFE6;
    min-width: 100px;
    height: 30px;
    line-height: 30px;
    vertical-align: middle;
    border-radius: 5px;
    margin-left: 30px;
    margin-top: 20px;
}
.contact{
    border: 1px solid #DCDFE6;
    min-width: 120px;
    height: 30px;
    line-height: 30px;
    vertical-align: middle;
    border-radius: 5px;
    margin-left: 30px;
    margin-top: 20px;
}
.detail-middle{
    width:100%;
    height: 200px;
}
.detail-middle > textarea{
    width: 95%;
    height: 200px;
    border: 0;
    border-radius: 10px;
    text-indent: 5%;
    line-height: 30px;
    font-size: 18px;
}
.detail-bottom{
    width: 100%;
    height: 30px;
    margin-top:10px;
    display: flex;
    justify-content: flex-end;
}
.borrowHistory a{
    text-decoration: none;
    cursor: pointer;
}

/* 表格样式 */
.el-table >>>.row-class1{
    background-color: #dbe9e9;
}
.el-table >>>.row-class{
    background-color: #e9e1df;
}
.el-table >>>.header-class{
    color:rgba(0, 0, 0, 0.822);
    text-align: center;
    background-color: #cddab9;
}
.el-button --success{
    background-color: #7f7fc7;
}
/* <style lang="scss" scoped>，删掉scoped就好了，
但是删掉scoped有个问题:删掉以后会污染外面全局的css，
全局都会受到这个css影响，建议单独再写一个<style> */
</style>
<style lang="css"> 
    .el-tooltip__popper{ 
        max-width:50%;
        min-height: 30px;
        line-height: 30px;
        vertical-align: middle;
        font-size: 12px;
        background: white !important;/*背景色  !important优先级*/
        opacity: 0.9 !important;/*背景色透明度*/
        color: black !important;
    } 
</style>