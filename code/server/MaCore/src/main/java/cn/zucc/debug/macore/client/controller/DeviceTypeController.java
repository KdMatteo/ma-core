package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.model.pojo.DeviceType;
import cn.zucc.debug.macore.model.pojo.Group;
import cn.zucc.debug.macore.model.pojo.WaterObject;
import cn.zucc.debug.macore.model.service.DeviceTypeService;
import cn.zucc.debug.macore.model.service.GroupService;
import cn.zucc.debug.macore.model.service.WaterObjectService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/deviceType")
@SessionAttributes(value = "host_id",types={Integer.class})
@Controller
public class DeviceTypeController extends CommonController {

    @Autowired
    DeviceTypeService deviceTypeService;

    /**
     * 获取列表
     * http://localhost:8080/deviceType/list
     *
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list() {
        JSONObject jsonObject = new JSONObject();
        List<DeviceType> deviceTypeList = deviceTypeService.findAll();
        if (deviceTypeList != null) {
            jsonObject.put("data", JSONUtil.fromList(deviceTypeList, "*"));
        }
        return success(jsonObject);
    }

    /**
     * 添加
     * http://localhost:8080/deviceType/add?name=pump&table_name=pump&multi=1
     *
     * @param name
     * @param multi
     * @param tableName
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestParam("name") String name, @RequestParam("multi") Integer multi,
                      @RequestParam("table_name") String tableName) {
        JSONObject jsonObject = new JSONObject();
        DeviceType deviceType = deviceTypeService.findByTableName(tableName);
        if (deviceType != null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_TYPE_ALREADY_EXIST, jsonObject);
        } else {
            deviceType = new DeviceType();
            deviceType.setMulti(multi);
            deviceType.setName(name);
            deviceType.setTableName(tableName);
            deviceTypeService.save(deviceType);
            return success(jsonObject);
        }
    }

    /**
     * 更新
     * http://localhost:8080/deviceType/update?id=1&name=pump&table_name=t_pump&multi=1
     *
     * @param name
     * @param multi
     * @param tableName
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam("id") Integer id, @RequestParam("name") String name,
                         @RequestParam("multi") Integer multi, @RequestParam("table_name") String tableName) {
        JSONObject jsonObject = new JSONObject();
        DeviceType deviceType = deviceTypeService.findById(id);
        if (deviceType == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_TYPE_NOT_EXIST, jsonObject);
        } else {
            deviceType.setMulti(multi);
            deviceType.setName(name);
            deviceType.setTableName(tableName);
            deviceTypeService.updateById(deviceType);
            return success(jsonObject);
        }
    }


    /**
     * 删除
     * http://localhost:8080/deviceType/delete?id=2
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        DeviceType deviceType = deviceTypeService.findById(id);
        if (deviceType == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_TYPE_NOT_EXIST, jsonObject);
        } else {
            deviceTypeService.deleteById(id);
            return success(jsonObject);
        }
    }
}
