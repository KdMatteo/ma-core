import Vue from 'vue'
import Vuex from 'vuex'
import login from './modules/login'
import deviceType from './modules/deviceType'
import deviceAttrType from './modules/deviceAttrType'
import object from './modules/object'
import group from './modules/group'
import device from './modules/device'
import dbSql from './modules/dbSql'
import user from './modules/user'
import host from './modules/host'
import terminal from './modules/terminal'

Vue.use(Vuex)

export default new Vuex.Store({
	modules: {
		login,
		deviceType,
		deviceAttrType,
		object,
		group,
		device,
		dbSql,
		user,
		host,
		terminal
	},
	strict: process.env.NODE_ENV !== 'production'
})
