package de.fhdw.std;

import de.fhdw.std.utils.ExcludeFromJacocoGeneratedReport;

public class Transaction {
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    private String type;
    private double amount;
    private double balance;

    public Transaction(String type, double amount, double balance) {
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    @ExcludeFromJacocoGeneratedReport
    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }

    // setters, ...
}