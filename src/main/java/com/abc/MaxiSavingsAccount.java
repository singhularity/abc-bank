package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ssingh on 8/28/15.
 * Class representing a Maxi Savings account. Provides a concrete implementation for Account
 */
public class MaxiSavingsAccount extends Account {
    public MaxiSavingsAccount() {
        super(AccountType.MAXI_SAVINGS);
    }

    /**
     *
     * @return Total interest earned on this account
     */
    public double interestEarned() {
        double amount = 0.0;
        Map<Date, List<Transaction>> dailyTransactions = getTransactions();
        List<Date> dates = new ArrayList<Date>(dailyTransactions.keySet());
        Date lastWithdrawalDate = null;
        //Interate though transactions on each day and calculate the daily interest
        for(int index = dates.size() - 1; index >= 0; index--){
            //Iterate through transactions looking for a Withdrawal.
            for(Transaction t : dailyTransactions.get(dates.get(index)))
            {
                if (t.getType() == TransactionType.WITHDRAWAL)
                {
                    lastWithdrawalDate = t.getTransactionDate();
                    break;
                }
            }
        }
        //Calculate the daily amount with interest
        for (Date transactionDate : dailyTransactions.keySet()) {
            amount += calculateAmountWithInterest(transactionDate, lastWithdrawalDate, amount);
        }

        return amount;
    }

    /**
     *
     * @param transactionDate Date of transaction
     * @param lastTransactionDate Last withdrawal date
     * @param amount Amount before this day
     * @return
     */

    private double calculateAmountWithInterest(Date transactionDate, Date lastTransactionDate, double amount)
    {
        double amountThisDay = amount + sumTransactions(transactionDate);
        //Calculate a date which represents 20 days before this transaction date
        Date tenDays = Util.getDateOnly(DateProvider.INSTANCE.after(transactionDate, -10));
        double interest = 0.001;
        //Make sure there was a withdrawal and the withdrawal was before the transaction date
        if (lastTransactionDate == null || (lastTransactionDate.before(transactionDate) && lastTransactionDate.before(tenDays))) {
            interest = 0.05;
        }
        return amountThisDay * interest;
    }

    /**
     *
     * @return Statement for this account
     */
    public Statement statementForAccount() {
        StringBuilder s = new StringBuilder();
        s.append("Maxi Savings Account\n");
        double total = transactionSummary(s);
        return new Statement(s.toString(), total);
    }
}

