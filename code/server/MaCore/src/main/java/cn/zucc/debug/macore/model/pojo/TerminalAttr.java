package cn.zucc.debug.macore.model.pojo;

public class TerminalAttr extends Entity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_terminal_attr.c_id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_terminal_attr.c_terminal_id
     *
     * @mbggenerated
     */
    private Integer terminalId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_terminal_attr.c_deviceattr_id
     *
     * @mbggenerated
     */
    private Integer deviceattrId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_terminal_attr.c_plc_address
     *
     * @mbggenerated
     */
    private String plcAddress;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_terminal_attr.c_id
     *
     * @return the value of t_terminal_attr.c_id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_terminal_attr.c_id
     *
     * @param id the value for t_terminal_attr.c_id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_terminal_attr.c_terminal_id
     *
     * @return the value of t_terminal_attr.c_terminal_id
     *
     * @mbggenerated
     */
    public Integer getTerminalId() {
        return terminalId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_terminal_attr.c_terminal_id
     *
     * @param terminalId the value for t_terminal_attr.c_terminal_id
     *
     * @mbggenerated
     */
    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_terminal_attr.c_deviceattr_id
     *
     * @return the value of t_terminal_attr.c_deviceattr_id
     *
     * @mbggenerated
     */
    public Integer getDeviceattrId() {
        return deviceattrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_terminal_attr.c_deviceattr_id
     *
     * @param deviceattrId the value for t_terminal_attr.c_deviceattr_id
     *
     * @mbggenerated
     */
    public void setDeviceattrId(Integer deviceattrId) {
        this.deviceattrId = deviceattrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_terminal_attr.c_plc_address
     *
     * @return the value of t_terminal_attr.c_plc_address
     *
     * @mbggenerated
     */
    public String getPlcAddress() {
        return plcAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_terminal_attr.c_plc_address
     *
     * @param plcAddress the value for t_terminal_attr.c_plc_address
     *
     * @mbggenerated
     */
    public void setPlcAddress(String plcAddress) {
        this.plcAddress = plcAddress == null ? null : plcAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_terminal_attr
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
        sb.append(", terminalId=").append(terminalId);
        sb.append(", deviceattrId=").append(deviceattrId);
        sb.append(", plcAddress=").append(plcAddress);
        sb.append("]");
        return sb.toString();
    }
}