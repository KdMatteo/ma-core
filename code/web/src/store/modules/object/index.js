import * as types from '../../types'

const state = {
    objectList: [],
	objectTotal: 0
}

const actions = {
	// 设置水厂列表
    setObjectList({ commit }, res) {
		commit(types.SET_OBJECT_LIST, res)
    },
	// 设置水厂总数
    setObjectTotal({ commit }, res) {
		commit(types.SET_OBJECT_TOTAL, res)
    }
}

const getters = {
    getObjectList: state => state.objectList,
	getObjectTotal: state => state.objectTotal
}

const mutations = {
	[types.SET_OBJECT_LIST](state, res) {
		state.objectList = res
	},
	[types.SET_OBJECT_TOTAL](state, res) {
		state.objectTotal = res
	}
}

export default {
    state,
    actions,
    getters,
    mutations
}
