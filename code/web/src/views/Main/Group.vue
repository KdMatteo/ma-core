<template>
    <div class="group-wrap">
        <div class="crumbs">
            <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{name:'host'}"><i class="el-icon-location"></i> 主机管理</el-breadcrumb-item>
            <el-breadcrumb-item :to="toObject"> 水厂</el-breadcrumb-item>
            <el-breadcrumb-item>运行单元</el-breadcrumb-item>
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
                prop="id"
                :index="indexMethod"
                width="60">
            </el-table-column>
            <el-table-column
                prop="name"
                label="名称"
                width="250">
            </el-table-column>
            <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-button @click="update(scope.row)" size="mini">修改</el-button>
                    <el-button @click="del(scope.row)" type="danger" size="mini">删除</el-button>
                    <el-button @click="handleClick(scope.row)" size="mini">查看设备</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="运行单元" :visible.sync="dialogVisible" @close="closeDialog()">
            <el-form :model="formData" :rules="rulesData" ref="formData" label-width="100px">
                <el-form-item label="名称" prop="name">
                    <el-input v-model="formData.name" auto-complete="off"></el-input>
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
                object_id: 0,
                host_id: 0,
                name: ''
            },
            toObject: "",
            rulesData: {
                name: [
                    { required: true, message: '请输入名称', trigger: 'blur' }
                ]
            }
           
        };
    },
    computed: {
        ...mapGetters({
            tableData: 'getGroupList'
        })
    },
    methods: {
        ...mapActions([
            'setGroupList'
        ]),
        indexMethod(index) {
            return index + 1;
        },
        getList() {
            let self = this
            api.getGroupList(self.formData.object_id)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.setGroupList(res.data)
                    }
                })
                .catch(error => {

                })
        },
        addData(data) {
            let self = this
            api.addGroup(self.formData.object_id, data)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.getList()
                    }
                })
                .catch(error => {

                })
        },
        updateData(data) {
            let self = this
            api.updateGroup(data)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.getList()
                    }
                })
                .catch(error => {

                })
        },
        deleteData(id) {
            let self = this
            api.deleteGroup(id)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.getList()
                    }
                })
                .catch(error => {

                })
        },
        add() {
            this.dialogVisible = true
        },
        update(row) {
            this.dialogVisible = true

            this.$nextTick(() => {
                this.formData.id = row.id
                this.formData.name = row.name
            })
        },
        del(row) {
            let self = this
            this.$confirm('是否确定删除该条记录?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                self.deleteData(row.id)
            }).catch(() => {
                
            });
        },
        handleClick(row) {
            this.$router.push('/main/host/' + this.formData.host_id + '/object/' + this.formData.object_id + '/group/' + row.id + '/device');
        },
        cancelDialog() {
            this.dialogVisible = false
        },
        closeDialog() {
            this.formData.id = 0
            this.$refs.formData.resetFields()           
        },
        submit(formData) {
            let self = this
            this.$refs[formData].validate((valid) => {
                if (valid) {
                    self.cancelDialog()

                    if (self.formData.id > 0) {
                        self.updateData(self.formData)
                    } else {
                        self.addData(self.formData)
                    }
                } else {
                    console.log('error submit!!')
                    return false
                }
            });
        }
    },
    created() {
        this.formData.host_id = parseInt(this.$route.params.id, 10)
        this.formData.object_id = parseInt(this.$route.params.oid, 10)
        this.toObject = '/main/host/' + this.formData.host_id + '/object'
        this.getList()
    }
}
</script>

<style>

</style>