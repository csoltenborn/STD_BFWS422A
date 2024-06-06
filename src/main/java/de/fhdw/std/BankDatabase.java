package de.fhdw.std;

import java.util.List;

public interface BankDatabase {
    BankAccount getAccount(String accountId);
    void updateAccount(BankAccount account);

    List<Transaction> getTransactions(String accountId);
    void addTransaction(String accountId, Transaction transaction);
}