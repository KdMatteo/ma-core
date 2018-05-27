package cn.zucc.debug.macore.client.request;

import net.sf.json.JSONArray;

public class TerminalUpdateRequest {

    private Integer id;

    private String name;

    private String code;

    private Integer terminalIpId;

    private JSONArray attrs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
