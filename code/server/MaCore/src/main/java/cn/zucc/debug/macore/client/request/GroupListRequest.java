package cn.zucc.debug.macore.client.request;

public class GroupListRequest {

    private Integer objectId;

    private String page;

    private String search;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObject_id(Integer objectId) {
        this.objectId = objectId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
