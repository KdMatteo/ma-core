package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.ObjectDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-07 16:41
 * Description:
 **/
public interface ObjectDeviceService extends BaseService<ObjectDevice, Integer>{

    /**
     * 查询符合特征的有几条数据
     * @param objectId
     * @param deviceTypeId
     * @return
     */
    int count(@Param("objectId") int objectId,@Param("deviceTypeId") int deviceTypeId);

    /**
     * 通过objectId删除
     * @param objectId
     * @return
     */
    int deleteByObjectId(int objectId);

    /**
     * 选择所有的objectId数据
     * @param objectId
     * @return
     */
    List<ObjectDevice> selectByObjectId(int objectId);
}
