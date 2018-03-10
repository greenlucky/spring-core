package com.devopslam.services.common;

public class Account {

    protected String id;
    private String number;
    private String customerId;
    private int amount;

    public Account() {
    }

    public Account(String number, String customerId, int amount) {
        this.number = number;
        this.customerId = customerId;
        this.amount = amount;
    }

    public Account(String id, String number, String customerId, int amount) {
        this.id = id;
        this.number = number;
        this.customerId = customerId;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", customerId='" + customerId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
