package cn.zucc.debug.macore.model.pojo;

public class DeviceAttr extends Entity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_device_attr.c_id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_device_attr.c_device_id
     *
     * @mbggenerated
     */
    private Integer deviceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_device_attr.c_attrtype_id
     *
     * @mbggenerated
     */
    private Integer attrtypeId;

    private Float factorA;
    private Float factorB;
    private Float max;
    private Float min;
    private Integer readWrite;
    private Integer enable;
    private String mask;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_device_attr.c_id
     *
     * @return the value of t_device_attr.c_id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_device_attr.c_id
     *
     * @param id the value for t_device_attr.c_id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_device_attr.c_device_id
     *
     * @return the value of t_device_attr.c_device_id
     *
     * @mbggenerated
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_device_attr.c_device_id
     *
     * @param deviceId the value for t_device_attr.c_device_id
     *
     * @mbggenerated
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_device_attr.c_attrtype_id
     *
     * @return the value of t_device_attr.c_attrtype_id
     *
     * @mbggenerated
     */
    public Integer getAttrtypeId() {
        return attrtypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_device_attr.c_attrtype_id
     *
     * @param attrtypeId the value for t_device_attr.c_attrtype_id
     *
     * @mbggenerated
     */
    public void setAttrtypeId(Integer attrtypeId) {
        this.attrtypeId = attrtypeId;
    }

    public Float getFactorA() {
        return factorA;
    }

    public void setFactorA(Float factorA) {
        this.factorA = factorA;
    }

    public Float getFactorB() {
        return factorB;
    }

    public void setFactorB(Float factorB) {
        this.factorB = factorB;
    }

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }

    public Integer getReadWrite() {
        return readWrite;
    }

    public void setReadWrite(Integer readWrite) {
        this.readWrite = readWrite;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    @Override
    public String toString() {
        return "DeviceAttr{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", attrtypeId=" + attrtypeId +
                ", factorA=" + factorA +
                ", factorB=" + factorB +
                ", max=" + max +
                ", min=" + min +
                ", readWrite=" + readWrite +
                ", enable=" + enable +
                ", mask='" + mask + '\'' +
                '}';
    }
}