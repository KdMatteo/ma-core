package cn.zucc.debug.macore.client.request;

import net.sf.json.JSONArray;

public class TerminalAddRequest {

    private Integer objectId;

    private Integer terminalIpId;

    private String name;

    private String code;

    private JSONArray attrs;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObject_id(Integer objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getTerminalIpId() {
        return terminalIpId;
    }

    public void setTerminal_ip_id(Integer terminalIpId) {
        this.terminalIpId = terminalIpId;
    }

    public JSONArray getAttrs() {
        return attrs;
    }

    public void setAttrs(JSONArray attrs) {
        this.attrs = attrs;
    }
}
