package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.client.request.*;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.console.util.RemoteDataBaseManager;
import cn.zucc.debug.macore.model.pojo.Host;
import cn.zucc.debug.macore.model.pojo.WaterObject;
import cn.zucc.debug.macore.model.service.HostService;
import cn.zucc.debug.macore.model.service.WaterObjectService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/host")
@Controller
public class HostController extends CommonController {

    @Autowired
    HostService hostService;
    @Autowired
    WaterObjectService waterObjectService;
    /**
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list() {
        List<Host> hostList = hostService.findAll();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", JSONUtil.fromList(hostList, "*"));
        return success(jsonObject);
    }

    /**
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestBody HostAddRequest request) {
        Host host = hostService.findByIpAndPortAndAccount(request.getIp(), request.getPort(), request.getAccount());
        JSONObject jsonObject = new JSONObject();
        if (host != null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_HOST_ALREADY_EXIST, jsonObject);
        } else {
            host = new Host();
            host.setIp(request.getIp());
            host.setAccount(request.getAccount());
            host.setPassword(request.getPassword());
            host.setPort(request.getPort());
            hostService.save(host);
            jsonObject.put("data", JSONUtil.fromObject(host, "*"));
            return success(jsonObject);
        }
    }

    /**
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody HostUpdateRequest request) {
        Host host = hostService.findById(request.getId());
        JSONObject jsonObject = new JSONObject();
        if (host == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_HOST_NOT_EXIST, jsonObject);
        } else {
            host.setIp(request.getIp());
            host.setAccount(request.getAccount());
            host.setPassword(request.getPassword());
            host.setPort(request.getPort());
            hostService.updateById(host);
            return success(jsonObject);
        }
    }

    /**
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestBody UserDeleteRequest request) {
        Host host = hostService.findById(request.getId());
        JSONObject jsonObject = new JSONObject();
        if (host == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_HOST_NOT_EXIST, jsonObject);
        } else {
            List<WaterObject> waterObjectList = waterObjectService.findByHostId(host.getId());
            if (waterObjectList != null && waterObjectList.size() > 0)  {
                for (WaterObject waterObject : waterObjectList) {
                    if (!RemoteDataBaseManager.deleteDatabase(hostService.findById(waterObject.getHostId()), waterObject.getDatabaseName())){
                        return responseJSON(MyError.ERROR_CODE_REMOTE_WRONG, MyError.MESSAGE_REMOTE_WRONG, jsonObject);
                    } else {
                        waterObjectService.deleteById(waterObject.getId());
                    }
                }
            }
            hostService.deleteById(host.getId());
            return success(jsonObject);
        }
    }
}
