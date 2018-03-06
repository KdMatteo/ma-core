package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.DeviceAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-07 20:16
 * Description:
 **/
public interface DeviceAttrService extends BaseService<DeviceAttr, Integer>{


    /**
     * 更改plcAddress
     * @param attrId
     * @param plcAddress
     * @return
     */
    int updatePlcAddress(@Param("attrId") int attrId, @Param("plcAddress") String plcAddress);

    /**
     * 通过device_id 查询
     * @param deviceId
     * @return
     */
    List<DeviceAttr> queryByDeviceId(int deviceId);

    /**
     * 通过objectId删除数据
     * @param deviceId
     * @return
     */
    int deleteByDeviceId(int deviceId);

}
