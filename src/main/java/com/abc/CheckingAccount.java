package com.abc;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ssingh on 8/28/15.
 */
public class CheckingAccount extends Account
{
    public CheckingAccount() {
        super(AccountType.CHECKING);
    }

    public double interestEarned() {
        double amount = 0.0;
        Map<Date, List<Transaction>> dailyTrasactions = getTransactions();
        for(Date transactionDate : dailyTrasactions.keySet())
        {
            amount += ((amount + sumTransactions(transactionDate)) * 0.001);
        }
        return amount;
    }
}

