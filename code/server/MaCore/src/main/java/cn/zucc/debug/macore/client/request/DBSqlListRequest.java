package cn.zucc.debug.macore.client.request;

import net.sf.json.JSONObject;

public class DBSqlListRequest {

    private Page page;

    private JSONObject search;

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
