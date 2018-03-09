package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.console.util.RemoteDataBaseManager;
import cn.zucc.debug.macore.model.pojo.Host;
import cn.zucc.debug.macore.model.pojo.WaterObject;
import cn.zucc.debug.macore.model.service.HostService;
import cn.zucc.debug.macore.model.service.WaterObjectService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/object")
@SessionAttributes(value = "username",types={Integer.class})
@Controller
public class ObjectController extends CommonController {

    @Autowired
    WaterObjectService waterObjectService;

    @Autowired
    HostService hostService;

    /**
     * 获取水厂列表
     * http://localhost:8080/object/list?page={%22size%22:5,%22index%22:1}&search={}
     *
     * @param hostId
     * @param page
     * @param search
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list(@ModelAttribute("host_id") Integer hostId, @RequestParam("page") String page, @RequestParam("search") String search) {
        int errorCode = 0;
        String errorMessage = "";
        JSONObject jsonObject = new JSONObject();
        JSONObject pageJson = JSONObject.fromObject(page);
        JSONObject searchJson = JSONObject.fromObject(search);
        List<WaterObject> waterObjectList = waterObjectService.findByHostIdPager(hostId, Integer.valueOf(pageJson.get("size").toString()),
                Integer.valueOf(pageJson.get("index").toString()));
        if (waterObjectList != null) {
            jsonObject.put("data", JSONUtil.fromList(waterObjectList, "*"));
            jsonObject.put("page", pageJson);
        }
        return responseJSON(errorCode, errorMessage, jsonObject);
    }

    /**
     * 添加水厂（数据库）
     * test: http://localhost:8080/object/add?database_name=testcreate2&name=test2&address=xxx&linkman=xxx&mobile=123
     * @param hostId
     * @param databaseName
     * @param name
     * @param address
     * @param linkman
     * @param mobile
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@ModelAttribute("host_id") Integer hostId, @RequestParam("database_name") String databaseName,
                      @RequestParam("name") String name, @RequestParam("address") String address, @RequestParam("linkman") String linkman,
                      @RequestParam("mobile") String mobile) {
        int errorCode = 0;
        String errorMessage = "";
        JSONObject jsonObject = new JSONObject();
        WaterObject waterObject = waterObjectService.findByHostIdAndDatabaseName(hostId, databaseName);
        if (waterObject != null) {
            errorCode = 1;
            errorMessage = "this object already exit";
        } else {
            Host host = hostService.findById(hostId);
            if (RemoteDataBaseManager.createDataBase(host, databaseName) != null) {
                waterObject = new WaterObject();
                waterObject.setDatabaseName(databaseName);
                waterObject.setHostId(hostId);
                waterObject.setAddress(address);
                waterObject.setLinkman(linkman);
                waterObject.setMobile(mobile);
                waterObject.setName(name);
                waterObjectService.save(waterObject);
            } else {
                errorCode = 2;
                errorMessage = "create db wrong";
            }
        }
        return responseJSON(errorCode, errorMessage, jsonObject);
    }

    /**
     * 更新
     * http://localhost:8080/object/update?id=3&name=test3&address=xxx&linkman=xxx&mobile=123
     * @param hostId
     * @param id
     * @param name
     * @param address
     * @param linkman
     * @param mobile
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@ModelAttribute("host_id") Integer hostId, @RequestParam("id") Integer id, @RequestParam("name") String name,
                         @RequestParam("address") String address, @RequestParam("linkman") String linkman, @RequestParam("mobile") String mobile) {
        int errorCode = 0;
        String errorMessage = "";
        JSONObject jsonObject = new JSONObject();
        WaterObject waterObject = waterObjectService.findById(id);
        if (waterObject == null) {
            errorCode = 1;
            errorMessage = "this object is not exist";
        } else if (!waterObject.getHostId().equals(hostId)) {
            errorCode = 2;
            errorMessage = "you are not access to control this object";
        } else {
            waterObject.setAddress(address);
            waterObject.setLinkman(linkman);
            waterObject.setMobile(mobile);
            waterObject.setName(name);
            waterObjectService.updateById(waterObject);
        }
        return responseJSON(errorCode, errorMessage, jsonObject);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@ModelAttribute("host_id") Integer hostId, @RequestParam("id") Integer id) {
        int errorCode = 0;
        String errorMessage = "";
        JSONObject jsonObject = new JSONObject();
        WaterObject waterObject = waterObjectService.findById(id);
        if (waterObject == null) {
            errorCode = 1;
            errorMessage = "this object is not exist";
        } else if (!waterObject.getHostId().equals(hostId)) {
            errorCode = 2;
            errorMessage = "you are not access to control this object";
        } else if (RemoteDataBaseManager.deleteDatabase(hostService.findById(hostId), waterObject.getDatabaseName())){
            waterObjectService.deleteById(waterObject.getId());
        } else {
            errorCode = 3;
            errorMessage = "delete db wrong";
        }
        return responseJSON(errorCode, errorMessage, jsonObject);
    }
}
