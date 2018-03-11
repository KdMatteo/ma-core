package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.GroupMapper;
import cn.zucc.debug.macore.model.pojo.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("groupService")
public class GroupServiceImpl extends BaseServiceImpl<Group, Integer> implements GroupService {

    @Autowired
    GroupMapper groupMapper;

    @Override
    public BaseMapper<Group, Integer> getMapper() {
        return groupMapper;
    }

    @Override
    public List<Group> findByObjectIdSearch(Integer objectId) {
        return groupMapper.selectByObjectIdLimit(objectId);
    }

    @Override
    public Group findByObjectIdAndName(Integer objectId, String name) {
        return groupMapper.selectByObjectIdAndName(objectId, name);
    }
}
