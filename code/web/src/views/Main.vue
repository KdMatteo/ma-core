<template>
    <div class="main-wrap">
        <el-container>
            <el-header>
                <div class="logo">后台管理系统</div>
                <div class="user-info">
                    <el-dropdown @command="handleCommand">
                        <span class="el-dropdown-link">
                            <img class="user-logo" src="../../static/img/user.jpg" /> {{loginInfo.account}}
                            <i class="el-icon-arrow-down"></i>
                        </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="logout">退出</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu
                        :default-active="active"
                        background-color="#324157"
                        text-color="#fff"
                        active-text-color="#20a0ff"
                        router>
                        <el-menu-item index="/main/host">
                            <i class="el-icon-location"></i>
                            <span slot="title">主机管理</span>
                        </el-menu-item>
                        <el-menu-item index="/main/deviceType">
                            <i class="el-icon-menu"></i>
                            <span slot="title">设备类型</span>
                        </el-menu-item>
                        <el-menu-item index="/main/dbSQL">
                            <i class="el-icon-document"></i>
                            <span slot="title">执行SQL</span>
                        </el-menu-item>
                        <el-menu-item index="/main/user">
                            <i class="el-icon-setting"></i>
                            <span slot="title">用户管理</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-main><router-view></router-view></el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { mapActions } from 'vuex'

export default {
    data() {
        return {
            
        }
    },
    computed: {
        ...mapGetters({
            loginInfo: 'getLoginInfo'
        }),
        active() {
            let path = this.$route.path
            if (path.indexOf('/main/host') > -1) {
                return '/main/host'
            } else if (path.indexOf('/main/deviceType') > -1) {
                return '/main/deviceType'
            } else if (path.indexOf('/main/dbSQL') > -1) {
                return '/main/dbSQL'
            } else if (path.indexOf('/main/user') > -1) {
                return '/main/user'
            }
        }
    },
    methods: {
        ...mapActions([
            'clearLoginInfo'
        ]),
        handleCommand(command) {
            if (command == 'logout') {
                this.clearLoginInfo();
                this.$router.push('/login');
            }
        }
    }
}
</script>

<style>
@import "../../static/css/common.css";

.main-wrap {
    height: 100%;
}
.main-wrap .el-menu {
    border: 0;
    background-color: #324157;
}
.main-wrap .el-container {
    height: 100%;
}
.main-wrap .el-header {
    background-color: #242f42;
    color: #fff;
    line-height: 60px;
}
.main-wrap .el-aside {
    background-color: #324157;
}
.main-wrap .el-menu-item:hover {
    background-color: #48576a !important;
}
.el-menu-item i {
    color: #fff;
}
.main-wrap .logo {
    float: left;
    text-align: center;
    font-size: 22px;
}
.main-wrap .user-info {
    float: right;
    font-size: 16px;
    color: #fff;
}
.main-wrap .user-info .user-logo {
    float: left;
    margin: 10px 5px 0 0;
    width: 40px;
    height: 40px;
    border-radius: 50%;
}
.main-wrap .el-dropdown-link {
    display: inline-block;
    color: #fff;
    cursor: pointer;
}

.main-wrap .crumbs {
    position: relative;
    height: 40px;
}
.main-wrap .crumbs .el-button {
    position: absolute;
    top: 0;
    right: 0;
}
.main-wrap .el-table td,
.main-wrap .el-table th {
    padding: 6px 0;
}
.main-wrap .el-table th {
    background-color: #eef1f6;
    border-right: 1px solid #dfe6ec;
    color: #1f2d3d;
}
.main-wrap .el-select {
    display: block;
}

</style>