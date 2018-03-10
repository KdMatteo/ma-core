package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.WaterObjectMapper;
import cn.zucc.debug.macore.model.pojo.WaterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("waterObjectService")
public class WaterObjectServiceImpl extends BaseServiceImpl<WaterObject, Integer> implements WaterObjectService {

    @Autowired
    WaterObjectMapper waterObjectMapper;

    @Override
    public BaseMapper<WaterObject, Integer> getMapper() {
        return waterObjectMapper;
    }

    @Override
    public List<WaterObject> findByHostIdPagerAndSearch(Integer hostId, Integer pageSize, Integer pageIndex, Map<String, Object> searchParams) {
        return waterObjectMapper.selectByHostIdLimit(hostId, (pageIndex-1) * pageSize, pageSize, searchParams);
    }

    @Override
    public WaterObject findByHostIdAndDatabaseName(Integer hostId, String databaseName) {
        return waterObjectMapper.selectByHostIdAndDatabaseName(hostId, databaseName);
    }
}
