<template>
    <div>
        <admin-header :index="index"></admin-header>
        <div class="summaryContent">
            <fieldset class="fieldset_showdata">
                <legend style="font-size:20px">数据统计</legend>
                <div class="summary">
                    <div class="borrow-content">
                        <div class="fieldset_showdata_data">
                            <div style="margin-top: 10px; text-align: center;font-size: 16px;">今日借出统计</div>
                            <div style="margin-top: 15px;text-align: center;font-size:30px;color: #2E852F;">{{summary.borrowDay}}</div>
                        </div>
                        <div class="fieldset_showdata_data">
                            <div style="margin-top: 10px; text-align: center;font-size: 16px;">本月借出统计</div>
                            <div style="margin-top: 15px;text-align: center;font-size:30px;color: #2E852F;">{{summary.borrowMonth}}</div>
                        </div>
                        <div class="fieldset_showdata_data">
                            <div style="margin-top: 10px; text-align: center;font-size: 16px;">年度借出统计</div>
                            <div style="margin-top: 15px;text-align: center;font-size:30px;color: #2E852F;">{{summary.borrowYear}}</div>
                        </div>
                    </div>
                    <div class="return-content">
                        <div class="fieldset_showdata_data">
                            <div style="margin-top: 10px; text-align: center;font-size: 16px;">今日归还统计</div>
                            <div style="margin-top: 15px;text-align: center;font-size:30px;color: #2E852F;">{{summary.returnDay}}</div>
                        </div>
                        <div class="fieldset_showdata_data">
                            <div style="margin-top: 10px; text-align: center;font-size: 16px;">本月归还统计</div>
                            <div style="margin-top: 15px;text-align: center;font-size:30px;color: #2E852F;">{{summary.returnMonth}}</div>
                        </div>
                        <div class="fieldset_showdata_data">
                            <div style="margin-top: 10px; text-align: center;font-size: 16px;">年度归还统计</div>
                            <div style="margin-top: 15px;text-align: center;font-size:30px;color: #2E852F;">{{summary.returnYear}}</div>
                        </div>
                    </div>
                </div>
            </fieldset>
        </div>
    </div>
</template>

<script>
import adminHeader from '@/views/admin/adminHeader'
import axios from 'axios'
export default {
    name:"borrow-summary",
    components:{
        adminHeader
    },
    created(){
        axios({
            url:this.GLOBAL.basePath+"/borrowAndReturnCount",
            method:'get',
        }).then(data=>{
            this.summary = data.data
        }).catch(err=>{
            console.log("请求统计数据错误:"+err)
        })
    },
    data(){
        return{
            index:this.$route.params.index,
            summary:{}
        }
    }
}
</script>

<style scoped>
.summaryContent{
    width:80%;
    height: 450px;
    margin:0 auto;
}
  .fieldset_showdata{
        width:1000px;
        height: 350px;
        border-color: #D5D7CB;
    }
    .fieldset_showdata_data{
        min-width: 180px;
        height: 100px;
        background-color: #F8F8F8;
        margin: 0 20px;
        float: left;
        padding:0 5px;
    }  
    .summary > div{
        display: flex;
        justify-content: space-between;
    }

    .borrow-content{
        margin-top: 20px;
        margin-bottom: 40px;
    }
</style>