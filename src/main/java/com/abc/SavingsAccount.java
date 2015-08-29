package com.abc;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ssingh on 8/28/15.
 */
public class SavingsAccount extends Account {
    public SavingsAccount() {
        super(AccountType.SAVINGS);

    }

    public double interestEarned() {
        double amount = 0.0;
        Map<Date, List<Transaction>> dailyTrasactions = getTransactions();
        for (Date transactionDate : dailyTrasactions.keySet()){
            amount += calculateAmountWithInterest(transactionDate, amount);
        }
        return amount;
    }

    private double calculateAmountWithInterest(Date transactionDate, double amount){
        double amountThisDay = amount + sumTransactions(transactionDate);
        if (amountThisDay <= 1000)
            amount += amountThisDay * 0.001;
        else
            amount += 1 + (amountThisDay - 1000) * 0.002;
        return amount;
    }
}


