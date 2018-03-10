package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.DeviceTypeMapper;
import cn.zucc.debug.macore.model.pojo.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deviceTypeService")
public class DeviceTypeServiceImpl extends BaseServiceImpl<DeviceType, Integer> implements DeviceTypeService{

    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    @Override
    public BaseMapper<DeviceType, Integer> getMapper() {
        return deviceTypeMapper;
    }

    @Override
    public List<DeviceType> findAll() {
        return deviceTypeMapper.selectAll();
    }

    @Override
    public DeviceType findByTableName(String tableName) {
        return deviceTypeMapper.selectByTableName(tableName);
    }
}
