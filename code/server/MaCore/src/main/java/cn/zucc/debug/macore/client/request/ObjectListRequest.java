package cn.zucc.debug.macore.client.request;

import net.sf.json.JSONObject;

public class ObjectListRequest {

    private Integer hostId;

    private Page page;

    private JSONObject search;

    public Integer getHostId() {
        return hostId;
    }

    public void setHost_id(Integer hostId) {
        this.hostId = hostId;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public JSONObject getSearch() {
        return search;
    }

    public void setSearch(JSONObject search) {
        this.search = search;
    }
}
