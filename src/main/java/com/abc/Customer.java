package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account account : accounts)
            total += account.interestEarned();
        return total;
    }

    public String getStatement() {
        StringBuilder statementBuilder = new StringBuilder("Statement for " + name + "\n");
        double total = 0.0;
        for (Account a : accounts) {
            Statement accountStatement = a.statementForAccount();
            statementBuilder.append("\n").append(accountStatement.getStatementSummary()).append("\n");

            total += accountStatement.getStatementTotal();
        }
        statementBuilder.append("\nTotal In All Accounts ").append(Util.toDollars(total));
        return statementBuilder.toString();
    }
}
