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

/**This abstract class contains attributes for bank accounts
 * @author David Morales
 */
public abstract class Account {
	protected int accountNum;
	protected double startingBalance;
	protected double currentBalance;
	protected ArrayList<String> transactionLog;

	/**Default constructor
	*
	*/
	public Account() {
	}

	/**
	 * @param aC Account number for specific account 
	 * @param aB Account balance for specific account
	 */
	public Account(int aC, double sB, double cB, ArrayList<String> tL) {
		this.accountNum = aC;
		this.startingBalance = sB;
		this.currentBalance = cB;
		this.transactionLog = tL;
	}

	//Setters
	public void setAccountNum(int aC) {
		this.accountNum = aC; 
	}

	public void setStartingBalance(double sB) {
		this.startingBalance = sB;
	}

	public void setCurrentBalance(double cB) {
		this.currentBalance = cB;
	}

	public void setTransactionLog(ArrayList<String> tL) {
		this.transactionLog = tL;
	}

	//Getters
	public int getAccountNum() {
		return accountNum;
	}

	public double getStartingBalance() {
		return startingBalance;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public ArrayList<String> getTransactionLog() {
		return transactionLog;
	}

	/**
	 * Prints out all account info of the bank user
	 * @return String with account info.
	 */
	public String toString() {
		return "Account#" + accountNum + " " + "Your balance is: $" + currentBalance + ". \n";
	}

}