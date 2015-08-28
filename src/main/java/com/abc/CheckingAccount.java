package com.abc;

/**
 * Created by ssingh on 8/28/15.
 */
public class CheckingAccount extends Account
{
    public CheckingAccount() {
        super(AccountType.CHECKING);
    }

    public double interestEarned() {
        double amount = sumTransactions();
        return amount * 0.001;
    }
}

