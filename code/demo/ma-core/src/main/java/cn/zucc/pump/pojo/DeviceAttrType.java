package cn.zucc.pump.pojo;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-02-07 16:09
 * Description:
 **/
public class DeviceAttrType {
    private int id;
    private int devicetype_id;
    private String name;
    private String field_name;
    private String label;
    private String data_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDevicetype_id() {
        return devicetype_id;
    }

    public void setDevicetype_id(int devicetype_id) {
        this.devicetype_id = devicetype_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }
}
