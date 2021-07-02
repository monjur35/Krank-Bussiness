package com.example.krankbusiness.models;

public class Product {
    private String uId;
    private String productId;
    private String productName;
    private int productPrice;
    private int production_cost;
    private SizeList sizeLisT;
    private int totalInStock;

    public Product() {
    }



    public Product(String uId, String productId, String productName, int productPrice, int production_cost, SizeList sizeLisT, int totalInStock) {
        this.uId = uId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.production_cost = production_cost;
        this.sizeLisT = sizeLisT;
        this.totalInStock = totalInStock;
    }

    public SizeList getSizeLisT() {
        return sizeLisT;
    }

    public void setSizeLisT(SizeList sizeLisT) {
        this.sizeLisT = sizeLisT;
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


    public int getTotalInStock() {
        return totalInStock;
    }

    public void setTotalInStock(int totalInStock) {
        this.totalInStock = totalInStock;
    }
}
