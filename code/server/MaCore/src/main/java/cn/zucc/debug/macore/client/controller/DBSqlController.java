package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.helper.JSONUtil;
import cn.zucc.debug.macore.client.request.DBSqlAddRequest;
import cn.zucc.debug.macore.client.request.DBSqlListRequest;
import cn.zucc.debug.macore.model.pojo.DBSql;
import cn.zucc.debug.macore.model.service.DBSqlService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/dbSql")
@Controller
public class DBSqlController extends CommonController {

    @Autowired
    DBSqlService dbSqlService;

    /**
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public String list(@RequestBody DBSqlListRequest request) {
        List<DBSql> dbSqlList = dbSqlService.findBySearch(request.getPage().getSize(), request.getPage().getIndex(), request.getSearch());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", JSONUtil.fromList(dbSqlList, "*", JSONUtil.TYPE_UNDERLINE));
        return success(jsonObject);
    }

    /**
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public String add(@RequestBody DBSqlAddRequest request) {
        JSONObject jsonObject = new JSONObject();
        DBSql dbSql = new DBSql();
        dbSql.setSqlString(request.getSqlString());
        dbSqlService.save(dbSql);
        return success(jsonObject);
    }
}
