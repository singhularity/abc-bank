package com.abc;

/**
 * Created by ssingh on 8/29/15.
 *
 * A helper class representing a Statement.
 * It has a string representation of the Summary and total on the Statement
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
