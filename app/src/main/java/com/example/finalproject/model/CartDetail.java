package com.example.finalproject.model;

public class CartDetail {
    private int cartId;
    private int prodId;
    private double currPrice;
    private double totalPrice;
    private int currQuan;
    private int totalQuan;

    public CartDetail(int cartId, int prodId, double currPrice, double totalPrice, int currQuan, int totalQuan) {
        this.cartId = cartId;
        this.prodId = prodId;
        this.currPrice = currPrice;
        this.currQuan = currQuan;
        this.totalPrice = totalPrice;
        this.totalQuan = totalQuan;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public double getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(double currPrice) {
        this.currPrice = currPrice;
    }

    public int getCurrQuan() {
        return currQuan;
    }

    public void setCurrQuan(int currQuan) {
        this.currQuan = currQuan;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuan() {
        return totalQuan;
    }

    public void setTotalQuan(int totalQuan) {
        this.totalQuan = totalQuan;
    }
}
