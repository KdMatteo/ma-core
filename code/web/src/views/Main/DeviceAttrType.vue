<template>
    <div class="deviceAttrType-wrap">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{name:'deviceType'}"><i class="el-icon-menu"></i> 设备类型</el-breadcrumb-item>
                <el-breadcrumb-item>设备属性类型</el-breadcrumb-item>
            </el-breadcrumb>
            <el-button type="primary"icon="el-icon-plus" size="mini" @click="onClickAddDeviceAttrType()">新增</el-button>
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
                prop="field_name"
                label="字段名称"
                width="120">
            </el-table-column>
            <el-table-column
                prop="label"
                label="标签"
                width="120">
            </el-table-column>
            <el-table-column
                prop="data_type"
                label="数据类型"
                width="120">
            </el-table-column>
            <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-button @click="onClickUpdateDeviceAttrType(scope.row)" size="mini">修改</el-button>
                    <el-button type="danger" @click="onClickDeleteDeviceAttrType(scope.row)" size="mini">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="设备属性类型" :visible.sync="dialogVisible" @close="closeDialog()">
            <el-form :model="formDeviceAttrType" :rules="rulesDeviceAttrType" ref="formDeviceAttrType" label-width="80px">
                <el-form-item label="名称" prop="name">
                    <el-input v-model="formDeviceAttrType.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="字段名称" prop="field_name">
                    <el-input v-model="formDeviceAttrType.field_name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="标签" prop="label">
                    <el-input v-model="formDeviceAttrType.label" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="数据类型" prop="data_type">
                    <el-input v-model="formDeviceAttrType.data_type" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelDialog()">取 消</el-button>
                <el-button type="primary" @click="submitNewDeviceAttrType('formDeviceAttrType')">确 定</el-button>
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
            formDeviceAttrType: {
                id: 0,
                devicetype_id: 0,
                name: '',
                field_name: '',
                label: '',
                data_type: ''
            },
            rulesDeviceAttrType: {
                name: [
                    { required: true, message: '请输入名称', trigger: 'blur' }
                ],
                field_name: [
                    { required: true, message: '请输入字段名称', trigger: 'blur' }
                ],
                label: [
                    { required: true, message: '请输入标签', trigger: 'blur' }
                ],
                data_type: [
                    { required: true, message: '请输入数据类型', trigger: 'blur' }
                ]
            }
        }
    },
    computed: {
        ...mapGetters({
            tableData: 'getDeviceAttrTypeList'
        })
    },
    methods: {
        ...mapActions([
            'setDeviceAttrTypeList'
        ]),
        onClickAddDeviceAttrType() {
            this.dialogVisible = true
        },
        submitNewDeviceAttrType(formName) {
            let self = this
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    self.cancelDialog()

                    if (self.formDeviceAttrType.id > 0) {
                        self.updateDeviceAttrType(self.formDeviceAttrType)
                    } else {
                        self.addDeviceAttrType(self.formDeviceAttrType)
                    }
                } else {
                    console.log('error submit!!')
                    return false
                }
            })
        },
        onClickUpdateDeviceAttrType(row) {
            this.dialogVisible = true

            this.$nextTick(() => {
                this.formDeviceAttrType.id = row.id
                this.formDeviceAttrType.name = row.name
                this.formDeviceAttrType.field_name = row.field_name
                this.formDeviceAttrType.label = row.label
                this.formDeviceAttrType.data_type = row.data_type
            })
        },
        onClickDeleteDeviceAttrType(row) {
            let self = this
            this.$confirm('是否确定删除该条记录?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                self.deleteDeviceAttrType(row.id)
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
            this.formDeviceAttrType.id = 0
            this.$refs.formDeviceAttrType.resetFields()
        },
        getList() {
            let self = this
            api.getDeviceAttrTypeList(this.formDeviceAttrType.devicetype_id)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.setDeviceAttrTypeList(res.data)
                    }
                })
                .catch(error => {

                })
        },
        addDeviceAttrType(data) {
            let self = this
            api.addDeviceAttrType(data)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.getList()
                    }
                })
                .catch(error => {

                })
        },
        updateDeviceAttrType(data) {
            let self = this
            api.updateDeviceAttrType(data)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.getList()
                    }
                })
                .catch(error => {

                })
        },
        deleteDeviceAttrType(id) {
            let self = this
            api.deleteDeviceAttrType(id)
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
        this.formDeviceAttrType.devicetype_id = parseInt(this.$route.params.id, 10)

        this.getList()
    }
}
</script>

<style>

</style>