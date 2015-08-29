package com.abc;

import java.util.*;

/**
 * This abstract class serves as a base class for all account types.
 * If you need to implement a new concrete class it'll need to provide a concrete implemetation
 * which defines how the interest is calculated and how the statement is provided for the account
 */

public abstract class Account {

    //Type of this account, defined by the concrete class implementation
    private final AccountType accountType;

    //A map to track all transactions on this account, the transactions are tracked for a particular day
    private Map<Date, List<Transaction>> transactions;

    public Account(AccountType accountType) {
        this.accountType = accountType;
        this.transactions = new TreeMap<Date, List<Transaction>>();
    }

    /**
     *
     * @param amount Amount to deposite
     * A wrapper for deposits that get made on the current day
     */
    public void deposit(double amount) {
        deposit(amount, null);
    }

    /**
     *
     * @param amount Amount to deposit
     * @param transactionDate Date of transaction
     *
     * Method which creates a transaction for deposits and adds it to the transaction map
     */
    public void deposit(double amount, Date transactionDate) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            Transaction transaction = new Transaction(amount, TransactionType.DEPOSIT);
            if (transactionDate != null)
                transaction.setTransactionDate(transactionDate);
            addTransaction(transaction);
        }
    }

    /**
     *
     * @param amount Amount to withdraw
     * A wrapper for withdrawals that get made on the current day
     */
    public void withdraw(double amount) {
        withdraw(amount, null);
    }

    /**
     *
     * @param amount Amount to withdraw
     * @param transactionDate Date of withdrawal
     *
     *  Method which creates a transaction for withdrawals and adds it to the transaction map
     */
    public void withdraw(double amount, Date transactionDate) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            Transaction transaction = new Transaction(amount, TransactionType.WITHDRAWAL);
            if (transactionDate != null)
                transaction.setTransactionDate(transactionDate);
            addTransaction(transaction);
        }
    }

    /**
     *
     * @param transaction Transaction to add to transactions map
     *
     * Helper method to add a transaction
     */
    private void addTransaction(Transaction transaction)
    {
        Date transactionDate = Util.getDateOnly(transaction.getTransactionDate());
        if(transactions.containsKey(transactionDate))
        {
            transactions.get(transactionDate).add(transaction);
        }
        else
        {
            List<Transaction> transactionList = new ArrayList<Transaction>();
            transactionList.add(transaction);
            transactions.put(transactionDate, transactionList);
        }
    }

    /**
     *
     * @param to Account where the amount is transferred
     * @param amount Amount to transfer
     *
     * Method that allows transferring money from one account to another
     */
    public void transfer(Account to, double amount)
    {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            this.withdraw(amount);
            to.deposit(amount);
        }
    }

    /**
     *
     * @return List<Transaction>
     *
     * Get a list of all transactions on this account
     */
    protected List<Transaction> getTransactionsList() {
        List<Transaction> allTransactions = new ArrayList<Transaction>();
        for(Date date: transactions.keySet()) {
            allTransactions.addAll(transactions.get(date));
        }
        return allTransactions;
    }

    /**
     *
     * @return Map
     *
     * Getter for the transactions map
     */
    protected Map<Date, List<Transaction>> getTransactions() {
        return transactions;
    }

    /**
     *
     * @return interest
     *
     * Abstract method to calculate interests on this account, to be implemented by sub classes
     */
    public abstract double interestEarned();

    /**
     *
     * @return
     * Builds the statement for this account
     */
    public abstract Statement statementForAccount();

    /**
     *
     * @param dateOfTransaction Date of transactions
     * @return Total transactions on a given day
     *
     * Method to calculate the total amount of transactions on a given date
     */
    public double sumTransactions(Date dateOfTransaction) {
        double amount = 0.0;
        for (Transaction t: getTransactions().get(dateOfTransaction))
            amount += t.getAmount();
        return amount;
    }

    /**
     *
     * @return Amount of all transactions on all days
     *
     * Calculates the total amount of transactions on all days
     */
    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t: getTransactionsList())
            amount += t.getAmount();
        return amount;
    }

    /**
     *
     * @param s String Builder
     * @return Summary of transactions
     */
    protected double transactionSummary(StringBuilder s)
    {
        double total = 0.0;
        for (Transaction t : getTransactionsList()) {
            Double amount = t.getAmount();
            s.append(t);
            total += amount;
        }
        s.append("Total ").append(Util.toDollars(total));
        return total;
    }
}
