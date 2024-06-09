package de.fhdw.std;

import de.fhdw.std.exception.IllegalAmountException;
import de.fhdw.std.exception.InsufficientFundsException;
import de.fhdw.std.utils.ExcludeFromJacocoGeneratedReport;

public class CheckingAccount extends BankAccount {
    public static final double MAX_LEGAL_AMOUNT = 20000;

    private double overdraftLimit;
    private double overdraftFee;

    public CheckingAccount(String accountId, double balance, double overdraftLimit, double overdraftFee) {
        super(accountId, balance);
        this.overdraftLimit = overdraftLimit;
        this.overdraftFee = overdraftFee;
    }

    @Override
    public void deposit(double amount) {
        if (amount > MAX_LEGAL_AMOUNT) {
            throw new IllegalAmountException();
        }
        super.deposit(amount);
    }

    @Override
    public void withdraw(double amount) {
        checkAmountIsPositive(amount);

        if (amount > balance + overdraftLimit) {
            throw new InsufficientFundsException();
        } else {
            balance -= amount;
        }

        if (balance < 0) {
            balance -= overdraftFee;
        }
    }

    @ExcludeFromJacocoGeneratedReport
    @Override
    public String toString() {
        return "CheckingAccount{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                ", overdraftLimit=" + overdraftLimit +
                ", overdraftFee=" + overdraftFee +
                '}';
    }
}
