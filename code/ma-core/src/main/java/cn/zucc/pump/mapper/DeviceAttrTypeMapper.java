package cn.zucc.pump.mapper;

import cn.zucc.pump.pojo.DeviceAttrType;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-07 16:08
 * Description:
 **/
public interface DeviceAttrTypeMapper {
    /**
     * 获取所有数据
     * @return
     */
    List<DeviceAttrType> getAllList();

    /**
     * 通过id 查询
     * @param id
     * @return
     */
    DeviceAttrType queryById(int id);
}
