package de.fhdw.std;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CheckingAccount checkingAccount = new CheckingAccount("1", 1000, 1000, 10);
        System.out.println(checkingAccount);
        SavingsAccount savingsAccount = new SavingsAccount("2", 1000, 0.05);
        System.out.println(savingsAccount);

        BankDatabase database = new BankDatabaseImpl();
        database.updateAccount(checkingAccount);
        database.updateAccount(savingsAccount);

        BankService bank = new BankService(database);

        bank.deposit("1", 100);
        System.out.println(checkingAccount);

        bank.deposit("2", 100);
        System.out.println(savingsAccount);

        bank.transfer("1", "2", 200);
        System.out.println(checkingAccount);
        System.out.println(savingsAccount);

        List<Transaction> statements = bank.generateStatement("1");
        System.out.println(statements);
    }
}