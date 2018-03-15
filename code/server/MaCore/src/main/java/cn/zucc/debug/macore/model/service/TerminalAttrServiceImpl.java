package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.TerminalAttrMapper;
import cn.zucc.debug.macore.model.pojo.TerminalAttr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("terminalAttrService")
public class TerminalAttrServiceImpl extends BaseServiceImpl<TerminalAttr, Integer> implements TerminalAttrService {

    @Autowired
    TerminalAttrMapper terminalAttrMapper;

    @Override
    public BaseMapper<TerminalAttr, Integer> getMapper() {
        return terminalAttrMapper;
    }

    @Override
    public List<TerminalAttr> findByTerminalId(Integer terminalId) {
        return terminalAttrMapper.selectByTerminalId(terminalId);
    }
}
