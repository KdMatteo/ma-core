package cn.zucc.pump.mapper;

import cn.zucc.pump.pojo.DeviceType;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-07 15:36
 * Description:
 **/
public interface DeviceTypeMapper {
    /**
     * 获取所有数据
     * @return
     */
    List<DeviceType> getAllList ();

    /**
     * 通过id查找
     * @param id
     * @return
     */
    DeviceType queryById(int id);
}
