package de.fhdw.std;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.fhdw.std.assertions.BankAccountAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;


public class BankServiceIntergrationTest {

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

}
