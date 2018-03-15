package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.TerminalAttr;

import java.util.List;

public interface TerminalAttrService extends BaseService<TerminalAttr, Integer> {

    List<TerminalAttr> findByTerminalId(Integer terminalId);
}
