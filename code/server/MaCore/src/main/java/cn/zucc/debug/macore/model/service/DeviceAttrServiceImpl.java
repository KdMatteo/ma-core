package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.DeviceAttrMapper;
import cn.zucc.debug.macore.model.pojo.DeviceAttr;
import org.springframework.beans.factory.annotation.Autowired;

public class DeviceAttrServiceImpl extends BaseServiceImpl<DeviceAttr, Integer> implements DeviceAttrService {

    @Autowired
    DeviceAttrMapper deviceAttrMapper;

    @Override
    public BaseMapper<DeviceAttr, Integer> getMapper() {
        return deviceAttrMapper;
    }
}
