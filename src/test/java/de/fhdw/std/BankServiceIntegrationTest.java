package de.fhdw.std;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.fhdw.std.assertions.BankAccountAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;


public class BankServiceIntegrationTest {

    private BankDatabase bankDatabase;

    private BankService bankServiceUnderTest;

    @BeforeEach
    public void setUp() {
        bankDatabase = new BankDatabaseImpl();
        bankServiceUnderTest = new BankService(bankDatabase);
    }

    @Test
    public void massWithdraw_checkingAccountWithAvailableAmount_withdrawPerformed() {
        CheckingAccount targetAccount = new CheckingAccount("1", 0, 0, 0);
        CheckingAccount sourceAccount = new CheckingAccount("2", 1000, 1000, 0);
        bankDatabase.updateAccount(targetAccount);
        bankDatabase.updateAccount(sourceAccount);

        List<String> failedWithdrawals = bankServiceUnderTest.massWithdraw(targetAccount.getId(), List.of(sourceAccount.getId()), 10);

        assertThat(failedWithdrawals).isEmpty();
        assertThat(targetAccount).hasBalance(10);
        assertThat(sourceAccount).hasBalance(990);
    }

    @Test
    public void massWithdraw_checkingAccountWithoutAvailableAmount_isContainedInResult() {
        CheckingAccount targetAccount = new CheckingAccount("1", 0, 0, 0);
        CheckingAccount sourceAccount = new CheckingAccount("2", 5, 1000, 0);
        bankDatabase.updateAccount(targetAccount);
        bankDatabase.updateAccount(sourceAccount);

        List<String> failedWithdrawals = bankServiceUnderTest.massWithdraw(targetAccount.getId(), List.of(sourceAccount.getId()), 10);

        assertThat(failedWithdrawals).hasSameElementsAs(List.of(sourceAccount.getId()));
        assertThat(targetAccount).hasBalance(0);
        assertThat(sourceAccount).hasBalance(5);
    }

    @Test
    public void massWithdraw_savingsAccountWitAvailableAmount_withdrawPerformed() {
        CheckingAccount targetAccount = new CheckingAccount("1", 0, 0, 0);
        SavingsAccount sourceAccount = new SavingsAccount("2", 1000, 5);
        bankDatabase.updateAccount(targetAccount);
        bankDatabase.updateAccount(sourceAccount);

        List<String> failedWithdrawals = bankServiceUnderTest.massWithdraw(targetAccount.getId(), List.of(sourceAccount.getId()), 10);

        assertThat(failedWithdrawals).isEmpty();
        assertThat(targetAccount).hasBalance(10);
        assertThat(sourceAccount).hasBalance(990);
    }

    @Test
    public void massWithdraw_checkingAccountWithNegativeBalance_withdrawPerformed() {
        CheckingAccount targetAccount = new CheckingAccount("1", 0, 0, 0);
        CheckingAccount sourceAccount = new CheckingAccount("2", -100, 1000, 0);
        bankDatabase.updateAccount(targetAccount);
        bankDatabase.updateAccount(sourceAccount);

        List<String> failedWithdrawals = bankServiceUnderTest.massWithdraw(targetAccount.getId(), List.of(sourceAccount.getId()), 10);

        assertThat(failedWithdrawals).isEmpty();
        assertThat(targetAccount).hasBalance(10);
        assertThat(sourceAccount).hasBalance(-110);
    }

}
