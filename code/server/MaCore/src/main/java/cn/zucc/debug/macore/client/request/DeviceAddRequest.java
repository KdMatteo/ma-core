package cn.zucc.debug.macore.client.request;

import net.sf.json.JSONArray;

import java.util.List;

public class DeviceAddRequest {

    private Integer objectId;
    private Integer groupId;
    private Integer devicetypeId;
    private JSONArray attrs;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObject_id(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroup_id(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getDevicetypeId() {
        return devicetypeId;
    }

    public void setDevicetype_id(Integer devicetypeId) {
        this.devicetypeId = devicetypeId;
    }

    public JSONArray getAttrs() {
        return attrs;
    }

    public void setAttrs(JSONArray attrs) {
        this.attrs = attrs;
    }
}
