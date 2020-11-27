<template>
    <div>
        <admin-header :index="index"></admin-header>
        <div class="editor">
            <div class="notice-list">
                <el-table
                    :data="noticeList"
                    style="width: 100%;"
                    max-height="450">
                    <el-table-column
                    fixed
                    prop="title"
                    label="标题"
                    width="200">
                    </el-table-column>
                    
                    <el-table-column
                    fixed="left"
                    label="操作"
                    width="120">
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                    </template>
                    </el-table-column>
                </el-table>
                <span>修改标题</span>
                <el-input
                   
                    placeholder="请输入标题"
                    v-model="title"
                    clearable>
                </el-input>
            </div>
            
            <div>
                <quill-editor v-model="noticeContent" :options="editorOption" style="height:450px;"></quill-editor>
                
            </div>
            <div class="save-btn">
                <el-button @click="updateNotice" type="info" size="mini" >保存</el-button>
            </div>
            
        </div>
        
    </div>
</template>

<script>
import adminHeader from '@/views/admin/adminHeader'
import axios from 'axios'
export default {
    name:"notice",
    components:{
        adminHeader,
    },
    created(){
        this.showNoticeList();
    },
    data(){
      
        return{
            title:"",
            index:this.$route.params.index,
            noticeContent:"",
            editorOption: {
              placeholder: "请在这里输入",
              modules:{
                toolbar:[
                          ['bold', 'italic', 'underline', 'strike'],    //加粗，斜体，下划线，删除线
                          ['blockquote', 'code-block'],     //引用，代码块
                          [{ 'header': 1 }, { 'header': 2 }],        // 标题，键值对的形式；1、2表示字体大小
                          [{ 'list': 'ordered'}, { 'list': 'bullet' }],     //列表
                          [{ 'script': 'sub'}, { 'script': 'super' }],   // 上下标
                          [{ 'indent': '-1'}, { 'indent': '+1' }],     // 缩进
                          [{ 'direction': 'rtl' }],             // 文本方向
                          [{ 'size': ['small', false, 'large', 'huge'] }], // 字体大小
                          [{ 'header': [1, 2, 3, 4, 5, 6, false] }],     //几级标题
                          [{ 'color': [] }, { 'background': [] }],     // 字体颜色，字体背景颜色
                          [{ 'font': [] }],     //字体
                          [{ 'align': [] }],    //对齐方式
                          ['clean'],    //清除字体样式
                          ['image','video']    //上传图片、上传视频
                          ]
                      }
                },
            
            noticeList:[]
            
        }
    },
    methods: {
        showNoticeList(){
            axios({
                url:this.GLOBAL.basePath+"/showGonggao",
                method:'get',
            }).then(data=>{
                this.noticeList = data.data
            }).catch(err=>{
                console.log("请求公告列表数据错误:"+err)
            })
        },
        handleClick(row){
            this.noticeContent = row.body;
            this.title = row.title;
            this.gId = row.gId;
        },

        // 转码
        //         后台接收到数据后会将字符中的标签进行转码，所以我们要先进行一个解码的操作让他变成标签形式的字符串：
        // 例如后台接收的数据如下："&lt;h1&gt;title&lt;"  ，对应解码后就是`<h1>title</h1>`。
        //把实体格式字符串转义成HTML格式的字符串
        escapeStringHTML(str) {
            str = str.replace(/&lt;/g,'<');
            str = str.replace(/&gt;/g,'>');
            return str;
        },
        updateNotice(){
            this.noticeContent = this.escapeStringHTML(this.noticeContent);
            let notice = {
                body:this.noticeContent,
                author:JSON.parse(localStorage.getItem('admin')).userName,
                gId:this.gId,
                title:this.title
            }
            axios({
                url:this.GLOBAL.basePath+"/updateGonggao",
                method:'post',
                contentType:'application/json',
                data:notice
            }).then(data=>{
                let res = data.data;
                if(res == 1){
                    this.alertSuccess(this.GLOBAL.systemName,'更新公告完成',1500);
                    this.showNoticeList();
                }
            }).catch(err=>{
                console.log("请求公告列表数据错误:"+err)
            })
        }

    },
}
</script>

<style scoped>
.editor{
    width:100%;
    height: 550px;
    background: whitesmoke;
    display: flex;
    justify-content: flex-start;
}
.save-btn button{
    margin-top: 20px;
    float: right;
    margin-right: 10px;
    margin-left: 10px;
}
.save-btn :hover{
    background-color: greenyellow;
}


.notice-list{
    max-width:350px;
    height: 500px;
}
</style>