package cn.zucc.debug.macore.client.request;

import net.sf.json.JSONArray;

public class DeviceUpdateRequest {

    private Integer id;
    private JSONArray attrs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JSONArray getAttrs() {
        return attrs;
    }

    public void setAttrs(JSONArray attrs) {
        this.attrs = attrs;
    }
}
