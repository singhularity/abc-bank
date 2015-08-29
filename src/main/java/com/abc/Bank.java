package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Bank.
 * Defines methods for creating a new Customer, calculating the total interest for this customer and a summary
 * of transactions for this customer
 */
public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    /**
     * Method to add a new Customer to the Bank
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     *
     * @return Summary of transactions for this customer on all his accounts
     */
    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + Util.format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    /**
     *
     * @return The total interest paid to this customer on all accounts
     */
    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }
}
