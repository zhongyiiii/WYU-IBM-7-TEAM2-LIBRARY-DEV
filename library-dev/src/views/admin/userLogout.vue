<template>
    <div>
        <admin-header :index="index"></admin-header>
    </div>
</template>

<script>
import adminHeader from '@/views/admin/adminHeader'
export default {
    name:"user-logout",
    components:{
        adminHeader
    },
    data(){
        return{
            index:this.$route.params.index
        }
    }
}
</script>

<style scoped>
    
</style>