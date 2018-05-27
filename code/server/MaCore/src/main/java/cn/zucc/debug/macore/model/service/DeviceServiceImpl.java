package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.DeviceMapper;
import cn.zucc.debug.macore.model.pojo.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deviceService")
public class DeviceServiceImpl extends BaseServiceImpl<Device, Integer> implements DeviceService {
    @Autowired
    DeviceMapper deviceMapper;

    @Override
    public BaseMapper<Device, Integer> getMapper() {
        return deviceMapper;
    }


    @Override
    public List<Device> findByObjectIdAndGroupId(Integer objectId, Integer groupId) {
        return deviceMapper.selectByObjectIdAndGroupId(objectId, groupId);
    }

    @Override
    public List<Device> findByObjectIdAndDevicetypeId(Integer objectId, Integer devicetypeId) {
        return deviceMapper.selectByObjectIdAndDevicetypeId(objectId, devicetypeId);
    }
}
