package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.Host;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-06 17:06
 * Description:host表dao
 **/
public interface HostService extends BaseService<Host, Integer>{

    Host findByIpAndPortAndAccount(String ip, int port, String account);

}
