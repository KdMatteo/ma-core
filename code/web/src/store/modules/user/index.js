import * as types from '../../types'

const state = {
    userList: []
}

const actions = {
	// 设置用户列表
    setUserList({ commit }, res) {
		commit(types.SET_USER_LIST, res)
    }
}

const getters = {
    getUserList: state => state.userList
}

const mutations = {
	[types.SET_USER_LIST](state, res) {
		state.userList = res
	}
}

export default {
    state,
    actions,
    getters,
    mutations
}
