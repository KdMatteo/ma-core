import * as types from '../../types'

const state = {
    groupList: []
}

const actions = {
	// 设置运行单元列表
    setGroupList({ commit }, res) {
		commit(types.SET_GROUP_LIST, res)
    }
}

const getters = {
    getGroupList: state => state.groupList
}

const mutations = {
	[types.SET_GROUP_LIST](state, res) {
		state.groupList = res
	}
}

export default {
    state,
    actions,
    getters,
    mutations
}
