package de.fhdw.std;

import java.util.*;

public class BankDatabaseImpl implements BankDatabase {
    private final Map<String, BankAccount> accounts = new HashMap<>();
    private final Map<String, List<Transaction>> transactions = new HashMap<>();

    public BankAccount getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public void updateAccount(BankAccount account) {
        accounts.put(account.getId(), account);
    }

    @Override
    public List<Transaction> getTransactions(String accountId) {
        return Collections.unmodifiableList(internalGetTransactions(accountId));
    }

    @Override
    public void addTransaction(String accountId, Transaction transaction) {
        internalGetTransactions(accountId).add(transaction);
    }

    private List<Transaction> internalGetTransactions(String accountId) {
        List<Transaction> accountTransactions = transactions.get(accountId);
        if (accountTransactions == null) {
            accountTransactions = new ArrayList<>();
            transactions.put(accountId, accountTransactions);
        }
        return accountTransactions;
    }

}