package com.abc;

/**
 * Created by ssingh on 8/28/15.
 */
public class MaxiSavingsAccount extends Account {
    public MaxiSavingsAccount() {
        super(AccountType.MAXI_SAVINGS);
    }

    public double interestEarned() {
        double amount = sumTransactions();
        if (amount <= 1000)
            return amount * 0.02;

        if (amount <= 2000)
            return 20 + (amount-1000) * 0.05;

        return 70 + (amount-2000) * 0.1;

    }
}

