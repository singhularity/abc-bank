package com.abc;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private final AccountType accountType;
    private List<Transaction> transactions;

    public Account(AccountType accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount, TransactionType.DEPOSIT));
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount, TransactionType.WITHDRAWAL));
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public abstract double interestEarned();

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.getAmount();
        return amount;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}
