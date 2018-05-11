<template>
    <div class="terminal-wrap">
        <div class="crumbs">
            <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{name:'host'}"><i class="el-icon-location"></i> 主机管理</el-breadcrumb-item>
            <el-breadcrumb-item :to="toObject"> 水厂</el-breadcrumb-item>
            <el-breadcrumb-item>终端管理</el-breadcrumb-item>
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
                label="编码"
                width="180">
            </el-table-column>
            <el-table-column
                prop="name"
                label="名称">
            </el-table-column>
            <el-table-column
                prop="ip"
                label="IP">
            </el-table-column>
            <el-table-column
                prop="port"
                label="端口"
                width="70">
            </el-table-column>
            <el-table-column
                label="操作"
                width="300">
                <template slot-scope="scope">
                    <el-button @click="update(scope.row)" size="mini">修改</el-button>
                    <el-button @click="del(scope.row)" type="danger" size="mini">删除</el-button>
                    <el-button @click="config(scope.row)" size="mini">配置</el-button>
                    <el-button @click="exportCfg(scope.row)" size="mini">导出</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="终端" :visible.sync="dialogVisible" @close="closeDialog(0)" width="600px" top="20px">
            <el-form :model="formData" :rules="rulesData" ref="formData" label-width="100px">
                <el-form-item label="编码" prop="code">
                    <el-input v-model="formData.code" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="名称" prop="name">
                    <el-input v-model="formData.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="IP" prop="ip">
                    <el-input v-model="formData.ip" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="端口" prop="port">
                    <el-input v-model="formData.port" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelDialog(0)">取 消</el-button>
                <el-button type="primary" @click="submit('formData')">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="终端" :visible.sync="dialogAttrVisible" @close="closeDialog(1)" width="800px" top="20px">
            <div class="dlg-left">
                <div class="dlg-title">已选属性</div>
                <el-table
                    :data="checkValues"
                    height="400"
                    border
                    style="width:100%">
                    <el-table-column
                        prop="group"
                        label="分组"
                        width="100">
                    </el-table-column>
                    <el-table-column
                        prop="device"
                        label="设备">
                    </el-table-column>
                    <el-table-column
                        prop="attr"
                        label="属性"
                        width="100">
                    </el-table-column>
                </el-table>
            </div>
            <div class="dlg-right">
                <div class="dlg-title">属性列表</div>
                <el-tree :data="treeData" show-checkbox node-key="id" ref="tree" default-expand-all @check="nodeChecked">
                    <span class="custom-tree-node" slot-scope="{ node, data }">
                        <span>{{ data.label }}</span>
                        <div v-if="node.level===3">
                            <span>
                                <el-input v-model="data.plcAddress" placeholder="PLC地址"></el-input>
                            </span>
                        </div>
                    </span>
                </el-tree>
            </div>            
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelDialog(1)">取 消</el-button>
                <el-button type="primary" @click="submit('')">确 定</el-button>
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
            dialogAttrVisible: false,
            toObject: "",
            formData: {
                id: 0,
                host_id: 0,
                object_id: 0,
                name: '',
                code: '',
                ip: '',
                port: 8080,
                attrs: null
            },
            treeData: [],
            rulesData: {
                name: [
                    { required: true, message: '请输入名称', trigger: 'blur' }
                ],
                code: [
                    { required: true, message: '请输入编码', trigger: 'blur' }
                ],
                ip: [
                    { required: true, message: '请输入IP', trigger: 'blur' }
                ],
                port: [
                    { required: true, message: '请输入端口', trigger: 'blur' }
                ]
            },
            deviceTypeList: {},
            terminalAttr: null,
            checkKeys: null,
            checkValues: []
        }
    },
    computed: {
        ...mapGetters({
            tableData: 'getTerminalList'
        })
    },
    methods: {
        ...mapActions([
            'setTerminalList'
        ]),
        indexMethod(index) {
            return index + 1
        },
        nodeChecked() {
            let self = this

            self.checkValues = []

            let nodes = self.$refs.tree.getCheckedNodes(true)
            let nodeAttr = null
            let nodeAttrTxt = null
            nodes.forEach((node) => {
                nodeAttr = self.$refs.tree.getNode(node.id);

                nodeAttrTxt = {}
                nodeAttrTxt.attr = nodeAttr.data.label
                nodeAttr = nodeAttr.parent
                nodeAttrTxt.device = nodeAttr.data.label
                nodeAttr = nodeAttr.parent
                nodeAttrTxt.group = nodeAttr.data.label
                
                self.checkValues.push(nodeAttrTxt)
            })
        },
        getList() {
            let self = this
            api.getTerminalList(self.formData.object_id)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.setTerminalList(res.data)
                    }
                })
                .catch(error => {

                })
        },
        getGroupList() {
            let self = this
            self.treeData = [];
            api.getGroupList(self.formData.object_id)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.getGroupInfo(res.data)
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
                        res.data.forEach((item) => {
                            item.attrs.forEach((attr) => {
                                self.deviceTypeList[attr.id] = attr.label
                            })
                        })
                        this.getGroupList()
                    }
                })
                .catch(error => {

                })
        },
        getDeviceAttrById(attrs) {
            let self = this
            let deviceAttrList = []
            let deviceAttr = null
            
            attrs.forEach((attr) => {
                deviceAttr = {}
                deviceAttr.id = "attr_" + attr.id
                deviceAttr.rid = attr.id
                deviceAttr.attrtype_id = attr.attrtype_id
                deviceAttr.label = self.deviceTypeList[attr.attrtype_id]
                deviceAttr.plcAddress = ""

                if (typeof self.terminalAttr[attr.id] != "undefined") {
                    deviceAttr.plcAddress = self.terminalAttr[attr.id].plc_address
                }

                deviceAttrList.push(deviceAttr)
            })

            return deviceAttrList
        
        },
        getGroupInfo(data) {
            let self = this
            let treeData = []
            let promises = []
            let promise = null

            for (let j = 0; j < data.length; j++) {
                let tmp = {}
                tmp.id = data[j].id ? "group_" + data[j].id : 0
                tmp.label = data[j].name ? data[j].name : ""
                tmp.children = []
                
                if (tmp.id) {
                    promises.push(
                        new Promise((resolve, reject) => {
                            api.getDeviceList(this.formData.object_id, data[j].id)
                                .then(ret => {
                                    if (0 === ret.errorCode) {
                                        let tmpDev = null
                                        
                                        ret.data.forEach((item, index) => {                                    
                                            tmpDev = self.getDeviceInfo(item)
                                            tmp.children.push(tmpDev)
                                        })
                                    }

                                    resolve({})
                                })
                                .catch(error => {
                                    reject({})
                                })
                        })
                    )
                }
                
                treeData.push(tmp)
            }

            Promise.all(promises).then(rets => {
                self.$refs.tree.setCheckedKeys(self.checkKeys)

                self.nodeChecked()
            }).catch(error => {
                //console.log(error);
            });

            self.treeData = treeData
        },
        getDeviceInfo(data) {
            let tmp = {}
            tmp.id = data.id ? "device_" + data.id : 0
            tmp.label = data.code + data.index

            let tmpDev = {}
            tmpDev = this.getDeviceAttrById(data.attrs)
            tmp.children = tmpDev

            return tmp
        },
        addData(data, attrs) {
            let self = this
            api.addTerminal(data, attrs)
                .then(res => {
                    if (0 === res.errorCode) {
                        self.getList()
                    }
                })
                .catch(error => {

                })
        },
        updateData(data, attrs) {
            let self = this
            api.updateTerminal(data, attrs)
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
            api.deleteTerminal(id)
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
            let self = this

            this.dialogVisible = true

            this.$nextTick(() => {
                this.formData.id = row.id
                this.formData.name = row.name
                this.formData.code = row.code
                this.formData.ip = row.ip
                this.formData.port = row.port
                this.formData.attrs = row.attrs
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
        config(row) {
            let self = this

            this.formData.id = row.id
            this.formData.name = row.name
            this.formData.code = row.code
            this.formData.ip = row.ip
            this.formData.port = row.port

            this.terminalAttr = {}
            this.checkKeys = []
   
            row.attrs.forEach((attr) => {
                self.terminalAttr[attr.deviceattr_id] = attr
                self.checkKeys.push("attr_" + attr.deviceattr_id)
            })

            this.dialogAttrVisible = true

            this.getDeviceTypeList()
        },
        exportCfg(row) {
            api.exportTerminal(row.code)
        },
        cancelDialog(type) {
            if (0 === type) {// 终端信息
                this.dialogVisible = false
            } else {// 终端关联属性
                this.dialogAttrVisible = false
            }
        },
        closeDialog(type) {
            if (0 === type) {// 终端信息
                this.formData.id = 0
                this.$refs.formData.resetFields() 
            } else {// 终端关联属性
                this.checkValues = []
            }       
        },
        submit(formData) {
            let self = this

            if (formData != "") {// 终端信息
                this.$refs[formData].validate((valid) => {
                    if (valid) {
                        self.cancelDialog(0)

                        if (self.formData.id > 0) {
                            self.updateData(self.formData, self.formData.attrs)
                        } else {
                            self.addData(self.formData, [])
                        }
                    } else {
                        console.log('error submit!!')
                        return false
                    }
                });
            } else {// 终端关联属性
                let nodes = this.$refs.tree.getCheckedNodes()
                let attrs = []
                let node = null

                for (let i = 0; i < nodes.length; i++) {
                    node = nodes[i]
                    if (typeof node.attrtype_id != "undefined") {
                        attrs.push({
                            deviceattr_id: node.rid,
                            plc_address: node.plcAddress
                        });
                    }
                }

                self.cancelDialog(1);

                self.updateData(self.formData, attrs)
            }
            
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
.terminal-wrap .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
}
.terminal-wrap .el-dialog__body {
    display: flex;
    flex-direction: row;
}
.terminal-wrap .dlg-left {
    width: 350px;
    flex-grow: 0;
    border: 1px solid #dcdfe6;
    border-right: 0;
}
.terminal-wrap .dlg-right {
    flex-grow: 1;
    border: 1px solid #dcdfe6;
}
.terminal-wrap .dlg-title {
    height: 30px;
    line-height: 30px;
    background-color: #cccccc;
    padding-left: 5px;
}
.terminal-wrap .el-tree {
    height: 400px;
    overflow: auto;
}
.terminal-wrap .el-tree .el-input__inner {
    width: 120px;
    height: 20px;
}
</style>