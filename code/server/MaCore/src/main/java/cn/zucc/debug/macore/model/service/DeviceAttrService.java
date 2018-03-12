package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.DeviceAttr;

import java.util.List;


/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-07 20:16
 * Description:
 **/
public interface DeviceAttrService extends BaseService<DeviceAttr, Integer>{


    List<DeviceAttr> findByDeviceId(Integer deviceId);

}
