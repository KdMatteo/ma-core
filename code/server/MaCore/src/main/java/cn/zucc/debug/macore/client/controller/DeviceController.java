package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.client.request.*;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.model.pojo.DeviceAttr;
import cn.zucc.debug.macore.model.pojo.Group;
import cn.zucc.debug.macore.model.pojo.ObjectDevice;
import cn.zucc.debug.macore.model.pojo.WaterObject;
import cn.zucc.debug.macore.model.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/device")
//@SessionAttributes(value = "host_id",types={Integer.class})
@Controller
public class DeviceController extends CommonController {

    @Autowired
    ObjectDeviceService objectDeviceService;
    @Autowired
    DeviceAttrService deviceAttrService;
    @Autowired
    DeviceAttrTypeService deviceAttrTypeService;
    @Autowired
    DeviceTypeService deviceTypeService;

    /**
     * 获取运行单元列表
     *
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestBody DeviceListRequest request) {
        JSONObject jsonObject = new JSONObject();
        List<ObjectDevice> deviceList = objectDeviceService.findByObjectIdAndGroupId(request.getObjectId(),request.getGroupId());
        if (deviceList != null) {
            JSONArray deviceArray = new JSONArray();
            for (ObjectDevice device : deviceList) {
                JSONObject item = JSONUtil.fromObject(device, "*");
                List<DeviceAttr> deviceAttrList = deviceAttrService.findByDeviceId(device.getId());
                item.put("attrs", JSONUtil.fromList(deviceAttrList, "id, attrTypeId", JSONUtil.TYPE_UNDERLINE));
                deviceArray.add(item);
            }
            jsonObject.put("data", deviceArray);
        }
        return success(jsonObject);
    }

    /**
     * 添加运行单元
     *
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestBody GroupAddRequest request) {
        JSONObject jsonObject = new JSONObject();
        WaterObject waterObject = waterObjectService.findById(request.getObjectId());
        Group group = groupService.findByObjectIdAndName(request.getObjectId(), request.getName());
        if (group != null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_GROUP_ALREADY_EXIST, jsonObject);
        } else {
            group = new Group();
            group.setName(request.getName());
            group.setObjectId(request.getObjectId());
            groupService.save(group);
            return success(jsonObject);
        }
    }

//    /**
//     * 更新
//     *
//     * @return
//     */
//    @RequestMapping("/update")
//    @ResponseBody
//    public String update(@RequestBody GroupUpdateRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        Group group = groupService.findById(request.getId());
//        if (group == null) {
//            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_GROUP_NOT_EXIST, jsonObject);
//        } else {
//            group.setName(request.getName());
//            groupService.updateById(group);
//            return success(jsonObject);
//        }
//    }


    /**
     * 删除
     *
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestBody GroupDeleteRequest request) {
        JSONObject jsonObject = new JSONObject();
        Group group = groupService.findById(request.getId());
        if (group == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_GROUP_NOT_EXIST, jsonObject);
        } else {
            groupService.deleteById(request.getId());
            return success(jsonObject);
        }
    }
}
