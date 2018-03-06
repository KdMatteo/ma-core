package cn.zucc.pump.pojo;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-02-07 15:07
 * Description:
 **/
public class DeviceType {
    private int id;
    private String name;
    private String table_name;
    private int multi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public int getMulti() {
        return multi;
    }

    public void setMulti(int multi) {
        this.multi = multi;
    }
}
