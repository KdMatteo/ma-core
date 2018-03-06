package cn.zucc.pump.pojo;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-02-07 16:42
 * Description:
 **/
public class ObjectDevice {
    private int id;
    private int object_id;
    private int devicetype_id;
    private String code;
    private int index;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public int getDevicetype_id() {
        return devicetype_id;
    }

    public void setDevicetype_id(int devicetype_id) {
        this.devicetype_id = devicetype_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
