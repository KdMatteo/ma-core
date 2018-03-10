package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.macore.console.common.MyError;
import cn.zucc.debug.macore.model.pojo.Host;
import cn.zucc.debug.macore.model.service.HostService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
public class UserController extends CommonController {

    @Autowired
    HostService hostService;

    /**
     * 登录 test url:http://localhost:8080/user/login?ip=localhost&port=3306&account=rot&password=12345
     * @param ip
     * @param port
     * @param account
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam("ip") String ip, @RequestParam("port") Integer port,
                        @RequestParam("account") String account, @RequestParam("password") String password) {
        Host host = hostService.findByIpAndPortAndAccount(ip, port, account);
        JSONObject jsonObject = new JSONObject();
        if (host == null) {
            return responseJSON(MyError.ERROR_CODE_ALREADY_OR_NOT_EXIST, MyError.MESSAGE_ACCOUNT_NOT_EXIST, jsonObject);
        } else if (!host.getPassword().equals(password)){
            return responseJSON(MyError.ERROR_CODE_NOT_ACCESS, MyError.MESSAGE_WRONG_PASSWORD, jsonObject);
        }
        return success(jsonObject);
    }
}
