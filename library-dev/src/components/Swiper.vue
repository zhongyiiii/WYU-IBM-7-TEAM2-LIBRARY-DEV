<template>
  <div class="swiper">
    <div class="swiper-container">
      <el-carousel class="swiper-img" :interval="4000"  height="500px">
        <el-carousel-item class="swiper-img-item" v-for="(item, index) in swiperList" :key="index">
          <img @click="enter" :src="item.picImg"/>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
// import { swiperList } from '../assets/data/swiper'
import axios from "axios"

export default {
  name: 'swiper',
  data () {
    return {
      swiperList: []
    }
  },
  created () {
    axios({
      url:"/swiperImg.json",
      method:"get"
    }).then(data=>{
      console.log(data.data);
      this.swiperList = data.data
    }).catch(err=>{
      console.log("err:"+err)
    })
  },
  methods:{
    enter(){
      alert("hellllo")
    }
  }

}
</script>

<style scoped>
  .swiper {
      width: 55%;
      height: 550px;
    margin-left: 20px;

}
img {
    width: 100%;
    height: 500px;
    margin: 0;
  }
</style>
