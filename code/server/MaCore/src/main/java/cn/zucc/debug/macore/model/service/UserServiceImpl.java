package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.UserMapper;
import cn.zucc.debug.macore.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public BaseMapper<User, Integer> getMapper() {
        return userMapper;
    }

    @Override
    public User findByAccount(String account) {
        return userMapper.selectByAccount(account);
    }
}
