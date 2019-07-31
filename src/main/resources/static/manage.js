let app = new Vue({
    el: '#app',
    data: {
        defaultActive: '11',
        list: [], //用户列表数据
        searchEntity: {}, //查询实体类
        loading: true,
        createDialogVisible: false,
        dataForm: {
            oldname: '',
            newname: '',
        },
        rules: {
            newname: [{required: true, message: '对象名称不能为空', trigger: 'blur'}]
        },

        updateDialogVisible: false,
        mobileStatus: false, //是否是移动端
        sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
        sidebarFlag: ' openSidebar ', //侧边栏标志
        dialogVisible: false,
    },
    created() {
        window.onload = function () {
            app.changeDiv();
        }
        window.onresize = function () {
            app.changeDiv();
        }

        this.search();
    },
    mounted() {
        //this.$refs.loader.style.display = 'none';
    },
    methods: {
        _notify(message, type) {
            this.$message({
                message: message,
                type: type
            })
        },
        //获取列表
        search() {
            this.loading = true;
            this.$http.get(api.storage.images.list).then(response => {
                this.list = response.body;
                this.loading = false;
            })
        },

        //触发关闭按钮
        handleClose() {
            this.dialogVisible = false; //关闭模态框
        },

        //触发删除按钮
        handleDelete(imagekey) {
            //alert(imagekey);
            this.$confirm('确定永久删除？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.get(api.storage.images.deleteOne(imagekey)).then(response => {
                    if (response.body) {
                        this._notify("删除成功", 'success')
                    } else {
                        this._notify("删除失败", 'error')
                    }
                    this.search();
                });
            }).catch(() => {
                this._notify('已取消删除', 'info');
            })
        },

        //触发新增按钮
        handleSave() {
            this.createDialogVisible = true;
        },

        //上传
        upload(item) {
            console.log(item);
            const formData = new FormData();
            formData.append('file', item.file);
            this.$http.post(api.storage.images.upload, formData).then(response => {
                this.createDialogVisible = false;
                if (response.body.code == 200) {
                    this._notify(response, 'success')
                } else {
                    this._notify(response, 'error')
                }
                this.search();
            })
        },

        //触发更新按钮
        handleUpdate(name) {
            this.dataForm.oldname = name;
            this.dataForm.newname = name;
            this.updateDialogVisible = true;
        },

       /* update() {
            this.$refs['dataForm'].validate((valid) => {
                if (valid) {
                    console.log(this.dataForm);
                    this.$http.get(api.storage.images.updateOne(this.dataForm.oldname, this.dataForm.newname)).then(response => {
                        this.updateDialogVisible = false;
                        if (response.body.code == 200) {
                            this._notify(response, 'success')
                        } else {
                            this._notify(response, 'error')
                        }
                        this.search();
                    });
                })
        },*/

        //文件上传前的前的钩子函数
        beforeUpload(file){
            const isJPG = file.type === 'image/jpeg';
            const isGIF = file.type === 'image/gif';
            const isPNG = file.type === 'image/png';
            const isBMP = file.type === 'image/bmp';
            const isLt2M = file.size / 1024 / 1024 < 40;

            if (!isJPG && !isGIF && !isPNG && !isBMP) {
                this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传图片大小不能超过 40MB!');
            }
            return (isJPG || isBMP || isGIF || isPNG) && isLt2M;
        },

        //触发导出按钮
        handleCopy(imageKey,type) {
            var domain = window.location.host;
            var path ="http://"+domain+"/image/getImage/"+imageKey+"."+type;
            //window.clipboardData.setData("Text",path);
            //alert("已复制到剪贴板："+path);
            this._notify("请手动复制复制链接： "+path,"success");
            //window.open(path,'','',false);
        },

        /**
         * 监听窗口改变UI样式（区别PC和Phone）
         */
        changeDiv() {
            let isMobile = this.isMobile();
            if (isMobile) {
                //手机访问
                this.sidebarFlag = ' hideSidebar mobile ';
                this.sidebarStatus = false;
                this.mobileStatus = true;
            } else {
                this.sidebarFlag = ' openSidebar';
                this.sidebarStatus = true;
                this.mobileStatus = false;
            }
        },
        isMobile() {
            let rect = body.getBoundingClientRect();
            return rect.width - RATIO < WIDTH
        },
        //蒙版
        drawerClick() {
            this.sidebarStatus = false;
            this.sidebarFlag = ' hideSidebar mobile '
        }
    },
});