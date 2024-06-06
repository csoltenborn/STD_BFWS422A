package de.fhdw.std;

import java.util.List;

public class BankDatabaseStub implements BankDatabase{
    public BankAccount bankAccount = new CheckingAccount("1", 0, 0, 0);
    public List<Transaction> transactions = List.of(
            new Transaction("1", 100, 100)
    );

    @Override
    public BankAccount getAccount(String accountId) {
        return bankAccount;
    }

    @Override
    public void updateAccount(BankAccount account) {
    }

    @Override
    public List<Transaction> getTransactions(String accountId) {
        return transactions;
    }

    @Override
    public void addTransaction(String accountId, Transaction transaction) {
    }
}
