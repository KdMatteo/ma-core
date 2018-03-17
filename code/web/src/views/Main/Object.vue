<template>
    <div class="object-wrap">
        <div class="crumbs">
            <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{name:'host'}"><i class="el-icon-location"></i> 主机管理</el-breadcrumb-item>
            <el-breadcrumb-item> 水厂</el-breadcrumb-item>
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
                width="180">
            </el-table-column>
            <el-table-column
                prop="database_name"
                label="数据库名称">
            </el-table-column>
            <el-table-column
                prop="linkman"
                label="联系人"
                width="80">
            </el-table-column>
            <el-table-column
                prop="address"
                label="地址">
            </el-table-column>
            <el-table-column
                prop="mobile"
                label="电话">
            </el-table-column>
            <el-table-column
                label="操作"
                width="360">
                <template slot-scope="scope">
                    <el-button @click="update(scope.row)" size="mini">修改</el-button>
                    <el-button @click="del(scope.row)" type="danger" size="mini">删除</el-button>
                    <el-button @click="viewGroup(scope.row)" size="mini">查看运行单元</el-button>
                    <el-button @click="viewTerminal(scope.row)" size="mini">查看终端</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="page.index"
                :page-sizes="[20, 30, 50, 100]"
                :page-size="20"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
        </div>
        <el-dialog title="水厂" :visible.sync="dialogVisible" @close="closeDialog()">
            <el-form :model="formData" :rules="rulesData" ref="formData" label-width="100px">
                <el-form-item label="名称" prop="name">
                    <el-input v-model="formData.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="数据库名称" prop="database_name">
                    <el-input v-model="formData.database_name" :disabled="databaseName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="联系人" prop="linkman">
                    <el-input v-model="formData.linkman" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="地址" prop="address">
                    <el-input v-model="formData.address" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="mobile">
                    <el-input v-model="formData.mobile" auto-complete="off"></el-input>
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
            page: {
                index: 1,
                size: 20
            },
            formData: {
                id: 0,
                host_id: 0,
                name: '',
                database_name: '',
                linkman: '',
                address:'',
                mobile:''
            },
            rulesData: {
                name: [
                    { required: true, message: '请输入名称', trigger: 'blur' }
                ],
                database_name: [
                    { required: true, message: '请输入数据库名称', trigger: 'blur' }
                ],
                linkman: [
                    { required: true, message: '请输入联系人', trigger: 'blur' }
                ],
                address: [
                    { required: true, message: '请输入地址', trigger: 'blur' }
                ],
                mobile: [
                    { required: true, message: '请输入电话', trigger: 'blur' }
                ]
            },
            databaseName: false
        }
    },
    computed: {
        ...mapGetters({
            tableData: 'getObjectList',
            total: 'getObjectTotal'
        })
    },
    methods: {
        ...mapActions([
            'setObjectList',
            'setObjectTotal'
        ]),
        indexMethod(index) {
            return index + 1 + (this.page.index - 1) * this.page.size;
        },
        handleSizeChange(val) {
            this.page.size = val

            this.getList()
        },
        handleCurrentChange(val) {
            this.page.index = val

            this.getList()
        },
        getList() {
            let self = this
            api.getObjectList(self.formData.host_id, self.page)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.setObjectList(res.data)
                        self.setObjectTotal(res.page.total)
                    }
                })
                .catch(error => {

                })
        },
        addData(data) {
            let self = this
            api.addObject(data)
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
            api.updateObject(data)
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
            api.deleteObject(id)
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
            this.databaseName = false
        },
        update(row) {
            this.dialogVisible = true
            this.databaseName = true

            this.$nextTick(() => {
                this.formData.id = row.id
                this.formData.name = row.name
                this.formData.database_name = row.database_name
                this.formData.linkman = row.linkman,
                this.formData.mobile = row.mobile,
                this.formData.address = row.address
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
        viewGroup(row) {
            this.$router.push('/main/host/' + this.formData.host_id + '/object/' + row.id + '/group');
        },
        viewTerminal(row) {
            this.$router.push('/main/host/' + this.formData.host_id + '/object/' + row.id + '/terminal');
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
        this.getList()
    }
}
</script>

<style>

</style>