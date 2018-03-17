import * as types from '../../types'

const state = {
    terminalList: []
}

const actions = {
	// 设置终端列表
    setTerminalList({ commit }, res) {
		commit(types.SET_TERMINAL_LIST, res)
    }
}

const getters = {
    getTerminalList: state => state.terminalList
}

const mutations = {
	[types.SET_TERMINAL_LIST](state, res) {
		state.terminalList = res
	}
}

export default {
    state,
    actions,
    getters,
    mutations
}
