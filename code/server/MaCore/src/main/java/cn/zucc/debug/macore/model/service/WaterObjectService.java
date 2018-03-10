package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.WaterObject;

import java.util.List;
import java.util.Map;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-06 22:28
 * Description:
 **/
public interface WaterObjectService extends BaseService<WaterObject, Integer> {

    List<WaterObject> findByHostIdPagerAndSearch(Integer hostId, Integer pageSize, Integer pageIndex, Map<String, Object> searchParams) ;

    WaterObject findByHostIdAndDatabaseName(Integer hostId, String databaseName);
}
