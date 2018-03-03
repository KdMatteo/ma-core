package cn.zucc.pump.mapper;

import cn.zucc.pump.pojo.ObjectDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-07 16:41
 * Description:
 **/
public interface ObjectDeviceMapper {
    /**
     * 添加数据
     * @param objectDevice
     * @return
     */
    int add(ObjectDevice objectDevice);

    /**
     * 查询符合特征的有几条数据
     * @param objectId
     * @param deviceTypeId
     * @return
     */
    int count(@Param("objectId") int objectId,@Param("deviceTypeId") int deviceTypeId);

    /**
     * 通过id删除设备
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    ObjectDevice queryById(int id);

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
