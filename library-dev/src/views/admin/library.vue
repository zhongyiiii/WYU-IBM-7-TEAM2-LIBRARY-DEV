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
                        <el-button @click="addBook" type="success">添加</el-button>
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
                <div class="list-detail" height="450px">
                    <el-table :data="bookList" border style="width: 100%" height="380px"  :header-cell-class-name="headClass" :row-class-name="rowClass">
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
                        <el-table-column show-overflow-tooltip align="center" prop="inboundTime" label="上架时间" width="150">
                        </el-table-column>
                        <!-- 剩余书本的数量 -->
                        <el-table-column show-overflow-tooltip align="center" fixed="right" label="剩余(本)" width="80" prop="remainCount" style="text-align:center;">
                            
                        </el-table-column>

                        <el-table-column align="center" fixed="right" label="操作" width="150">
                            <template slot-scope="scope">
                                <!-- <el-tag
                                type="mini"
                                style="margin-right:30px;"
                                    disable-transitions>{{scope.row.remainCount}}</el-tag> -->
                                <el-button  @click="openBookDetail(scope.row)" type="primary" size="small">编辑</el-button>
                                <!-- <el-button disabled style="margin:0;" v-show="scope.row.remainCount > 0 ? false:true"  type="success" size="small">可借</el-button> -->
                            </template>
                        </el-table-column>
                    </el-table>
                </div>

                <div class="page-bar">
                    <pagination  @changeCurrentPage="changeCurrentPage" @changePageSize="changePageSize" @getCST="getCST" :bookPageBean="bookPageBean"></pagination>
                </div>
            </div>
        </div>
        <!-- 弹窗 -->
        <div v-if="addBookWindow"  class="book-detail-head">
                <div class="close-detail" @click="addBookWindow=false">
                    <img src="../../assets/icons/close-icon.png" alt="">
                </div>
                <div class="detail-top">
                    <div class="detail-top-img">
                        <img src="../../assets/logo.png" alt='' width="105" height="120">
                    </div>
                    
                    <div class="input-content"> 
                        <div id="checkBookTips" class="checkTips"></div>                 
                        <div class="input-box" >
                            <div>
                                <el-input @blur="checkBook" size="mini" style="width:200px;margin-right:20px;"
                                    placeholder="请输入书名"
                                    v-model="enterBookName"
                                    clearable>
                                </el-input>
                            </div>
                            <div>
                                <el-input @blur="checkBook" size="mini" style="width:200px;margin-right:20px;"
                                    placeholder="请输入作者"
                                    v-model="enterAuthor"
                                    clearable>
                                </el-input>
                            </div>
                            <div>
                            <el-input size="mini" style="width:100px;"
                                    placeholder="上架数量"
                                    v-model="enterInBoundCount"
                                    clearable>
                            </el-input>
                        </div>
                        </div>
                        <div class="select-box">
                            <el-input v-show="typeInputBox" size="mini" style="width:200px;"
                                    placeholder="请输入类型"
                                    v-model="enterBookType"
                                    clearable>
                            </el-input>
                            <el-select  size="mini" v-if="showBookType" v-model="enterBookType" clearable placeholder="请选择类型">
                                <el-option
                                v-for="(a, index) in bookTypeList"
                                :key="index"
                                :value="a">
                                </el-option>
                            </el-select>
                            <el-button v-show="typeInputBtn" @click="change2SelectBookType" type="info" size="mini" plain>选择类型</el-button>
                            <el-button v-show="typeInputBtn2" @click="change2EnterBookType" type="info" size="mini" plain>输入类型</el-button>
                        </div>
                        <div class="select-box">
                            <el-input v-show="themeInputBox" size="mini" style="width:200px;"
                                    placeholder="请输入主题"
                                    v-model="enterBookTheme"
                                    clearable>
                            </el-input>
                            <el-select  size="mini" v-if="showBookTheme" v-model="enterBookTheme" clearable placeholder="请选择主题">
                                <el-option
                                v-for="(a, index) in bookThemeList"
                                :key="index"
                                :value="a">
                                </el-option>
                            </el-select>
                            <el-button v-show="themeInputBtn" @click="change2SelectBookTheme" type="info" size="mini" plain>选择主题</el-button>
                            <el-button v-show="themeInputBtn2" @click="change2EnterBookTheme" type="info" size="mini" plain>输入主题</el-button>
                        </div>
                        
                    </div>
                </div>
                <div class="detail-middle">
                    <textarea maxlength="250" v-model="enterDescription">
                    
                    </textarea>
                </div>
                <div class="detail-bottom">
                    <el-button @click="addBookWindow=false"  size="small" type="info">关闭</el-button>
                    <el-button id="saveBtn" @click="saveBook" style="margin-right:20px;" size="small" type="primary">保存</el-button>
                </div>
        </div>

         <!-- 编辑弹窗 -->
        <div v-if="bookDetail"  class="book-detail-head">
                <div class="close-detail" @click="bookDetail=false">
                    <img src="../../assets/icons/close-icon.png" alt="">
                </div>
                <div class="detail-top">
                    <div class="detail-top-img">
                        <img src="../../assets/logo.png" alt='' width="105" height="120">
                    </div>
                    <div class="input-content">
                        <div class="input-box" >
                            <div>
                                <el-input size="mini" style="width:200px;margin-right:20px;"
                                    placeholder="请输入书名"
                                    v-model="editBookName"
                                    clearable>
                                </el-input>
                            </div>
                            <div>
                                <el-input size="mini" style="width:200px;margin-right:20px;"
                                    placeholder="请输入作者"
                                    v-model="editAuthor"
                                    clearable>
                                </el-input>
                            </div>
                            <div>
                            
                        </div>
                        </div>
                        <div class="select-box">
                            <el-input v-show="typeInputBox" size="mini" style="width:200px;"
                                    placeholder="请输入类型"
                                    v-model="editBookType"
                                    clearable>
                            </el-input>
                            <el-select  size="mini" v-if="showBookType" v-model="editBookType" clearable placeholder="请选择类型">
                                <el-option
                                v-for="(a, index) in bookTypeList"
                                :key="index"
                                :value="a">
                                </el-option>
                            </el-select>
                            <el-button v-show="typeInputBtn" @click="change2SelectBookType" type="info" size="mini" plain>选择类型</el-button>
                            <el-button v-show="typeInputBtn2" @click="change2EnterBookType" type="info" size="mini" plain>输入类型</el-button>
                        </div>
                        <div class="select-box">
                            <el-input v-show="themeInputBox" size="mini" style="width:200px;"
                                    placeholder="请输入主题"
                                    v-model="editBookTheme"
                                    clearable>
                            </el-input>
                            <el-select  size="mini" v-if="showBookTheme" v-model="editBookTheme" clearable placeholder="请选择主题">
                                <el-option
                                v-for="(a, index) in bookThemeList"
                                :key="index"
                                :value="a">
                                </el-option>
                            </el-select>
                            <el-button v-show="themeInputBtn" @click="change2SelectBookTheme" type="info" size="mini" plain>选择类型</el-button>
                            <el-button v-show="themeInputBtn2" @click="change2EnterBookTheme" type="info" size="mini" plain>输入类型</el-button>
                            <div style="font-size:13px;margin-top:5px;margin-left:5px;">上架数量:</div>
                            <el-input size="mini" style="width:100px;"
                                    placeholder="上架数量"
                                    v-model="editInBoundCount"
                                    clearable>
                            </el-input>
                            <div style="font-size:13px;margin-top:5px;margin-left:5px;">已借阅:</div>
                            <el-input size="mini" style="width:100px;"
                                    placeholder="已借阅"
                                    v-model="borrowedCount"
                                    clearable
                                    readonly="readonly"
                                    >
                            </el-input>
                        </div>
                        
                    </div>
                </div>
                <div class="detail-middle">
                    <textarea maxlength="250" v-model="editDescription">
                    
                    </textarea>
                </div>
                <div class="detail-bottom">
                    <el-button @click="bookDetail=false"  size="small" type="info">关闭</el-button>
                    <el-button @click="modifyBook"  style="margin-right:20px;" size="small" type="primary">修改</el-button>
                </div>
        </div>
    </div>
</template>

<script>
import adminHeader from '@/views/admin/adminHeader'
import Pagination from '../../components/pagination.vue'
import axios from 'axios'
export default {
    name:"library",
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

            typeInputBtn2:false,
            typeInputBox:true,
            typeInputBtn:true,
            showBookType:false,
            
            themeInputBtn2:false,
            themeInputBox:true,
            themeInputBtn:true,
            showBookTheme:false,

            enterBookType:"",
            enterBookTheme:"",
            enterBookName:"",
            enterAuthor:"",
            enterDescription:"",
            enterInBoundCount:0,

            editBookType:"",
            editBookTheme:"",
            editBookName:"",
            editAuthor:"",
            editDescription:"",
            editInBoundCount:0,
            borrowedCount:0,

            addBookWindow:false,
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
            bookBorrowedList:[]
        }
    },
    methods:{
        checkBook(){
            let bookName = this.enterBookName;
            let author = this.enterAuthor;
            let flag = !(bookName == null || bookName == '' || author == null || author == '')
            if(flag){
                let book = {
                    bookName:bookName,
                    author:author
                }
                axios({
                    url:this.GLOBAL.basePath+'/checkBook',
                    method:'post',
                    contentType:'application/json',
                    data:book
                }).then(data=>{
                    let res = data.data;
                    if(res > 0){
                        let e = document.getElementById('checkBookTips');
                        e.innerHTML = '该书本和作者已存在';
                        let btn = document.getElementById('saveBtn');
                        btn.setAttribute('disabled','true');
                    }
                }).catch(err=>{
                    console.log('判断是否有这本书失败：'+err);
                })
            }else{
                let e = document.getElementById('checkBookTips');
                e.innerHTML = '';
                let btn = document.getElementById('saveBtn');
                btn.removeAttribute('disabled');
            }
        },
        saveBook(){
            let book = {
                bookName:this.enterBookName,
                bookType:this.enterBookType,
                bookTheme:this.enterBookTheme,
                author:this.enterAuthor,
                description:this.enterDescription,
                inboundCount:this.enterInBoundCount
            }
            let flag = (book.bookName == null || book.bookName == '' || book.bookType == null || book.bookType == '' ||
                        book.bookTheme == null || book.bookTheme == '' || book.author == null || book.author == '' ||
                        book.inboundCount == null || book.inboundCount == '');
            if(flag){
                this.alertWarning(this.GLOBAL.systemName,'请完成必要的输入框',3000);
                return false;
            }
            this.confirm(this.GLOBAL.systemName,'确认添加？ ?').then(res=>{
                if(res){
                    axios({
                    url:this.GLOBAL.basePath+'/addBook',
                    method:'post',
                    contentType:'application/json',
                    data:book
                }).then(data=>{
                    let res = data.data;
                    if(res == 1){
                        this.alertSuccess(this.GLOBAL.systemName,'添加成功',3000);
                        this.addBookWindow = false;
                        this.getCST();

                    }else{
                        console.log("添加书本错误");
                        // alert("服务器繁忙！");
                        this.alertInfo(this.GLOBAL.systemName,'服务器繁忙，请稍后重试',1500);
                    }
                    }).catch(err=>{
                        console.log("添加书本失败:"+err);
                    })
                }
            })
        },
        modifyBook(){
            this.confirm(this.GLOBAL.systemName,'确认修改？ ?').then(res=>{
                if(res){
                        let selectedModifyBook = {
                        bookId:this.selectedBookId,
                        description:this.editDescription,
                        author:this.editAuthor,
                        bookName:this.editBookName,
                        bookType:this.editBookType,
                        bookTheme:this.editBookTheme,
                        inboundCount:this.editInBoundCount
                    }
                    let flag = (selectedModifyBook.bookName == null || selectedModifyBook.bookName == '' || selectedModifyBook.bookType == null || selectedModifyBook.bookType == '' ||
                            selectedModifyBook.bookTheme == null || selectedModifyBook.bookTheme == '' || selectedModifyBook.author == null || selectedModifyBook.author == '' ||
                            selectedModifyBook.inboundCount == null || selectedModifyBook.inboundCount == '');
                    if(flag){
                        this.alertWarning(this.GLOBAL.systemName,'请完成必要的输入框',1500);
                        return false;
                    }
                    axios({
                        url:this.GLOBAL.basePath+'/updateBookById',
                        method:'post',
                        contentType:'application/json',
                        data:selectedModifyBook
                    }).then(data=>{
                        let res = data.data;
                        console.log(res)
                        if(res == 1){
                
                            this.alertSuccess(this.GLOBAL.systemName,'修改成功',1500);
                            this.getCST();
                            this.bookDetail = false;
                        }else if(res == 2){
                            this.alertWarning(this.GLOBAL.systemName,'上架数量不能小于借阅的数量',1500);
                        }else{
                            console.log("更新书本信息错误");
                            this.alertInfo(this.GLOBAL.systemName,'服务器繁忙！',1500);
                        }
                    }).catch(err=>{
                        console.log("更新书本信息失败:"+err);
                    })
                }
            })
            
        },
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
        change2SelectBookType(){
            this.typeInputBox = false;
            this.typeInputBtn = false;
            this.showBookType = true;
            this.typeInputBtn2 = true;
        },
        change2EnterBookType(){
            this.showBookType = false;
            this.typeInputBox = true;
            this.typeInputBtn = true;
            this.typeInputBtn2 = false;
        },
        change2SelectBookTheme(){
            this.themeInputBox = false;
            this.themeInputBtn = false;
            this.showBookTheme = true;
            this.themeInputBtn2 = true;
        },
        change2EnterBookTheme(){
            this.showBookTheme = false;
            this.themeInputBox = true;
            this.themeInputBtn = true;
            this.themeInputBtn2 = false;
        },
        getValue(){
            this.getBooksCondition.book.bookType = this.bookType;
            this.getBooksCondition.book.author = this.author;
            this.getBooksCondition.book.bookTheme = this.bookTheme;   

            this.bookPageBean.currentPage = 1;
            // this.bookPageBean.pageSize = 5;
            this.getCST()
        },
        handleClick(row){
            console.log(row.bookId);
            this.bookDetail = true;
        },
        openBookDetail(row){
            this.bookDetail = true;
            this.editInBoundCount = row.inboundCount;
            this.borrowedCount = row.borrowCount;
            this.editDescription = row.description;
            this.editAuthor = row.author;
            this.editBookName = row.bookName;
            this.editBookType = row.bookType;
            this.editBookTheme = row.bookTheme;
            this.selectedBookId = row.bookId;
        },
        addBook(){
            this.addBookWindow = true;
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
    display: flex;
    justify-content: flex-start;
}
.detail-top-img{
    width: 105px;
    height: 120px;
    margin-top: 5px;
    margin-left: 20px;
    float: left;
}
.detail-top >div{
    float: left;
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


/* 悬浮窗 */

.input-content{
    width: 100%;
    height: 120px;
    padding-left: 40px;
}
.input-box{
    display: flex;
    justify-content: flex-start;
    margin-bottom: 10px;
}
.select-box{
    display: flex;
    align-content: flex-start;
    margin-bottom: 10px;
}
.detail-top input{
    min-width: 100px;
    height: 25px;
    border:1px solid whitesmoke;
    text-indent: 10%;
    margin-right: 10px;
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

.detail-middle > textarea{
    text-indent: 5%;
    line-height: 30px;
    font-size: 18px;
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
.checkTips{
    margin-top:-10px;color:red;
    font-size: 10px;
    padding-bottom: 5px;
    margin-left: -500px;
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