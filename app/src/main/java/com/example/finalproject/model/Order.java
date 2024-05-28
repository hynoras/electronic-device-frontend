package com.example.finalproject.model;

public class Order {
    private int ordId;
    private int userId;
    private String custName;
    private String custPhoneNo;
    private String shippingMethod;
    private String shippingAddr;
    private String ordDate;

    // Getters and Setters
    public int getOrdId() {
        return ordId;
    }

    public void setOrdId(int ordId) {
        this.ordId = ordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhoneNo() {
        return custPhoneNo;
    }

    public void setCustPhoneNo(String custPhoneNo) {
        this.custPhoneNo = custPhoneNo;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getShippingAddr() {
        return shippingAddr;
    }

    public void setShippingAddr(String shippingAddr) {
        this.shippingAddr = shippingAddr;
    }

    public String getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(String ordDate) {
        this.ordDate = ordDate;
    }
}
