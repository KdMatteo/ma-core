package cn.zucc.debug.macore.console.dbremote;

import cn.zucc.debug.macore.model.pojo.DeviceAttrType;
import cn.zucc.debug.macore.console.util.TextUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    public static Connection createDataBase(String databaseName, String ip, int port, String account, String password) {
        try {
            Connection connection = connectHost(ip, port, account, password);
            Statement statement = connection.createStatement();
            String createDatabase = "CREATE DATABASE " + databaseName;
            int isSuccess = statement.executeUpdate(createDatabase);
            if (isSuccess == 0) {
                logger.info("database create fail");
            } else {
                logger.info("database create success");
                return connection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 连接数据库服务器
     */
    public static Connection connectHost(String ip, int port, String account, String password) {
        try {
            String url = "jdbc:mysql://" + ip + ":" + port + "?characterEncoding=UTF-8&serverTimezone=UTC";
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, account, password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 连接数据库
     */
    public static Connection connectDataBase(String ip, int port, String account, String password, String database) {
        String url = "jdbc:mysql://" + ip + ":" + port + "/" + database + "?characterEncoding=UTF-8&serverTimezone=UTC";
        System.out.println(url);
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, account, password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除数据库
     *
     * @param ip       ip
     * @param port     短口
     * @param account  账号
     * @param password 密码
     * @param database 数据库名
     */
    public static void deleteDatabase(String ip, int port, String account, String password, String database) {
        Connection connection = connectHost(ip, port, account, password);
        String deleteDataBaseSql = "drop database IF EXISTS " + database + ";";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteDataBaseSql);
            logger.info("deleteDatabase success");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("deleteDatabase fail");
        }
    }

    /**
     * 删除表字段
     *
     * @param delAttrList 要删除的字段
     * @param connection  远程数据库的连接
     * @param tableName   表名
     */
    public static void deleteTableField(List<DeviceAttrType> delAttrList, Connection connection, String tableName) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            if (!TextUtil.isEmpty(delAttrList)) {
                for (int i = 0; i < delAttrList.size(); i++) {
                    String delAttrSql = "ALTER TABLE " + tableName + " DROP " + delAttrList.get(i).getFieldName() + ";";
                    statement.executeUpdate(delAttrSql);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("deleteTableField fail");
        }


    }

    /**
     * 增加表字段
     *
     * @param addAttrList 要增加的字段
     * @param connection  远程数据库的连接
     * @param tableName   表名
     */
    public static void addTableField(List<DeviceAttrType> addAttrList, Connection connection, String tableName) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            if (!TextUtil.isEmpty(addAttrList)) {
                for (int i = 0; i < addAttrList.size(); i++) {
                    String addAttrSql = "ALTER TABLE " + tableName + " ADD " + addAttrList.get(i).getFieldName() + " " + addAttrList.get(i).getDataType() + ";";
                    statement.executeUpdate(addAttrSql);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("addTableField fail");
        }
    }

    /**
     * 删除表
     *
     * @param connection 远程数据库的连接
     * @param tableName  表名
     */
    public static void deleteTable(Connection connection, String tableName) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String dropTableSql = "drop table if exists " + tableName + ";";
            statement.executeUpdate(dropTableSql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("deleteTable fail");
        }
    }

    /**
     * 创建表
     *
     * @param connection         远程数据库连接
     * @param tableName          表名
     * @param deviceAttrTypeList 表字段
     */
    public static void createTable(Connection connection, String tableName, List<DeviceAttrType> deviceAttrTypeList) {
        String createTableSql = "create table " + tableName + "(id int not null AUTO_INCREMENT,";
        if (TextUtil.isEmpty(deviceAttrTypeList)) {
            logger.error("no deviceAttrType");
            return;
        }
        for (int i = 0; i < deviceAttrTypeList.size(); i++) {
            createTableSql = createTableSql + deviceAttrTypeList.get(i).getFieldName() + " " + deviceAttrTypeList.get(i).getDataType() + ",";
        }
        createTableSql = createTableSql + "PRIMARY KEY(id));";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableSql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("createTable fail");
        }
    }
}

