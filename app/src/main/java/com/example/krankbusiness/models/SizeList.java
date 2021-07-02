package com.example.krankbusiness.models;

public class SizeList {
    private String productId;
    private String mSize;
    private String lSize;
    private String xlSize;
    private String xxlSize;
    private String xxxlSize;

    public SizeList() {
    }

    public SizeList(String productId, String mSize, String lSize, String xlSize, String xxlSize, String xxxlSize) {
        this.productId = productId;
        this.mSize = mSize;
        this.lSize = lSize;
        this.xlSize = xlSize;
        this.xxlSize = xxlSize;
        this.xxxlSize = xxxlSize;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getmSize() {
        return mSize;
    }

    public void setmSize(String mSize) {
        this.mSize = mSize;
    }

    public String getlSize() {
        return lSize;
    }

    public void setlSize(String lSize) {
        this.lSize = lSize;
    }

    public String getXlSize() {
        return xlSize;
    }

    public void setXlSize(String xlSize) {
        this.xlSize = xlSize;
    }

    public String getXxlSize() {
        return xxlSize;
    }

    public void setXxlSize(String xxlSize) {
        this.xxlSize = xxlSize;
    }

    public String getXxxlSize() {
        return xxxlSize;
    }

    public void setXxxlSize(String xxxlSize) {
        this.xxxlSize = xxxlSize;
    }
}
