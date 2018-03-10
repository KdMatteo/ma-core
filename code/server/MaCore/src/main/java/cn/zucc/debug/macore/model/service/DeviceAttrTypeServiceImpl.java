package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.DeviceAttrTypeMapper;
import cn.zucc.debug.macore.model.pojo.DeviceAttrType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deviceAttrTypeService")
public class DeviceAttrTypeServiceImpl extends BaseServiceImpl<DeviceAttrType, Integer> implements DeviceAttrTypeService {

    @Autowired
    DeviceAttrTypeMapper deviceAttrTypeMapper;

    @Override
    public BaseMapper<DeviceAttrType, Integer> getMapper() {
        return deviceAttrTypeMapper;
    }


    @Override
    public List<DeviceAttrType> findByDevicetypeId(Integer devicetypeId) {
        return deviceAttrTypeMapper.selectByDevicetypeId(devicetypeId);
    }

    @Override
    public DeviceAttrType findByDevicetypeIdAndFieldName(Integer devicetypeId, String fieldName) {
        return deviceAttrTypeMapper.selectByDevicetypeIdAndFieldName(devicetypeId, fieldName);
    }
}
