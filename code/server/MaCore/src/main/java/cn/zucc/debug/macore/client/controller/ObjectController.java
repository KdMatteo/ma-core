package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.console.common.MyError;
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
@SessionAttributes(value = "host_id",types={Integer.class})
@Controller
public class ObjectController extends CommonController {

    @Autowired
    WaterObjectService waterObjectService;

    @Autowired
    HostService hostService;

    /**
     * 获取水厂列表
     * http://localhost:8080/object/list?page={"size":5,"index":1}&search={"id":1,"name":"test"}
     *
     * @param hostId
     * @param page
     * @param search
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list(@ModelAttribute("host_id") Integer hostId, @RequestParam("page") String page, @RequestParam("search") String search) {
        JSONObject jsonObject = new JSONObject();
        JSONObject pageJson = JSONObject.fromObject(page);
        JSONObject searchJson = JSONObject.fromObject(search);
        List<WaterObject> waterObjectList = waterObjectService.findByHostIdPagerAndSearch(hostId, Integer.valueOf(pageJson.get("size").toString()),
                Integer.valueOf(pageJson.get("index").toString()), searchJson);
        if (waterObjectList != null) {
            jsonObject.put("data", JSONUtil.fromList(waterObjectList, "*"));
            jsonObject.put("page", pageJson);
        }
        return success(jsonObject);
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
        JSONObject jsonObject = new JSONObject();
        WaterObject waterObject = waterObjectService.findByHostIdAndDatabaseName(hostId, databaseName);
        if (waterObject != null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_OBJECT_ALREADY_EXIST, jsonObject);
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
                return success(jsonObject);
            } else {
                return responseJSON(MyError.ERROR_CODE_REMOTE_WRONG, MyError.MESSAGE_REMOTE_WRONG, jsonObject);
            }
        }
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
        JSONObject jsonObject = new JSONObject();
        WaterObject waterObject = waterObjectService.findById(id);
        if (waterObject == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_OBJECT_NOT_EXIST, jsonObject);
        } else if (!waterObject.getHostId().equals(hostId)) {
            return responseJSON(MyError.ERROR_CODE_NOT_ACCESS, MyError.MESSAGE_NO_ACCESS_TO_OBJECT_, jsonObject);
        } else {
            waterObject.setAddress(address);
            waterObject.setLinkman(linkman);
            waterObject.setMobile(mobile);
            waterObject.setName(name);
            waterObjectService.updateById(waterObject);
            return success(jsonObject);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@ModelAttribute("host_id") Integer hostId, @RequestParam("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        WaterObject waterObject = waterObjectService.findById(id);
        if (waterObject == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_OBJECT_NOT_EXIST, jsonObject);
        } else if (!waterObject.getHostId().equals(hostId)) {
            return responseJSON(MyError.ERROR_CODE_NOT_ACCESS, MyError.MESSAGE_NO_ACCESS_TO_OBJECT_, jsonObject);
        } else if (RemoteDataBaseManager.deleteDatabase(hostService.findById(hostId), waterObject.getDatabaseName())){
            waterObjectService.deleteById(waterObject.getId());
            return success(jsonObject);
        } else {
            return responseJSON(MyError.ERROR_CODE_REMOTE_WRONG, MyError.MESSAGE_REMOTE_WRONG, jsonObject);
        }
    }
}
