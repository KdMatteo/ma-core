package cn.zucc.debug.macore.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
public class UserController extends CommonController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(@RequestParam("xxx") String xxx) {
        return xxx;
    }
}
