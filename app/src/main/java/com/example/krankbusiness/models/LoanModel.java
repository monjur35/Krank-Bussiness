package com.example.krankbusiness.models;

public class LoanModel {
    private String userId;
    private String loanId;
    private String lenderName;
    private String amount;
    private String rePayedAmount;
    private String dueAmount;

    public LoanModel() {
    }

    public LoanModel(String userId, String loanId, String lenderName, String amount, String rePayedAmount, String dueAmount) {
        this.userId = userId;
        this.loanId = loanId;
        this.lenderName = lenderName;
        this.amount = amount;
        this.rePayedAmount = rePayedAmount;
        this.dueAmount = dueAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRePayedAmount() {
        return rePayedAmount;
    }

    public void setRePayedAmount(String rePayedAmount) {
        this.rePayedAmount = rePayedAmount;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }
}
