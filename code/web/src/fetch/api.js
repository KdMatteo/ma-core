import axios from 'axios'

// api 路径
const HOST = 'http://120.78.74.186/maCore'
//const HOST = 'http://192.168.1.170:8080/maCore'

function fetch(url, data, method = 'post') {
    return new Promise((resolve, reject) => {
		axios({
			method: method,
			url: HOST + url,
			data: JSON.stringify(data),
			headers: {
				"Content-Type": "application/json"
			}
		})
		.then((response) => {
			resolve(response.data)
		})
		.catch((error) => {
			reject(error)
		})
    })
}

export default {

	// 登录
    login(account, password) {
        return fetch("/login", {
			account: account,
			password: password
		})
    },

	// 获取设备类型列表
    getDeviceTypeList() {
        return fetch("/deviceType/list", {})
    },
	// 新增设备类型
    addDeviceType(deviceType) {
        return fetch("/deviceType/add", {
			name: deviceType.name,
			table_name: deviceType.table_name,
			multi: parseInt(deviceType.multi, 10)
		})
    },
	// 修改设备类型
	updateDeviceType(deviceType) {
        return fetch("/deviceType/update", {
			id: deviceType.id,
			name: deviceType.name,
			table_name: deviceType.table_name,
			multi: parseInt(deviceType.multi, 10)
		})
    },
	// 删除设备类型
	deleteDeviceType(id) {
		return fetch("/deviceType/delete", {
			id: id
		})
	},

	// 获取设备属性类型列表
    getDeviceAttrTypeList(deviceTypeId) {
        return fetch("/deviceAttrType/list", {
			devicetype_id: deviceTypeId
		})
    },
	// 新增设备属性类型
    addDeviceAttrType(deviceAttrType) {
        return fetch("/deviceAttrType/add", {
			devicetype_id: deviceAttrType.devicetype_id,
			name: deviceAttrType.name,
			field_name: deviceAttrType.field_name,
			label: deviceAttrType.label,
			data_type: deviceAttrType.data_type
		})
    },
	// 修改设备属性类型
	updateDeviceAttrType(deviceAttrType) {
        return fetch("/deviceAttrType/update", {
			id: deviceAttrType.id,
			name: deviceAttrType.name,
			field_name: deviceAttrType.field_name,
			label: deviceAttrType.label,
			data_type: deviceAttrType.data_type
		})
    },
	// 删除设备属性类型
	deleteDeviceAttrType(id) {
		return fetch("/deviceAttrType/delete", {
			id: id
		})
	},

	// 获取水厂列表
	getObjectList(hostId, page) {
		return fetch("/object/list", {
			host_id: hostId,
			page: {
				size: page.size,
				index: page.index
			},
			search: {}
		})
	},
	// 新增水厂（库）
    addObject(data) {
        return fetch("/object/add", {
			host_id: data.host_id,
			database_name: data.database_name,
			name: data.name,
			address: data.address,
			linkman: data.linkman,
			mobile: data.mobile
		})
    },
	// 修改水厂（库）
    updateObject(object) {
        return fetch("/object/update", {
			id: object.id,
			name: object.name,
			address: object.address,
			linkman: object.linkman,
			mobile: object.mobile
		})
    },
	// 删除水厂（库）
	deleteObject(id) {
		return fetch("/object/delete", {
			id: id
		})
	},

	// 获取运行单元列表（数据）
	getGroupList(objectId) {
		return fetch("/group/list", {
			object_id: objectId
		})
	},
	// 新增运行单元（数据）
    addGroup(objectId, data) {
        return fetch("/group/add", {
			object_id: objectId,
			name: data.name
		})
    },
	// 修改运行单元（数据）
    updateGroup(data) {
        return fetch("/group/update", {
			id: data.id,
			name: data.name
		})
    },
	// 删除运行单元（数据）
	deleteGroup(id) {
		return fetch("/group/delete", {
			id: id
		})
	},

	// 获取设备列表（表）
	getDeviceList(objectId, groupId) {
		return fetch("/device/list", {
			object_id: objectId,
			group_id: groupId
		})
	},
	// 新增设备（表）
    addDevice(objectId, groupId, data) {
		let param = {
			object_id: objectId,
			group_id: groupId,
			devicetype_id: data.devicetype_id,
			attrs: []
		}

		data.attrs.forEach((attr) => {
			param.attrs.push({
				attrtype_id: attr
			})
		})

        return fetch("/device/add", param)
    },
	// 修改设备（表）
    updateDevice(data) {
		let param = {
			id: data.id,
			attrs: []
		}

		data.attrs.forEach((attr) => {
			param.attrs.push({
				attrtype_id: attr
			})
		})

        return fetch("/device/update", param)
    },
	// 删除设备（表）
	deleteDevice(id) {
		return fetch("/device/delete", {
			id: id
		})
	},

	// 获取SQL列表（数据）
	getDBSqlList(page) {
		return fetch("/dbSql/list", {
			page: {
				size: page.size,
				index: page.index
			},
			search: {}
		})
	},
	// 新增SQL（数据）
    addDBSql(data) {
        return fetch("/dbSql/add", {
			sql_string: data.sql_string
		})
    },
	
	// 获取用户列表（数据）
	getUserList() {
		return fetch("/user/list", {})
	},
	// 新增用户（数据）
    addUser(data) {
        return fetch("/user/add", {
			account: data.account,
			password: data.password
		})
    },
	// 修改用户（数据）
    updateUser(data) {
        return fetch("/user/update", {
			id: data.id,
			account: data.account,
			password: data.password
		})
    },
	// 删除用户（数据）
	deleteUser(id) {
		return fetch("/user/delete", {
			id: id
		})
	},

	// 获取主机列表（数据）
	getHostList() {
		return fetch("/host/list", {})
	},
	// 新增主机（数据）
    addHost(data) {
        return fetch("/host/add", {
			ip: data.ip,
			port: data.port,
			account: data.account,
			password: data.password
		})
    },
	// 修改主机（数据）
    updateHost(data) {
        return fetch("/host/update", {
			id: data.id,
			ip: data.ip,
			port: data.port,
			account: data.account,
			password: data.password
		})
    },
	// 删除主机（数据）
	deleteHost(id) {
		return fetch("/host/delete", {
			id: id
		})
	},

	// 获取终端列表（数据）
	getTerminalList(objectId) {
		return fetch("/terminal/list", {
			object_id: objectId
		})
	},
	// 新增终端（数据）
    addTerminal(data, attrs) {
        return fetch("/terminal/add", {
			object_id: data.object_id,
			name: data.name,
			code: data.code,
			ip: data.ip,
			port: data.port,
			attrs: attrs
		})
    },
	// 修改终端（数据）
    updateTerminal(data, attrs) {
        return fetch("/terminal/update", {
			id: data.id,
			name: data.name,
			code: data.code,
			ip: data.ip,
			port: data.port,
			attrs: attrs
		})
    },
	// 删除终端（数据）
	deleteTerminal(id) {
		return fetch("/terminal/delete", {
			id: id
		})
	},
	// 导出终端（文件）
	exportTerminal(code) {
		window.open(HOST + "/terminal/export?code=" + code)
	}
}