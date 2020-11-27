<template>
    <div>
       <user-header :title="titleList"/>
       <div class="book-content" v-for="(b, index) in borrowedList" :key="index">
           <div class="book-content-header">
               <el-input style="margin-right:30px;"
                    v-model="bookStatus"
                    prefix-icon="el-icon-success"
                    :disabled="true"
                    class="status-box"
                    >
                </el-input>
                <el-button @click="returnBook(b.historyId)" type="primary">还书</el-button>
           </div>
           <div class="detail-item">
                <p>用户编号:</p>
                <div>{{loginId}}</div>

                <p>用户姓名:</p>
                <div>{{userName}}</div>

                <p>归还时间剩余:</p>
                <div>{{b.remainDay}}</div>

                <p>书本名称:</p>
                <div>{{b.bookName}}</div>

                <p>借阅时间:</p>
                <div>{{b.borrowTime}}</div>

                <p>借阅有效期:</p>
                <div>{{b.returnTime}}</div>
                
                <p>作者:</p>
                <div>{{b.author}}</div>

                <p>分类:</p>
                <div>{{b.bookType}}</div>

                <p>简介:</p>
                <div  class="desc">{{b.description}}</div>
           </div>
        </div>
       
    </div>
</template>

<script>
import userHeader from '@/views/user/userHeader'
import axios from "axios"
export default {
    name:"my-book-shell",
    components:{
        userHeader
    },
    mounted:function(){
        this.showBorrowedBookList();
    },
    data(){
        return{
            titleList:this.$route.params.title,
            bookStatus:"已借入书籍",
            borrowedList:[],
            loginId:JSON.parse(localStorage.getItem('userInfo')).loginId,
            userName:JSON.parse(localStorage.getItem('userInfo')).userName
        }
    },
    methods:{
        showBorrowedBookList(){
            let history = {
                uId:JSON.parse(localStorage.getItem('userInfo')).userId
            }
            axios({
                url:this.GLOBAL.basePath+"/getHistoryByUId",
                method:"post",
                contentType:'application/json',
                data:history
            }).then(data=>{
                this.borrowedList = data.data
                
            }).catch(err=>{
                console.log("error...:"+err)
            })
        },
        returnBook(id){
            this.confirm(this.GLOBAL.systemName,'确认还书 ?', 1000).then(res=>{
                if(res){
                    let historyId = {
                        historyId:id
                    }
                    axios({
                        url:this.GLOBAL.basePath+"/returnBook",
                        method:"post",
                        contentType:'application/json',
                        data:historyId
                    }).then(data=>{
                        let res = data.data
                        // console.log(res)
                        if(res == 1){
                            this.alertSuccess(this.GLOBAL.systemName,'还书成功',1500);
                            this.showBorrowedBookList();
                            //还书成功后更改localStorage中的剩余借阅图书数量
                            let userInfo = JSON.parse(localStorage.getItem('userInfo'));
                            userInfo.remainingBooks +=  1;
                            localStorage.setItem('userInfo',JSON.stringify(userInfo));
                        }
                        
                    }).catch(err=>{
                        console.log("error...:"+err)
                    })
                }
            })
            
            
        }
    }
}
</script>

<style scoped>
.book-content{
    width: 80%;
    margin:0 auto;
    margin-bottom: 20px;
}

.book-content-header{
    display: flex;
    justify-content: flex-start;
}
.status-box{
    font-size: 18px;
}

.detail-item {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    width: 100%;
    height: 300px;
    margin-top: 10px;
    background: #f5f5f5;
    padding:10px;
    border-radius: 10px;
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
    overflow: hidden;
}

.close-detail {
    float: right;
    width: 20px;
    height: 20px;
    margin-top: -10px;
    margin-right: 10px;
    cursor: pointer;
}
.desc{
    width:100%;
    min-height:120px;
    line-height: 30px;
    overflow-x: hidden; 
    overflow-y: auto; 
    text-align: left;
    text-indent: 5%;
}
</style>