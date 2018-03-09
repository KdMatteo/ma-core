package cn.zucc.debug.frame.ssm;

import java.io.Serializable;
import java.util.List;

/**
 * @author GongTengPangYi
 */
public interface BaseMapper<T extends Serializable, E extends Serializable> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(E id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated
     */
    int insert(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated
     */
    int insertSelective(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated
     */
    T selectByPrimaryKey(E id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(T record);

    List<T> queryAll();
}