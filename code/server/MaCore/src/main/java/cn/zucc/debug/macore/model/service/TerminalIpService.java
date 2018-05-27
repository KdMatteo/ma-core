package cn.zucc.debug.macore.model.service;

import cn.zucc.debug.frame.ssm.BaseService;
import cn.zucc.debug.macore.model.pojo.TerminalIp;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TerminalIpService extends BaseService<TerminalIp, Integer> {
    List<TerminalIp> findAll();

}
