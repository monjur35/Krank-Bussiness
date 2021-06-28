package com.example.krankbusiness.models;

public class ExpenseModel {
    private String userId;
    private String expId;
    private String expTitle;
    private String amount;
    private String underOf;
    private String date;
    private String monthName;

    public ExpenseModel() {
    }

    public ExpenseModel(String userId, String expId, String expTitle, String amount, String underOf, String date, String monthName) {
        this.userId = userId;
        this.expId = expId;
        this.expTitle = expTitle;
        this.amount = amount;
        this.underOf = underOf;
        this.date = date;
        this.monthName = monthName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExpId() {
        return expId;
    }

    public void setExpId(String expId) {
        this.expId = expId;
    }

    public String getExpTitle() {
        return expTitle;
    }

    public void setExpTitle(String expTitle) {
        this.expTitle = expTitle;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnderOf() {
        return underOf;
    }

    public void setUnderOf(String underOf) {
        this.underOf = underOf;
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
}
