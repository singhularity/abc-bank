package com.abc;

/**
 * Created by ssingh on 8/29/15.
 */
public class Statement {
    private String statementSummary;
    private double statementTotal;

    public Statement(String statementSummary, double statementTotal) {
        this.statementSummary = statementSummary;
        this.statementTotal = statementTotal;
    }

    public String getStatementSummary() {
        return statementSummary;
    }

    public double getStatementTotal() {
        return statementTotal;
    }
}
