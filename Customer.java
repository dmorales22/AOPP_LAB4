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

/**This class contains all personal and account information. 
 * @author Axel Diaz
*/
public class Customer extends Person implements Printable {
    private Checking myChecking;
    private Savings mySavings;
    private Credit myCredit;
    private String password;

    public Customer() { //Default constructor 
        super();
    }
    /**Customer constructor with all attributes 
	 * @param fN First name.
	 * @param lN Last name.
	 * @param dob Date of birth.
	 * @param ad Address.
	 * @param pN Phone number.
	 * @param eM Email.
	 * @param id ID number.
	 * @param uC Checking account.
	 * @param uS Savings account.
	 * @param uCr Credit account.
	 * @param pS Password. 
	 * @author Axel Diaz 
	 */
    public Customer(String fN, String lN, String dob, String ad, String pN, String eM, int id, Checking uC, Savings uS, Credit uCr, String pS) { 
        super(fN, lN, dob, ad, pN, eM, id);
        this.myChecking = uC;
        this.mySavings = uS;
        this.myCredit = uCr;
        this.password = pS;
    }

    //Setters
    public void setChecking(Checking mC) {
        this.myChecking = mC;
    }

    public void setSavings(Savings mS) {
        this.mySavings = mS;
    }

    public void setCredit(Credit mC) {
        this.myCredit = mC;
    }

    public void setPassword(String pS) {
        this.password = pS;
    }

    //Getters 
    public Checking getChecking() {
        return myChecking;
    }

    public Savings getSavings() {
        return mySavings;
    }

    public Credit getCredit() {
        return myCredit;
    }

    public String getPassword() {
        return password;
    }

    /** This print method prints all the individual user information 
    * @author Axel Diaz 
    */
    public void print(){
        System.out.println("BANK USER INFORMATION");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNum);
        System.out.println("Email:" + email);
        System.out.println("Password: " + password);
        System.out.println("Identification Number: " + idNum);
        System.out.println("");
        System.out.println(myChecking.toString());
        System.out.println(mySavings.toString());
        System.out.println(myCredit.toString());
        System.out.println("");
    }

    /** This print method prints all the individual user transactions. 
     * @author Axel Diaz 
     */
    public void printTransactionLog() {
        System.out.println("BANK USER TRANSACTIONS");
        System.out.println("Checking Account History:");
        for(int i = 0; i < myChecking.getTransactionLog().size(); i++) { //Prints out checking account transactions
			System.out.println(myChecking.getTransactionLog().get(i));
		}

		System.out.println("Savings Account History:");

		for(int i = 0; i < mySavings.getTransactionLog().size(); i++) { //Prints out savings account transactions
			System.out.println(mySavings.getTransactionLog().get(i));
		}

		System.out.println("Credit Account History:"); 

		for(int i = 0; i < myCredit.getTransactionLog().size(); i++) { //Prints out credit account transactions
			System.out.println(myCredit.getTransactionLog().get(i));
		}
		System.out.println("");
    }

	/** This print method prints the individual user bank statement. 
	 * @author Axel Diaz 
	*/
    public void printBankStatement() { 
		Date date = new Date();

		System.out.println("David Axel Bank Statement            Date:" + date);
		System.out.println("-----------------------------------------------------------");
		System.out.println("Name: " + firstName + " " + lastName);
		System.out.println("Date of Birth: " + dob); 
		System.out.println("Identification Number: " + idNum);
		System.out.println("Address: " + address);
		System.out.println("Phone Number: " + phoneNum);
		System.out.println("");
		System.out.println("Checking Account Number: " + myChecking.getAccountNum());
		System.out.println("Savings Account Number: " + mySavings.getAccountNum());
		System.out.println("Credit Account Number: " + myCredit.getAccountNum());
		System.out.println("");
		System.out.println("Checking Starting Balance: $" + myChecking.getStartingBalance());
		System.out.println("Savings Starting Balance: $" + mySavings.getStartingBalance());
		System.out.println("Credit Starting Balance: $" + myCredit.getStartingBalance());
		System.out.println("");
		System.out.println("Checking Current Balance: $" + myChecking.getCurrentBalance());
		System.out.println("Savings Current Balance: $" + mySavings.getCurrentBalance());
		System.out.println("Credit Current Balance: $" + myCredit.getCurrentBalance());
		System.out.println("");
		System.out.println("Transaction History:");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Checking Account History:");

		for(int i = 0; i < myChecking.getTransactionLog().size(); i++) { //Prints out checking account transactions
			System.out.println(myChecking.getTransactionLog().get(i));
		}

		System.out.println("Savings Account History:");

		for(int i = 0; i < mySavings.getTransactionLog().size(); i++) { //Prints out savings account transactions
			System.out.println(mySavings.getTransactionLog().get(i));
		}

		System.out.println("Credit Account History:"); 

		for(int i = 0; i < myCredit.getTransactionLog().size(); i++) { //Prints out credit account transactions
			System.out.println(myCredit.getTransactionLog().get(i));
		}
		
		System.out.println("");
    }

	/**
	 * Returns all user account info
	 * @return All account info
	 * @author David Morales
	 */
	public String toString() {
		return "First Name: " + firstName + "\nLast Name: " + lastName + "\nDate of Birth: " + dob + "\nAddress: " + address + "\nPhone Number: " + phoneNum + "\nEmail:" + email + "\nIdentification Number: " + idNum + "\n" + this.getChecking().toString() + "\n" + this.getSavings().toString() + "\n" + this.getCredit().toString();
	}
}