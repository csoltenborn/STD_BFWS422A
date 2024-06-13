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
        return hasBalance(balance, 0);
    }

    public BankAccountAssert hasBalance(double balance, double tolerance) {
        isNotNull();
        if (Math.abs(actual.getBalance() - balance) > tolerance) {
            failWithMessage("Expected the account's balance to be <%.2f> but was <%.2f> (tolerance: <%.10f>)", balance, actual.getBalance(), tolerance);
        }
        return this;
    }

}