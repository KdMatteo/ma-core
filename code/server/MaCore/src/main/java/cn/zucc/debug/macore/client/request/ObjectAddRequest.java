package cn.zucc.debug.macore.client.request;

public class ObjectAddRequest {

    private String databaseName;

    private String name;

    private String address;

    private String linkman;

    private String mobile;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabase_name(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
