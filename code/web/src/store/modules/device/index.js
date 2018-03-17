import * as types from '../../types'

const state = {
    deviceList: []
}

const actions = {
	// 设置设备列表
    setDeviceList({ commit }, res) {
		commit(types.SET_DEVICE_LIST, res)
    }
}

const getters = {
    getDeviceList: state => state.deviceList
}

const mutations = {
	[types.SET_DEVICE_LIST](state, res) {
		state.deviceList = res
	}
}

export default {
    state,
    actions,
    getters,
    mutations
}
