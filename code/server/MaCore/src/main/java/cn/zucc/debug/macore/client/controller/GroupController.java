package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.client.request.GroupAddRequest;
import cn.zucc.debug.macore.client.request.GroupDeleteRequest;
import cn.zucc.debug.macore.client.request.GroupListRequest;
import cn.zucc.debug.macore.client.request.GroupUpdateRequest;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.model.pojo.Group;
import cn.zucc.debug.macore.model.pojo.WaterObject;
import cn.zucc.debug.macore.model.service.GroupService;
import cn.zucc.debug.macore.model.service.WaterObjectService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/group")
@SessionAttributes(value = "host_id",types={Integer.class})
@Controller
public class GroupController extends CommonController {

    @Autowired
    WaterObjectService waterObjectService;

    @Autowired
    GroupService groupService;

    /**
     * 获取运行单元列表
     *
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestBody GroupListRequest request) {
        JSONObject jsonObject = new JSONObject();
        JSONObject pageJson = JSONObject.fromObject(request.getPage());
        JSONObject searchJson = JSONObject.fromObject(request.getSearch());
        List<Group> groupList = groupService.findByObjectIdPagerAndSearch(request.getObjectId(), Integer.valueOf(pageJson.get("size").toString()),
                Integer.valueOf(pageJson.get("index").toString()), searchJson);
        if (groupList != null) {
            jsonObject.put("data", JSONUtil.fromList(groupList, "*"));
            jsonObject.put("page", pageJson);
        }
        return success(jsonObject);
    }

    /**
     * 添加运行单元
     *
     * @param hostId
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@ModelAttribute("host_id") Integer hostId, @RequestBody GroupAddRequest request) {
        JSONObject jsonObject = new JSONObject();
        WaterObject waterObject = waterObjectService.findById(request.getObjectId());
        Group group = groupService.findByObjectIdAndName(request.getObjectId(), request.getName());
        if (waterObject == null || !waterObject.getHostId().equals(hostId)) {
            return responseJSON(MyError.ERROR_CODE_NOT_ACCESS, MyError.MESSAGE_NO_ACCESS_TO_GROUP, jsonObject);
        } else if (group != null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_GROUP_ALREADY_EXIST, jsonObject);
        } else {
            group = new Group();
            group.setName(request.getName());
            group.setObjectId(request.getObjectId());
            groupService.save(group);
            return success(jsonObject);
        }
    }

    /**
     * 更新
     *
     * @param hostId
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@ModelAttribute("host_id") Integer hostId, @RequestBody GroupUpdateRequest request) {
        JSONObject jsonObject = new JSONObject();
        Group group = groupService.findById(request.getId());
        if (group == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_GROUP_NOT_EXIST, jsonObject);
        } else {
            if (group.getObjectId() == null) {
                return responseJSON(MyError.ERROR_CODE_NOT_ACCESS, MyError.MESSAGE_NO_ACCESS_TO_GROUP, jsonObject);
            } else {
                WaterObject waterObject = waterObjectService.findById(group.getObjectId());
                if (waterObject == null || waterObject.getHostId() == null || !waterObject.getHostId().equals(hostId)) {
                    return responseJSON(MyError.ERROR_CODE_NOT_ACCESS, MyError.MESSAGE_NO_ACCESS_TO_GROUP, jsonObject);
                } else {
                    group.setName(request.getName());
                    groupService.updateById(group);
                    return success(jsonObject);
                }
            }
        }
    }


    /**
     * 删除
     *
     * @param hostId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@ModelAttribute("host_id") Integer hostId, @RequestBody GroupDeleteRequest request) {
        JSONObject jsonObject = new JSONObject();
        Group group = groupService.findById(request.getId());
        if (group == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_GROUP_NOT_EXIST, jsonObject);
        } else {
            if (group.getObjectId() == null) {
                return responseJSON(MyError.ERROR_CODE_NOT_ACCESS, MyError.MESSAGE_NO_ACCESS_TO_GROUP, jsonObject);
            } else {
                WaterObject waterObject = waterObjectService.findById(group.getObjectId());
                if (waterObject == null || waterObject.getHostId() == null || !waterObject.getHostId().equals(hostId)) {
                    return responseJSON(MyError.ERROR_CODE_NOT_ACCESS, MyError.MESSAGE_NO_ACCESS_TO_GROUP, jsonObject);
                } else {
                    groupService.deleteById(request.getId());
                    return success(jsonObject);
                }
            }
        }
    }
}
