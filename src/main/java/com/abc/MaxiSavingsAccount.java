package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ssingh on 8/28/15.
 */
public class MaxiSavingsAccount extends Account {
    public MaxiSavingsAccount() {
        super(AccountType.MAXI_SAVINGS);
    }

    public double interestEarned() {
        double amount = 0.0;
        Map<Date, List<Transaction>> dailyTransactions = getTransactions();
        List<Date> dates = new ArrayList<Date>(dailyTransactions.keySet());
        Date lastTransactionDate = dates.get(dates.size() - 1);

        for (Date transactionDate : dailyTransactions.keySet()) {
            amount += calculateAmountWithInterest(transactionDate, lastTransactionDate, amount);
        }

        return amount;
    }

    private double calculateAmountWithInterest(Date transactionDate, Date lastTransactionDate, double amount)
    {
        double amountThisDay = amount + sumTransactions(transactionDate);
        Date tenDays = Util.getDateOnly(DateProvider.INSTANCE.after(transactionDate, -10));
        double interest = 0.001;
        if (lastTransactionDate.before(tenDays)) {
            interest = 0.05;
        }
        return amountThisDay * interest;
    }

    public Statement statementForAccount() {
        StringBuilder s = new StringBuilder();
        s.append("Maxi Savings Account\n");

        double total = transactionSummary(s);
        s.append("Total ").append(Util.toDollars(total));
        return new Statement(s.toString(), total);
    }
}

