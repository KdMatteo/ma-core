package cn.zucc.debug.macore.client.request;

public class DeviceAttrTypeUpdateRequest {
    private Integer id;
    private String name;
    private String label;
    private String dataType;
    private String fieldName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void set_label(String label) {
        this.label = label;
    }

    public String getDataType() {
        return dataType;
    }

    public void setData_type(String dataType) {
        this.dataType = dataType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setField_name(String fieldName) {
        this.fieldName = fieldName;
    }

}
