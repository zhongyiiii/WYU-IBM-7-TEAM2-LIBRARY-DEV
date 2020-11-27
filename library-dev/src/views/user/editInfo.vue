<template>
    <div>
        <user-header :title="titleList"/>
        <detail-info></detail-info>
    </div>
</template>

<script>
import userHeader from '@/views/user/userHeader'
import detailInfo from '@/views/user/detailInfo'
export default {
    name:"edit-info",
    components:{
        userHeader,
        detailInfo  
    },
    // mounted:function(){
    //     console.log(this.titleList)
    // },
    data(){
        return{
            titleList:this.$route.params.titleList
        }
    },
    methods:{
        
    }
}
</script>

<style scoped>
    
</style>