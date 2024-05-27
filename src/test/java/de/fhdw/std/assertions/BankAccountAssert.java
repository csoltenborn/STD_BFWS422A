package de.fhdw.std.assertions;

import de.fhdw.std.BankAccount;
import org.assertj.core.api.AbstractAssert;

public class BankAccountAssert extends AbstractAssert<BankAccountAssert, BankAccount> {
    public BankAccountAssert(BankAccount bankAccount) {
        super(bankAccount, BankAccountAssert.class);
    }

    public static BankAccountAssert assertThat(BankAccount bankAccount) {
        return new BankAccountAssert(bankAccount);
    }

    public BankAccountAssert hasBalance(double balance) {
        isNotNull();
        if (actual.getBalance() != balance) {
            failWithMessage("Expected the account's balance to be <%.2f> but was <%.2f>", balance, actual.getBalance());
        }
        return this;
    }

}