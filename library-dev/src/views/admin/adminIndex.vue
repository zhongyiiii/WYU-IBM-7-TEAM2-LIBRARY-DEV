<template>
    <div class="admin-index">
        <admin-header/>
        <div class="admin-index-content">
            <div @click="jump2UserList(0)" class="content-item">
                <img src="@/assets/superUserImage/userListIcon.png" alt="">
                <p><strong>用户列表</strong></p>
            </div>

            <div @click="jump2BookBorrow(1)" class="content-item">
                <img src="@/assets/superUserImage/bookBorrow.png" alt="">
                <p><strong>图书借阅与归还</strong></p>
            </div>

            <div @click="jump2Notice(4)" class="content-item">
                <img src="@/assets/superUserImage/notice.png" alt="">
                <p><strong>首页公告管理</strong></p>
            </div>

            <div @click="jump2Library(3)" class="content-item">
                <img src="@/assets/superUserImage/library.png" alt="">
                <p><strong>书库</strong></p>
            </div>

            <div @click="jump2BorrowSummary(5)" class="content-item">
                <img src="@/assets/superUserImage/summary.png" alt="">
                <p><strong>图书借阅统计</strong></p>
            </div>
        </div>
    </div>
</template>

<script>
import adminHeader from '@/views/admin/adminHeader'
export default {
    name:"admin-index",
    components:{
        adminHeader
    },
    data(){
        return{

        }
    },
    methods:{
        enterContent(event){
            let element = event.currentTarget;
            element.style.border = "1px solid white";
        },
        jump2UserList(index){
             this.$router.push({
                name: "userList",
                params: {
                    index: index
                }
            })
        },
        jump2BookBorrow(index){
            this.$router.push({
                name: "bookBorrow",
                params: {
                    index: index
                }
            })
        },
        jump2Notice(index){
            this.$router.push({
                name: "notice",
                params: {
                    index: index
                }
            })
        },
        jump2Library(index){
            this.$router.push({
                name: "library",
                params: {
                    index: index
                }
            })
        },
        jump2BorrowSummary(index){
            this.$router.push({
                name: "borrowSummary",
                params: {
                    index: index
                }
            })
        }
    }
    // @mouseleave="leaveContent($event)" @mouseenter="enterContent($event)"
}
</script>

<style scoped>
.admin-index{
    background:whitesmoke;
}
.admin-index-content{
    width: 1000px;
    height: 470px;
    margin:0 auto;
    margin-top: 10px;
    padding:40px;
    padding-left: 50px;
    border:1px solid white;
    background: whitesmoke;
    border-radius: 15px;
}

.content-item{
    width: 400px;
    height: 150px;
    float:left;
    margin-right: 80px;
    margin-bottom:20px;
    border:1px solid whitesmoke;
    cursor: pointer;
}

.admin-index-content img{
    width: 110px;
    height: 110px;
    
}
.admin-index-content p{
    margin: 0;
    padding:0;
    font-size: 18px;
}
.content-item:hover{
   border:1px solid white;
}
</style>