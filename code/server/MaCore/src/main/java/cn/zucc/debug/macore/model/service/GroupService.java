package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.Group;

import java.util.List;
import java.util.Map;

public interface GroupService extends BaseService<Group, Integer>{

    List<Group> findByObjectIdSearch(Integer objectId);

    Group findByObjectIdAndName(Integer objectId, String name);
}
