package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.DeviceAttrType;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-07 16:08
 * Description:
 **/
public interface DeviceAttrTypeService extends BaseService<DeviceAttrType, Integer> {

    List<DeviceAttrType> findByDevicetypeId(Integer devicetypeId);

    DeviceAttrType findByDevicetypeIdAndFieldName(Integer devicetypeId, String fieldName);
}
