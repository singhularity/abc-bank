package com.abc;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ssingh on 8/28/15.
 * Class representing a Checking account, provides a concrete implementation for Account
 *
 */

public class CheckingAccount extends Account
{
    public CheckingAccount() {
        super(AccountType.CHECKING);
    }

    /**
     *
     * @return Total interest earned on this account
     */
    public double interestEarned() {
        double amount = 0.0;
        Map<Date, List<Transaction>> dailyTransactions = getTransactions();
        for(Date transactionDate : dailyTransactions.keySet())
        {
            amount += ((amount + sumTransactions(transactionDate)) * 0.001);
        }
        return amount;
    }

    /**
     *
     * @return A statement for this account
     */
    public Statement statementForAccount() {
        StringBuilder s = new StringBuilder();
        s.append("Checking Account\n");
        double total = transactionSummary(s);
        return new Statement(s.toString(), total);
    }
}

