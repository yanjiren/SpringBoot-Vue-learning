package org.server.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Operation {
    private int id;
    private int eid;
    private int productOutput;
    private int outputValue;
    private int tax;
    private int energyUsed;
    private Date reportDate;

    public Operation() {
    }

    public Operation(int eid, int productOutput, int outputValue, int tax, int energyUsed, Date reportDate) {
        this.eid = eid;
        this.productOutput = productOutput;
        this.outputValue = outputValue;
        this.tax = tax;
        this.energyUsed = energyUsed;
        this.reportDate = reportDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getProductOutput() {
        return productOutput;
    }

    public void setProductOutput(int productOutput) {
        this.productOutput = productOutput;
    }

    public int getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(int outputValue) {
        this.outputValue = outputValue;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getEnergyUsed() {
        return energyUsed;
    }

    public void setEnergyUsed(int energyUsed) {
        this.energyUsed = energyUsed;
    }

    @JsonFormat(pattern = "yyyy-MM")
    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

}
