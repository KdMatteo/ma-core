package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.client.request.ObjectAddRequest;
import cn.zucc.debug.macore.client.request.ObjectDeleteRequest;
import cn.zucc.debug.macore.client.request.ObjectListRequest;
import cn.zucc.debug.macore.client.request.ObjectUpdateRequest;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.console.util.RemoteDataBaseManager;
import cn.zucc.debug.macore.model.pojo.Host;
import cn.zucc.debug.macore.model.pojo.WaterObject;
import cn.zucc.debug.macore.model.service.HostService;
import cn.zucc.debug.macore.model.service.WaterObjectService;
import net.sf.json.JSONArray;
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
     *
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list(@ModelAttribute("host_id") Integer hostId, @RequestBody ObjectListRequest request) {
        JSONObject jsonObject = new JSONObject();
        JSONObject pageJson = JSONObject.fromObject(request.getPage());
        JSONObject searchJson = request.getSearch();
        Integer total = waterObjectService.countByHostIdAndSearch(hostId, searchJson);
        List<WaterObject> waterObjectList = waterObjectService.findByHostIdPagerAndSearch(hostId, Integer.valueOf(pageJson.get("size").toString()),
                Integer.valueOf(pageJson.get("index").toString()), searchJson);
        if (waterObjectList != null) {
            jsonObject.put("data", JSONUtil.fromList(waterObjectList, "*", JSONUtil.TYPE_UNDERLINE));
            pageJson.put("total", total);
            jsonObject.put("page", pageJson);
        }
        return success(jsonObject);
    }

    /**
     * 添加水厂（数据库）
     *
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@ModelAttribute("host_id") Integer hostId, @RequestBody ObjectAddRequest request) {
        JSONObject jsonObject = new JSONObject();
        WaterObject waterObject = waterObjectService.findByHostIdAndDatabaseName(hostId, request.getDatabaseName());
        if (waterObject != null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_OBJECT_ALREADY_EXIST, jsonObject);
        } else {
            Host host = hostService.findById(hostId);
            if (RemoteDataBaseManager.createDataBase(host, request.getDatabaseName()) != null) {
                waterObject = new WaterObject();
                waterObject.setDatabaseName(request.getDatabaseName());
                waterObject.setHostId(hostId);
                waterObject.setAddress(request.getAddress());
                waterObject.setLinkman(request.getLinkman());
                waterObject.setMobile(request.getMobile());
                waterObject.setName(request.getName());
                waterObjectService.save(waterObject);
                return success(jsonObject);
            } else {
                return responseJSON(MyError.ERROR_CODE_REMOTE_WRONG, MyError.MESSAGE_REMOTE_WRONG, jsonObject);
            }
        }
    }

    /**
     * 更新
     *
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@ModelAttribute("host_id") Integer hostId, @RequestBody ObjectUpdateRequest request) {
        JSONObject jsonObject = new JSONObject();
        WaterObject waterObject = waterObjectService.findById(request.getId());
        if (waterObject == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_OBJECT_NOT_EXIST, jsonObject);
        } else if (!waterObject.getHostId().equals(hostId)) {
            return responseJSON(MyError.ERROR_CODE_NOT_ACCESS, MyError.MESSAGE_NO_ACCESS_TO_OBJECT_, jsonObject);
        } else {
            waterObject.setAddress(request.getAddress());
            waterObject.setLinkman(request.getLinkman());
            waterObject.setMobile(request.getMobile());
            waterObject.setName(request.getName());
            waterObjectService.updateById(waterObject);
            return success(jsonObject);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@ModelAttribute("host_id") Integer hostId, @RequestBody ObjectDeleteRequest request) {
        JSONObject jsonObject = new JSONObject();
        WaterObject waterObject = waterObjectService.findById(request.getId());
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
