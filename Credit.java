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

/**This class file contains checking account information
 * @author Axel Diaz
 */
public class Credit extends Account {
    private double creditMax;// Credit class

    public Credit(){
    }

    /**
     * This class inherits from Account, no added attributes.
     * @param creditNumber Credit account number.
     * @param creditStartBalance Credit starting account balance.
     * @param creditCurrentBalance Credit current account balance. 
     * @param creditTransactionLog Transaction log for account.
     * @param creditMax The credit max for the account. 
     * @author Axel Diaz
     */
    public Credit(int creditNumber, double creditStartBalance, double creditCurrentBalance, ArrayList<String> creditTransactionLog, double creditMax) {
        super(creditNumber, creditStartBalance, creditCurrentBalance, creditTransactionLog);
        this.creditMax = creditMax;
    }

    //Getters 
    public double getCreditMax(){
        return creditMax;
    }

    //Setters 
    public void setCreditMax(double creditMax){
        this.creditMax = creditMax;
    }

	/**
	 * Returns credit account info
	 * @return Credit account info
	 * @author David Morales
	 */
	@Override
	public String toString() {
		return "Credit Account Number: " + accountNum + "\nCredit Account Balance: $" + currentBalance + "\n";
	}
}