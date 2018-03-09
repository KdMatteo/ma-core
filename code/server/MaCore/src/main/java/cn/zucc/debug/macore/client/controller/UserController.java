package cn.zucc.debug.macore.client.controller;

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
        int errorCode = 0;
        String errorMessage = "";
        Host host = hostService.findByIpAndPortAndAccount(ip, port, account);
        JSONObject jsonObject = new JSONObject();
        if (host == null) {
            errorCode = 2;
            errorMessage = "account not exit";
        } else if (!host.getPassword().equals(password)){
            errorCode = 1;
            errorMessage = "wrong password";
        }
        return responseJSON(errorCode, errorMessage, jsonObject);
    }
}
