package com.example.krankbusiness.models;

public class UserData {
    private String uId;
    private String companyName;
    private String mobile;
    private String totalCapital;
    private String totalLoan;
    private String totalCash;
    private String totalProfit;
    private String totalExpense;

    public UserData() {
    }

    public UserData(String uId, String companyName, String mobile, String totalCapital, String totalLoan, String totalCash, String totalProfit, String totalExpense) {
        this.uId = uId;
        this.companyName = companyName;
        this.mobile = mobile;
        this.totalCapital = totalCapital;
        this.totalLoan = totalLoan;
        this.totalCash = totalCash;
        this.totalProfit = totalProfit;
        this.totalExpense = totalExpense;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTotalCapital() {
        return totalCapital;
    }

    public void setTotalCapital(String totalCapital) {
        this.totalCapital = totalCapital;
    }

    public String getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(String totalLoan) {
        this.totalLoan = totalLoan;
    }

    public String getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(String totalCash) {
        this.totalCash = totalCash;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(String totalExpense) {
        this.totalExpense = totalExpense;
    }
}
