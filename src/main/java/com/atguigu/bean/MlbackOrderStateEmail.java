package com.atguigu.bean;

public class MlbackOrderStateEmail {
    private Integer orderstateemailId;

    private String orderstateemailName;

    private String orderstateemailOne;

    private String orderstateemailTwo;

    private String orderstateemailThree;

    private String orderstateemailFour;

    private String orderstateemailFive;

    public Integer getOrderstateemailId() {
        return orderstateemailId;
    }

    public void setOrderstateemailId(Integer orderstateemailId) {
        this.orderstateemailId = orderstateemailId;
    }

    public String getOrderstateemailName() {
        return orderstateemailName;
    }

    public void setOrderstateemailName(String orderstateemailName) {
        this.orderstateemailName = orderstateemailName == null ? null : orderstateemailName.trim();
    }

    public String getOrderstateemailOne() {
        return orderstateemailOne;
    }

    public void setOrderstateemailOne(String orderstateemailOne) {
        this.orderstateemailOne = orderstateemailOne == null ? null : orderstateemailOne.trim();
    }

    public String getOrderstateemailTwo() {
        return orderstateemailTwo;
    }

    public void setOrderstateemailTwo(String orderstateemailTwo) {
        this.orderstateemailTwo = orderstateemailTwo == null ? null : orderstateemailTwo.trim();
    }

    public String getOrderstateemailThree() {
        return orderstateemailThree;
    }

    public void setOrderstateemailThree(String orderstateemailThree) {
        this.orderstateemailThree = orderstateemailThree == null ? null : orderstateemailThree.trim();
    }

    public String getOrderstateemailFour() {
        return orderstateemailFour;
    }

    public void setOrderstateemailFour(String orderstateemailFour) {
        this.orderstateemailFour = orderstateemailFour == null ? null : orderstateemailFour.trim();
    }

    public String getOrderstateemailFive() {
        return orderstateemailFive;
    }

    public void setOrderstateemailFive(String orderstateemailFive) {
        this.orderstateemailFive = orderstateemailFive == null ? null : orderstateemailFive.trim();
    }
}