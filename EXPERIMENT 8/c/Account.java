package com.example.transactiondemo;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private int accountId;
    private String name;
    private double balance;

    public Account() {}
    public Account(int accountId, String name, double balance) {
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
    }

    // Getters & Setters
    public int getAccountId() { return accountId; }
    public String getName() { return name; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
