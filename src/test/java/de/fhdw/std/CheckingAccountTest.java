package de.fhdw.std;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

    private static Stream<Arguments> provideValuesForWithdraw() {
        return Stream.of(
                Arguments.of(1000, 1500, 5, 1200, -205),
                Arguments.of(1000, 500, 5, 200, 800)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValuesForWithdraw")
    void withdrawAvailableAmount(double balance, double overdraftLimit, double overdraftFee, double amount, double expectedBalance) {
        CheckingAccount account = new CheckingAccount("1", balance, overdraftLimit, overdraftFee);

        account.withdraw(amount);

        assertThat(account).hasBalance(expectedBalance);
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