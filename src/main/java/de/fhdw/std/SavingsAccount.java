package de.fhdw.std;

import de.fhdw.std.utils.ExcludeFromJacocoGeneratedReport;

public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountId, double balance, double interestRate) {
        super(accountId, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        checkAmountIsPositive(amount);

        if (amount >= balance) {
            throw new InsufficientFundsException();
        }
        balance -= amount;
    }

    public void addInterest() {
        balance += balance * interestRate;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    @ExcludeFromJacocoGeneratedReport
    @Override
    public String toString() {
        return "SavingsAccount{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                '}';
    }
}
