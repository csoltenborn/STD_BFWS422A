package de.fhdw.std;

import de.fhdw.std.exception.IllegalAmountException;
import de.fhdw.std.exception.InsufficientFundsException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static de.fhdw.std.assertions.BankAccountAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CheckingAccountTest {

    /*
    Borders for deposit: 0, MAX_LEGAL_AMOUNT
     */

    @ParameterizedTest
    @ValueSource(doubles = {
            1,
            CheckingAccount.MAX_LEGAL_AMOUNT - 1,
            CheckingAccount.MAX_LEGAL_AMOUNT})
    void depositPositive(double amount) {
        CheckingAccount account = new CheckingAccount("1", 0, 0, 0);

        account.deposit(amount);

        assertThat(account).hasBalance(amount);
    }

    private static Stream<Arguments> provideNegativeValuesForDeposit() {
        return Stream.of(
                Arguments.of(-1, IllegalArgumentException.class),
                Arguments.of(0, IllegalArgumentException.class),
                Arguments.of(CheckingAccount.MAX_LEGAL_AMOUNT + 1, IllegalAmountException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNegativeValuesForDeposit")
    void depositNegative(double amount, Class exceptionClass) {
        CheckingAccount account = new CheckingAccount("1", 0, 0, 0);

        assertThatThrownBy(() -> account.deposit(amount)).isInstanceOf(exceptionClass);
    }

    /*
    Borders for withdraw:
    0, balance, balance + overdraftLimit

    Values for balance, overdraftLimit:
    - balance: -1, 0 (extreme cases), 1000
    - overdraftLimit: 0 (extreme case), 1000

    overdraftFee only influences resulting balance - let's take 5 :-)
     */

    /*
        TODO talk to accounting department! See comments below...
     */
    private static Stream<Arguments> providePositiveValuesForWithdraw() {
        return Stream.of(
                Arguments.of(1000, 0, 5, 999, 1),
                Arguments.of(1000, 0, 5, 1000, 0),
                // all following test cases result in balance < -overdraftLimit - is this ok?
                // next test case: balance already negative - apply overdraftFee again? Or should it happen once when balance
                // changes to negative?
                Arguments.of(-1, 1000, 5, 999, -1005),
                Arguments.of(0, 1000, 5, 999, -1004),
                Arguments.of(0, 1000, 5, 999, -1004),
                Arguments.of(0, 1000, 5, 1000, -1005),
                Arguments.of(1000, 1000, 5, 1999, -1004),
                Arguments.of(1000, 1000, 5, 2000, -1005)
        );
    }

    @ParameterizedTest
    @MethodSource("providePositiveValuesForWithdraw")
    void withdrawPositive(double balance, double overdraftLimit, double overdraftFee, double amount, double expectedBalance) {
        CheckingAccount account = new CheckingAccount("1", balance, overdraftLimit, overdraftFee);

        account.withdraw(amount);

        assertThat(account).hasBalance(expectedBalance);
    }

    private static Stream<Arguments> provideNegativeValuesForWithdraw() {
        return Stream.of(
                Arguments.of(-1, 1000, 5, 1000, InsufficientFundsException.class),
                Arguments.of(-1, 1000, 5, 1001, InsufficientFundsException.class),
                Arguments.of(0, 0, 5, -1, IllegalArgumentException.class),
                Arguments.of(0, 0, 5, 0, IllegalArgumentException.class),
                Arguments.of(0, 0, 5, 1, InsufficientFundsException.class),
                Arguments.of(1000, 0, 5, 1001, InsufficientFundsException.class),
                Arguments.of(0, 1000, 5, 1001, InsufficientFundsException.class),
                Arguments.of(1000, 1000, 5, 2001, InsufficientFundsException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNegativeValuesForWithdraw")
    void withdrawNegative(double balance, double overdraftLimit, double overdraftFee, double amount, Class exceptionClass) {
        CheckingAccount account = new CheckingAccount("1", balance, overdraftLimit, overdraftFee);

        assertThatThrownBy(() -> account.withdraw(amount)).isInstanceOf(exceptionClass);
    }

}