package com.source.mmt.neighbourhood.model;

public class ServiceInfo {
    private String aptId;
    private String catName;
    private int catId;
    private Boolean status;
    private int parentCatId;
    private String action;
    private int statusCode;

    public String getAptId() {
        return aptId;
    }

    public void setAptId(String aptId) {
        this.aptId = aptId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getParentCatId() {
        return parentCatId;
    }

    public void setParentCatId(int parentCatId) {
        this.parentCatId = parentCatId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
