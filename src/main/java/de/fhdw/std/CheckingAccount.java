package de.fhdw.std;

public class CheckingAccount extends BankAccount {
    private double overdraftLimit;
    private double overdraftFee;

    public CheckingAccount(String accountId, double balance, double overdraftLimit, double overdraftFee) {
        super(accountId, balance);
        this.overdraftLimit = overdraftLimit;
        this.overdraftFee = overdraftFee;
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
