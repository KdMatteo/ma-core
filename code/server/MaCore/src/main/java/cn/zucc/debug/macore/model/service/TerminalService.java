package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.Terminal;

import java.util.List;

public interface TerminalService extends BaseService<Terminal, Integer> {
    Terminal findByObjectIdAndCode(Integer objectId, String code);
    List<Terminal> findByObjectId(Integer objectId);
    List<Terminal> findByTerminalIpId(Integer terminalIpId);
}
