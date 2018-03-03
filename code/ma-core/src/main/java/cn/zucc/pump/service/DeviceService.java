package cn.zucc.pump.service;

import cn.zucc.pump.database.LocalDataBaseManager;
import cn.zucc.pump.database.RemoteDataBaseManager;
import cn.zucc.pump.mapper.DeviceAttrMapper;
import cn.zucc.pump.mapper.DeviceAttrTypeMapper;
import cn.zucc.pump.mapper.DeviceTypeMapper;
import cn.zucc.pump.mapper.ObjectDeviceMapper;
import cn.zucc.pump.pojo.*;
import cn.zucc.pump.utils.TextUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-02-07 9:26
 * Description:设备
 **/
public class DeviceService {
    public static final Logger logger = Logger.getLogger(DeviceService.class);

    /**
     * 获取所有的设备类型供选择
     */
    public List<DeviceType> findAllDeviceType() {
        List<DeviceType> deviceTypeList = LocalDataBaseManager.getInstance().getMapper(DeviceTypeMapper.class).getAllList();
        if (!TextUtil.isEmpty(deviceTypeList)) {
            logger.info("findAllDeviceType success");
            return deviceTypeList;
        }
        logger.error("no device_type data");
        return null;
    }

    /**
     * 查询所有AttrType
     */
    public List<DeviceAttrType> findAllAttrType() {
        List<DeviceAttrType> deviceAttrTypeList = LocalDataBaseManager.getInstance().getMapper(DeviceAttrTypeMapper.class).getAllList();
        if (!TextUtil.isEmpty(deviceAttrTypeList)) {
            logger.info("findAllAttrType success");
            return deviceAttrTypeList;
        }
        logger.error("no device_attr_type data");
        return null;
    }

    /**
     * 新建一个设备，指定其所属取水点和设备类型
     * @param objectId  所属取水点id
     * @param deviceTypeId 设备类型id
     */
    public void createDevice(Integer objectId, Integer deviceTypeId) {
        ObjectDevice objectDevice = new ObjectDevice();
        objectDevice.setObject_id(objectId);
        objectDevice.setDevicetype_id(deviceTypeId);
        DeviceType deviceType = LocalDataBaseManager.getInstance().getMapper(DeviceTypeMapper.class).queryById(deviceTypeId);
        if (deviceType != null) {
            String tableName = deviceType.getTable_name();
            objectDevice.setCode(tableName);
            int count = LocalDataBaseManager.getInstance().getMapper(ObjectDeviceMapper.class).count(objectId, deviceTypeId);
            objectDevice.setIndex(count + 1);
            int isSuccess = LocalDataBaseManager.getInstance().getMapper(ObjectDeviceMapper.class).add(objectDevice);
            LocalDataBaseManager.getInstance().commit();
            if (isSuccess > 0) {
                logger.info("createDevice success");
            } else {
                logger.error("createDevice fail");
            }
            LocalDataBaseManager.getInstance().close();
        } else {
            logger.error("no deviceType");
        }

    }

    /**
     * 删除设备及其对应数据表
     *
     * @param deviceId
     */
    public void deleteDevice(Integer deviceId) {
        int isSuccess = LocalDataBaseManager.getInstance().getMapper(ObjectDeviceMapper.class).deleteById(deviceId);

        if (isSuccess > 0) {
            logger.info("deleteDevice success");
        } else {
            logger.error("deleteDevice fail");
        }
        LocalDataBaseManager.getInstance().commit();
        LocalDataBaseManager.getInstance().close();
    }

    /**
     * 创建设备对应属性，并指定所属的设备和属性类型
     *
     * @param deviceId
     * @param attrTypeId
     * @param plcAddress
     */
    public void createDeviceAttr(Integer deviceId, Integer attrTypeId, String plcAddress) {
        DeviceAttr deviceAttr = new DeviceAttr();
        deviceAttr.setDevice_id(deviceId);
        deviceAttr.setAttrtype_id(attrTypeId);
        deviceAttr.setPlc_address(plcAddress);
        int isSuccess = LocalDataBaseManager.getInstance().getMapper(DeviceAttrMapper.class).add(deviceAttr);
        if (isSuccess > 0) {
            logger.info("createDeviceAttr success");
            LocalDataBaseManager.getInstance().commit();
            LocalDataBaseManager.getInstance().close();
        } else {
            logger.error("createDeviceAttr fail");
        }
    }

    /**
     * 删除属性
     *
     * @param attrId
     */
    public void deleteDeviceAttr(Integer attrId) {
        int isSuccess = LocalDataBaseManager.getInstance().getMapper(DeviceAttrMapper.class).deleteById(attrId);
        if (isSuccess > 0) {
            logger.info("deleteDeviceAttr success");
            LocalDataBaseManager.getInstance().commit();
            LocalDataBaseManager.getInstance().close();
        } else {
            logger.error("createDeviceAttr fail");
        }


    }

    /**
     * 修改属性的plcAddress值
     *
     * @param attrId
     * @param plcAddress
     */
    public void changeAttr(Integer attrId, String plcAddress) {
        int isSuccess = LocalDataBaseManager.getInstance().getMapper(DeviceAttrMapper.class).updatePlcAddress(attrId, plcAddress);
        if (isSuccess > 0) {
            logger.info("changeAttr success");
            LocalDataBaseManager.getInstance().commit();
            LocalDataBaseManager.getInstance().close();
        } else {
            logger.error("changeAttr fail");
        }
    }

    /**
     * 根据设备id创建新表，和建库不同的是，新建object和建库同步，但是新建device后还要添加指定attr，
     * 而且中途可能大量修改，所以建表与其异步，指定完成之后可以执行建表语句，且建表前增加删表
     *
     * @param deviceId
     */
    public void createTable(Integer deviceId) {
        HostInfo hostInfo = LocalDataBaseManager.getHostInfo(deviceId);
        if (hostInfo == null) {
            logger.error("get host info error");
            return;
        }
        //拼接字段名和类型
        List<DeviceAttr> deviceAttrList = LocalDataBaseManager.getInstance().getMapper(DeviceAttrMapper.class).queryByDeviceId(deviceId);
        if (TextUtil.isEmpty(deviceAttrList)) {
            logger.error("no device_attr with device_id = " + deviceId);
            return;
        }
        List<DeviceAttrType> deviceAttrTypeList = new ArrayList<DeviceAttrType>();
        for (int i = 0; i < deviceAttrList.size(); i++) {
            DeviceAttrType deviceAttrType = LocalDataBaseManager.getInstance().getMapper(DeviceAttrTypeMapper.class).queryById(deviceAttrList.get(i).getAttrtype_id());
            if (deviceAttrType == null) {
                logger.error("no device_attr_type with id = " + deviceAttrList.get(i).getAttrtype_id());
                return;
            }
            deviceAttrTypeList.add(deviceAttrType);
        }
        Connection connection = RemoteDataBaseManager.connectDataBase(hostInfo.getIp(), hostInfo.getPort(), hostInfo.getAccount(), hostInfo.getPassword(), hostInfo.getDatabaseName());
        if (connection == null) {
            logger.error("connect database fail");
            return;
        }
        RemoteDataBaseManager.deleteTable(connection, hostInfo.getTableName());
        RemoteDataBaseManager.createTable(connection, hostInfo.getTableName(), deviceAttrTypeList);
        logger.info("createTable success");
    }

    /**
     * 对于改动量不大且需要保留数据的情况可能需要用到这个接口，用于修改表结构
     * 删除给定字段，新增给定字段
     *
     * @param deviceId
     * @param addAttrIds 新增字段,可为null
     * @param delAttrIds 删除字段,可为null
     */
    public void changeTable(Integer deviceId, List<Integer> addAttrIds, List<Integer> delAttrIds) {
        HostInfo hostInfo = LocalDataBaseManager.getHostInfo(deviceId);
        if (hostInfo == null) {
            logger.error("get host info error");
            return;
        }
        List<DeviceAttrType> delAttrList = new ArrayList<DeviceAttrType>();
        List<DeviceAttrType> addAttrList = new ArrayList<DeviceAttrType>();
        if (!TextUtil.isEmpty(delAttrIds)) {
            for (int i = 0; i < delAttrIds.size(); i++) {
                DeviceAttrType deviceAttrType = LocalDataBaseManager.getInstance().getMapper(DeviceAttrTypeMapper.class).queryById(delAttrIds.get(i));
                delAttrList.add(deviceAttrType);
            }
        }
        if (!TextUtil.isEmpty(addAttrIds)) {
            for (int i = 0; i < addAttrIds.size(); i++) {
                DeviceAttrType deviceAttrType = LocalDataBaseManager.getInstance().getMapper(DeviceAttrTypeMapper.class).queryById(addAttrIds.get(i));
                addAttrList.add(deviceAttrType);
            }
        }
        Connection connection = RemoteDataBaseManager.connectDataBase(hostInfo.getIp(), hostInfo.getPort(), hostInfo.getAccount(), hostInfo.getPassword(), hostInfo.getDatabaseName());
        RemoteDataBaseManager.deleteTableField(delAttrList, connection, hostInfo.getTableName());
        RemoteDataBaseManager.addTableField(addAttrList, connection, hostInfo.getTableName());
        logger.info("changeTable success");
    }
}
