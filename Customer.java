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

public class Customer extends Person {
    private Checking myChecking;
    private Savings mySavings;
    private Credit myCredit;
    private String password;

    public Customer() { //Default constructor 
        super();
    }

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

	/**
	 * Returns all user account info
	 * @return All account info
	 */
	public String toString() {
		return "First Name: " + firstName + "\nLast Name: " + lastName + "\nDate of Birth: " + dob + "\nAddress: " + address + "\nPhone Number: " + phoneNum + "\nEmail:" + email + "\nIdentification Number: " + idNum + "\n" + this.getChecking().toString() + "\n" + this.getSavings().toString() + "\n" + this.getCredit().toString();
	}
}