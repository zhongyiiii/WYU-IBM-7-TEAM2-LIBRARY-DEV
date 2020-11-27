<template>
    <div>
        <admin-header :index="index"></admin-header>
        <div class="book-list-content">
            <div class="select-header">
                <div class="select-header-item">
                    <p>作者</p>
                    <el-select v-model="author" clearable placeholder="请选择">
                        <el-option
                        v-for="(a, index) in authorList"
                        :key="index"
                        :value="a">
                        </el-option>
                    </el-select>

                    <p>类型</p>
                    <el-select v-model="bookType" clearable placeholder="请选择">
                        <el-option
                        v-for="(a, index) in bookTypeList"
                        :key="index"
                        :value="a">
                        </el-option>
                    </el-select>

                    <p>主题</p>
                    <el-select v-model="bookTheme" clearable placeholder="请选择">
                        <el-option
                        v-for="(a, index) in bookThemeList"
                        :key="index"
                        :value="a">
                        </el-option>
                    </el-select>

                    <div class="search-btn">
                        <el-button @click="getValue" type="primary">查询</el-button>
                    </div>
                </div>
            </div>
            <div class="list-content">
                <div class="list-content-search">
                    <el-input
                    style="width:700px;margin-right:20px;"
                        placeholder="请输入内容"
                        v-model="input"
                        clearable>
                    </el-input>
                    <el-button style="margin-right:50px" @click="searchBooks" type="primary">搜索</el-button>
                </div>

                <div class="list-detail">
                    <el-table :data="bookList" border style="width: 100%" height="380px" :header-cell-class-name="headClass" :row-class-name="rowClass" >
                        <el-table-column show-overflow-tooltip align="center" prop="bookName" label="书名" width="200" style="text-align: center;">
                        </el-table-column>
                        <el-table-column show-overflow-tooltip align="center" prop="author" label="作者" width="150">
                        </el-table-column>
                        <el-table-column show-overflow-tooltip align="center" prop="bookType" label="分类" width="120">
                        </el-table-column>
                        <el-table-column show-overflow-tooltip align="center" prop="bookTheme" label="主题" width="120">
                        </el-table-column>
                        <el-table-column show-overflow-tooltip prop="description" label="简介" width="500">
                        </el-table-column>
                        <el-table-column show-overflow-tooltip align="center" prop="inboundTime" label="上架时间" width="150">
                        </el-table-column>
                        <!-- 剩余书本的数量 -->
                        <el-table-column show-overflow-tooltip align="center" fixed="right" label="剩余(本)" width="80" prop="remainCount" style="text-align:center;">
                        </el-table-column>
                        <el-table-column  align="center" fixed="right" label="操作" width="150">
                            <template slot-scope="scope">
                                <el-button style="background-color:#8181ff;border:0;"  @click="openBookDetail(scope.row)" type="success" size="small">详情</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>

                <div class="page-bar">
                    <pagination   @changeCurrentPage="changeCurrentPage" @changePageSize="changePageSize" @getCST="getCST" :bookPageBean="bookPageBean"></pagination>
                </div>
            </div>
        </div>

        <div v-if="bookDetail"  class="book-detail-head">
            <div class="close-detail" @click="bookDetail=false">
                <img src="../../assets/icons/close-icon.png" alt="">
            </div>
            <div class="detail-top">
                <div class="detail-top-img">
                    <img src="../../assets/logo.png" alt='' width="105" height="120">
                </div>
                <div>
                    <div class="bookName">《{{selectedBook.bookName}}》</div>
                    <div class="author">{{selectedBook.author}}</div>
                </div>
                <div>
                     <div class="bookType">剩余:{{selectedBook.remainCount}}</div>
                </div>
                <div>
                     <div class="bookType">借出数:{{selectedBook.inboundCount-selectedBook.remainCount}}</div>
                </div>
            </div>
            <div class="detail-middle">
                <el-table
                    :header-cell-style="headClass"
                    :data="bookBorrowedList"
                    height="195"
                    border
                    :header-cell-class-name="headClass"
                    style="width: 99%">
                    <el-table-column
                    align="center"
                    prop="userName"
                    label="借阅人"
                    width="250">
                    </el-table-column>
                    <el-table-column
                    align="center"
                    prop="borrowTime"
                    label="借阅日期"
                    width="250">
                    </el-table-column>
                    <el-table-column
                    align="center"
                    prop="remainDay"
                    label="剩余时间（天）">
                    </el-table-column>
                </el-table>
            </div>
            <div class="detail-bottom">
                 <el-button style="margin-right:30px;" @click="bookDetail=false"  size="small" type="info">关闭</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import adminHeader from '@/views/admin/adminHeader'
import Pagination from '../../components/pagination.vue'
import axios from 'axios'
export default {
    name:"book-borrow",
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
            book:{
                bookType:this.bookType,
                bookTheme:this.bookTheme,
                author:this.author
            }
        }
        this.getCST()
    },
    mounted:function(){               
        //得到下拉列表
        axios({
            url:this.GLOBAL.basePath+"/getBooksLists",
            methods:"get"
        }).then(data=>{
            this.authorList = data.data.authors;
            this.bookTypeList = data.data.bookType;
            this.bookThemeList = data.data.bookThemes
        }).catch(err=>{
            console.log("error..."+err);
        })
        
    },
    data(){
        return{

            bookPageBean:{},
            getBooksCondition:{},


            index:this.$route.params.index,
            bookDetail:false,
            titleList:this.$route.params.title,
            author: "",
            bookType:"",
            bookTheme:"",
            authorList:[],
            bookTypeList:[],
            bookThemeList:[],
            input:"",
            bookList:[],
            bookBorrowedList:[],

            selectedBook:{}
        }
    },
    methods:{
        searchBooks(){
            this.getBooksCondition.bookPrimaryKey = '';
            this.getBooksCondition.bookPrimaryKey = this.input;
            axios({
                url:this.GLOBAL.basePath+'/searchBooks',
                method:'post',
                contentType:'application/json',
                data:this.getBooksCondition
            }).then(data=>{
                this.bookList = data.data.books;
                this.bookPageBean.currentPage = data.data.currentPage;
                this.bookPageBean.pageSize = data.data.pageSize;
                this.bookPageBean.totalCount = data.data.totalCount;
            }).catch(err=>{
                console.log("获取失败:"+err);
            })
        },
        changeCurrentPage(currentPage, pageSize){
            console.log("当前页："+currentPage);
            console.log("当前页显示："+pageSize);
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
            // console.log(this.getBooksCondition)
            axios({
                url:this.GLOBAL.basePath+'/getBooksLike',
                method:'post',
                contentType:'application/json',
                data:this.getBooksCondition
            }).then(data=>{
                this.bookList = data.data.books;
                this.bookPageBean.currentPage = data.data.currentPage;
                this.bookPageBean.pageSize = data.data.pageSize;
                this.bookPageBean.totalCount = data.data.totalCount;
            }).catch(err=>{
                console.log("获取失败:"+err);
            })
        },
        getValue(){
            this.getBooksCondition.book.bookType = this.bookType;
            this.getBooksCondition.book.author = this.author;
            this.getBooksCondition.book.bookTheme = this.bookTheme;   

            this.bookPageBean.currentPage = 1;
            // this.bookPageBean.pageSize = 5;
            this.getCST()
            console.log("总页数:"+this.bookPageBean.totalCount)
        },

        openBookDetail(row){
            this.selectedBook = row;
 
            this.bookDetail = true;
            let bid = {
                bId:row.bookId
            }
            axios({
                url:this.GLOBAL.basePath+"/getHistoryByBId",
                method:"post",
                contentType:'application/json',
                data:bid
            }).then(data=>{
                let list = data.data;
                let len = list.length;
                for (let i = 0; i < len; i++) {
                    if(list[i].remainDay == 0){
                        list[i].remainDay = '已归还';
                    }  
                }
                this.bookBorrowedList = list;
            }).catch(err=>{
                console.log("error..."+err);
            });
        },
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
    }
}
</script>

<style scoped>
.book-list-content{
    width: 90%;
    margin:0 auto;
    margin-top: 10px;
}

.select-header{
    width: 100%;
    height: 40px;
}
.select-header-item p{
    min-width: 100px;
    height: 40px;
    line-height: 40px;
    vertical-align: middle;
    margin:0;
    padding:0;
    font-weight: 600;
}
.select-header-item{
    display: flex;
    justify-content: flex-start;
}

.search-btn{
    margin-left: 30px;
}

.list-content-search{
    margin-top: 20px;
    display: flex;
    justify-content: flex-end;
}

.list-detail{
    margin-top: 10px;
}

.page-bar{
    margin-top: 30px;
}
.book-detail-head{
    width:60%;
    min-height: 400px;
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

.bookName{
    border: 1px solid #DCDFE6;
    min-width: 250px;
    height: 30px;
    line-height: 30px;
    vertical-align: middle;
    border-radius: 5px;
    margin-left: 30px;
    margin-top: 20px;
}

.author{
    border: 1px solid #DCDFE6;
    min-width: 250px;
    height: 30px;
    line-height: 30px;
    vertical-align: middle;
    border-radius: 5px;
    margin-left: 30px;
    margin-top: 20px;
}
.bookType{
    border: 1px solid #DCDFE6;
    min-width: 100px;
    height: 30px;
    line-height: 30px;
    vertical-align: middle;
    border-radius: 5px;
    margin-left: 30px;
    margin-top: 20px;
    text-indent:10%;
    text-align: left;
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
}
.detail-bottom{
    width: 100%;
    height: 30px;
    margin-top:10px;
    display: flex;
    justify-content: flex-end;
} 
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