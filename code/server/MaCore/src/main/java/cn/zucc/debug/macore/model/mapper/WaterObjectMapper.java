package cn.zucc.debug.macore.model.mapper;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.macore.model.pojo.WaterObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WaterObjectMapper extends BaseMapper<WaterObject, Integer> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_object
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_object
     *
     * @mbggenerated
     */
    int insert(WaterObject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_object
     *
     * @mbggenerated
     */
    int insertSelective(WaterObject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_object
     *
     * @mbggenerated
     */
    WaterObject selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_object
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(WaterObject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_object
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(WaterObject record);

    List<WaterObject> selectByHostIdLimit(@Param("hostId") Integer hostId, @Param("limitFrom") Integer limitFrom,
                                          @Param("limitCount") Integer limitCount,@Param("searchParams") Map<String, Object>searchParams);

    WaterObject selectByHostIdAndDatabaseName(@Param("hostId") Integer hostId, @Param("databaseName") String databaseName);
}