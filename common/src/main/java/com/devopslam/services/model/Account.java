package com.devopslam.remicro.persistence.model;


public class Account {

    private String id;
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
}
