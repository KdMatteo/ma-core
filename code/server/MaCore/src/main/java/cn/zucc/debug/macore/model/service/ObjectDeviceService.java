package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.ObjectDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-07 16:41
 * Description:
 **/
public interface ObjectDeviceService extends BaseService<ObjectDevice, Integer>{


    List<ObjectDevice> findByObjectIdAndGroupId(Integer objectId, Integer groupId);

    List<ObjectDevice> findByObjectIdAndGroupIdAndDevicetypeId(Integer objectId, Integer groupId, Integer devicetypeId);

}
