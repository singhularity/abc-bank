package com.abc;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


/**
 * Created by ssingh on 8/29/15.
 */
public class CheckingAccountTest {

    private static final double DOUBLE_DELTA = 1e-4;

    @Test
    public void testTotalInterest()
    {
        Account checkingAccount = new CheckingAccount();
        checkingAccount.deposit(100, DateProvider.INSTANCE.after(new Date(), -20));
        checkingAccount.deposit(50, DateProvider.INSTANCE.after(new Date(), -10));
        checkingAccount.deposit(100);
        assertEquals(0.2502, checkingAccount.interestEarned(), DOUBLE_DELTA);
    }
}
