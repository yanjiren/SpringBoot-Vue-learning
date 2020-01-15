package org.server.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Project {
    private int id;
    private int eid;
    private String name;
    private String pCode;
    private String principal;
    private int scale;
    private String industry;
    private String location;
    private Date startDate;
    private Date endDate;
    private String entName;

    public Project() {
    }

    public Project(int eid, String name, String pCode, String principal, int scale, String industry, String location, Date startDate, Date endDate) {
        this.eid = eid;
        this.name = name;
        this.pCode = pCode;
        this.principal = principal;
        this.scale = scale;
        this.industry = industry;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
