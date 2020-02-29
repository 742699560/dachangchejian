package com.dccj.entity;

public class CUser {
    private Integer id;

    private String name;

    private String rightPower;

    private String power;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRightPower() {
        return rightPower;
    }

    public void setRightPower(String rightPower) {
        this.rightPower = rightPower;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}