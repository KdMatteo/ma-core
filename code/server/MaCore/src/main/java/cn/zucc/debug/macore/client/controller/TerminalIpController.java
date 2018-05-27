package cn.zucc.debug.macore.client.controller;


import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.client.request.TerminalIpAddRequest;
import cn.zucc.debug.macore.client.request.TerminalIpDeleteRequest;
import cn.zucc.debug.macore.client.request.TerminalIpUpdateRequest;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.model.pojo.Terminal;
import cn.zucc.debug.macore.model.pojo.TerminalIp;
import cn.zucc.debug.macore.model.service.TerminalIpService;
import cn.zucc.debug.macore.model.service.TerminalService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/terminalIp")
@Controller
public class TerminalIpController extends CommonController{
    @Autowired
    TerminalIpService terminalIpService;
    @Autowired
    TerminalService terminalService;

    @RequestMapping("/list")
    @ResponseBody
    public String list() {
        List<TerminalIp> terminalIpList = terminalIpService.findAll();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", JSONUtil.fromList(terminalIpList, "*"));
        return success(jsonObject);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestBody TerminalIpAddRequest request) {

        TerminalIp terminalIp = terminalIpService.findById(request.getId());
        JSONObject jsonObject = new JSONObject();
        if(terminalIp != null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_TERMINAL_ALREADY_EXIST, jsonObject);
        } else {
            terminalIp = new TerminalIp();
            terminalIp.setId(request.getId());
            terminalIp.setIp(request.getIp());
            terminalIp.setPort(request.getPort());
            terminalIpService.save(terminalIp);
            return success(jsonObject);
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody TerminalIpUpdateRequest request) {
        TerminalIp terminalIp = terminalIpService.findById(request.getId());
        JSONObject jsonObject = new JSONObject();
        if(terminalIp == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_TERMINAL_NOT_EXIST, jsonObject);
        } else {
            terminalIp.setIp(request.getIp());
            terminalIp.setPort(request.getPort());
            terminalIpService.updateById(terminalIp);
            List<Terminal> terminalList = terminalService.findByTerminalIpId(request.getId());
            if(terminalList != null) {
                for (Terminal terminal : terminalList) {
                    terminalService.deleteById(terminal.getId());
                }
            }
            return success(jsonObject);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestBody TerminalIpDeleteRequest request) {
        TerminalIp terminalIp = terminalIpService.findById(request.getId());
        JSONObject jsonObject = new JSONObject();
        if(terminalIp == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_TERMINAL_ALREADY_EXIST, jsonObject);
        } else {
            terminalIpService.deleteById(request.getId());
            List<Terminal> terminalList = terminalService.findByTerminalIpId(request.getId());
            if(terminalList != null) {
                for (Terminal terminal : terminalList) {
                    terminalService.deleteById(terminal.getId());
                }
            }
            return success(jsonObject);
        }
    }
}
