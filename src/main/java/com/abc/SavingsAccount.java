package com.abc;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ssingh on 8/28/15.
 * Class representing a Savings account. Provides a concrete implementation for Account
 */
public class SavingsAccount extends Account {

    public SavingsAccount() {
        super(AccountType.SAVINGS);

    }

    /**
     *
     * @return Interest earned on this account
     */
    public double interestEarned() {
        double amount = 0.0;
        Map<Date, List<Transaction>> dailyTransactions = getTransactions();
        for (Date transactionDate : dailyTransactions.keySet()){
            amount += calculateAmountWithInterest(transactionDate, amount);
        }
        return amount;
    }

    /**
     *
     * @param transactionDate Date of transaction
     * @param amount Amount before this day
     * @return
     */
    private double calculateAmountWithInterest(Date transactionDate, double amount){
        double amountThisDay = amount + sumTransactions(transactionDate);
        if (amountThisDay <= 1000)
            amount += amountThisDay * 0.001;
        else
            amount += 1 + (amountThisDay - 1000) * 0.002;
        return amount;
    }

    /**
     *
     * @return Statement for this account
     */
    public Statement statementForAccount() {
        StringBuilder s = new StringBuilder();
        s.append("Savings Account\n");
        double total = transactionSummary(s);
        return new Statement(s.toString(), total);
    }
}


