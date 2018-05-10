package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.ObjectDeviceMapper;
import cn.zucc.debug.macore.model.pojo.ObjectDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("objectDeviceService")
public class ObjectDeviceServiceImpl extends BaseServiceImpl<ObjectDevice, Integer> implements ObjectDeviceService{
    @Autowired
    ObjectDeviceMapper objectDeviceMapper;

    @Override
    public BaseMapper<ObjectDevice, Integer> getMapper() {
        return objectDeviceMapper;
    }


    @Override
    public List<ObjectDevice> findByObjectIdAndGroupId(Integer objectId, Integer groupId) {
        return objectDeviceMapper.selectByObjectIdAndGroupId(objectId, groupId);
    }

    @Override
    public List<ObjectDevice> findByObjectIdAndDevicetypeId(Integer objectId, Integer devicetypeId) {
        return objectDeviceMapper.selectByObjectIdAndDevicetypeId(objectId, devicetypeId);
    }
}
