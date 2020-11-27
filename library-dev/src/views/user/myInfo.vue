<template>
    <div class="my-info">
        <user-header :title="title"></user-header>
        <div class="content">
            <div class="modify" @click="jump2EditInfo(title,$event)">
                <img id="editInfo" @mouseleave="toNormal('editInfo')" @mouseenter="magnify('editInfo')" src="@/assets/icons/modifyInfo.png" width="180" height="180" alt="">
                <p>信息修改</p>
            </div>
            <div class="history" @click="jump2BorrorHistory(title,$event)">
                <img id="borrowHistory" @mouseleave="toNormal('borrowHistory')" @mouseenter="magnify('borrowHistory')" src="@/assets/icons/history.png" width="180" height="180" alt="">
                <p>借阅历史</p>
            </div>
        </div>
    </div>
</template>

<script>
import userHeader from '@/views/user/userHeader'
export default {
    name:"my-info",
    components:{
        userHeader  
    },
    data(){
        return{
            title:this.$route.params.title,
            titleList:[]
        }
    },
    methods:{
        jump2EditInfo(t,event){
            let element = event.currentTarget;
            let value = element.querySelector("p").innerHTML;
            this.titleList.push(t);
            this.titleList.push(value);
            var that = this
            this.$router.push({
                name:"editInfo",
                params:{
                    titleList:that.titleList
                }
            })
        },
        jump2BorrorHistory(t,event){
            let element = event.currentTarget;
            let value = element.querySelector("p").innerHTML;
            this.titleList.push(t);
            this.titleList.push(value);
            var that = this
            this.$router.push({
                name:"userBorrowHistory",
                params:{
                    titleList:that.titleList
                }
            })
        },
        magnify(imgId){
            let img = document.getElementById(imgId);
            img.width = 200;
            img.height = 200;
            let color = '#fd757d';
            let p  = img.nextElementSibling;
            p.style.color = color;

        },
        toNormal(imgId){
            let img = document.getElementById(imgId);
            img.width = 180;
            img.height = 180;
            let color = 'black';
            let p  = img.nextElementSibling;
            p.style.color = color;
        },
    }  
}
</script>

<style scoped>
.content{
    width:100%;
    height: 300px;
    display: flex;
    justify-content: center;
}
.content > div{
    width:30%;
    height:280px ;
    margin-right: 30px;
    cursor: pointer;
}
.content img{
    margin-top: 40px;
}
.content p{
    font-size: 26px;
    font-family: emoji;
    font-weight: 600;
}
</style>