package cn.zucc.debug.macore.client.request;

public class DeviceTypeAddRequest {

    private String name;

    private Integer multi;

    private String tableName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMulti() {
        return multi;
    }

    public void setMulti(Integer multi) {
        this.multi = multi;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTable_name(String tableName) {
        this.tableName = tableName;
    }
}
