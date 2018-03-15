package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.User;

import java.util.List;

public interface UserService extends BaseService<User, Integer>{

    User findByAccount(String account);

    List<User> findAll();
}
