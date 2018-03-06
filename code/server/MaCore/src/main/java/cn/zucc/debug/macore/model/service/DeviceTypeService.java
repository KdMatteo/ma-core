package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.DeviceType;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-07 15:36
 * Description:
 **/
public interface DeviceTypeService extends BaseService<DeviceType, Integer>{
    /**
     * 获取所有数据
     * @return
     */
    List<DeviceType> getAllList();

}
