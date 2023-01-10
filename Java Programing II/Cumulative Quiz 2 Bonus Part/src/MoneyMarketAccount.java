//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Money Market Account
// Course: CS 300 Fall 2020
//
// Author: Weiqian Zhi
// Email: wzhi3@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class extends BankAccount.class and models a a special type of bank accounts which earns
 * extra interest, but has penalty for withdrawal operations.
 */
public class MoneyMarketAccount extends BankAccount {
    private static final double PENALTY_RATE = 0.1;// represent the penalty to be withdrawn along with every
    // withdrawal operation.
    private static final double INTEREST_RATE = 0.05;// represents the interest rate.

    /**
     * The constructor of the MoneyMarketAccount class creates a new money market bank account with a
     * unique number, an initial balance (provided as input), and adds it to the list of accounts.
     * <p>
     * It throws an IllegalArgumentException with a descriptive error message if the provided
     * initialBalance is less than < 10.0.
     *
     * @param initialBalance the provided bank account balance
     * @throws IllegalArgumentException if the provided initialBalance is less than 10.0
     */
    public MoneyMarketAccount(double initialBalance) {
        super(initialBalance);
    }

    /**
     * This method is to adds interest to the balance of this account.
     */
    public void addInterest() {
        deposit(INTEREST_RATE * this.balance());// Call deposit method in BankAccount.class to add the
        // balance with interest.
    }

    /**
     * This method is to withdraw the specified amount, plus the penalty for withdrawing from this
     * money market account. The penalty is accounted as amount ∗ penalty rate. This method overrides
     * the withdraw() method defined in the super class BankAccount, and uses BankAccount.withdraw()
     * method to perform the actual withdrawal operation.
     *
     * @param amount the amount of money to be withdrew
     * @return true if this operation completes successfully, and false if not
     */
    @Override
    public boolean withdraw(double amount) {
        double penaltyAmount = amount + amount * PENALTY_RATE;// The amount after penalty

        if (super.withdraw(penaltyAmount)) {// Uses BankAccount.withdraw() method
            return true;
        }

        return false;
    }

    /**
     * This method return a string that contains all created money market Bank name, and its account
     * number and balance.
     *
     * @return a string representation of this money market bank account that have been created
     */
    public String toString() {
        return "Money Market Account #" + NUMBER + ": $" + this.balance();
    }

}

