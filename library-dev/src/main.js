import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';


import qs from "qs"
import QuillEditor from 'vue-quill-editor'

import 'quill/dist/quill.core.css'

import 'quill/dist/quill.bubble.css'

import 'quill/dist/quill.snow.css'

import axios from "axios"

import global_ from './components/Global'
import util from './components/util'

Vue.use(util)

Vue.prototype.GLOBAL = global_//挂载到Vue实例上面



axios.defaults.withCredentials=true;
// Vue.http.options.xhr = { withCredentials: true }



Vue.prototype.$qs = qs

// Vue.prototype.$axios = axios

// // // //设置默认路径
// // // Axios.defaults.baseURL = '/api'

// Vue.config.productionTip = false


Vue.use(QuillEditor)

Vue.use(ElementUI)



router.beforeEach((to, from, next) => {
    /* 路由发生变化修改页面title */
    if (to.meta.title) {
      document.title = to.meta.title
    }
    next()
})





Vue.config.productionTip = false

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')