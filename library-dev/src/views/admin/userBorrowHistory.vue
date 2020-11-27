<template>
<div class="user-borrow-history">
    <div class="history-item-detail" v-if="showDetail">
        <div class="close-detail" @click="showDetail=false">
            <img src="../../assets/icons/close-icon.png" alt="">
        </div>
        <div class="detail-item">
            <p>用户编号:</p>
            <div>{{detailHistory.loginId}}</div>

            <p>用户姓名:</p>
            <div>{{detailHistory.userName}}</div>

            <p>归还时间剩余:</p>
            <div>{{detailHistory.remainDay==0? '已归还':detailHistory.remainDay}}</div>

            <p>书本名称:</p>
            <div>{{detailHistory.bookName}}</div>

            <p>借阅时间:</p>
            <div>{{detailHistory.borrowTime}}</div>

            <p>借阅有效期:</p>
            <div>{{detailHistory.returnTime}}</div>

        </div>

    </div>
    <div class="history-list">
        <div>
            <div class="search-borrow-history">
                <el-input placeholder="请输入内容" v-model="primaryKey" clearable>
                </el-input>
                <el-button style="margin-left:20px;"  @click="getValue" type="primary">搜索</el-button>
            </div>
            <div class="borrow-history-list">
                <el-table :data="usereBorrowList" border style="width: 100%" height="450px" :header-cell-class-name="headClass" :row-class-name="rowClass"> 

                    <el-table-column show-overflow-tooltip align="center" prop="bookName" label="书名" width="200" style="text-align: center;">
                    </el-table-column>
                    <el-table-column show-overflow-tooltip align="center" prop="author" label="作者" width="150">
                    </el-table-column>
                    <el-table-column show-overflow-tooltip align="center" prop="bookType" label="分类" width="120">
                    </el-table-column>
                    <el-table-column show-overflow-tooltip align="center" prop="bookTheme" label="主题" width="120">
                    </el-table-column>
                    <el-table-column show-overflow-tooltip  prop="description" label="简介" width="500">
                    </el-table-column>
                    <el-table-column show-overflow-tooltip align="center" prop="inboundTime" label="上架日期" width="150">
                    </el-table-column>
                    <el-table-column  align="center" fixed="right" label="操作" width="100">
                        <template slot-scope="scope">
                            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
    </div>
    <div class="page-bar">
        <pagination @changeCurrentPage="changeCurrentPage" @changePageSize="changePageSize" @getCST="getCST" :bookPageBean="bookPageBean"></pagination>
    </div>
</div>
</template>

<script>

import Pagination from '../../components/pagination.vue'
import axios from 'axios'
export default {
    name: "user-borrow-history",
    components: {
        Pagination
    },
    created(){
        this.bookPageBean = {
            currentPage:1,
            pageSize:10,
            totalCount:20
        }
        this.getBooksCondition = {
            userId: this.$route.query.userId,
            currentPage:1,
            pageSize:5,
            totalCount:100,
            searchPrimaryKey:'',
            
        }
        this.getCST()
    },
    data() {
        return {
            showDetail: false,
            primaryKey: '',
            title: this.$route.params.titleList,
            usereBorrowList: [],
            userId:this.$route.query.userId,
            bookPageBean:{},
            getBooksCondition:{},
            detailHistory:{}
        }
    },
    methods: {
        getValue(){
             
            this.getBooksCondition.searchPrimaryKey = this.primaryKey;
            this.bookPageBean.currentPage = 1;
            // this.bookPageBean.pageSize = 5;
            this.getCST()

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
                url:this.GLOBAL.basePath+'/findHistories',
                method:'post',
                contentType:'application/json',
                data:this.getBooksCondition
            }).then(data=>{
                this.usereBorrowList = data.data.histories;
                this.bookPageBean.currentPage = data.data.currentPage;
                this.bookPageBean.pageSize = data.data.pageSize;
                this.bookPageBean.totalCount = data.data.totalCount;
            }).catch(err=>{
                console.log("获取失败:"+err);
            })
        },
        handleClick(row) {
            let userInfo = JSON.parse(localStorage.getItem('userInfo'));
            this.detailHistory = row;
            this.detailHistory.loginId = userInfo.loginId;
            this.detailHistory.userName = userInfo.userName;
            this.showDetail = true;
            // console.log(this.primaryKey);
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
    mounted: function () {
       
    }
}
</script>

<style scoped>
.history-list {
    width: 80%;
    margin: 0 auto;
    margin-top: 20px;
}

.search-borrow-history {
    display: flex;
    justify-content: flex-end;
}

.borrow-history-list {
    margin-top: 20px;
    text-align: center;
}

.history-item-detail {

    width: 80%;
    min-height: 110px;
    padding-top: 15px;
    margin: 0 auto;
    background: #f5f5f5;
    margin-bottom: 10px;
    border-radius: 10px;
}

.detail-item {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
}

.detail-item>p {
    margin: 0;
    padding: 0;

    height: 30px;
    min-width: 80px;
    line-height: 30px;
    vertical-align: middle;

    margin-bottom: 20px;

}

.detail-item>div {
    margin-left: 10px;
    min-width: 220px;
    height: 30px;
    border: 1px solid #DCDFE6;
    border-radius: 5px;
    line-height: 30px;
    vertical-align: middle;
    margin-right: 30px;
}

.close-detail {
    float: right;
    width: 20px;
    height: 20px;
    margin-top: -10px;
    margin-right: 10px;
    cursor: pointer;
}

.page-bar{
    margin-top: 20px;
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
    background-color: #b9c8da;
}
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
