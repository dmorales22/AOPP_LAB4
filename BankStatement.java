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

/**This class contains information for bank statements for users.
 * @author David Morales
 */
public class BankStatement implements Printable { 
	private Customer bankUser;
	private String date; 

	/**Default constructor 
	 *@author David Morales
	 *
	*/
	public BankStatement() { 
	}

	/**Constructor
	 * @param bU The bank user object.
	 * @param d String that has the date. 
	 * @author David Morales
	*/
	public BankStatement(Customer bU, String d) { 
		this.bankUser = bU;
		this.date = d;
	}
	
	//Setters
	public void setBankUser(Customer bU) { 
		this.bankUser = bU;
	}

	public void setDate(String d) {
		this.date = d;
	}

	//Getters
	public Customer getBankUser() {
		return bankUser;
	}

	public String getDate() {
		return date;
	}

	/**This method is to create bank statements for the user containing all personal and account
	* information, including transaction history from all accounts. 
	* @author David Morales
	*/
	public void createBankStatement() throws IOException {
		try { 
			String bankStatementFilename = bankUser.getLastName() + "_" + bankUser.getFirstName() + "_BankStatement.txt"; 
			File bankStatementTxt = new File(bankStatementFilename);

			if(!bankStatementTxt.exists()) { //If text file doesn't exit, then it creates one
				bankStatementTxt.createNewFile();
			}

			FileWriter fw = new FileWriter(bankStatementFilename);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter textWriter = new PrintWriter(bw);
			
			//Writes the text file with strings
			textWriter.println("David Axel Bank Statement            Date:" + date);
			textWriter.println("-----------------------------------------------------------");
			textWriter.println("Name: " + bankUser.getFirstName() + " " + bankUser.getLastName());
			textWriter.println("Date of Birth: " + bankUser.getDob()); 
			textWriter.println("Identification Number: " + bankUser.getIdNum());
			textWriter.println("Address: " + bankUser.getAddress());
			textWriter.println("Phone Number: " + bankUser.getPhoneNum());
			textWriter.println("");
			textWriter.println("Checking Account Number: " + bankUser.getChecking().getAccountNum());
			textWriter.println("Savings Account Number: " + bankUser.getSavings().getAccountNum());
			textWriter.println("Credit Account Number: " + bankUser.getCredit().getAccountNum());
			textWriter.println("");
			textWriter.println("Checking Starting Balance: $" + bankUser.getChecking().getStartingBalance());
			textWriter.println("Savings Starting Balance: $" + bankUser.getSavings().getStartingBalance());
			textWriter.println("Credit Starting Balance: $" + bankUser.getCredit().getStartingBalance());
			textWriter.println("");
			textWriter.println("Checking Current Balance: $" + bankUser.getChecking().getCurrentBalance());
			textWriter.println("Savings Current Balance: $" + bankUser.getSavings().getCurrentBalance());
			textWriter.println("Credit Current Balance: $" + bankUser.getCredit().getCurrentBalance());
			textWriter.println("");
			textWriter.println("Transaction History:");
			textWriter.println("-----------------------------------------------------------");
			textWriter.println("Checking Account History:");
			
			Checking userChecking = bankUser.getChecking();
			Savings userSavings = bankUser.getSavings();
			Credit userCredit = bankUser.getCredit();
	
			for(int i = 0; i < userChecking.getTransactionLog().size(); i++) { //Prints out checking account transactions
				textWriter.println(bankUser.getChecking().getTransactionLog().get(i));
			}

			textWriter.println("Savings Account History:");

			for(int i = 0; i < userSavings.getTransactionLog().size(); i++) { //Prints out savings account transactions
				textWriter.println(bankUser.getSavings().getTransactionLog().get(i));
			}

			textWriter.println("Credit Account History:"); 

			for(int i = 0; i < userCredit.getTransactionLog().size(); i++) { //Prints out credit account transactions
				textWriter.println(bankUser.getCredit().getTransactionLog().get(i));
			}

			textWriter.close();
			System.out.println("Bank statement successful.");
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}

	/** This print method prints all the individual user information 
	 * @author Axel Diaz 
	*/
	public void print(){
		System.out.println("BANK USER INFORMATION");
		System.out.println("First Name: " + bankUser.getFirstName());
		System.out.println("Last Name: " + bankUser.getLastName());
		System.out.println("Date of Birth: " + bankUser.getDob());
		System.out.println("Address: " + bankUser.getAddress());
		System.out.println("Phone Number: " + bankUser.getPhoneNum());
		System.out.println("Email:" + bankUser.getEmail());
		System.out.println("Password: " + bankUser.getPassword());
		System.out.println("Identification Number: " + bankUser.getIdNum());
		System.out.println(bankUser.getChecking().toString());
		System.out.println(bankUser.getSavings().toString());
		System.out.println(bankUser.getCredit().toString());
	}

	/** This print method prints all the individual user transactions. 
	 * @author Axel Diaz 
	*/
	public void printTransactionLog() {
		Checking userChecking = bankUser.getChecking();
		Savings userSavings = bankUser.getSavings();
		Credit userCredit = bankUser.getCredit();

		System.out.println("BANK USER TRANSACTIONS");
		System.out.println("Checking Account History:");
		for(int i = 0; i < userChecking.getTransactionLog().size(); i++) { //Prints out checking account transactions
			System.out.println(userChecking.getTransactionLog().get(i));
		}

		System.out.println("Savings Account History:");

		for(int i = 0; i < userSavings.getTransactionLog().size(); i++) { //Prints out savings account transactions
			System.out.println(userSavings.getTransactionLog().get(i));
		}

		System.out.println("Credit Account History:"); 

		for(int i = 0; i < userCredit.getTransactionLog().size(); i++) { //Prints out credit account transactions
			System.out.println(userCredit.getTransactionLog().get(i));
		}
		System.out.println("");
	}

	/** This print method prints the individual user bank statement. 
	 * @author Axel Diaz 
	*/
	public void printBankStatement() { 
		System.out.println("David Axel Bank Statement            Date:" + date);
		System.out.println("-----------------------------------------------------------");
		System.out.println("Name: " + bankUser.getFirstName() + " " + bankUser.getLastName());
		System.out.println("Date of Birth: " + bankUser.getDob()); 
		System.out.println("Identification Number: " + bankUser.getIdNum());
		System.out.println("Address: " + bankUser.getAddress());
		System.out.println("Phone Number: " + bankUser.getPhoneNum());
		System.out.println("Checking Account Number: " + bankUser.getChecking().getAccountNum());
		System.out.println("Savings Account Number: " + bankUser.getSavings().getAccountNum());
		System.out.println("Credit Account Number: " + bankUser.getCredit().getAccountNum());
		System.out.println("");
		System.out.println("Checking Starting Balance: $" + bankUser.getChecking().getStartingBalance());
		System.out.println("Savings Starting Balance: $" + bankUser.getSavings().getStartingBalance());
		System.out.println("Credit Starting Balance: $" + bankUser.getCredit().getStartingBalance());
		System.out.println("");
		System.out.println("Checking Current Balance: $" + bankUser.getChecking().getCurrentBalance());
		System.out.println("Savings Current Balance: $" + bankUser.getSavings().getCurrentBalance());
		System.out.println("Credit Current Balance: $" + bankUser.getCredit().getCurrentBalance());
		System.out.println("");
		System.out.println("Transaction History:");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Checking Account History:");
			
		Checking userChecking = bankUser.getChecking();
		Savings userSavings = bankUser.getSavings();
		Credit userCredit = bankUser.getCredit();

		for(int i = 0; i < userChecking.getTransactionLog().size(); i++) { //Prints out checking account transactions
			System.out.println(bankUser.getChecking().getTransactionLog().get(i));
		}

		System.out.println("Savings Account History:");

		for(int i = 0; i < userSavings.getTransactionLog().size(); i++) { //Prints out savings account transactions
			System.out.println(bankUser.getSavings().getTransactionLog().get(i));
		}

		System.out.println("Credit Account History:"); 

		for(int i = 0; i < userCredit.getTransactionLog().size(); i++) { //Prints out credit account transactions
			System.out.println(bankUser.getCredit().getTransactionLog().get(i));
		}

		System.out.println("");
	}
}