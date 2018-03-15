package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.HostMapper;
import cn.zucc.debug.macore.model.pojo.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("hostService")
public class HostServiceImpl extends BaseServiceImpl<Host, Integer> implements HostService{

    @Autowired
    HostMapper hostMapper;

    @Override
    public BaseMapper<Host, Integer> getMapper() {
        return hostMapper;
    }


    @Override
    public Host findByIpAndPortAndAccount(String ip, int port, String account) {
        return hostMapper.selectByIpAndAccountAndPassword(ip, port, account);
    }

    @Override
    public List<Host> findAll() {
        return hostMapper.selectAll();
    }
}
