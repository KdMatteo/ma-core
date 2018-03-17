import * as types from '../../types'

const state = {
    hostList: []
}

const actions = {
	// 设置主机列表
    setHostList({ commit }, res) {
		commit(types.SET_HOST_LIST, res)
    }
}

const getters = {
    getHostList: state => state.hostList
}

const mutations = {
	[types.SET_HOST_LIST](state, res) {
		state.hostList = res
	}
}

export default {
    state,
    actions,
    getters,
    mutations
}
