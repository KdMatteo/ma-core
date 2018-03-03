package cn.zucc.pump.service;

import cn.zucc.pump.utils.TextUtil;
import cn.zucc.pump.database.LocalDataBaseManager;
import cn.zucc.pump.mapper.HostMapper;
import cn.zucc.pump.database.RemoteDataBaseManager;
import cn.zucc.pump.pojo.Host;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-02-06 23:05
 * Description:数据库服务器Host
 **/
public class HostService {
    public static final Logger logger = Logger.getLogger(HostService.class);
    /**
     * 创建一个数据库服务器
     * @param ip
     * @param port
     * @param account
     * @param password
     */
   public void  createHost(String ip, int port, String account, String password){
       Host host =new Host();
       host.setIp(ip);
       host.setPort(port);
       host.setAccount(account);
       host.setPassword(password);
       int isSuccess = LocalDataBaseManager.getInstance().getMapper(HostMapper.class).add(host);
       LocalDataBaseManager.getInstance().commit();
       if(isSuccess > 0){
           logger.info("createHost success");
       }else{
           logger.error("createHost fail");
       }
       LocalDataBaseManager.getInstance().close();
   }

    /**
     * 修改数据库服务器
     * @param hostId 服务器id
     * @param ip
     * @param port
     * @param account
     * @param password
     */
   public void changeHost(Integer hostId, String ip, int port, String account, String password){
       Host host =new Host();
       host.setId(hostId);
       host.setIp(ip);
       host.setPort(port);
       host.setAccount(account);
       host.setPassword(password);
       int isSuccess = LocalDataBaseManager.getInstance().getMapper(HostMapper.class).changeHost(host);
       LocalDataBaseManager.getInstance().commit();
       if(isSuccess > 0){
           logger.info("changeHost success");
       }else{
           logger.error("changeHost fail");
       }
       LocalDataBaseManager.getInstance().close();
   }

    /***
     * 删除服务器
     */
   public void deleteHost(Integer hostId){
       int isSuccess = LocalDataBaseManager.getInstance().getMapper(HostMapper.class).deleteById(hostId);
       LocalDataBaseManager.getInstance().commit();
       if(isSuccess > 0){
           logger.info("deleteHost success");
       }else{
           logger.error("deleteHost fail");
       }
       LocalDataBaseManager.getInstance().close();
   }

    /**
     * 获取所有的主机供前端选择
     */
   public List<Host> findAllHost(){
       List<Host> hostList=  LocalDataBaseManager.getInstance().getMapper(HostMapper.class).getAllList();
       if(TextUtil.isEmpty(hostList)){
           logger.error("findAllHost fail");
           logger.error("no host data");
           return null;
       }
       logger.info("findAllHost success");
       return hostList;
    }

    /***
     * 连接数据库服务器
     * @param hostId 服务器id
     */
    public Connection connectHost(Integer hostId){
        Connection connection = null;
        Host host = LocalDataBaseManager.getInstance().getMapper(HostMapper.class).queryById(hostId);
        if(host == null){
            logger.error("no host with id");
        }else{
            connection = RemoteDataBaseManager.connectHost(host.getIp(),host.getPort(),host.getAccount(),host.getPassword());
        }
        return connection;
    }
}
