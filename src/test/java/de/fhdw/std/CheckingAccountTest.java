package de.fhdw.std;

import org.junit.jupiter.api.Test;

import static de.fhdw.std.assertions.BankAccountAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CheckingAccountTest {

    @Test
    void depositPositive() {
        CheckingAccount account = new CheckingAccount("1", 0, 500, 5);

        account.deposit(100);

        assertThat(account).hasBalance(100);
    }

    @Test
    void depositNegative() {
        CheckingAccount account = new CheckingAccount("1", 0, 500, 5);

        assertThatThrownBy(() -> account.deposit(-100))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void withdrawAvailableAmount() {
        CheckingAccount account = new CheckingAccount("1", 1000, 500, 5);

        account.withdraw(200);

        assertThat(account).hasBalance(800);
    }

    @Test
    void withdrawWithinLimit() {
        CheckingAccount account = new CheckingAccount("1", 1000, 500, 5);

        account.withdraw(1200);

        assertThat(account).hasBalance(-205);
    }

    @Test
    void withdrawAboveLimit() {
        CheckingAccount account = new CheckingAccount("1", 1000, 500, 5);

        assertThatThrownBy(() -> account.withdraw(1700))
                .isInstanceOf(InsufficientFundsException.class);
    }

    @Test
    void withdrawNegativeAmount() {
        CheckingAccount account = new CheckingAccount("1", 0, 500, 5);

        assertThatThrownBy(() -> account.withdraw(-100))
                .isInstanceOf(IllegalArgumentException.class);
    }

}