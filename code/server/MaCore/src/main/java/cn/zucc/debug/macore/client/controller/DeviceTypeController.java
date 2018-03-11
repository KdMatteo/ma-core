package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.client.request.DeviceTypeAddRequest;
import cn.zucc.debug.macore.client.request.DeviceTypeDeleteRequest;
import cn.zucc.debug.macore.client.request.DeviceTypeUpdateRequest;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.model.pojo.DeviceType;
import cn.zucc.debug.macore.model.service.DeviceTypeService;
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
     *
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestBody DeviceTypeAddRequest request) {
        JSONObject jsonObject = new JSONObject();
        DeviceType deviceType = deviceTypeService.findByTableName(request.getTableName());
        if (deviceType != null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_TYPE_ALREADY_EXIST, jsonObject);
        } else {
            deviceType = new DeviceType();
            deviceType.setMulti(request.getMulti());
            deviceType.setName(request.getName());
            deviceType.setTableName(request.getTableName());
            deviceTypeService.save(deviceType);
            return success(jsonObject);
        }
    }

    /**
     * 更新
     *
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody DeviceTypeUpdateRequest request) {
        JSONObject jsonObject = new JSONObject();
        DeviceType deviceType = deviceTypeService.findById(request.getId());
        if (deviceType == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_TYPE_NOT_EXIST, jsonObject);
        } else {
            deviceType.setMulti(request.getMulti());
            deviceType.setName(request.getName());
            deviceType.setTableName(request.getTableName());
            deviceTypeService.updateById(deviceType);
            return success(jsonObject);
        }
    }


    /**
     * 删除
     *
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestBody DeviceTypeDeleteRequest request) {
        JSONObject jsonObject = new JSONObject();
        DeviceType deviceType = deviceTypeService.findById(request.getId());
        if (deviceType == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_TYPE_NOT_EXIST, jsonObject);
        } else {
            deviceTypeService.deleteById(request.getId());
            return success(jsonObject);
        }
    }
}
