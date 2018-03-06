package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.WaterObject;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-06 22:28
 * Description:
 **/
public interface ObjectService extends BaseService<WaterObject, Integer> {
    /**
     * 通过name查询
     * @param name
     */
    List<WaterObject> queryByName(String name);


}
