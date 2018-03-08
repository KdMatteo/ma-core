package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.macore.model.pojo.Host;
import cn.zucc.debug.macore.model.service.HostService;
import cn.zucc.debug.macore.model.service.WaterObjectService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/object")
@Controller
public class ObjectController extends CommonController {

    @Autowired
    WaterObjectService waterObjectService;


    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestParam("database_name") String databaseName, @RequestParam("name") String name,
                      @RequestParam("address") String address, @RequestParam("linkman") String linkman,
                      @RequestParam("mobile") String mobile, @RequestParam("host_id") Integer hostId) {
        int errorCode = 0;
        String errorMessage = "";
        JSONObject jsonObject = new JSONObject();

        return responseJSON(errorCode, errorMessage, jsonObject);
    }
}
