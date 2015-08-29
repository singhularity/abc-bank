package com.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by ssingh on 8/28/15.
 */
public class MaxiSavingsAccount extends Account {
    public MaxiSavingsAccount() {
        super(AccountType.MAXI_SAVINGS);
    }

    public double interestEarned() {
        double amount = sumTransactions();
        Date tenDays = Util.getDateOnly(DateProvider.INSTANCE.after(-10));

        List<Date> dates = new ArrayList<Date>(getTransactions().keySet());
        Collections.sort(dates);
        double interest = 0.001;
        Date lastTransactionDate = dates.get(dates.size() - 1);
        if (lastTransactionDate.before(tenDays))
        {
            interest = 0.05;
        }

        return amount * interest;
    }
}

