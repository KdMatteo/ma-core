import * as types from '../../types'

const state = {
    deviceAttrTypeList: []
}

const actions = {
	// 设置设备属性类型列表
    setDeviceAttrTypeList({ commit }, res) {
		commit(types.SET_DEVICE_ATTR_TYPE_LIST, res)
    }
}

const getters = {
    getDeviceAttrTypeList: state => state.deviceAttrTypeList
}

const mutations = {
	[types.SET_DEVICE_ATTR_TYPE_LIST](state, res) {
		state.deviceAttrTypeList = res
	}
}

export default {
    state,
    actions,
    getters,
    mutations
}
