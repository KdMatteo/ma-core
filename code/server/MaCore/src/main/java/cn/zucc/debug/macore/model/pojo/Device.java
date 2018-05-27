package cn.zucc.debug.macore.model.pojo;

public class Device extends Entity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_object_device.c_id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_object_device.c_group_id
     *
     * @mbggenerated
     */
    private Integer groupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_object_device.c_object_id
     *
     * @mbggenerated
     */
    private Integer objectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_object_device.c_devicetype_id
     *
     * @mbggenerated
     */
    private Integer devicetypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_object_device.c_code
     *
     * @mbggenerated
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_object_device.c_index
     *
     * @mbggenerated
     */
    private Integer index;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_object_device.c_id
     *
     * @return the value of t_object_device.c_id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_object_device.c_id
     *
     * @param id the value for t_object_device.c_id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_object_device.c_group_id
     *
     * @return the value of t_object_device.c_group_id
     *
     * @mbggenerated
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_object_device.c_group_id
     *
     * @param groupId the value for t_object_device.c_group_id
     *
     * @mbggenerated
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_object_device.c_object_id
     *
     * @return the value of t_object_device.c_object_id
     *
     * @mbggenerated
     */
    public Integer getObjectId() {
        return objectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_object_device.c_object_id
     *
     * @param objectId the value for t_object_device.c_object_id
     *
     * @mbggenerated
     */
    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_object_device.c_devicetype_id
     *
     * @return the value of t_object_device.c_devicetype_id
     *
     * @mbggenerated
     */
    public Integer getDevicetypeId() {
        return devicetypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_object_device.c_devicetype_id
     *
     * @param devicetypeId the value for t_object_device.c_devicetype_id
     *
     * @mbggenerated
     */
    public void setDevicetypeId(Integer devicetypeId) {
        this.devicetypeId = devicetypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_object_device.c_code
     *
     * @return the value of t_object_device.c_code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_object_device.c_code
     *
     * @param code the value for t_object_device.c_code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_object_device.c_index
     *
     * @return the value of t_object_device.c_index
     *
     * @mbggenerated
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_object_device.c_index
     *
     * @param index the value for t_object_device.c_index
     *
     * @mbggenerated
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_object_device
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupId=").append(groupId);
        sb.append(", objectId=").append(objectId);
        sb.append(", devicetypeId=").append(devicetypeId);
        sb.append(", code=").append(code);
        sb.append(", index=").append(index);
        sb.append("]");
        return sb.toString();
    }
}