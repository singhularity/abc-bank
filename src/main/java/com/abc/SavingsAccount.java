package com.abc;

/**
 * Created by ssingh on 8/28/15.
 */
public class SavingsAccount extends Account {
    public SavingsAccount() {
        super(AccountType.SAVINGS);

    }

    public double interestEarned() {
        double amount = sumTransactions();

        if (amount <= 1000)
            return amount * 0.001;
        else
            return 1 + (amount-1000) * 0.002;

    }
}


