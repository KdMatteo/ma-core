package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.client.request.*;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.model.pojo.Terminal;
import cn.zucc.debug.macore.model.pojo.TerminalAttr;
import cn.zucc.debug.macore.model.service.TerminalAttrService;
import cn.zucc.debug.macore.model.service.TerminalService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/terminal")
@Controller
public class TerminalController extends CommonController {

    @Autowired
    TerminalService terminalService;

    @Autowired
    TerminalAttrService terminalAttrService;

    /**
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestBody TerminalListRequest request) {
        List<Terminal> terminalList = terminalService.findByObjectId(request.getObjectId());
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Terminal terminal : terminalList) {
            JSONObject object = JSONUtil.fromObject(terminal, "*");
            object.put("attrs", terminalAttrService.findByTerminalId(terminal.getId()));
            jsonArray.add(object);
        }
        jsonObject.put("data", jsonArray);
        return success(jsonObject);
    }

    /**
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestBody TerminalAddRequest request) {
        Terminal terminal = terminalService.findByObjectIdAndCode(request.getObjectId(), request.getCode());
        JSONObject jsonObject = new JSONObject();
        if (terminal != null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_TERMINAL_ALREADY_EXIST, jsonObject);
        } else {
            terminal = new Terminal();
            terminal.setCode(request.getCode());
            terminal.setIp(request.getIp());
            terminal.setName(request.getName());
            terminal.setObjectId(request.getObjectId());
            terminal.setPort(request.getPort());
            terminalService.save(terminal);
            saveAttrs(request.getAttrs(), terminal);
            jsonObject.put("data", JSONUtil.fromObject(terminal, "*"));
            return success(jsonObject);
        }
    }

    /**
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody TerminalUpdateRequest request) {
        Terminal terminal = terminalService.findById(request.getId());
        JSONObject jsonObject = new JSONObject();
        if (terminal == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_TERMINAL_NOT_EXIST, jsonObject);
        } else {
            terminal.setCode(request.getCode());
            terminal.setIp(request.getIp());
            terminal.setName(request.getName());
            terminal.setPort(request.getPort());
            terminalService.updateById(terminal);
            saveAttrs(request.getAttrs(), terminal);
            return success(jsonObject);
        }
    }

    private void saveAttrs(JSONArray attrs, Terminal terminal) {
        for (Object attrInfo : attrs) {
            TerminalAttr terminalAttr = new TerminalAttr();
            terminalAttr.setDeviceattrId((Integer) ((JSONObject)attrInfo).get("deviceattrId"));
            terminalAttr.setPlcAddress((String) ((JSONObject)attrInfo).get("plc_address"));
            terminalAttr.setTerminalId(terminal.getId());
            terminalAttrService.save(terminalAttr);
        }
    }

    /**
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestBody TerminalDeleteRequest request) {
        Terminal terminal = terminalService.findById(request.getId());
        JSONObject jsonObject = new JSONObject();
        if (terminal == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_TERMINAL_NOT_EXIST, jsonObject);
        } else {
            terminalService.deleteById(terminal.getId());
            List<TerminalAttr> terminalAttrList = terminalAttrService.findByTerminalId(request.getId());
            for (TerminalAttr terminalAttr : terminalAttrList) {
                terminalAttrService.deleteById(terminalAttr.getId());
            }
            return success(jsonObject);
        }
    }
}
