package de.fhdw.std;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SavingsAccountTest {

    @Test
    void depositPositive() {
        SavingsAccount account = new SavingsAccount("1", 1000, 0.05);

        account.deposit(100);

        Assertions.assertEquals(1100, account.getBalance());
    }

    @Test
    void depositNegative() {
        SavingsAccount account = new SavingsAccount("1", 1000, 0.05);

        Assertions.assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
    }

    @Test
    void withdrawAvailableAmount() {
        SavingsAccount account = new SavingsAccount("1", 1000, 0.05);

        account.withdraw(200);

        Assertions.assertEquals(800, account.getBalance());
    }

    @Test
    void withdrawAboveAmount() {
        SavingsAccount account = new SavingsAccount("1", 1000, 0.05);

        Assertions.assertThrows(InsufficientFundsException.class, () -> account.withdraw(1100));
    }

    @Test
    void withdrawNegativeAmount() {
        SavingsAccount account = new SavingsAccount("1", 1000, 0.05);

        Assertions.assertThrows(IllegalArgumentException.class, () -> account.withdraw(-100));
    }
}