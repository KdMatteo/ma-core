package cn.zucc.debug.macore.console.util;

import cn.zucc.debug.frame.dbcreate.DBCreator;
import cn.zucc.debug.frame.dbcreate.TableCreator;
import cn.zucc.debug.frame.dbcreate.core.StandardDBCreator;
import cn.zucc.debug.frame.dbcreate.model.Field;
import cn.zucc.debug.macore.model.pojo.DeviceAttrType;
import cn.zucc.debug.macore.model.pojo.Host;
import org.apache.log4j.Logger;
import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-02-06 19:40
 * Description:数据库管理类
 * 调用frame的dbCreator和tableCreator去完成远程数据库管理工作
 * 将参数层封装成项目pojo对象
 *
 **/
public class RemoteDataBaseManager {
    public static final Logger logger = Logger.getLogger(RemoteDataBaseManager.class);
    public static final String driver = "com.mysql.cj.jdbc.Driver";

    /***
     * 创建数据库
     * @param databaseName
     * @return
     */
    public static TableCreator createDataBase(Host host, String databaseName) {
        if (host != null) {
            DBCreator dbCreator = new StandardDBCreator(host.getIp(), host.getPort(), host.getAccount(), host.getPassword());
            return dbCreator.createDatabase(databaseName);
        }
        return null;
    }

    /**
     * 连接数据库
     */
    public static TableCreator connectDataBase(Host host, String databaseName) {
        if (host != null) {
            DBCreator dbCreator = new StandardDBCreator(host.getIp(), host.getPort(), host.getAccount(), host.getPassword());
            return dbCreator.chooseDatabase(databaseName);
        }
        return null;
    }

    /**
     * 删除数据库
     * @param databaseName 数据库名
     */
    public static boolean deleteDatabase(Host host, String databaseName) {
        if (host != null) {
            DBCreator dbCreator = new StandardDBCreator(host.getIp(), host.getPort(), host.getAccount(), host.getPassword());
            return dbCreator.deleteDatabase(databaseName);
        }
        return false;
    }

    /**
     * 删除表字段
     *
     * @param tableCreator  远程数据库的创建器
     * @param tableName   表名
     * @param delAttrList 要删除的字段
     */
    public static boolean deleteTableField(TableCreator tableCreator, String tableName, List<DeviceAttrType> delAttrList) {
        String[] fieldNames = new String[delAttrList.size()];
        for (int i = 0; i < delAttrList.size(); i++) {
            fieldNames[i] = delAttrList.get(i).getFieldName();
        }
        return tableCreator.deleteTableFields(tableName, fieldNames);
    }

    /**
     * 增加表字段
     *
     * @param tableCreator  远程数据库的创建器
     * @param tableName   表名
     * @param addAttrList 要增加的字段
     */
    public static boolean addTableField(TableCreator tableCreator, String tableName, List<DeviceAttrType> addAttrList) {
        return tableCreator.addTableFields(tableName, getFieldsByAttrTypeList(addAttrList));
    }

    /**
     * 删除表
     *
     * @param tableCreator 远程数据库的连接
     * @param tableName  表名
     */
    public static boolean deleteTable(TableCreator tableCreator, String tableName) {
        return tableCreator.deleteTable(tableName);
    }

    /**
     * 创建表
     *
     * @param tableCreator         远程数据库连接
     * @param tableName          表名
     * @param deviceAttrTypeList 表字段
     */
    public static boolean createTable(TableCreator tableCreator, String tableName, List<DeviceAttrType> deviceAttrTypeList) {
        Field idField = new Field();
        idField.setType("int");
        idField.setName("c_id");
        idField.setPk("true");
        idField.setAuto("true");
        Field createTimeField = new Field();
        createTimeField.setName("c_create_time");
        createTimeField.setType("datetime");
        return tableCreator.createTable(tableName, getFieldsByAttrTypeList(deviceAttrTypeList, idField, createTimeField));
    }

    /**
     * 获取字段数组
     * @param deviceAttrTypeList
     * @return
     */
    private static Field[] getFieldsByAttrTypeList(List<DeviceAttrType> deviceAttrTypeList, Field... commonFields) {
        int length = commonFields.length;
        Field[] fields = new Field[deviceAttrTypeList.size() + length];
        for (int i = 0; i < length; i++) {
            Field field = new Field();
            fields[i] = commonFields[i];
        }
        for (int i = 0; i < deviceAttrTypeList.size(); i++) {
            Field field = new Field();
            DeviceAttrType attrType = deviceAttrTypeList.get(i);
            if (attrType != null) {
                field.setName(attrType.getFieldName());
                field.setType(attrType.getDataType());
                fields[i + length] = field;
            }
        }
        return fields;
    }
}

