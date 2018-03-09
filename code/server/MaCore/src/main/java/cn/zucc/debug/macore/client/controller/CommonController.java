package cn.zucc.debug.macore.client.controller;

import cn.zucc.debug.frame.ssm.BaseController;
import net.sf.json.JSONObject;

public class CommonController extends BaseController {


    /**
     *
     * @param errorCode
     * @param errorMessage
     * @param jsonObject
     * @return
     */
    protected String responseJSON(int errorCode, String errorMessage, JSONObject jsonObject) {
        if (jsonObject == null) {
            jsonObject = new JSONObject();
        }
        jsonObject.put("errorCode", errorCode);
        jsonObject.put("errorMessage", errorMessage);
        return jsonObject.toString();
    }
}
