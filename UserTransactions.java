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

/**This class has the methods to perform all the user banking functions.
* @author Axel Diaz
*/
public class UserTransactions {

	//Functions
	/**This function transfers money(values) from one bank user's balance to another's. 
	 * @param otherAccount The other bank user to send money to
	 * @param amountToBePaid The amount of money to send to the other user
	 * @param accountType The account type (checking, savings, credit) of the other user
	 * @param myAccountType The account type from the bank user sending money
	 * @param myFirstName The bank user sending money first name
	 * @param myLastName The bank user sending money last name
	 * @author Axel Diaz 
	 */
	public static void payAccount(Customer myAccount, Customer otherAccount, double amountToBePaid, int otherAccountType, int myAccountType) throws IOException {
		try {
			Date date = new Date(); //Get date
			String transactionStr = "";

			if(amountToBePaid < 0) { //Checks balance of checking an savings accounts
				System.out.println("No negative amounts. \n");
				return;
			}

			Checking otherChecking = otherAccount.getChecking();
			Savings otherSavings = otherAccount.getSavings();
			Credit otherCredit = otherAccount.getCredit();

			switch(myAccountType) { //switch statement to send the other bank user money  
				case 0: //Sending from checking account
					if(amountToBePaid > myAccount.getChecking().getCurrentBalance()) {
						System.out.println("Transfer failed. Insufficient amount. \n");
						return;
					}

					myAccount.getChecking().setCurrentBalance(myAccount.getChecking().getCurrentBalance() - amountToBePaid);
					switch(otherAccountType) { //Switch statement to send money to.
						case 0: //To checking accounts 
							otherChecking.setCurrentBalance(otherChecking.getCurrentBalance() + amountToBePaid); //Adds onto the other user's balance

							transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Checking,pays," + otherAccount.getFirstName() + "," + otherAccount.getLastName() + ",Checking," + amountToBePaid;

							UserUtilities.transactionLogger(transactionStr); //Writes the string to log the transaction in transactionLog.txt 
							myAccount.getChecking().getTransactionLog().add(transactionStr);
							break;

						case 1: //To savings accounts
							otherSavings.setCurrentBalance(otherSavings.getCurrentBalance() + amountToBePaid); //Adds onto the other user's balance

							transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Checking,pays," + otherAccount.getFirstName() + "," + otherAccount.getLastName() + ",Savings," + amountToBePaid;

							UserUtilities.transactionLogger(transactionStr); //Writes the string to log the transaction in transactionLog.txt 
							myAccount.getChecking().getTransactionLog().add(transactionStr);
							break;

						case 2: //To credit accounts 
							otherCredit.setCurrentBalance(otherCredit.getCurrentBalance() + amountToBePaid); //Adds onto the other user's balance

							transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Checking,pays," + otherAccount.getFirstName() + "," + otherAccount.getLastName() + ",Credit," + amountToBePaid;

							UserUtilities.transactionLogger(transactionStr); //Writes the string to log the transaction in transactionLog.txt 
							myAccount.getChecking().getTransactionLog().add(transactionStr);
							break;
					}
					System.out.println("Transfer success! From " + myAccount.getFirstName() + " " + myAccount.getLastName() + ". Your balance is: $" + myAccount.getChecking().getCurrentBalance() + "\n");
					break;

				case 1: //Sending from savings account
					if(amountToBePaid > myAccount.getSavings().getCurrentBalance()) {
						System.out.println("Transfer failed. Insufficient amount. \n");
						return;
					}

					myAccount.getSavings().setCurrentBalance(myAccount.getSavings().getCurrentBalance() - amountToBePaid);
					switch(otherAccountType) {
						case 0: //To other user checking account
							otherChecking.setCurrentBalance(otherChecking.getCurrentBalance() + amountToBePaid); //Adds onto the other user's balance

							transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Savings,pays," + otherAccount.getFirstName() + "," + otherAccount.getLastName() + ",Checking," + amountToBePaid;

							UserUtilities.transactionLogger(transactionStr); //Writes the string to log the transaction in transactionLog.txt 
							myAccount.getSavings().getTransactionLog().add(transactionStr);
							break;

						case 1: //To other user savings account 
							otherSavings.setCurrentBalance(otherSavings.getCurrentBalance() + amountToBePaid); //Adds onto the other user's balance

							transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Savings,pays," + otherAccount.getFirstName() + "," + otherAccount.getLastName() + ",Savings," + amountToBePaid;

							UserUtilities.transactionLogger(transactionStr); //Writes the string to log the transaction in transactionLog.txt 
							myAccount.getSavings().getTransactionLog().add(transactionStr);
							break;

						case 2: //To other user credit account
							otherCredit.setCurrentBalance(otherCredit.getCurrentBalance() + amountToBePaid); //Adds onto the other user's balance

							transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Savings,pays," + otherAccount.getFirstName() + "," + otherAccount.getLastName() + ",Credit," + amountToBePaid;

							UserUtilities.transactionLogger(transactionStr); //Writes the string to log the transaction in transactionLog.txt 
							myAccount.getSavings().getTransactionLog().add(transactionStr);
							break;
					}
					System.out.println("Transfer success! From " + myAccount.getFirstName() + " " + myAccount.getLastName() + ". Your balance is: $" + myAccount.getSavings().getCurrentBalance());
					break;

				case 2: //From credit accounts
					if(myAccount.getCredit().getCreditMax() < -(myAccount.getCredit().getCurrentBalance() - amountToBePaid)) { //Checks if account is not at max credit
						System.out.println("Insufficient credit. \n");
						return;
					}

					myAccount.getCredit().setCurrentBalance(myAccount.getCredit().getCurrentBalance() - amountToBePaid);
					switch(otherAccountType) {
						case 0: //To other user checking account
							otherChecking.setCurrentBalance(otherChecking.getCurrentBalance() + amountToBePaid); //Adds onto the other user's balance

							transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Credit,pays," + otherAccount.getFirstName() + "," + otherAccount.getLastName() + ",Checking," + amountToBePaid;

							UserUtilities.transactionLogger(transactionStr); //Writes the string to log the transaction in transactionLog.txt 
							myAccount.getSavings().getTransactionLog().add(transactionStr);
							break;

						case 1: //To other user savings account 
							otherSavings.setCurrentBalance(otherSavings.getCurrentBalance() + amountToBePaid); //Adds onto the other user's balance

							transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Credit,pays," + otherAccount.getFirstName() + "," + otherAccount.getLastName() + ",Savings," + amountToBePaid;

							UserUtilities.transactionLogger(transactionStr); //Writes the string to log the transaction in transactionLog.txt 
							myAccount.getCredit().getTransactionLog().add(transactionStr);
							break;

						case 2: //To other user credit account 
							otherCredit.setCurrentBalance(otherCredit.getCurrentBalance() + amountToBePaid); //Adds onto the other user's balance

							transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Credit,pays," + otherAccount.getFirstName() + "," + otherAccount.getLastName() + ",Credit," + amountToBePaid;

							UserUtilities.transactionLogger(transactionStr); //Writes the string to log the transaction in transactionLog.txt 
							myAccount.getCredit().getTransactionLog().add(transactionStr);
							break;
					}
					System.out.println("Transfer success! From " + myAccount.getFirstName() + " " + myAccount.getLastName() + ". Your balance is: $" + myAccount.getCredit().getCurrentBalance());
					break;
			}
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}

	/**This function deposits/adds to the bank user's balance and records it.
	 * @param depositAccount The amount of money to deposit.
	 * @param accountType The account type (checking, savings, credit) to deposit to.
	 * @param myAccount The user account object.
	 * @author Axel Diaz
	 */
	public static void depositAccount(double depositAmount, int accountType, Customer myAccount) throws IOException { 
		try {
			Date date = new Date();
			String transactionStr = "";

			if (depositAmount < 0) { //Checks if amount inputted is negative 
				System.out.println("No negative amounts. Please try again. \n");
				return;
			}

			switch(accountType) { //Switch statement to deposit money in different account types 
				case 0: //Deposits money into checking accounts
					myAccount.getChecking().setCurrentBalance(myAccount.getChecking().getCurrentBalance() + depositAmount);
					System.out.println("Deposit success! New " + myAccount.getFirstName() + " " + myAccount.getLastName() + " " + "checking balance is: $" + myAccount.getChecking().getCurrentBalance() + "\n");
					transactionStr = date + ",,,deposits," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Checking," + depositAmount;

					UserUtilities.transactionLogger(transactionStr);
					myAccount.getChecking().getTransactionLog().add(transactionStr);
					break;

				case 1: //Deposits money into savings accounts
					myAccount.getSavings().setCurrentBalance(myAccount.getSavings().getCurrentBalance() + depositAmount);
					System.out.println("Deposit success! New " + myAccount.getFirstName() + " " + myAccount.getLastName() + " " + "savings balance is: $" + myAccount.getSavings().getCurrentBalance() + "\n");
					transactionStr = date + ",,,deposits," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Savings," + depositAmount;

					UserUtilities.transactionLogger(transactionStr);
					myAccount.getSavings().getTransactionLog().add(transactionStr);
					break;

				case 2: //Deposits money into credit accounts
					myAccount.getCredit().setCurrentBalance(myAccount.getCredit().getCurrentBalance() + depositAmount);
					System.out.println("Deposit success! New " + myAccount.getFirstName() + " " + myAccount.getLastName() + " " + "credit balance is: $" + myAccount.getCredit().getCurrentBalance() + "\n");
					transactionStr = date + ",,,deposits," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Credit," + depositAmount;

					UserUtilities.transactionLogger(transactionStr);
					myAccount.getCredit().getTransactionLog().add(transactionStr);
					break;
			}
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}

	/**This method just prints account balance
	* @param myAccount The bank user account.
	* @param accountType The account type.
	* @author Axel Diaz
	*/
	public static void inquiryAccount(Customer myAccount, int accountType) throws IOException {
		try {
			Date date = new Date();
			String transactionStr;

			switch(accountType) {
				case 0:
					if(myAccount.getChecking().getAccountNum() == 0) {
						System.out.println("Inquiry failed. Account does not exist.");
						return;
					}

					System.out.println("Inquiry success! " + myAccount.getFirstName() + " " + myAccount.getLastName() + " Checking account balance: $" + myAccount.getChecking().getCurrentBalance() + "\n");
					transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Checking,inquires,,,,";
					UserUtilities.transactionLogger(transactionStr);
					myAccount.getChecking().getTransactionLog().add(transactionStr);
					break;

				case 1:
					if(myAccount.getSavings().getAccountNum() == 0) {
						System.out.println("Inquiry failed. Account does not exist.");
						return;
					}

					System.out.println("Inquiry success! " + myAccount.getFirstName() + " " + myAccount.getLastName() + " Savings account balance: $" + myAccount.getSavings().getCurrentBalance() + "\n");
					transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Savings,inquires,,,,";
					UserUtilities.transactionLogger(transactionStr);
					myAccount.getSavings().getTransactionLog().add(transactionStr);
					break;

				case 2:
					if(myAccount.getCredit().getAccountNum() == 0) {
						System.out.println("Inquiry failed. Account does not exist.");
						return;
					}

					System.out.println("Inquiry success! " + myAccount.getFirstName() + " " + myAccount.getLastName() + " Credit account balance: $" + myAccount.getCredit().getCurrentBalance() + "\n");
					transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Credit,inquires,,,,";
					UserUtilities.transactionLogger(transactionStr);
					myAccount.getChecking().getTransactionLog().add(transactionStr);
					break;
			}
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}

	/**This function withdraws the bank user's balance and records it
	 * @param amountWithdraw The amount of money to take out of the bank user's account
	 * @param accountType The account type (checking, savings, credit) to withdraw from
	 * @param myAccount The bank user account object.
	 * @author Axel Diaz
	 */
	public static void withdrawAccount(double amountWithdraw, int accountType, Customer myAccount) throws IOException {
		try {
			Date date = new Date();
			String transactionStr = "";

			switch(accountType) { //Switch statement for withdraw for different types of accounts
				case 0: //Withdraws from checking accounts
					if(myAccount.getChecking().getCurrentBalance() < amountWithdraw || amountWithdraw < 0) {
						System.out.println("Cannot complete transaction. Insufficient balance. \n");
						return;
					}

					myAccount.getChecking().setCurrentBalance(myAccount.getChecking().getCurrentBalance() - amountWithdraw); //Withdraws balance
					System.out.println("Withdraw success! New " + myAccount.getFirstName() + " " + myAccount.getLastName() + " " + "checking balance is: $" + myAccount.getChecking().getCurrentBalance() + "\n");
					transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Checking,withdraws,,,," + amountWithdraw;

					UserUtilities.transactionLogger(transactionStr);
					myAccount.getChecking().getTransactionLog().add(transactionStr);
					break;

				case 1: //Withdraws from savings accounts
					if(myAccount.getSavings().getCurrentBalance() < amountWithdraw || amountWithdraw < 0) {
						System.out.println("Cannot complete transaction. Insufficient balance. \n");
						return;
					}

					myAccount.getSavings().setCurrentBalance(myAccount.getSavings().getCurrentBalance() - amountWithdraw);//Withdraws balance
					System.out.println("Withdraw success! New " + myAccount.getFirstName() + " " + myAccount.getLastName() + " " + "savings balance is: $" + myAccount.getSavings().getCurrentBalance() + "\n");
					transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Savings,withdraws,,,," + amountWithdraw;

					UserUtilities.transactionLogger(transactionStr);
					myAccount.getSavings().getTransactionLog().add(transactionStr);
					break;

				case 2: //Withdraws from credit accounts
					if(amountWithdraw < 0 || (myAccount.getCredit().getCreditMax() < -(myAccount.getCredit().getCurrentBalance() - amountWithdraw))) {
						System.out.println("Cannot complete transaction. Insufficient credit. \n");
						return;
					}

					myAccount.getCredit().setCurrentBalance(myAccount.getCredit().getCurrentBalance() - amountWithdraw);//Withdraws balance
					System.out.println("Withdraw success! New " + myAccount.getFirstName() + " " + myAccount.getLastName() + " " + "credit balance is: $" + myAccount.getCredit().getCurrentBalance() + "\n");
					transactionStr = date + "," + myAccount.getFirstName() + "," + myAccount.getLastName() + ",Credit,withdraws,,,," + amountWithdraw;

					UserUtilities.transactionLogger(transactionStr);
					myAccount.getCredit().getTransactionLog().add(transactionStr);
					break;
			}
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}

}