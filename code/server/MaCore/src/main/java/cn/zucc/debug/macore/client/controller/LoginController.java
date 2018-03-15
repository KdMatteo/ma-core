package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.macore.client.request.LoginRequest;
import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.model.pojo.User;
import cn.zucc.debug.macore.model.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")
@Controller
public class LoginController extends CommonController {

    @Autowired
    UserService userService;

    /**
     * 登录 test url:http://localhost:8080/user/login?ip=localhost&port=3306&account=rot&password=12345
     * @return
     */
    @RequestMapping("")
    @ResponseBody
    public String login(@RequestBody LoginRequest request) {
        User user = userService.findByAccount(request.getAccount());
        JSONObject jsonObject = new JSONObject();
        if (user == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_ACCOUNT_NOT_EXIST, jsonObject);
        } else if (!user.getPassword().equals(request.getPassword())){
            return responseJSON(MyError.ERROR_CODE_NOT_ACCESS, MyError.MESSAGE_WRONG_PASSWORD, jsonObject);
        }
        return success(jsonObject);
    }
}
