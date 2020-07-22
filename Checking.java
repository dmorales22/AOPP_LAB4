/*
Name: David Morales
Date: 07/10/2020
Course: Advanced Object-Oriented Programming
Instructor: Dr. Daniel Mejia
Lab Assignment: Programming Assignment 3
Lab Description: Simple bank program w/ extended features.

I confirm that the work of this assignment is completely my own. By turning in this assignment,
I declare that I did not receive unauthorized assistance. Moreover, all deliverables including,
but not limited to the source code, lab report and output files were written and produced by me
alone.
*/

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

/**This class file contains checking account information
 * @author David Morales
 */
public class Checking extends Account{
	public Checking() {
	}

	/**
	 * This class inherits from Account, no added attributes.
	 * @param accountNum Checking account number
	 * @param accountBalance Checking account balance
	 */
	public Checking(int accountNum, double startingBalance, double currentBalance, ArrayList<String> tL) {
		super(accountNum, startingBalance, currentBalance, tL);
	}

	/**
	 * Returns checking account info
	 * @return Checking account info
	 */
	@Override
	public String toString() {
		return "Checking Account Number: " + accountNum + "\nChecking Account Balance: $" + currentBalance + "\n";
	}
}