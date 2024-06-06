package de.fhdw.std;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static de.fhdw.std.assertions.BankAccountAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
public class BankServiceTest {

    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;

    @Mock
    private BankDatabase bankDatabase;

    private BankService bankServiceUnderTest;

    @BeforeEach
    public void setUp() {
        reset(bankDatabase);

        checkingAccount = new CheckingAccount("1", 0, 1000, 5);
        savingsAccount = new SavingsAccount("2", 1000, 0.05);

        when(bankDatabase.getAccount("1")).thenReturn(checkingAccount);
        when(bankDatabase.getAccount("2")).thenReturn(savingsAccount);

        bankServiceUnderTest = new BankService(bankDatabase);
    }

    @Test
    public void depositPositiveAmount_checkingsAccount_balanceCorrect() {
        bankServiceUnderTest.deposit(checkingAccount.getId(), 500);

        assertThat(checkingAccount).hasBalance(500);
    }

    @Test
    public void depositPositiveAmount_savingsAccount_balanceCorrect() {
        bankServiceUnderTest.deposit(savingsAccount.getId(), 500);

        assertThat(savingsAccount).hasBalance(1500);
    }

    @Test
    public void depositPositiveAmount_checkingsAccount_transactionIsLogged() {
        bankServiceUnderTest.deposit(checkingAccount.getId(), 500);

        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);
        verify(bankDatabase, times(1)).addTransaction(eq(checkingAccount.getId()), argument.capture());

        Transaction transaction = argument.getValue();
        assertThat(transaction.getType()).isEqualTo("Deposit");
        assertThat(transaction.getAmount()).isEqualTo(500);
        assertThat(transaction.getBalance()).isEqualTo(500);
    }

    @Test
    public void depositPositiveAmount_savingsAccount_transactionIsLogged() {
        bankServiceUnderTest.deposit(savingsAccount.getId(), 500);

        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);
        verify(bankDatabase, times(1)).addTransaction(eq(savingsAccount.getId()), argument.capture());
        Transaction transaction = argument.getValue();
        assertThat(transaction.getType()).isEqualTo("Deposit");
        assertThat(transaction.getAmount()).isEqualTo(500);
        assertThat(transaction.getBalance()).isEqualTo(1500);
    }

}
