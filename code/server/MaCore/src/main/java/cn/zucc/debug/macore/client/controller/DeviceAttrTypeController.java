package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.client.request.DeviceAttrTypeAddRequest;
import cn.zucc.debug.macore.client.request.DeviceAttrTypeDeleteRequest;
import cn.zucc.debug.macore.client.request.DeviceAttrTypeListRequest;
import cn.zucc.debug.macore.client.request.DeviceAttrTypeUpdateRequest;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.model.pojo.DeviceAttrType;
import cn.zucc.debug.macore.model.service.DeviceAttrTypeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/deviceAttrType")
@SessionAttributes(value = "host_id",types={Integer.class})
@Controller
public class DeviceAttrTypeController extends CommonController {

    @Autowired
    DeviceAttrTypeService deviceAttrTypeService;

    /**
     * 获取列表
     *
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestBody DeviceAttrTypeListRequest request) {
        JSONObject jsonObject = new JSONObject();
        List<DeviceAttrType> deviceAttrTypeList = deviceAttrTypeService.findByDevicetypeId(request.getDevicetypeId());
        if (deviceAttrTypeList != null) {
            jsonObject.put("data", JSONUtil.fromList(deviceAttrTypeList, "*"));
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
    public String add(@RequestBody DeviceAttrTypeAddRequest request) {
        JSONObject jsonObject = new JSONObject();
        DeviceAttrType deviceAttrType = deviceAttrTypeService.findByDevicetypeIdAndFieldName(request.getDevicetypeId(),
                request.getFieldName());
        if (deviceAttrType != null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_ATTR_TYPE_ALREADY_EXIST, jsonObject);
        } else {
            deviceAttrType = new DeviceAttrType();
            deviceAttrType.setDataType(request.getDataType());
            deviceAttrType.setDevicetypeId(request.getDevicetypeId());
            deviceAttrType.setFieldName(request.getFieldName());
            deviceAttrType.setLabel(request.getLabel());
            deviceAttrType.setName(request.getName());
            deviceAttrTypeService.save(deviceAttrType);
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
    public String update(@RequestBody DeviceAttrTypeUpdateRequest request) {
        JSONObject jsonObject = new JSONObject();
        DeviceAttrType deviceAttrType = deviceAttrTypeService.findById(request.getId());
        if (deviceAttrType == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_ATTR_TYPE_NOT_EXIST, jsonObject);
        } else {
            deviceAttrType.setDataType(request.getDataType());
            deviceAttrType.setFieldName(request.getFieldName());
            deviceAttrType.setLabel(request.getLabel());
            deviceAttrType.setName(request.getName());
            deviceAttrTypeService.updateById(deviceAttrType);
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
    public String delete(@RequestBody DeviceAttrTypeDeleteRequest request) {
        JSONObject jsonObject = new JSONObject();
        DeviceAttrType deviceAttrType = deviceAttrTypeService.findById(request.getId());
        if (deviceAttrType == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_ATTR_TYPE_NOT_EXIST, jsonObject);
        } else {
            deviceAttrTypeService.deleteById(request.getId());
            return success(jsonObject);
        }
    }
}
