package cn.zucc.debug.macore.model.mapper;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.macore.model.pojo.ObjectDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ObjectDeviceMapper extends BaseMapper<ObjectDevice, Integer> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_object_device
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_object_device
     *
     * @mbggenerated
     */
    int insert(ObjectDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_object_device
     *
     * @mbggenerated
     */
    int insertSelective(ObjectDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_object_device
     *
     * @mbggenerated
     */
    ObjectDevice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_object_device
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ObjectDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_object_device
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ObjectDevice record);

    List<ObjectDevice> selectByObjectIdAndGroupId(@Param("objectId") Integer objectId, @Param("groupId") Integer groupId);

    List<ObjectDevice> selectByObjectIdAndDevicetypeId(@Param("objectId") Integer objectId, @Param("devicetypeId") Integer devicetypeId);
}