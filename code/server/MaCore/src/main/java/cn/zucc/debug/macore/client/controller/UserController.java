package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.client.request.UserAddRequest;
import cn.zucc.debug.macore.client.request.UserDeleteRequest;
import cn.zucc.debug.macore.client.request.UserUpdateRequest;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.model.pojo.User;
import cn.zucc.debug.macore.model.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController extends CommonController {

    @Autowired
    UserService userService;

    /**
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list() {
        List<User> userList = userService.findAll();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", JSONUtil.fromList(userList, "*"));
        return success(jsonObject);
    }

    /**
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestBody UserAddRequest request) {
        User user = userService.findByAccount(request.getAccount());
        JSONObject jsonObject = new JSONObject();
        if (user != null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_ACCOUNT_ALREADY_EXIST, jsonObject);
        } else {
            user = new User();
            user.setAccount(request.getAccount());
            user.setPassword(request.getPassword());
            userService.save(user);
            jsonObject.put("data", JSONUtil.fromObject(user, "*"));
            return success(jsonObject);
        }
    }

    /**
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody UserUpdateRequest request) {
        User user = userService.findById(request.getId());
        JSONObject jsonObject = new JSONObject();
        if (user == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_ACCOUNT_NOT_EXIST, jsonObject);
        } else {
            user.setAccount(request.getAccount());
            user.setPassword(request.getPassword());
            userService.updateById(user);
            return success(jsonObject);
        }
    }

    /**
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestBody UserDeleteRequest request) {
        User user = userService.findById(request.getId());
        JSONObject jsonObject = new JSONObject();
        if (user == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_ACCOUNT_NOT_EXIST, jsonObject);
        } else {
            userService.deleteById(user.getId());
            return success(jsonObject);
        }
    }
}
