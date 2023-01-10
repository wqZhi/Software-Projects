//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Bank Account
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

import java.util.ArrayList;

/**
 * This class models a general bank account with the ability to make deposits and withdrawals.
 */
public class BankAccount {
    private static final String BANK_NAME = "Fantastic Bank";// represents the name of the bank.
    private static ArrayList<BankAccount> accounts = new ArrayList<>();// stores all the created bank
    // accounts.
    private static int numberGenerator = 1001;// represents the number which will be assigned to the
    // next bank account to be created.
    protected final int NUMBER;// represents the unique account number of this BankAccount.
    private double balance;// represents the balance of this bank account.

    /**
     * The constructor of the BankAccount class creates a new bank account with a unique number, an
     * initial balance (provided as input), and adds it to the list of accounts.
     * <p>
     * It throws an IllegalArgumentException with a descriptive error message if the provided
     * initialBalance is less than < 10.0.
     *
     * @param initialBalance the provided bank account balance
     * @throws IllegalArgumentException if provided initialBalance is less than 10.0
     */
    public BankAccount(double initialBalance) {
        if (initialBalance < 10.0) {
            throw new IllegalArgumentException("Initial-balance value cannot less than 10.");
        }
        NUMBER = numberGenerator;
        balance = initialBalance;
        accounts.add(this);// stores the created bank accounts
        numberGenerator++;// incremented by one each time a new bank account is created.
    }

    /**
     * This method adds the specified amount provided as input to the balance of this bank account.
     * <p>
     * It throws an IllegalArgumentException with a descriptive error message if the amount to deposit
     * is non-positive ( <= 0.0).
     *
     * @param amount the amount of money to be deposited
     * @throws IllegalArgumentException if provided amount is equal or less than 0.0
     */
    public void deposit(double amount) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Deposit amount value cannot equal or less than 10.");
        }
        this.balance += amount;// Add the amount to this bank account balance
    }

    /**
     * This method is to withdraw the specified amount from this bank account from the balance of this
     * bank account.
     *
     * @param amount the amount of money to be withdrew
     * @return false if the provided amount as input is non-positive. false if balance of this account
     *         is not enough to complete this withdrawal operation. Otherwise, return true.
     */
    public boolean withdraw(double amount) {
        if (amount <= 0.0) {
            System.out.println("Withdraw amount value cannot equal or less than zero.");
            return false;
        } else if (this.balance - amount < 0) {
            System.out.println("The balance of this account is not enough to complete this withdrawal operation");
            return false;
        }

        this.balance -= amount;// Withdraw the amount from this bank account balance
        return true;
    }

    /**
     * This method is to get this bank account balance value.
     *
     * @return the current balance of this bank account
     */
    public double balance() {
        return this.balance;
    }

    /**
     * This method is to check whether this bank account equals a provided object as input.
     *
     * @param other an object with which to compare.
     * @return true if provided other object is an instance of BankAccount and has the same number as
     *         this account.
     */
    public boolean equals(Object other) {
        if (other instanceof BankAccount && ((BankAccount) other).NUMBER == NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * This method is to show this bank's number and balance.
     *
     * @return a string representation of this bank account
     */
    public String toString() {
        return "Bank Account #" + NUMBER + ": $" + balance;
    }

    /**
     * This method return a string that contains all created Bank name, and its account number and
     * balance.
     *
     * @return a string representation of all the accounts that have been created
     */
    public static String getAllAccounts() {
        String list = "*** " + BANK_NAME + " Account List ***\n";

        int sequenceNum = 1;
        for (int i = 0; i < accounts.size(); i++) {
            list += sequenceNum + ") " + accounts.get(i).toString() + "\n";
            sequenceNum++;
        }

        return list;
    }

    /**
     * This method is to runs a demo of this program.
     */
    public static void demo() {
        // Create 3 BankAccount accounts
        BankAccount bk1 = new BankAccount(100);
        BankAccount bk2 = new BankAccount(200);
        BankAccount bk3 = new BankAccount(300);
        try {
            BankAccount bk4 = new BankAccount(9);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Error detected successfully in constructor!");
        }

        System.out.println("------");

        // Deposit
        bk1.deposit(50);// After deposit, bk1 balance is 150
        bk2.deposit(150);// After deposit, bk2 balance is 350
        bk3.deposit(250);// After deposit, bk3 balance is 550
        try{
            bk3.deposit(0);// IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Successfully caught: The deposit amount can not be zero!");
        }

        System.out.println("------");

        // Withdraw
        bk1.withdraw(100);// After withdraw, bk1 balance is 50
        bk2.withdraw(200);// After withdraw, bk1 balance is 150
        bk3.withdraw(300);// After withdraw, bk1 balance is 250
        if (!(bk3.withdraw(-1))) {
            System.out.println("Method detected a invalid amount successfully!");
        }else {
            System.out.println("Program Error: Method cannot detected a invalid amount!");
        }

        if (!(bk3.withdraw(1000))) {
            System.out.println("Method detected a invalid amount successfully!");
        }else {
            System.out.println("Program Error: Method cannot detected a invalid amount!");
        }

        System.out.println("------");

        // Create 2 MoneyMarketAccount accounts
        MoneyMarketAccount mm1 = new MoneyMarketAccount(200);
        MoneyMarketAccount mm2 = new MoneyMarketAccount(400);
        try {
            MoneyMarketAccount mm3 = new MoneyMarketAccount(-393);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Error detected successfully in constructor!");
        }

        System.out.println("------");

        // Deposit
        mm1.deposit(20);// After deposit, mm1 balance is 220.0
        mm2.deposit(100);// After deposit, mm1 balance is 500.0
        try{
            mm2.deposit(-1);// IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Successfully caught: The deposit amount can not be zero!");
        }

        System.out.println("------");

        // Withdraw
        mm1.withdraw(100);// After withdraw, mm1 balance is 110.0
        mm2.withdraw(300);// After withdraw, mm1 balance is 170.0
        if (!(mm2.withdraw(-1))) {
            System.out.println("Method detected a invalid amount successfully!");
        }else {
            System.out.println("Program Error: Method cannot detected a invalid amount!");
        }

        if (!(mm2.withdraw(1000))) {
            System.out.println("Method detected a invalid amount successfully!");
        }else {
            System.out.println("Program Error: Method cannot detected a invalid amount!");
        }

        System.out.println("------");

        // Add interest
        mm1.addInterest();// After add interest, mm1 balance is 115.5
        mm2.addInterest();// After add interest, mm1 balance is 178.5

        System.out.println(getAllAccounts());
    }

    /**
     * This main method only calls the demo() method.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        demo();
    }
}
