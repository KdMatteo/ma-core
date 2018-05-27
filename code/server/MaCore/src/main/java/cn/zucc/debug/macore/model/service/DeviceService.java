package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.Device;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-07 16:41
 * Description:
 **/
public interface DeviceService extends BaseService<Device, Integer>{


    List<Device> findByObjectIdAndGroupId(Integer objectId, Integer groupId);

    List<Device> findByObjectIdAndDevicetypeId(Integer objectId, Integer devicetypeId);

}
