package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
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
     * http://localhost:8080/group/list?page={%22size%22:5,%22index%22:1}&search={}
     *
     * @param objectId
     * @param page
     * @param search
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestParam("object_id") Integer objectId, @RequestParam("page") String page, @RequestParam("search") String search) {
        JSONObject jsonObject = new JSONObject();
        JSONObject pageJson = JSONObject.fromObject(page);
        JSONObject searchJson = JSONObject.fromObject(search);
        List<Group> groupList = groupService.findByObjectIdPagerAndSearch(objectId, Integer.valueOf(pageJson.get("size").toString()),
                Integer.valueOf(pageJson.get("index").toString()), searchJson);
        if (groupList != null) {
            jsonObject.put("data", JSONUtil.fromList(groupList, "*"));
            jsonObject.put("page", pageJson);
        }
        return success(jsonObject);
    }

    /**
     * 添加运行单元
     * @param hostId
     * @param objectId
     * @param name
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@ModelAttribute("host_id") Integer hostId, @RequestParam("object_id") Integer objectId,
                      @RequestParam("name") String name) {
        JSONObject jsonObject = new JSONObject();
        WaterObject waterObject = waterObjectService.findById(objectId);
        Group group = groupService.findByObjectIdAndName(objectId, name);
        if (waterObject == null || !waterObject.getHostId().equals(hostId)) {
            return responseJSON(MyError.ERROR_CODE_NOT_ACCESS, MyError.MESSAGE_NO_ACCESS_TO_GROUP, jsonObject);
        } else if (group != null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_GROUP_ALREADY_EXIST, jsonObject);
        } else {
            group = new Group();
            group.setName(name);
            group.setObjectId(objectId);
            groupService.save(group);
            return success(jsonObject);
        }
    }

    /**
     * 更新
     * http://localhost:8080/group/update?id=1&name=yyy
     * @param hostId
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@ModelAttribute("host_id") Integer hostId, @RequestParam("id") Integer id,
                      @RequestParam("name") String name) {
        JSONObject jsonObject = new JSONObject();
        Group group = groupService.findById(id);
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
                    group.setName(name);
                    groupService.updateById(group);
                    return success(jsonObject);
                }
            }
        }
    }


    /**
     * 删除
     * http://localhost:8080/group/delete?id=1
     * @param hostId
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@ModelAttribute("host_id") Integer hostId, @RequestParam("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        Group group = groupService.findById(id);
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
                    groupService.deleteById(id);
                    return success(jsonObject);
                }
            }
        }
    }
}
