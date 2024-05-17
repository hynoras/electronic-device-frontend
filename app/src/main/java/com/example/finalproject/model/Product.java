package com.example.finalproject.model;

public class Product {
    private int prodId;
    private String prodName;
    private String prodDesc;
    private double prodPrice;
    private int prodQuan;
    private String prodImg;

    public Product(String prodName, String prodDesc, double prodPrice, int prodQuan, String prodImg) {
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodPrice = prodPrice;
        this.prodQuan = prodQuan;
        this.prodImg = prodImg;
    }

    public int getProdId() {
        return prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public int getProdQuan() {
        return prodQuan;
    }

    public String getProdImg() {
        return prodImg;
    }

    // Setters
    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public void setProdPrice(double prodPrice) {
        this.prodPrice = prodPrice;
    }

    public void setProdQuan(int prodQuan) {
        this.prodQuan = prodQuan;
    }

    public void setProdImg(String prodImg) {
        this.prodImg = prodImg;
    }
}
