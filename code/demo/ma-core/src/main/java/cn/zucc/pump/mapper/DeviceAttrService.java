package cn.zucc.pump.mapper;

import cn.zucc.pump.pojo.DeviceAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-07 20:16
 * Description:
 **/
public interface DeviceAttrService {
    /**
     * 增加记录
     * @param deviceAttr
     * @return
     */
    int add(DeviceAttr deviceAttr);

    /**
     * 通过id删除数据
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 更改plcAddress
     * @param attrId
     * @param plcAddress
     * @return
     */
    int updatePlcAddress(@Param("attrId") int attrId,@Param("plcAddress") String plcAddress);

    /**
     * 通过device_id 查询
     * @param deviceId
     * @return
     */
    List<DeviceAttr> queryByDeviceId(int deviceId);

    /**
     * 通过objectId删除数据
     * @param objectId
     * @return
     */
    int deleteByDeviceId(int objectId);

}
