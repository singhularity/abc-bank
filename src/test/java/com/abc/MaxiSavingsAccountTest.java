package com.abc;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


/**
 * Created by ssingh on 8/29/15.
 */
public class MaxiSavingsAccountTest {

    private static final double DOUBLE_DELTA = 1e-4;

    @Test
    public void testTotalInterestWithWithdrawalBefore10Days()
    {
        Account maxiSavingsAccount = new MaxiSavingsAccount();
        maxiSavingsAccount.deposit(100, DateProvider.INSTANCE.after(new Date(), -20));
        maxiSavingsAccount.withdraw(50, DateProvider.INSTANCE.after(new Date(), -11));
        maxiSavingsAccount.deposit(100);
        assertEquals(5.0526, maxiSavingsAccount.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void testTotalInterestWithWithdrawalWithin10Days()
    {
        Account maxiSavingsAccount = new MaxiSavingsAccount();
        maxiSavingsAccount.deposit(100, DateProvider.INSTANCE.after(new Date(), -20));
        maxiSavingsAccount.deposit(50, DateProvider.INSTANCE.after(new Date(), -10));
        maxiSavingsAccount.withdraw(100);
        assertEquals(0.0502, maxiSavingsAccount.interestEarned(), DOUBLE_DELTA);
    }
}
