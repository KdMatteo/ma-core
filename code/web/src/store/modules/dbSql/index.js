import * as types from '../../types'

const state = {
    dbSqlList: [],
	dbSqlTotal: 0
}

const actions = {
	// 设置SQL列表
    setDBSqlList({ commit }, res) {
		commit(types.SET_DBSQL_LIST, res)
    },
	// 设置SQL总数
    setDBSqlTotal({ commit }, res) {
		commit(types.SET_DBSQL_TOTAL, res)
    }
}

const getters = {
    getDBSqlList: state => state.dbSqlList,
	getDBSqlTotal: state => state.dbSqlTotal
}

const mutations = {
	[types.SET_DBSQL_LIST](state, res) {
		state.dbSqlList = res
	},
	[types.SET_DBSQL_TOTAL](state, res) {
		state.dbSqlTotal = res
	}
}

export default {
    state,
    actions,
    getters,
    mutations
}
