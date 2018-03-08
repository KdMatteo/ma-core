package cn.zucc.debug.frame.ssm;


import java.io.Serializable;
import java.util.List;

/**
 *
 * @author GongTengPangYi
 */
public interface BaseService<T extends Serializable, E extends Serializable> {

    int deleteById(E id);

    int add(T record);

    int insertSelective(T record);

    T queryById(E id);

    int updateByPrimaryKeySelective(T record);

    int updateById(T record);

}
