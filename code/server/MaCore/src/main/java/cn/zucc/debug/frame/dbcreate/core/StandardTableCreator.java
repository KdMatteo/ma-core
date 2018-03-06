package cn.zucc.debug.frame.dbcreate.core;

import cn.zucc.debug.frame.dbcreate.TableCreator;
import cn.zucc.debug.frame.dbcreate.jdbc.ConnectionManager;
import cn.zucc.debug.frame.dbcreate.jdbc.DbAction;
import cn.zucc.debug.frame.dbcreate.model.Field;
import cn.zucc.debug.frame.dbcreate.model.Table;

import java.sql.Statement;

/**
 * @author GongTengPangYi
 */
public class StandardTableCreator extends TableCreator{
    private Statement statement;

    public StandardTableCreator(String ip, int port, String account, String password, String databaseName) {
        super(ip, port, account, password, databaseName);
        String url = "jdbc:mysql://" + ip + ":" + port + "/" + databaseName;
        ConnectionManager connectionManager = new ConnectionManager(url, account, password);
        connectionManager.connect();
        statement = connectionManager.getStatement();
    }

    @Override
    public boolean createTable(String tableName, Field[] fields) {
        Table table = new Table();
        table.setTableName(tableName);
        table.setFields(fields);
        return DbAction.createTable(statement, table);
    }

    @Override
    public boolean deleteTable(String tableName) {
        return DbAction.dropTable(statement, tableName);
    }

    @Override
    public boolean addTableFields(String tableName, Field[] fields) {
        for (Field field : fields) {
            if (!DbAction.addField(statement, tableName, field)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteTableFields(String tableName, String[] fieldNames) {
        for (String fieldName : fieldNames) {
            if (!DbAction.dropField(statement, tableName, fieldName)) {
                return false;
            }
        }
        return true;
    }


}
