package com.abc;

import java.util.*;

public abstract class Account {

    private final AccountType accountType;
    private Map<Date, List<Transaction>> transactions;

    public Account(AccountType accountType) {
        this.accountType = accountType;
        this.transactions = new TreeMap<Date, List<Transaction>>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            Transaction transaction = new Transaction(amount, TransactionType.DEPOSIT);
            addTransaction(transaction);
        }
    }

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

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            addTransaction(new Transaction(amount, TransactionType.WITHDRAWAL));
        }
    }

    public void transfer(Account to, double amount)
    {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            this.withdraw(amount);
            to.deposit(amount);
        }
    }

    protected List<Transaction> getTransactionsList() {
        List<Transaction> allTransactions = new ArrayList<Transaction>();
        for(Date date: transactions.keySet()) {
            allTransactions.addAll(transactions.get(date));
        }
        return allTransactions;
    }

    protected Map<Date, List<Transaction>> getTransactions() {
        return transactions;
    }

    public abstract double interestEarned();

    public abstract Statement statementForAccount();

    public double sumTransactions(Date dateOfTransaction) {
        double amount = 0.0;
        for (Transaction t: getTransactions().get(dateOfTransaction))
            amount += t.getAmount();
        return amount;
    }

    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t: getTransactionsList())
            amount += t.getAmount();
        return amount;
    }

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
