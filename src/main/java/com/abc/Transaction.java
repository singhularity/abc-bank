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
        this.amount = amount;
        this.type = type;
        this.transactionDate = DateProvider.INSTANCE.now();
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        return "  " + (type == TransactionType.WITHDRAWAL ? "withdrawal" : "deposit") + " " + Util.toDollars(amount) + "\n";
    }
}
