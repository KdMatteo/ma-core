package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseMapper;
import cn.zucc.debug.frame.ssm.BaseServiceImpl;
import cn.zucc.debug.macore.model.mapper.TerminalMapper;
import cn.zucc.debug.macore.model.pojo.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("terminalService")
public class TerminalServiceImpl extends BaseServiceImpl<Terminal, Integer> implements TerminalService {
    @Autowired
    TerminalMapper terminalMapper;

    @Override
    public BaseMapper<Terminal, Integer> getMapper() {
        return terminalMapper;
    }


    @Override
    public Terminal findByObjectIdAndCode(Integer objectId, String code) {
        return terminalMapper.selectByObjectIdAndCode(objectId, code);
    }

    @Override
    public List<Terminal> findByObjectId(Integer objectId) {
        return terminalMapper.selectByObjectId(objectId);
    }

    @Override
    public List<Terminal> findByTerminalIpId(Integer terminalIpId) {
        return terminalMapper.selectByTerminalIpId(terminalIpId);
    }
}
