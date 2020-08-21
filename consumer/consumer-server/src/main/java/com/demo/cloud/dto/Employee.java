package com.demo.cloud.dto;

/**
 * @author feiniu
 * @create 2020-07-23 10:19
 */
public class Employee {

    private String tb_name;
    private String op_type;
    private String ts;
    private String eId;
    private String eName;
    private double eSal;

    public Employee() {

    }

    public Employee(String eId, String eName, double eSal) {
        this.eId = eId;
        this.eName = eName;
        this.eSal = eSal;
    }

    public Employee(String tb_name, String op_type, String ts, String eId, String eName, double eSal) {
        this.tb_name = tb_name;
        this.op_type = op_type;
        this.ts = ts;
        this.eId = eId;
        this.eName = eName;
        this.eSal = eSal;
    }

    public String getTb_name() {
        return tb_name;
    }

    public void setTb_name(String tb_name) {
        this.tb_name = tb_name;
    }

    public String getOp_type() {
        return op_type;
    }

    public void setOp_type(String op_type) {
        this.op_type = op_type;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public double geteSal() {
        return eSal;
    }

    public void seteSal(double eSal) {
        this.eSal = eSal;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "tb_name='" + tb_name + '\'' +
                ", op_type='" + op_type + '\'' +
                ", ts='" + ts + '\'' +
                ", eId='" + eId + '\'' +
                ", eName='" + eName + '\'' +
                ", eSal=" + eSal +
                '}';
    }

}
