package cn.zucc.pump.mapper;

import cn.zucc.pump.pojo.Host;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-06 17:06
 * Description:host表dao
 **/
public interface HostMapper {
    /***
     * 添加数据
     * @param host
     */
    int add(Host host);

    /**
     * 查询
     * @param ip
     * @param port
     */
    List<Host> queryByIpAndPort(@Param("ip") String ip, @Param("port") int port);

    /**
     * 修改数据库
     * @param host
     * @return
     */
    int changeHost(Host host);

    /**
     * 删除数据库
     */
    int deleteById(int id);

    /**
     * 获取所有数据
     * @return
     */
    List<Host> getAllList();

    /**
     * 通过id 查询
     * @param id
     * @return
     */
    Host queryById(int id);

}
