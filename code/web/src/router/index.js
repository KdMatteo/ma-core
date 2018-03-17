import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    routes: [{
		path: '/login',
		name: 'login',
		component: resolve => {
			require.ensure(['@/views/Login.vue'], () => {
				resolve(require('@/views/Login.vue'))
			})
		}
	}, {
		path: '/main',
		redirect: '/main/host',
		name: 'main',
		component: resolve => {
			require.ensure(['@/views/Main.vue'], () => {
				resolve(require('@/views/Main.vue'))
			})
		},
		children: [{
			path: 'host',
			name: 'host',
			component: resolve => {
				require.ensure(['@/views/Main/Host.vue'], () => {
					resolve(require('@/views/Main/Host.vue'))
				})
			}
		}, {
			path: 'deviceType',
			name: 'deviceType',
			component: resolve => {
				require.ensure(['@/views/Main/DeviceType.vue'], () => {
					resolve(require('@/views/Main/DeviceType.vue'))
				})
			}
		}, {
			path: 'dbSQL',
			name: 'dbSQL',
			component: resolve => {
				require.ensure(['@/views/Main/DBSql.vue'], () => {
					resolve(require('@/views/Main/DBSql.vue'))
				})
			}
		}, {
			path: 'user',
			name: 'user',
			component: resolve => {
				require.ensure(['@/views/Main/User.vue'], () => {
					resolve(require('@/views/Main/User.vue'))
				})
			}
		}, {
			path: 'deviceType/:id/deviceAttrType',
			name: 'deviceAttrType',
			component: resolve => {
				require.ensure(['@/views/Main/DeviceAttrType.vue'], () => {
					resolve(require('@/views/Main/DeviceAttrType.vue'))
				})
			}
		}, {
			path: 'host/:id/object',
			name: 'object',
			component: resolve => {
				require.ensure(['@/views/Main/Object.vue'], () => {
					resolve(require('@/views/Main/Object.vue'))
				})
			}
		}, {
			path: 'host/:id/object/:oid/group',
			name: 'group',
			component: resolve => {
				require.ensure(['@/views/Main/Group.vue'], () => {
					resolve(require('@/views/Main/Group.vue'))
				})
			}
		}, {
			path: 'host/:id/object/:oid/group/:gid/device',
			name: 'device',
			component: resolve => {
				require.ensure(['@/views/Main/Device.vue'], () => {
					resolve(require('@/views/Main/Device.vue'))
				})
			}
		}, {
			path: 'host/:id/object/:oid/terminal',
			name: 'terminal',
			component: resolve => {
				require.ensure(['@/views/Main/Terminal.vue'], () => {
					resolve(require('@/views/Main/Terminal.vue'))
				})
			}
		}]
	}, {
		path: '/', 
        redirect: '/login'
	}]
})
