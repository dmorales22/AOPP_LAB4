/*
Name: David Morales and Axel Diaz 
Date: 07/26/2020
Course: Advanced Object-Oriented Programming
Instructor: Dr. Daniel Mejia
Lab Assignment: Programming Assignment 4
Lab Description: Bank program.

I confirm that the work of this assignment is completely my own. By turning in this assignment,
I declare that I did not receive unauthorized assistance. Moreover, all deliverables including,
but not limited to the source code, lab report and output files were written and produced by me
alone.
*/

import java.io.*;
import java.util.*;

/**This class file contains checking account information
 * @author Axel Diaz
 */
public class Savings extends Account {
    public Savings() {
    }

    /**
     * This class inherits from Account, no added attributes.
     * @param savingsNumber Savings account number.
     * @param savingsStartBalance Savings starting account balance.
     * @param savingsCurrentBalance Savings current account balance. 
     * @param savingsTransactionLog Transaction log for account.
     * @author Axel Diaz
     */
    public Savings(int savingsNumber, double savingsStartBalance, double savingsCurrentBalance, ArrayList<String> savingsTransactionLog) {
        super(savingsNumber, savingsStartBalance, savingsCurrentBalance, savingsTransactionLog);
    }

	/**
	 * Returns savings account info
	 * @return Savings account info
	 * @author David Morales
	 */
	@Override
	public String toString() {
		return "Savings Account Number: " + accountNum + "\nSavings Account Balance: $" + currentBalance + "\n";
	}
}