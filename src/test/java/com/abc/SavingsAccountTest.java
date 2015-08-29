package com.abc;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


/**
 * Created by ssingh on 8/29/15.
 */
public class SavingsAccountTest {

    private static final double DOUBLE_DELTA = 1e-4;

    @Test
    public void testTotalInterest()
    {
        Account savingsAccount = new SavingsAccount();
        savingsAccount.deposit(100, DateProvider.INSTANCE.after(new Date(), -20));
        savingsAccount.deposit(50, DateProvider.INSTANCE.after(new Date(), -10));
        savingsAccount.deposit(100);
        assertEquals(0.6004, savingsAccount.interestEarned(), DOUBLE_DELTA);
    }
}
