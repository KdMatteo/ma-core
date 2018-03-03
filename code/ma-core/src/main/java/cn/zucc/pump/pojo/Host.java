package cn.zucc.pump.pojo;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-02-06 16:07
 * Description:数据库服务器主机model
 **/
public class Host {
    private int id;
    private String ip;
    private int port;
    private String account;
    private String password;
    public Host(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
