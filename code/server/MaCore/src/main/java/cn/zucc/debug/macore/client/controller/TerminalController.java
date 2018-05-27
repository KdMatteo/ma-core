package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.client.request.*;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.model.pojo.*;
import cn.zucc.debug.macore.model.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/terminal")
@Controller
public class TerminalController extends CommonController {

    @Autowired
    TerminalService terminalService;
    @Autowired
    TerminalAttrService terminalAttrService;
    @Autowired
    TerminalIpService terminalIpService;
    @Autowired
    DeviceService deviceService;
    @Autowired
    DeviceAttrService deviceAttrService;
    @Autowired
    DeviceAttrTypeService deviceAttrTypeService;

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
            JSONObject object = JSONUtil.changeKey(JSONUtil.fromObject(terminal, "*"), JSONUtil.TYPE_UNDERLINE);
            TerminalIp terminalIp = terminalIpService.findById(terminal.getId());
            object.put("ip", terminalIp.getIp());
            object.put("port", terminalIp.getPort());
            object.put("attrs", JSONUtil.fromList(terminalAttrService.findByTerminalId(terminal.getId()),
                    "*", JSONUtil.TYPE_UNDERLINE));
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
            terminal.setTerminalIpId(request.getTerminalIpId());
            terminal.setCode(request.getCode());
            terminal.setName(request.getName());
            terminal.setObjectId(request.getObjectId());
            //terminal.setTerminalIpId(request.getTerminalIpId());
            terminalService.save(terminal);
            saveAttrs(request.getAttrs(), terminal);
            jsonObject.put("data", JSONUtil.changeKey(JSONUtil.fromObject(terminal, "*"), JSONUtil.TYPE_UNDERLINE));
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
            terminal.setName(request.getName());
            //terminal.setTerminalIpId(request.getTerminalIpId());
            terminal.setTerminalIpId(request.getTerminalIpId());
            terminalService.updateById(terminal);
            List<TerminalAttr> terminalAttrList = terminalAttrService.findByTerminalId(request.getId());
            for (TerminalAttr terminalAttr : terminalAttrList) {
                terminalAttrService.deleteById(terminalAttr.getId());
            }
            saveAttrs(request.getAttrs(), terminal);
            return success(jsonObject);
        }
    }

    private void saveAttrs(JSONArray attrs, Terminal terminal) {
        for (Object attrInfo : attrs) {
            TerminalAttr terminalAttr = new TerminalAttr();
            terminalAttr.setDeviceattrId((Integer) ((JSONObject)attrInfo).get("deviceattr_id"));
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

    /**
     * @return
     */
    @RequestMapping("/export")
    public ResponseEntity<String> export(@RequestParam("id") Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "export.xml");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        JSONObject jsonObject = new JSONObject();
        Terminal terminal = terminalService.findById(id);
        if (terminal == null) {
            String str = responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_TERMINAL_NOT_EXIST, jsonObject);
            return new ResponseEntity<String>(str, headers, HttpStatus.CREATED);
        } else {
            List<TerminalAttr> terminalAttrList = terminalAttrService.findByTerminalId(id);
            StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            stringBuilder.append("<Terminal Code=\"").append(terminal.getCode()).append("\">");
            if (terminalAttrList != null && terminalAttrList.size() > 0) {
                Map<String, StringBuilder> deviceXmlList = new HashMap<>();
                for (TerminalAttr terminalAttr : terminalAttrList) {
                    DeviceAttr attr = deviceAttrService.findById(terminalAttr.getDeviceattrId());
                    DeviceAttrType deviceAttrType = deviceAttrTypeService.findById(attr.getAttrtypeId());
                    Device device = deviceService.findById(attr.getDeviceId());
                    String dName = device.getCode();
                    if (device.getIndex() != null) {
                        dName = dName + device.getIndex();
                    }
                    StringBuilder xmlBuilder = deviceXmlList.get(dName);
                    if (xmlBuilder == null) {
                        xmlBuilder = new StringBuilder();
                    }
                    xmlBuilder.append("<Attribute Name=\"").append(deviceAttrType.getFieldName()).
                                    append("\" Address=\"").append(terminalAttr.getPlcAddress()).append("\"/>");
                    deviceXmlList.put(dName, xmlBuilder);
                }
                for (String dName : deviceXmlList.keySet()) {
                    stringBuilder.append("<Device Name=\"").append(dName).append("\">");
                    StringBuilder xmlBuilder = deviceXmlList.get(dName);
                    if (xmlBuilder != null) {
                        stringBuilder.append(xmlBuilder.toString());
                    }
                    stringBuilder.append("</Device>");
                }
            }
            stringBuilder.append("</Terminal>");
            return new ResponseEntity<>(stringBuilder.toString(), headers, HttpStatus.CREATED);

        }
    }

}
