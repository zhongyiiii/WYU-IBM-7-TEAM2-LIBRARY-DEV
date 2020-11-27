import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import HelloWorld from "../components/HelloWorld.vue"
import swiper from "../components/Swiper"
import login from '@/components/login'
import adminHeader from '../views/admin/adminHeader'
import adminIndex from '@/views/admin/adminIndex'
Vue.use(VueRouter) //Vue全局使用Router

const routes = [ //配置路由，这里是个数组
    {
        path: '/', //链接路径
        name: 'Home', //路由名称，
        component: Home //对应的组件模板
    },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import ( /* webpackChunkName: "about" */ '../views/About.vue')
    },
    {
        path: "/helloworld",
        name: "HelloWorld",
        component: HelloWorld
    },
    {
        path: "/swiper",
        name: "swiper",
        component: swiper
    },
    {
        path: "/login",
        name: "login",
        component: login
    },

    //user
    {
        path: "/user/index",
        name: "index",
        meta: {
            title: '用户主页'
        },
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/user/index.vue'),
        
    },
    {
        path: "/user/myInfo",
        name: "myInfo",
        meta: {
            title: '我的信息'
        },
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/user/myInfo.vue')
    },
    {
        path: "/user/userHeader",
        name: "userHeader",
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/user/userHeader.vue')
    },
    {
        path: "/user/editInfo",
        name: "editInfo",
        meta: {
            title: '信息修改'
        },
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/user/editInfo.vue')
    },
    {
        path: "/user/userBorrowHistory",
        name: "userBorrowHistory",
        meta: {
            title: '用户借阅历史'
        },
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/user/userBorrowHistory.vue')
    },
    {
        path: "/pagination",
        name: "pagination",
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/components/pagination.vue')
    },
    {
        path: "/user/myBookShell",
        name: "myBookShell",
        meta: {
            title: '我的书架'
        },
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/user/myBookShell.vue')
    },
    {
        path: "/user/bookList",
        name: "bookList",
        meta: {
            title: '图书库'
        },
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/user/bookList.vue')
    },



    //管理员
    {
        path: "/admin/adminIndex",
        name: "adminIndex",
        meta: {
            title: '管理员主页'
        },
        component: adminIndex
    },
    {
        path: "/admin/adminHeader",
        name: "adminHeader",
        component: adminHeader
    },
    {
        path: "/admin/bookBorrow",
        name: "bookBorrow",
        meta: {
            title: '图书借阅与归还'
        },
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/admin/bookBorrow.vue')
    },
    {
        path: "/admin/library",
        name: "library",
        meta: {
            title: '书库'
        },
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/admin/library.vue')
    },
    {
        path: "/admin/notice",
        name: "notice",
        meta: {
            title: '首页公告管理'
        },
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/admin/notice.vue')
    },
    {
        path: "/admin/borrowSummary",
        name: "borrowSummary",
        meta: {
            title: '图书借阅与统计'
        },
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/admin/borrowSummary.vue')
    },
    {
        path: "/admin/userList",
        name: "userList",
        meta: {
            title: '用户列表'
        },
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/admin/userList.vue')
    },
    {
        path: "/admin/userLogout",
        name: "userLogout",
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/admin/userLogout.vue')
    },
    {
        path: "/admin/userBorrowHistory",
        name: "borrowHistory",
        meta: {
            title: '管理员-用户借阅历史'
        },
        component: () =>
            import ( /* webpackChunkName: "about" */ '@/views/admin/userBorrowHistory.vue')
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router