package cn.zucc.debug.macore.model.service;


import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.TerminalIpMapper;
import cn.zucc.debug.macore.model.pojo.TerminalIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("terminalIpService")
public class TerminalIpServicelmpl extends BaseServiceImpl<TerminalIp,Integer> implements TerminalIpService{

    @Autowired
    TerminalIpMapper terminalIpMapper;

    @Override
    public BaseMapper<TerminalIp, Integer> getMapper() {
        return terminalIpMapper;
    }

    @Override
    public List<TerminalIp> findAll() {
        return terminalIpMapper.selectAll();
    }

}
