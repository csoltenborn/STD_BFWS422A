package de.fhdw.std;

public abstract class BankAccount {
    protected final String id;
    protected double balance;

    public BankAccount(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public abstract void withdraw(double amount);

    public double getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    public void deposit(double amount) {
        checkAmountIsPositive(amount);

        balance += amount;
    }

    protected void checkAmountIsPositive(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }
    }
}