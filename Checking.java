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

import java.util.*;

/**This class file contains checking acimport java.io.*;count information
 * @author Axel Diaz
 */
public class Checking extends Account{
	public Checking() {
	}

    /**
     * This class inherits from Account, no added attributes.
     * @param accountNum Checking account number.
     * @param startingBalance Checking starting account balance.
     * @param currentBalance Checking current account balance. 
     * @param tL Transaction log for account.
     * @author Axel Diaz
     */
	public Checking(int accountNum, double startingBalance, double currentBalance, ArrayList<String> tL) {
		super(accountNum, startingBalance, currentBalance, tL);
	}

	/**
	 * Returns checking account info
	 * @return Checking account info
	 * @author David Morales
	 */
	@Override
	public String toString() {
		return "Checking Account Number: " + accountNum + "\nChecking Account Balance: $" + currentBalance + "\n";
	}
}