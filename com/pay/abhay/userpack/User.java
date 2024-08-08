package com.pay.abhay.userpack;

import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    private String userName, email, ifscCode, bankName, mobile, accountNumber, history, type;
    private int accountPin;
    private double accountBalance;


    public User() {
        super();
    }

    public User(String userName, String email, String ifscCode, String bankName, String mobile, String accountNumber,
            String history, int accountPin, double accountBalance) {
        this.userName = userName;
        this.email = email;
        this.ifscCode = ifscCode;
        this.bankName = bankName;
        this.mobile = mobile;
        this.accountNumber = accountNumber;
        this.history = history;
        this.accountPin = accountPin;
        this.accountBalance = accountBalance;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountType(){
        return type;
    }
    
    public void setAccountType(String type){
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public int getAccountPin() {
        return accountPin;
    }

    public void setAccountPin(int accountPin) {
        this.accountPin = accountPin;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "User [userName=" + userName + ", email=" + email + ", ifscCode=" + ifscCode + ", bankName=" + bankName
                + ", mobile=" + mobile + ", accountNumber=" + accountNumber + ", history=" + history + ", accountPin="
                + accountPin + ", accountBalance=" + accountBalance + "]";
    }
    
}
