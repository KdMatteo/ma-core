<template>
    <div class="host-wrap">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-location"></i>主机管理</el-breadcrumb-item>
            </el-breadcrumb>
            <el-button type="primary"icon="el-icon-plus" size="mini" @click="add()">新增</el-button>
        </div>
        <el-table
            :data="tableData"
            height="400"
            border
            style="width:100%">
            <el-table-column
                type="index"
                label="序号"
                width="60">
            </el-table-column>
            <el-table-column
                prop="ip"
                label="IP"
                width="150">
            </el-table-column>
            <el-table-column
                prop="port"
                label="端口"
                width="100">
            </el-table-column>
            <el-table-column
                prop="account"
                label="账号"
                width="120">
            </el-table-column>
            <el-table-column
                prop="password"
                label="密码"
                width="120">
            </el-table-column>
            <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-button @click="update(scope.row)" size="mini">修改</el-button>
                    <el-button type="danger" @click="del(scope.row)" size="mini">删除</el-button>
                    <el-button @click="handleClick(scope.row)" size="mini">查看水厂</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="主机管理" :visible.sync="dialogVisible" @close="closeDialog()">
            <el-form :model="formData" :rules="rules" ref="formData" label-width="80px">
                <el-form-item label="IP" prop="ip">
                    <el-input v-model="formData.ip" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="端口" prop="port">
                    <el-input v-model="formData.port" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="账号" prop="account">
                    <el-input v-model="formData.account" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="formData.password" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelDialog()">取 消</el-button>
                <el-button type="primary" @click="submit('formData')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import api from '../../fetch/api'
import { mapGetters } from 'vuex'
import { mapActions } from 'vuex'

export default {
    data() {
        return {
            dialogVisible: false,
            formData: {
                id: 0,
                ip: '',
                port: 3306,
                account: '',
                password: ''
            },
            rules: {
                ip: [
                    { required: true, message: '请输入IP', trigger: 'blur' }
                ],
                port: [
                    { required: true, message: '请输入端口', trigger: 'blur' }
                ],
                account: [
                    { required: true, message: '请输入账号', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ]
            }
        }
    },
    computed: {
        ...mapGetters({
            tableData: 'getHostList'
        })
    },
    methods: {
        ...mapActions([
            'setHostList'
        ]),
        add() {
            this.dialogVisible = true
        },
        submit(formData) {
            let self = this
            this.$refs[formData].validate((valid) => {
                if (valid) {
                    self.cancelDialog()

                    if (self.formData.id > 0) {
                        self.updateHost(self.formData)
                    } else {
                        self.addHost(self.formData)
                    }
                } else {
                    console.log('error submit!!')
                    return false
                }
            });
        },
        update(row) {
            this.dialogVisible = true

            this.$nextTick(() => {
                this.formData.id = row.id
                this.formData.ip = row.ip
                this.formData.port = row.port
                this.formData.account = row.account
                this.formData.password = row.password
            })
        },
        del(row) {
            let self = this
            this.$confirm('是否确定删除该条记录?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                self.deleteHost(row.id)
            }).catch(() => {
                
            });
        },
        cancelDialog() {
            this.dialogVisible = false
        },
        closeDialog() {
            this.formData.id = 0
            this.$refs.formData.resetFields()           
        },
        getList() {
            let self = this
            api.getHostList()
                .then(res => {
                    if (0 === res.errorCode) {
                        self.setHostList(res.data)
                    }
                })
                .catch(error => {

                })
        },
        addHost(data) {
            let self = this
            api.addHost(data)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.getList()
                    }
                })
                .catch(error => {

                })
        },
        updateHost(data) {
            let self = this
            api.updateHost(data)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.getList()
                    }
                })
                .catch(error => {

                })
        },
        deleteHost(id) {
            let self = this
            api.deleteHost(id)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.getList()
                    }
                })
                .catch(error => {

                })
        },
        handleClick(row) {
            this.$router.push('/main/host/' + row.id + '/object');
        },
    },

    created() {
        this.getList()
    }
}
</script>

<style>

</style>