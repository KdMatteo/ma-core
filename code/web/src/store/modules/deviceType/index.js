import * as types from '../../types'

const state = {
    deviceTypeList: []
}

const actions = {
	// 设置设备类型列表
    setDeviceTypeList({ commit }, res) {
		commit(types.SET_DEVICE_TYPE_LIST, res)
    }
}

const getters = {
    getDeviceTypeList: state => state.deviceTypeList
}

const mutations = {
	[types.SET_DEVICE_TYPE_LIST](state, res) {
		state.deviceTypeList = res
	}
}

export default {
    state,
    actions,
    getters,
    mutations
}
