package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.DeviceAttrMapper;
import cn.zucc.debug.macore.model.pojo.DeviceAttr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deviceAttrService")
public class DeviceAttrServiceImpl extends BaseServiceImpl<DeviceAttr, Integer> implements DeviceAttrService {

    @Autowired
    DeviceAttrMapper deviceAttrMapper;

    @Override
    public BaseMapper<DeviceAttr, Integer> getMapper() {
        return deviceAttrMapper;
    }

    @Override
    public List<DeviceAttr> findByDeviceId(Integer deviceId) {
        return deviceAttrMapper.selectByDeviceId(deviceId);
    }
}
