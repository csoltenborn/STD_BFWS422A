package de.fhdw.std;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CheckingAccountTest {

    @Test
    void depositPositive() {
        CheckingAccount account = new CheckingAccount("1", 0, 500, 5);

        account.deposit(100);

        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    void depositNegative() {
        CheckingAccount account = new CheckingAccount("1", 0, 500, 5);

        Assertions.assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
    }

    @Test
    void withdrawAvailableAmount() {
        CheckingAccount account = new CheckingAccount("1", 1000, 500, 5);

        account.withdraw(200);

        Assertions.assertEquals(800, account.getBalance());
    }

    @Test
    void withdrawWithinLimit() {
        CheckingAccount account = new CheckingAccount("1", 1000, 500, 5);

        account.withdraw(1200);

        Assertions.assertEquals(-205, account.getBalance());
    }

    @Test
    void withdrawAboveLimit() {
        CheckingAccount account = new CheckingAccount("1", 1000, 500, 5);

        Assertions.assertThrows(InsufficientFundsException.class, () -> account.withdraw(1700));
    }

    @Test
    void withdrawNegativeAmount() {
        CheckingAccount account = new CheckingAccount("1", 0, 500, 5);

        Assertions.assertThrows(IllegalArgumentException.class, () -> account.withdraw(-100));
    }
}