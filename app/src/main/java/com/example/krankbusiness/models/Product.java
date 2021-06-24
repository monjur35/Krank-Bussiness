package com.example.krankbusiness.models;

public class Product {
    private String uId;
    private String productId;
    private String productName;
    private int productPrice;
    private int production_cost;
    private int mSizeInStock;
    private int LSizeInStock;
    private int xlSizeInStock;
    private int xxlSizeInStock;
    private int xxxlSizeInStock;
    private int totalInStock;

    public Product() {
    }

    public Product(String uId, String productId, String productName, int productPrice, int production_cost, int mSizeInStock,
                   int LSizeInStock, int xlSizeInStock, int xxlSizeInStock, int xxxlSizeInStock, int totalInStock) {
        this.uId = uId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.production_cost = production_cost;
        this.mSizeInStock = mSizeInStock;
        this.LSizeInStock = LSizeInStock;
        this.xlSizeInStock = xlSizeInStock;
        this.xxlSizeInStock = xxlSizeInStock;
        this.xxxlSizeInStock = xxxlSizeInStock;
        this.totalInStock = totalInStock;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProduction_cost() {
        return production_cost;
    }

    public void setProduction_cost(int production_cost) {
        this.production_cost = production_cost;
    }

    public int getmSizeInStock() {
        return mSizeInStock;
    }

    public void setmSizeInStock(int mSizeInStock) {
        this.mSizeInStock = mSizeInStock;
    }

    public int getLSizeInStock() {
        return LSizeInStock;
    }

    public void setLSizeInStock(int LSizeInStock) {
        this.LSizeInStock = LSizeInStock;
    }

    public int getXlSizeInStock() {
        return xlSizeInStock;
    }

    public void setXlSizeInStock(int xlSizeInStock) {
        this.xlSizeInStock = xlSizeInStock;
    }

    public int getXxlSizeInStock() {
        return xxlSizeInStock;
    }

    public void setXxlSizeInStock(int xxlSizeInStock) {
        this.xxlSizeInStock = xxlSizeInStock;
    }

    public int getXxxlSizeInStock() {
        return xxxlSizeInStock;
    }

    public void setXxxlSizeInStock(int xxxlSizeInStock) {
        this.xxxlSizeInStock = xxxlSizeInStock;
    }

    public int getTotalInStock() {
        return totalInStock;
    }

    public void setTotalInStock(int totalInStock) {
        this.totalInStock = totalInStock;
    }
}
