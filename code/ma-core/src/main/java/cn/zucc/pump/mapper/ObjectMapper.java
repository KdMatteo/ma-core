package cn.zucc.pump.mapper;

import cn.zucc.pump.pojo.ObjectPO;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @CreateTime 2018-02-06 22:28
 * Description:
 **/
public interface ObjectMapper {
    /**
     * 通过name查询
     * @param name
     */
    List<ObjectPO> queryByName(String name);

    /**
     * 添加数据
     * @param objectPO
     * @return
     */
    int add(ObjectPO objectPO);

    /**
     * 通过id删除数据
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 更改数据
     * @param objectPO
     * @return
     */
    int changeData(ObjectPO objectPO);

    /**
     * 获取所有数据
     * @return
     */
    List<ObjectPO> getAllList();

    /**
     * 通过id查询数据
     * @param id
     * @return
     */
    ObjectPO queryById(int id);
}
