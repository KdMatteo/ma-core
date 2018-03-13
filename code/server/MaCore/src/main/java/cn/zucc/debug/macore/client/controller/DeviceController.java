package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.dbcreate.TableCreator;
import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.client.request.*;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.console.util.RemoteDataBaseManager;
import cn.zucc.debug.macore.model.pojo.*;
import cn.zucc.debug.macore.model.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/device")
//@SessionAttributes(value = "host_id",types={Integer.class})
@Controller
public class DeviceController extends CommonController {
    @Autowired
    HostService hostService;
    @Autowired
    WaterObjectService waterObjectService;
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
    public String add(@RequestBody DeviceAddRequest request) {
        JSONObject jsonObject = new JSONObject();
        WaterObject waterObject = waterObjectService.findById(request.getObjectId());
        DeviceType deviceType = deviceTypeService.findById(request.getDeviceTypeId());
        List<ObjectDevice> objectDeviceList = objectDeviceService.findByObjectIdAndGroupIdAndDevicetypeId(request.getObjectId(),
                request.getGroupId(), request.getDeviceTypeId());
        if (waterObject == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_OBJECT_NOT_EXIST, jsonObject);
        } else if (deviceType == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_TYPE_NOT_EXIST, jsonObject);
        } else {
            Host host = hostService.findById(waterObject.getHostId());
            TableCreator tableCreator = RemoteDataBaseManager.connectDataBase(host, waterObject.getDatabaseName());
            String tableName = deviceType.getTableName();
            List<DeviceAttrType> deviceAttrTypeList = new ArrayList<>();
            Integer index = null;
            for (Integer attrTypeId : request.getAttrs()) {
                deviceAttrTypeList.add(deviceAttrTypeService.findById(attrTypeId));
            }
            if (host == null) {
                return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_HOST_NOT_EXIST, jsonObject);
            } else if (objectDeviceList == null || objectDeviceList.size() > 0){
                if (deviceType.getMulti() == 1) {
                    index = 1;
                    tableName = tableName + "" + index;
                }
            } else if (deviceType.getMulti() != 1){
                return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_TYPE_NOT_EXIST, jsonObject);
            } else {
                index = objectDeviceList.get(0).getIndex() + 1;
                tableName = tableName + "" + index;
            }
            if (RemoteDataBaseManager.createTable(tableCreator, tableName, deviceAttrTypeList)) {
                ObjectDevice objectDevice = new ObjectDevice();
                objectDevice.setCode(deviceType.getTableName());
                objectDevice.setIndex(index);
                objectDevice.setObjectId(request.getObjectId());
                objectDevice.setDevicetypeId(request.getDeviceTypeId());
                objectDevice.setGroupId(request.getGroupId());
                objectDeviceService.save(objectDevice);
                return success(jsonObject);
            } else {
                return responseJSON(MyError.ERROR_CODE_REMOTE_WRONG, MyError.MESSAGE_REMOTE_WRONG, jsonObject);
            }

        }
    }




    /**
     * 删除
     *
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestBody DeviceDeleteRequest request) {
        JSONObject jsonObject = new JSONObject();
        ObjectDevice objectDevice = objectDeviceService.findById(request.getId());
        if (objectDevice == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_DEVICE_NOT_EXIST, jsonObject);
        } else {
            WaterObject waterObject = waterObjectService.findById(objectDevice.getObjectId());
            Host host = hostService.findById(waterObject.getHostId());
            if (RemoteDataBaseManager.connectDataBase(host, waterObject.getDatabaseName()).deleteTable(objectDevice.getCode())) {
                objectDeviceService.deleteById(request.getId());
                List<DeviceAttr> attrList = deviceAttrService.findByDeviceId(request.getId());
                if (attrList != null && attrList.size() > 0) {
                    for (DeviceAttr attr : attrList) {
                        deviceAttrService.deleteById(attr.getId());
                    }
                }
                return success(jsonObject);
            } else {
                return responseJSON(MyError.ERROR_CODE_REMOTE_WRONG, MyError.MESSAGE_REMOTE_WRONG, jsonObject);
            }
        }
    }
}
