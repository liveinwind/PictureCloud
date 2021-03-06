//设置全局表单提交格式
Vue.http.options.emulateJSON = true;
const {body} = document;
const WIDTH = 1024;
const RATIO = 3;
const api = {
    common: {
        logout: '/admin/logout'
    },
    index: {
        articleCount: '/article/findAllCount',
        commentsCount: '/comments/findAllCount',
        tagsCount: '/tags/findAllCount',
        linksCount: '/links/findAllCount',
        allArticle: '/article/findAll',
        allComments: '/comments/findAll'
    },

    edit: {
        findById(id) {
            return '/article/findById?id=' + id
        },
        save: '/article/save',
        update: '/article/update',
        allCategory: '/category/findAll',
    },

    publish: {
        save: '/article/save',
        allCategory: '/category/findAll',
    },
    article: {
        findByPage(pageSize, pageCode) {
            return '/article/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        delete: '/article/delete',
    },
    comments: {
        findByPage(pageSize, pageCode) {
            return '/comments/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        delete: '/comments/delete',
    },
    category: {
        findByPage(flag, pageSize, pageCode) {
            return '/' + flag + '/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        delete(flag) {
            return '/' + flag + '/delete';
        },
        update(flag) {
            return '/' + flag + '/update'
        },
        save(flag) {
            return '/' + flag + '/save'
        },
        findById(flag, id) {
            return '/' + flag + '/findById?id=' + id
        },
    },
    cover: {
        findByPage(pageSize, pageCode) {
            return '/article/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        findById(id) {
            return '/article/findById?id=' + id
        },
        delete() {
            return '/article/update';
        },
        update: '/article/update',
        save(flag) {
            return '/' + flag + '/save'
        },
    },
    links: {
        findByPage(pageSize, pageCode) {
            return '/links/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        findById(id) {
            return '/links/findById?id=' + id
        },
        save: '/links/save',
        delete: '/links/delete',
        update: '/links/update',
    },
    user: {
        updateUserInfo: '/user/updateUserInfo',
        updateUserPassword:'/user/updateUserPassword',
        info: '/admin/info',
        localUpload: '/storage/upload',
        avatar: '/file/avatar.json',
        getSetting: '/user/getSetting',
        updateSetting: '/user/updateSetting'
    },
    //对象储存

    //内置图床管理
    storage: {
        images: {
            list: '/ImagesManagement/list',
            upload: '/image/imageUpload',
            download() {
                return '/image/getImage/' + name;
            },
            deleteOne(name) {
                return '/ImagesManagement/deleteOne?imageKey=' + name;
            },
            findOne(name) {
                return '/ImagesManagement/findOne?imageKey=' + name
            },
    },
},
    //系统监控
    monitor: {
        loginlog: {
            list(pageCode, pageSize) {
                return '/loginlog/list?pageCode=' + pageCode + '&pageSize=' + pageSize;
            },
            delete: '/loginlog/delete',
        },
        log: {
            list(pageCode, pageSize) {
                return '/log/list?pageCode=' + pageCode + '&pageSize=' + pageSize;
            },
            delete: '/log/delete',
        },
    },
};