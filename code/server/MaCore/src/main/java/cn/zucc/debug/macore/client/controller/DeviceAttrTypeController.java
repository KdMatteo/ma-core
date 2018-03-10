package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.model.pojo.DeviceAttrType;
import cn.zucc.debug.macore.model.pojo.DeviceType;
import cn.zucc.debug.macore.model.service.DeviceAttrTypeService;
import cn.zucc.debug.macore.model.service.DeviceTypeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@RequestMapping("/deviceAttrType")
@SessionAttributes(value = "host_id",types={Integer.class})
@Controller
public class DeviceAttrTypeController extends CommonController {

    @Autowired
    DeviceAttrTypeService deviceAttrTypeService;

    /**
     * 获取列表
     * http://localhost:8080/deviceAttrType/list?devicetype_id=1
     *
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestParam("devicetype_id") Integer devicetypeId) {
        JSONObject jsonObject = new JSONObject();
        List<DeviceAttrType> deviceAttrTypeList = deviceAttrTypeService.findByDevicetypeId(devicetypeId);
        if (deviceAttrTypeList != null) {
            jsonObject.put("data", JSONUtil.fromList(deviceAttrTypeList, "*"));
        }
        return success(jsonObject);
    }

    /**
     * 添加
     * http://localhost:8080/deviceAttrType/add?devicetype_id=1&name=param3&field_name=c_param3&data_type=int&label=param3
     *
     * @param devicetypeId
     * @param name
     * @param label
     * @param dataType
     * @param fieldName
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestParam("devicetype_id") Integer devicetypeId, @RequestParam("name") String name,
                      @RequestParam("label") String label, @RequestParam("data_type") String dataType,
                      @RequestParam("field_name") String fieldName) {
        JSONObject jsonObject = new JSONObject();
        DeviceAttrType deviceAttrType = deviceAttrTypeService.findByDevicetypeIdAndFieldName(devicetypeId, fieldName);
        if (deviceAttrType != null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_ATTR_TYPE_ALREADY_EXIST, jsonObject);
        } else {
            deviceAttrType = new DeviceAttrType();
            deviceAttrType.setDataType(dataType);
            deviceAttrType.setDevicetypeId(devicetypeId);
            deviceAttrType.setFieldName(fieldName);
            deviceAttrType.setLabel(label);
            deviceAttrType.setName(name);
            deviceAttrTypeService.save(deviceAttrType);
            return success(jsonObject);
        }
    }

    /**
     * 更新
     * http://localhost:8080/deviceAttrType/update?id=3&name=param3&field_name=c_param3&data_type=varchar&label=param3
     *
     * @param id
     * @param name
     * @param label
     * @param dataType
     * @param fieldName
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam("id") Integer id, @RequestParam("name") String name,
                      @RequestParam("label") String label, @RequestParam("data_type") String dataType,
                      @RequestParam("field_name") String fieldName) {
        JSONObject jsonObject = new JSONObject();
        DeviceAttrType deviceAttrType = deviceAttrTypeService.findById(id);
        if (deviceAttrType == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_ATTR_TYPE_NOT_EXIST, jsonObject);
        } else {
            deviceAttrType.setDataType(dataType);
            deviceAttrType.setFieldName(fieldName);
            deviceAttrType.setLabel(label);
            deviceAttrType.setName(name);
            deviceAttrTypeService.updateById(deviceAttrType);
            return success(jsonObject);
        }
    }


    /**
     * 删除
     * http://localhost:8080/deviceAttrType/delete?id=4
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        DeviceAttrType deviceAttrType = deviceAttrTypeService.findById(id);
        if (deviceAttrType == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_ATTR_TYPE_NOT_EXIST, jsonObject);
        } else {
            deviceAttrTypeService.deleteById(id);
            return success(jsonObject);
        }
    }
}
