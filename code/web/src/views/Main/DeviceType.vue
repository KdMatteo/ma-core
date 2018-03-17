<template>
    <div class="deviceType-wrap">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 设备类型</el-breadcrumb-item>
            </el-breadcrumb>
            <el-button type="primary"icon="el-icon-plus" size="mini" @click="onClickAddDeviceType()">新增</el-button>
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
                prop="name"
                label="名称"
                width="120">
            </el-table-column>
            <el-table-column
                prop="table_name"
                label="表名称"
                width="120">
            </el-table-column>
            <el-table-column
                prop="multi"
                label="是否多表"
                :formatter="multiFormat"
                width="120">
            </el-table-column>
            <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-button @click="onClickUpdateDeviceType(scope.row)" size="mini">修改</el-button>
                    <el-button type="danger" @click="onClickDeleteDeviceType(scope.row)" size="mini">删除</el-button>
                    <el-button @click="onClickGetDeviceAttrType(scope.row)" size="mini">查看属性</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="设备类型" :visible.sync="dialogVisible" @close="closeDialog()">
            <el-form :model="formDeviceType" :rules="rulesDeviceType" ref="formDeviceType" label-width="80px">
                <el-form-item label="名称" prop="name">
                    <el-input v-model="formDeviceType.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="表名称" prop="table_name">
                    <el-input v-model="formDeviceType.table_name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="是否多表" prop="multi">
                    <el-select v-model="formDeviceType.multi" placeholder="">
                        <el-option label="否" :value='0'></el-option>
                        <el-option label="是" :value='1'></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelDialog()">取 消</el-button>
                <el-button type="primary" @click="submitNewDeviceType('formDeviceType')">确 定</el-button>
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
            formDeviceType: {
                id: 0,
                name: '',
                table_name: '',
                multi: 0
            },
            rulesDeviceType: {
                name: [
                    { required: true, message: '请输入名称', trigger: 'blur' }
                ],
                table_name: [
                    { required: true, message: '请输入表名称', trigger: 'blur' }
                ]
            }
        }
    },
    computed: {
        ...mapGetters({
            tableData: 'getDeviceTypeList'
        })
    },
    methods: {
        ...mapActions([
            'setDeviceTypeList'
        ]),
        multiFormat(row, column, cellValue) {
            return (1 === row[column.property]) ? "是" : "否"
        },
        onClickAddDeviceType() {
            this.dialogVisible = true
        },
        submitNewDeviceType(formName) {
            let self = this
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    self.cancelDialog()

                    if (self.formDeviceType.id > 0) {
                        self.updateDeviceType(self.formDeviceType)
                    } else {
                        self.addDeviceType(self.formDeviceType)
                    }
                } else {
                    console.log('error submit!!')
                    return false
                }
            });
        },
        onClickGetDeviceAttrType(row) {
            this.$router.push('/main/deviceType/' + row.id + '/deviceAttrType');
        },
        onClickUpdateDeviceType(row) {
            this.dialogVisible = true

            this.$nextTick(() => {
                this.formDeviceType.id = row.id
                this.formDeviceType.name = row.name
                this.formDeviceType.table_name = row.table_name
                this.formDeviceType.multi = row.multi
            })
        },
        onClickDeleteDeviceType(row) {
            let self = this
            this.$confirm('是否确定删除该条记录?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                self.deleteDeviceType(row.id)
                /*self.$message({
                    type: 'success',
                    message: '删除成功!'
                });*/
            }).catch(() => {
                /*self.$message({
                    type: 'info',
                    message: '已取消删除'
                });*/
            });
        },
        cancelDialog() {
            this.dialogVisible = false
        },
        closeDialog() {
            this.formDeviceType.id = 0
            this.$refs.formDeviceType.resetFields()           
        },
        getList() {
            let self = this
            api.getDeviceTypeList()
                .then(res => {
                    if (0 === res.errorCode) {
                        self.setDeviceTypeList(res.data)
                    }
                })
                .catch(error => {

                })
        },
        addDeviceType(data) {
            let self = this
            api.addDeviceType(data)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.getList()
                    }
                })
                .catch(error => {

                })
        },
        updateDeviceType(data) {
            let self = this
            api.updateDeviceType(data)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.getList()
                    }
                })
                .catch(error => {

                })
        },
        deleteDeviceType(id) {
            let self = this
            api.deleteDeviceType(id)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.getList()
                    }
                })
                .catch(error => {

                })
        }
    },
    created() {
        this.getList()
    }
}
</script>

<style>

</style>