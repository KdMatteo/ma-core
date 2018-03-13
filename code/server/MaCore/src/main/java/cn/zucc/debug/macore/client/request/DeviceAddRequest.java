package cn.zucc.debug.macore.client.request;

import java.util.List;

public class DeviceAddRequest {

    private Integer objectId;
    private Integer groupId;
    private Integer deviceTypeId;
    private List<Integer> attrs;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public List<Integer> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<Integer> attrs) {
        this.attrs = attrs;
    }
}
