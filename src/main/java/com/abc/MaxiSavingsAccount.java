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
        Date lastWithdrawalDate = null;
        for(int index = dates.size() - 1; index >= 0; index--){
            for(Transaction t : dailyTransactions.get(dates.get(index)))
            {
                if (t.getType() == TransactionType.WITHDRAWAL)
                {
                    lastWithdrawalDate = t.getTransactionDate();
                    break;
                }
            }
        }
        for (Date transactionDate : dailyTransactions.keySet()) {
            amount += calculateAmountWithInterest(transactionDate, lastWithdrawalDate, amount);
        }

        return amount;
    }

    private double calculateAmountWithInterest(Date transactionDate, Date lastTransactionDate, double amount)
    {
        double amountThisDay = amount + sumTransactions(transactionDate);
        Date tenDays = Util.getDateOnly(DateProvider.INSTANCE.after(transactionDate, -10));
        double interest = 0.001;
        if (lastTransactionDate == null || (lastTransactionDate.before(transactionDate) && lastTransactionDate.before(tenDays))) {
            interest = 0.05;
        }
        return amountThisDay * interest;
    }

    public Statement statementForAccount() {
        StringBuilder s = new StringBuilder();
        s.append("Maxi Savings Account\n");
        double total = transactionSummary(s);
        return new Statement(s.toString(), total);
    }
}

