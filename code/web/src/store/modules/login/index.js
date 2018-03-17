import * as types from '../../types'

const state = {
    // 登录状态
    loginStatus: JSON.parse(localStorage.getItem('loginStatus')) || false,
    // 登录信息
    loginInfo: JSON.parse(localStorage.getItem('loginInfo')) || {}
}

const actions = {
	// 设置登录信息
    setLoginInfo({ commit }, res) {
        localStorage.setItem('loginStatus', true)
        localStorage.setItem('loginInfo', JSON.stringify(res))
        commit(types.SET_LOGIN_INFO, res)
        commit(types.SET_LOGIN_STATUS, true)
    },

    // 清除登录信息
    clearLoginInfo({ commit }) {
        localStorage.removeItem('loginStatus')
        localStorage.removeItem('loginInfo')
        commit(types.SET_LOGIN_STATUS, false)
        commit(types.SET_LOGIN_INFO, {})
    }
}

const getters = {
	getLoginStatus: state => state.loginStatus,
    getLoginInfo: state => state.loginInfo
}

const mutations = {
	[types.SET_LOGIN_STATUS](state, status) {
        state.loginStatus = status
    },
    [types.SET_LOGIN_INFO](state, res) {
        state.loginInfo = res
    }
}

export default {
    state,
    actions,
    getters,
    mutations
}