<template>
    <div class="login-wrap">
        <div class="ms-title">后台管理系统</div>
        <div class="ms-login">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0">
                <el-form-item prop="account">
                    <el-input v-model="ruleForm.account" placeholder="账号"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="密码" v-model="ruleForm.password" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
import api from '../fetch/api'
import { mapActions } from 'vuex'

export default {
    data() {
        return {
            ruleForm: {
                account: 'root',
                password: '123456'
            },
            rules: {
                account: [
                    { required: true, message: '请输入账号', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        ...mapActions([
            'setLoginInfo'
        ]),
        submitForm (formName) {
            let self = this
            self.$refs[formName].validate((valid) => {
                if (valid) {
                    api.login(self.ruleForm.account, self.ruleForm.password)
                        .then(res => {
                            if (0 === res.errorCode) {
                                self.setLoginInfo(self.ruleForm)
                                self.$router.push('/main')
                            } else {
                                self.$alert(res.errorMessage)
                            }
                        })
                        .catch(error => {
                            self.$alert("登录失败")
                        })
                } else {
                    //return false;
                }
            });
        }
    }
}
</script>

<style>
@import "../../static/css/common.css";

.login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    background-color: #324157;
}
.login-wrap .ms-title {
    position: absolute;
    top:50%;
    width:100%;
    margin-top: -230px;
    text-align: center;
    font-size:30px;
    color: #fff;
}
.login-wrap .ms-login {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 300px;
    margin: -150px 0 0 -190px;
    padding: 40px;
    border-radius: 5px;
    background: #fff;
}
.login-wrap .login-btn {
    text-align: center;
}
.login-wrap .login-btn button {
    width: 100%;
    height: 36px;
}
</style>