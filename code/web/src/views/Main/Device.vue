<template>
    <div class="device-wrap">
        <div class="crumbs">
            <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{name:'host'}"><i class="el-icon-location"></i> 主机管理</el-breadcrumb-item>
            <el-breadcrumb-item :to="toObject"> 水厂</el-breadcrumb-item>
            <el-breadcrumb-item :to="toGroup"> 运行单元</el-breadcrumb-item>
            <el-breadcrumb-item>设备</el-breadcrumb-item>
            </el-breadcrumb>
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="add()">新增</el-button>
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
                prop="code"
                label="表名"
                width="150">
            </el-table-column>
             <el-table-column
                prop="index"
                label="索引"
                width="100">
            </el-table-column>
            <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-button @click="update(scope.row)" size="mini">修改</el-button>
                    <el-button @click="del(scope.row)" type="danger" size="mini">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="设备" :visible.sync="dialogVisible" @close="closeDialog" class="dialog">
            <el-form :model="formData" :rules="rulesData" ref="formData" label-width="80px">
                <el-form-item label="设备类型" prop="devicetype_id">
                    <el-select v-model="formData.devicetype_id" @change="deviceTypeChange" :disabled="deviceType">
                        <el-option
                            v-for="deviceType in deviceTypeList"
                            :key="deviceType.id"
                            :label="deviceType.name"
                            :value="deviceType.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="设备属性" prop="attrs">
                    <el-checkbox-group v-model="formData.attrs">
                        <el-checkbox
                            v-for="attr in deviceTypeAttrs"
                            :label="attr.id"
                            :key="attr.id">
                           {{attr.label}}
                        </el-checkbox>
                    </el-checkbox-group>
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
                host_id: 0,
                object_id: 0,
                group_id: 0,
                devicetype_id: 0,
                attrs: []
            },
            rulesData: {
                
            },
            toGroup: "",
            toObject: "",
            deviceTypeAttrs: [],
            deviceType: false
        }
    },
    computed: {
        ...mapGetters({
            tableData: 'getDeviceList',
            deviceTypeList: 'getDeviceTypeList'
        })
    },
    methods: {
        ...mapActions([
            'setDeviceList',
            'setDeviceTypeList'
        ]),
        indexMethod(index) {
            return index + 1;
        },
        getList() {
            let self = this
            api.getDeviceList(self.formData.object_id, self.formData.group_id, self.page)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.setDeviceList(res.data)                        
                    }
                })
                .catch(error => {

                })
        },
        getDeviceTypeList() {
            let self = this
            api.getDeviceTypeList()
                .then(res => {
                    if (0 === res.errorCode) {                        
                        self.setDeviceTypeList(res.data)

                        if (res.data.length > 0) {
                            self.formData.devicetype_id = res.data[0].id
                            self.deviceTypeAttrs = res.data[0].attrs
                        }
                    }
                })
                .catch(error => {

                })
        },
        deviceTypeChange(id) {
            let self = this
            for (let i = 0, len = self.deviceTypeList.length; i < len; i++) {
                if (self.deviceTypeList[i].id === id) {
                    self.deviceTypeAttrs = self.deviceTypeList[i].attrs
                }
            }
            self.formData.attrs = [];
        },
        addData(data) {
            let self = this
            api.addDevice(self.formData.object_id, self.formData.group_id, data)
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
            api.updateDevice(data)
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
            api.deleteDevice(id)
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
            this.deviceType = false
        },
        update(row) {
            this.dialogVisible = true
            this.deviceType = true

            this.$nextTick(() => {
                this.formData.id = row.id
                this.formData.devicetype_id = row.devicetype_id
                
                this.deviceTypeChange(this.formData.devicetype_id)

                let attrs = []
                row.attrs.forEach((attr) => {
                    attrs.push(attr.attrtype_id)
                })
                this.formData.attrs = attrs;
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
            this.$router.push('/main/device/' + row.id);
        },
        cancelDialog() {
            this.dialogVisible = false
        },
        closeDialog() {
            this.formData.id = 0
            this.$refs.formData.resetFields()

            this.deviceTypeAttrs = []
            this.$nextTick(() => {
                if (this.deviceTypeList.length > 0) {
                    this.deviceTypeAttrs = this.deviceTypeList[0].attrs
                }
            })            
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
        this.formData.group_id = parseInt(this.$route.params.gid, 10)

        this.toGroup = '/main/host/' + this.formData.host_id + '/object/' + this.formData.object_id + '/group'
        this.toObject = '/main/host/' + this.formData.host_id + '/object'

        this.getList()
        this.getDeviceTypeList()
    }
}
</script>

<style>

</style>