package de.fhdw.std;

import java.util.List;

public class BankDatabaseDummy implements BankDatabase {
    @Override
    public BankAccount getAccount(String accountId) {
        return null;
    }

    @Override
    public void updateAccount(BankAccount account) {
    }

    @Override
    public List<Transaction> getTransactions(String accountId) {
        return null;
    }

    @Override
    public void addTransaction(String accountId, Transaction transaction) {
    }
}
