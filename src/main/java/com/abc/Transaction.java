package com.abc;

import java.util.Date;

public class Transaction {
    private final double amount;
    private TransactionType type;
    private Date transactionDate;

    public double getAmount() {
        return type == TransactionType.DEPOSIT ? amount : -amount;
    }

    public Transaction(double amount, TransactionType type) {
        this(amount, type, DateProvider.INSTANCE.now());
    }

    public Transaction(double amount, TransactionType type, Date transactionDate) {
        this.amount = amount;
        this.type = type;
        this.transactionDate = transactionDate;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "  " + (type == TransactionType.WITHDRAWAL ? "withdrawal" : "deposit") + " " + Util.toDollars(amount) + "\n";
    }
}
