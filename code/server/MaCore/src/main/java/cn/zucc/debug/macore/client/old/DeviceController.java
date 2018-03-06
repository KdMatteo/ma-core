//package cn.zucc.debug.macore.client.controller;
//
//import cn.zucc.debug.macore.console.dbremote.RemoteDataBaseManager;
//import cn.zucc.debug.macore.model.service.*;
//import cn.zucc.debug.macore.model.pojo.*;
//import cn.zucc.debug.macore.console.util.TextUtil;
//import org.apache.log4j.Logger;
//
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author szh
// * @Email 754456231@qq.com
// * @Time 2018-02-07 9:26
// * Description:设备
// **/
//public class DeviceController {
//    public static final Logger logger = Logger.getLogger(DeviceController.class);
//
//    DeviceTypeService deviceTypeMapper;
//    DeviceAttrTypeService deviceAttrTypeService;
//    ObjectDeviceService objectDeviceService;
//    DeviceAttrService deviceAttrService;
//    HostService hostService;
//    ObjectService objectService;
//
//    /**
//     * 获取所有的设备类型供选择
//     */
//    public List<DeviceType> findAllDeviceType() {
//        List<DeviceType> deviceTypeList = deviceTypeMapper.getAllList();
//        if (!TextUtil.isEmpty(deviceTypeList)) {
//            logger.info("findAllDeviceType success");
//            return deviceTypeList;
//        }
//        logger.error("no device_type data");
//        return null;
//    }
//
//    /**
//     * 查询所有AttrType
//     */
//    public List<DeviceAttrType> findAllAttrType() {
//        List<DeviceAttrType> deviceAttrTypeList = deviceAttrTypeService.getAllList();
//        if (!TextUtil.isEmpty(deviceAttrTypeList)) {
//            logger.info("findAllAttrType success");
//            return deviceAttrTypeList;
//        }
//        logger.error("no device_attr_type data");
//        return null;
//    }
//
//    /**
//     * 新建一个设备，指定其所属取水点和设备类型
//     *
//     * @param objectId     所属取水点id
//     * @param deviceTypeId 设备类型id
//     */
//    public void createDevice(Integer objectId, Integer deviceTypeId) {
//        ObjectDevice objectDevice = new ObjectDevice();
//        objectDevice.setObjectId(objectId);
//        objectDevice.setDevicetypeId(deviceTypeId);
//        DeviceType deviceType = deviceTypeMapper.queryById(deviceTypeId);
//        if (deviceType != null) {
//            String tableName = deviceType.getTableName();
//            objectDevice.setCode(tableName);
//            int count = objectDeviceService.count(objectId, deviceTypeId);
//            objectDevice.setIndex(count + 1);
//            int isSuccess = objectDeviceService.add(objectDevice);
//            if (isSuccess > 0) {
//                logger.info("createDevice success");
//            } else {
//                logger.error("createDevice fail");
//            }
//        } else {
//            logger.error("no deviceType");
//        }
//
//    }
//
//    /**
//     * 删除设备及其对应数据表
//     *
//     * @param deviceId
//     */
//    public void deleteDevice(Integer deviceId) {
//        int isSuccess = objectDeviceService.deleteById(deviceId);
//
//        if (isSuccess > 0) {
//            logger.info("deleteDevice success");
//        } else {
//            logger.error("deleteDevice fail");
//        }
//    }
//
//    /**
//     * 创建设备对应属性，并指定所属的设备和属性类型
//     *
//     * @param deviceId
//     * @param attrTypeId
//     * @param plcAddress
//     */
//    public void createDeviceAttr(Integer deviceId, Integer attrTypeId, String plcAddress) {
//        DeviceAttr deviceAttr = new DeviceAttr();
//        deviceAttr.setDeviceId(deviceId);
//        deviceAttr.setAttrtypeId(attrTypeId);
//        deviceAttr.setPlcAddress(plcAddress);
//        int isSuccess = deviceAttrService.add(deviceAttr);
//        if (isSuccess > 0) {
//            logger.info("createDeviceAttr success");
//        } else {
//            logger.error("createDeviceAttr fail");
//        }
//    }
//
//    /**
//     * 删除属性
//     *
//     * @param attrId
//     */
//    public void deleteDeviceAttr(Integer attrId) {
//        int isSuccess = deviceAttrService.deleteById(attrId);
//        if (isSuccess > 0) {
//            logger.info("deleteDeviceAttr success");
//        } else {
//            logger.error("createDeviceAttr fail");
//        }
//
//
//    }
//
//    /**
//     * 修改属性的plcAddress值
//     *
//     * @param attrId
//     * @param plcAddress
//     */
//    public void changeAttr(Integer attrId, String plcAddress) {
//        int isSuccess = deviceAttrService.updatePlcAddress(attrId, plcAddress);
//        if (isSuccess > 0) {
//            logger.info("changeAttr success");
//        } else {
//            logger.error("changeAttr fail");
//        }
//    }
//
//    /**
//     * 根据设备id创建新表，和建库不同的是，新建object和建库同步，但是新建device后还要添加指定attr，
//     * 而且中途可能大量修改，所以建表与其异步，指定完成之后可以执行建表语句，且建表前增加删表
//     *
//     * @param deviceId
//     */
//    public void createTable(Integer deviceId) {
//        Host hostInfo = getHostInfo(deviceId);
//        if (hostInfo == null) {
//            logger.error("get host info error");
//            return;
//        }
//        //拼接字段名和类型
//        List<DeviceAttr> deviceAttrList = deviceAttrService.queryByDeviceId(deviceId);
//        if (TextUtil.isEmpty(deviceAttrList)) {
//            logger.error("no device_attr with device_id = " + deviceId);
//            return;
//        }
//        List<DeviceAttrType> deviceAttrTypeList = new ArrayList<DeviceAttrType>();
//        for (int i = 0; i < deviceAttrList.size(); i++) {
//            DeviceAttrType deviceAttrType = deviceAttrTypeService.queryById(deviceAttrList.get(i).getAttrtype_id());
//            if (deviceAttrType == null) {
//                logger.error("no device_attr_type with id = " + deviceAttrList.get(i).getAttrtype_id());
//                return;
//            }
//            deviceAttrTypeList.add(deviceAttrType);
//        }
//        Connection connection = RemoteDataBaseManager.connectDataBase(hostInfo.getIp(), hostInfo.getPort(), hostInfo.getAccount(), hostInfo.getPassword(), hostInfo.getDatabaseName());
//        if (connection == null) {
//            logger.error("connect database fail");
//            return;
//        }
//        RemoteDataBaseManager.deleteTable(connection, hostInfo.getTableName());
//        RemoteDataBaseManager.createTable(connection, hostInfo.getTableName(), deviceAttrTypeList);
//        logger.info("createTable success");
//    }
//
//    /**
//     * 对于改动量不大且需要保留数据的情况可能需要用到这个接口，用于修改表结构
//     * 删除给定字段，新增给定字段
//     *
//     * @param deviceId
//     * @param addAttrIds 新增字段,可为null
//     * @param delAttrIds 删除字段,可为null
//     */
//    public void changeTable(Integer deviceId, List<Integer> addAttrIds, List<Integer> delAttrIds) {
//        HostInfo hostInfo = getHostInfo(deviceId);
//        if (hostInfo == null) {
//            logger.error("get host info error");
//            return;
//        }
//        List<DeviceAttrType> delAttrList = new ArrayList<DeviceAttrType>();
//        List<DeviceAttrType> addAttrList = new ArrayList<DeviceAttrType>();
//        if (!TextUtil.isEmpty(delAttrIds)) {
//            for (int i = 0; i < delAttrIds.size(); i++) {
//                DeviceAttrType deviceAttrType = deviceAttrTypeService.queryById(delAttrIds.get(i));
//                delAttrList.add(deviceAttrType);
//            }
//        }
//        if (!TextUtil.isEmpty(addAttrIds)) {
//            for (int i = 0; i < addAttrIds.size(); i++) {
//                DeviceAttrType deviceAttrType = deviceAttrTypeService.queryById(addAttrIds.get(i));
//                addAttrList.add(deviceAttrType);
//            }
//        }
//        Connection connection = RemoteDataBaseManager.connectDataBase(hostInfo.getIp(), hostInfo.getPort(), hostInfo.getAccount(), hostInfo.getPassword(), hostInfo.getDatabaseName());
//        RemoteDataBaseManager.deleteTableField(delAttrList, connection, hostInfo.getTableName());
//        RemoteDataBaseManager.addTableField(addAttrList, connection, hostInfo.getTableName());
//        logger.info("changeTable success");
//    }
//
//    private Host getHostInfo(Integer deviceId){
//        Host hostInfo = new Host();
//        //获取表名
//        ObjectDevice objectDevice = objectDeviceService.queryById(deviceId);
//        if (objectDevice == null) {
//            logger.error("no tableName with deviceId = "+deviceId);
//            return null;
//        }
//        String code = objectDevice.getCode();
//        int index = objectDevice.getIndex();
//        //获取数据库名
//        WaterObject objectPO = objectService.queryById(objectDevice.getObjectId());
//        if(objectPO == null){
//            logger.error("no databaseName with object_id"+objectDevice.getObjectId());
//            return null;
//        }
//        hostInfo.setDatabaseName(objectPO.getDatabase_name());
//        //获取数据库服务器ip,port,account,password
//        Host host = hostService.queryById(objectPO.getHost_id());
//        if(host == null){
//            logger.error("no host info with host_id = "+objectPO.getHost_id());
//            return null;
//        }
//        hostInfo.setIp(host.getIp());
//        hostInfo.setPort(host.getPort());
//        hostInfo.setAccount(host.getAccount());
//        hostInfo.setPassword(host.getPassword());
//        //判断multi,拼接表名
//        DeviceType deviceType = deviceTypeMapper.queryById(objectDevice.getDevicetype_id());
//        if (deviceType == null){
//            logger.error("no device_type with device_type_id = "+objectDevice.getDevicetype_id());
//            return null;
//        }
//        int multi = deviceType.getMulti();
//        if (multi == 1) {
//            hostInfo.setTableName(code + index);
//        } else {
//            hostInfo.setTableName(deviceType.getTable_name());
//        }
//        return hostInfo;
//    }
//}
