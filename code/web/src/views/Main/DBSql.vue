<template>
    <div class="dbsql-wrap">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{name:'object'}"><i class="el-icon-document"></i> 执行SQL</el-breadcrumb-item>
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
                prop="sql_string"
                label="SQL">
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
        <el-dialog title="SQL语句" :visible.sync="dialogVisible" @close="closeDialog()">
            <el-form :model="formData" :rules="rulesData" ref="formData" label-width="100px">
                <el-form-item label="SQL" prop="sql_string">
                    <el-input type="textarea" v-model="formData.sql_string" auto-complete="off"></el-input>
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
                sql_string: ''
            },
            rulesData: {
                sql_string: [
                    { required: true, message: '请输入SQL', trigger: 'blur' }
                ]
            }
           
        };
    },
    computed: {
        ...mapGetters({
            tableData: 'getDBSqlList',
            total: 'getDBSqlTotal'
        })
    },
    methods: {
        ...mapActions([
            'setDBSqlList',
            'setDBSqlTotal'
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
            api.getDBSqlList(self.page)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.setDBSqlList(res.data)
                        self.setDBSqlTotal(res.page.total)
                    }
                })
                .catch(error => {

                })
        },
        addData(data) {
            let self = this
            api.addDBSql(data)
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
        this.getList()
    }
}
</script>

<style>

</style>