package de.fhdw.std;

import org.junit.jupiter.api.Test;

import static de.fhdw.std.assertions.BankAccountAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SavingsAccountTest {

    @Test
    void depositPositive() {
        SavingsAccount account = new SavingsAccount("1", 1000, 0.05);

        account.deposit(100);

        assertThat(account).hasBalance(1100);
    }

    @Test
    void depositNegative() {
        SavingsAccount account = new SavingsAccount("1", 1000, 0.05);

        assertThatThrownBy(() -> account.deposit(-100))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void withdrawAvailableAmount() {
        SavingsAccount account = new SavingsAccount("1", 1000, 0.05);

        account.withdraw(200);

        assertThat(account).hasBalance(800);
    }

    @Test
    void withdrawAboveAmount() {
        SavingsAccount account = new SavingsAccount("1", 1000, 0.05);

        assertThatThrownBy(() -> account.withdraw(1100))
                .isInstanceOf(InsufficientFundsException.class);
    }

    @Test
    void withdrawNegativeAmount() {
        SavingsAccount account = new SavingsAccount("1", 1000, 0.05);

        assertThatThrownBy(() -> account.withdraw(-100))
                .isInstanceOf(IllegalArgumentException.class);
    }
}