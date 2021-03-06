package com.example.krankbusiness.models;

import java.util.List;

public class OrderModel {
    private String oderId;
    private String userId;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
   private List<Items> itemsList;
    private String totalPrice;
    private String discount;
    private String date;
    private String monthName;
   private String deliveryStatus;



    public OrderModel() {
    }

    public OrderModel(String oderId, String userId, String customerName, String customerPhone, String customerAddress,
                      List<Items> itemsList, String totalPrice, String discount, String date, String monthName, String deliveryStatus) {
        this.oderId = oderId;
        this.userId = userId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.itemsList = itemsList;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.date = date;
        this.monthName = monthName;
        this.deliveryStatus = deliveryStatus;
    }

    public String getOderId() {
        return oderId;
    }

    public void setOderId(String oderId) {
        this.oderId = oderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
