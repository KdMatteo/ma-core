package cn.zucc.pump.database;

import cn.zucc.pump.mapper.DeviceTypeMapper;
import cn.zucc.pump.mapper.HostMapper;
import cn.zucc.pump.mapper.ObjectDeviceMapper;
import cn.zucc.pump.mapper.ObjectMapper;
import cn.zucc.pump.pojo.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-02-06 19:51
 * Description:本机数据库管理
 **/
public class LocalDataBaseManager {

    private static SqlSession session;
    private static SqlSessionFactory factory;
    public static final Logger logger = Logger.getLogger(LocalDataBaseManager.class);
    public static SqlSession getInstance(){
        if(session == null){
            if(factory == null){
                String resource = "mybatis-config.xml";
                Reader reader = null;
                try {
                    reader = Resources.getResourceAsReader(resource);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
                factory = builder.build(reader);
            }
            session = factory.openSession();
        }
        return session;
    }

   public void close(){
       if(session != null){
           session.close();
           session = null;
       }
   }

    /**
     * 通过deviceId获取远程服务器相关信息
     * @param deviceId
     * @return
     */
   public static HostInfo getHostInfo(Integer deviceId){
       HostInfo hostInfo = new HostInfo();
       //获取表名
       ObjectDevice objectDevice = LocalDataBaseManager.getInstance().getMapper(ObjectDeviceMapper.class).queryById(deviceId);
       if (objectDevice == null) {
           logger.error("no tableName with deviceId = "+deviceId);
           return null;
       }
       String code = objectDevice.getCode();
       int index = objectDevice.getIndex();
       //获取数据库名
      ObjectPO objectPO = LocalDataBaseManager.getInstance().getMapper(ObjectMapper.class).queryById(objectDevice.getObject_id());
       if(objectPO == null){
           logger.error("no databaseName with object_id"+objectDevice.getObject_id());
           return null;
       }
       hostInfo.setDatabaseName(objectPO.getDatabase_name());
       //获取数据库服务器ip,port,account,password
       Host host = LocalDataBaseManager.getInstance().getMapper(HostMapper.class).queryById(objectPO.getHost_id());
       if(host == null){
           logger.error("no host info with host_id = "+objectPO.getHost_id());
           return null;
       }
       hostInfo.setIp(host.getIp());
       hostInfo.setPort(host.getPort());
       hostInfo.setAccount(host.getAccount());
       hostInfo.setPassword(host.getPassword());
       //判断multi,拼接表名
       DeviceType deviceType = LocalDataBaseManager.getInstance().getMapper(DeviceTypeMapper.class).queryById(objectDevice.getDevicetype_id());
       if (deviceType == null){
           logger.error("no device_type with device_type_id = "+objectDevice.getDevicetype_id());
           return null;
       }
       int multi = deviceType.getMulti();
       if (multi == 1) {
           hostInfo.setTableName(code + index);
       } else {
           hostInfo.setTableName(deviceType.getTable_name());
       }
       return hostInfo;
   }
}
