package cn.zucc.pump.service;

import cn.zucc.pump.database.LocalDataBaseManager;
import cn.zucc.pump.database.RemoteDataBaseManager;
import cn.zucc.pump.utils.TextUtil;
import cn.zucc.pump.mapper.DeviceAttrMapper;
import cn.zucc.pump.mapper.HostMapper;
import cn.zucc.pump.mapper.ObjectDeviceMapper;
import cn.zucc.pump.mapper.ObjectMapper;
import cn.zucc.pump.pojo.Host;
import cn.zucc.pump.pojo.ObjectDevice;
import cn.zucc.pump.pojo.ObjectPO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-02-07 9:20
 * Description:具体取水点
 **/
public class ObjectService {
    public static final Logger logger = Logger.getLogger(ObjectService.class);
    /**
     * 创建一个新“点？”（同时创建一个新数据库）
     * @param hostId 该点对应的服务器id
     * @param type 改点的类型（0-2分别表示。。。）
     * @param databaseName 需要创建的对应数据库名
     * @param name 该点的名字
     * @param address 该点的地址
     * @param linkman 联系人
     * @param mobile 联系电话
     */
    public void createObject(Integer hostId, int type, String databaseName, String name, String address, String linkman, String mobile){
        ObjectPO objectPO = new ObjectPO();
        objectPO.setAddress(address);
        objectPO.setDatabase_name(databaseName);
        objectPO.setType_id(type);
        objectPO.setLinkman(linkman);
        objectPO.setMobile(mobile);
        objectPO.setName(name);
        objectPO.setHost_id(hostId);
        Host host = LocalDataBaseManager.getInstance().getMapper(HostMapper.class).queryById(hostId);
        if(host != null){
           Connection connection = RemoteDataBaseManager.createDataBase(databaseName,host.getIp(),host.getPort(),host.getAccount(),host.getPassword());
            if(connection != null) {
                int isSuccess = LocalDataBaseManager.getInstance().getMapper(ObjectMapper.class).add(objectPO);
                LocalDataBaseManager.getInstance().commit();
                if (isSuccess > 0) {
                    logger.info("createObject success");
                    LocalDataBaseManager.getInstance().close();
                    return;
                }
            }
        }else{
            logger.error("no host with "+hostId);
        }
        logger.error("createObject fail");
    }

    /**
     * 仅删除供水点记录,不删除远程数据库
     * @param objectId 该点对应的id
     */
    public void deleteObject(Integer objectId){
       int isSucccess =  LocalDataBaseManager.getInstance().getMapper(ObjectMapper.class).deleteById(objectId);
        LocalDataBaseManager.getInstance().commit();
       if(isSucccess >0){
           logger.info("deleteObject success");
       }else{
           logger.error("deleteObject fail");
       }
        LocalDataBaseManager.getInstance().close();
    }

    /**
     * 删除供水点，删除远程数据库及其相关记录
     * @param objectId
     */
    public void deleteObjectInfo(Integer objectId){
        ObjectPO objectPO = LocalDataBaseManager.getInstance().getMapper(ObjectMapper.class).queryById(objectId);
        if(objectPO == null){
            logger.error("no object with id ="+objectId);
            return;
        }
        Host host = LocalDataBaseManager.getInstance().getMapper(HostMapper.class).queryById(objectPO.getHost_id());
        if(host == null){
            logger.error("no host with id="+objectPO.getHost_id());
            return;
        }
        //删除远程数据库
        RemoteDataBaseManager.deleteDatabase(host.getIp(),host.getPort(),host.getAccount(),host.getPassword(),objectPO.getDatabase_name());
        List<ObjectDevice> objectDevicesList = LocalDataBaseManager.getInstance().getMapper(ObjectDeviceMapper.class).selectByObjectId(objectId);
        //删除object_device记录
        LocalDataBaseManager.getInstance().getMapper(ObjectDeviceMapper.class).deleteByObjectId(objectId);
        LocalDataBaseManager.getInstance().commit();
        if(TextUtil.isEmpty(objectDevicesList)){
            logger.error("no object_device with ojectid="+objectId);
            return;
        }
        //删除decice_attr记录
        for(int i =0;i<objectDevicesList.size();i++){
            LocalDataBaseManager.getInstance().getMapper(DeviceAttrMapper.class).deleteByDeviceId(objectDevicesList.get(i).getId());
            LocalDataBaseManager.getInstance().commit();
        }
        //删除object记录
        deleteObject(objectId);
        logger.info("deleteObjectInfo success");
        LocalDataBaseManager.getInstance().close();

    }

    /**
     * 修改点的信息
     * @param objectId
     * @param type
     * @param name
     * @param address
     * @param linkman
     * @param mobile
     */
    public void changeObject(Integer objectId, int type, String name, String address, String linkman, String mobile){
        ObjectPO objectPO = new ObjectPO();
        objectPO.setId(objectId);
        objectPO.setType_id(type);
        objectPO.setName(name);
        objectPO.setAddress(address);
        objectPO.setLinkman(linkman);
        objectPO.setMobile(mobile);
        int isSuccess = LocalDataBaseManager.getInstance().getMapper(ObjectMapper.class).changeData(objectPO);
        LocalDataBaseManager.getInstance().commit();
        if(isSuccess >0){
          logger.info("changeObject success");
        }else{
            logger.error("changeObject fail");
        }
        LocalDataBaseManager.getInstance().close();
    }

    /**
     * 获取所有的点供前端选择
     */
    public List<ObjectPO> findAllObject(){
        List<ObjectPO> objectPOList = LocalDataBaseManager.getInstance().getMapper(ObjectMapper.class).getAllList();
        if (TextUtil.isEmpty(objectPOList)){
            logger.error("no object data");
            return null;
        }else{
            logger.info("findAllObject success");
            return objectPOList;
        }
    }

    /**
     * 连接某个点对应的数据库
     * @param objectId
     */
    public Connection connectDatabase(Integer objectId){
        Connection connection = null;
        ObjectPO objectPO = LocalDataBaseManager.getInstance().getMapper(ObjectMapper.class).queryById(objectId);
        if(objectPO == null){
            logger.error("no object data with"+objectId);
        }else{
           Host host = LocalDataBaseManager.getInstance().getMapper(HostMapper.class).queryById(objectPO.getHost_id());
           if(host == null){
               logger.error("no host data with"+objectPO.getHost_id());
           }else{
               connection = RemoteDataBaseManager.connectDataBase(host.getIp(),host.getPort(),host.getAccount(),host.getPassword(),objectPO.getDatabase_name());
           }
        }
        return connection;
    }
}
