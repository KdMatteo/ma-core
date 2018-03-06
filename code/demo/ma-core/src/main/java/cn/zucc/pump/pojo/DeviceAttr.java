package cn.zucc.pump.pojo;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-02-07 21:07
 * Description:
 **/
public class DeviceAttr {
    private int id;
    private int device_id;
    private int attrtype_id;
    private String plc_address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public int getAttrtype_id() {
        return attrtype_id;
    }

    public void setAttrtype_id(int attrtype_id) {
        this.attrtype_id = attrtype_id;
    }

    public String getPlc_address() {
        return plc_address;
    }

    public void setPlc_address(String plc_address) {
        this.plc_address = plc_address;
    }
}
