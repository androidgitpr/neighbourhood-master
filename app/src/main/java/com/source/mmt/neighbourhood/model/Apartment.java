package com.source.mmt.neighbourhood.model;

import java.util.Date;

public class Apartment  {
    private String name;
    private String addr;
    private int aptId;
    private String action;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getAptId() {
        return aptId;
    }

    public void setAptId(int aptId) {
        this.aptId = aptId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
