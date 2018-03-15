package cn.zucc.debug.macore.client.request;

import net.sf.json.JSONArray;

public class TerminalAddRequest {

    private Integer objectId;

    private String name;

    private String code;

    private String ip;

    private Integer port;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public JSONArray getAttrs() {
        return attrs;
    }

    public void setAttrs(JSONArray attrs) {
        this.attrs = attrs;
    }
}
