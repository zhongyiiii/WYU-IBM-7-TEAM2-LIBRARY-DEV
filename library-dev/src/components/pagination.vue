<template>    
  <div class="block">
    <el-pagination
    background
     
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage1"
      :page-sizes="[5, 10, 20, 30]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
</template>
<script>
// 分页组件需要：
// page-size，（number值,）
// page-sizes(数组，用于显示每一ye页的显示条数)
// current-page（用于当前显示页码）
  export default {
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`);
        this.$emit('changePageSize', val)
      },
      handleCurrentChange(val) {
        // console.log(`当前页: ${val},每页`+this.pageSize);
        this.$emit('changeCurrentPage', val, this.pageSize)
      }
    },


    // props:["getBooksCondition"],  //从bookList父组件传过来的值
    created(){
      this.$emit('getCST')
    },

    props:["bookPageBean"],
    watch:{
      bookPageBean:{
          handler:function(newVal, oldVal){
            console.log(newVal)
            console.log(oldVal)
            this.pageSize = this.bookPageBean.pageSize;
            this.currentPage1 = this.bookPageBean.currentPage;
            this.total = this.bookPageBean.totalCount;

        },
        // immediate:true,
        deep:true
      }
      
    },

    data() {
      return {
        currentPage1: 1,
        currentPage2: 5,
        currentPage3: 5,
        currentPage4: 4,
        pageSize:10,
        total:100
      };
    }
  }
</script>