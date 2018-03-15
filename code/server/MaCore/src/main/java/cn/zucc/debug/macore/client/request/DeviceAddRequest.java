package cn.zucc.debug.macore.client.request;

import java.util.List;

public class DeviceAddRequest {

    private Integer objectId;
    private Integer groupId;
    private Integer devicetypeId;
    private List<Integer> attrs;

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

    public List<Integer> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<Integer> attrs) {
        this.attrs = attrs;
    }
}
