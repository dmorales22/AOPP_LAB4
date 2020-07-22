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

	public BankStatement() { 
	}

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
			textWriter.println("David's Bank Bank Statement            Date:" + date);
			textWriter.println("-----------------------------------------------------------");
			textWriter.println("Name: " + bankUser.getFirstName() + " " + bankUser.getLastName());
			textWriter.println("Date of Birth: " + bankUser.getDob()); 
			textWriter.println("Identification Number: " + bankUser.getIdNum());
			textWriter.println("Address: " + bankUser.getAddress());
			textWriter.println("Phone Number: " + bankUser.getPhoneNum());
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

	public void print(Customer bankUser){
	}

	public void printTransactionLog(Customer bankUser) {
	}

	public void printBankStatement(Customer bankUser) { 
	}
}