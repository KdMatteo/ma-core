package cn.zucc.debug.macore.console.dbremote;

import cn.zucc.debug.frame.dbcreate.DBCreator;
import cn.zucc.debug.frame.dbcreate.TableCreator;
import cn.zucc.debug.frame.dbcreate.core.StandardDBCreator;
import cn.zucc.debug.frame.dbcreate.model.Field;
import cn.zucc.debug.macore.model.pojo.DeviceAttrType;
import org.apache.log4j.Logger;
import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-02-06 19:40
 * Description:数据库管理类
 **/
public class RemoteDataBaseManager {
    public static final Logger logger = Logger.getLogger(RemoteDataBaseManager.class);
    public static final String driver = "com.mysql.cj.jdbc.Driver";

    /***
     * 创建数据库
     * @param databaseName
     * @return
     */
    public static TableCreator createDataBase(String databaseName, String ip, int port, String account, String password) {
        DBCreator dbCreator = new StandardDBCreator(ip, port, account, password);
        return dbCreator.createDatabase(databaseName);
    }

    /**
     * 连接数据库
     */
    public static TableCreator connectDataBase(String ip, int port, String account, String password, String databaseName) {
        DBCreator dbCreator = new StandardDBCreator(ip, port, account, password);
        return dbCreator.chooseDatabase(databaseName);
    }

    /**
     * 删除数据库
     *
     * @param ip       ip
     * @param port     短口
     * @param account  账号
     * @param password 密码
     * @param databaseName 数据库名
     */
    public static boolean deleteDatabase(String ip, int port, String account, String password, String databaseName) {
        DBCreator dbCreator = new StandardDBCreator(ip, port, account, password);
        return dbCreator.deleteDatabase(databaseName);
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
        return tableCreator.addTableFields(tableName, getFieldsByAttrTypeList(deviceAttrTypeList));
    }

    private static Field[] getFieldsByAttrTypeList(List<DeviceAttrType> deviceAttrTypeList) {
        Field[] fields = new Field[deviceAttrTypeList.size()];
        for (int i = 0; i < deviceAttrTypeList.size(); i++) {
            Field field = new Field();
            DeviceAttrType attrType = deviceAttrTypeList.get(i);
            if (attrType != null) {
                field.setName(attrType.getFieldName());
                field.setType(attrType.getDataType());
                fields[i] = field;
            }
        }
        return fields;
    }
}

