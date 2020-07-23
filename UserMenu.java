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
import java.text.SimpleDateFormat;

/**This class contains methods for banking menu for the user to use. 
 * @author David Morales 
 */
public class UserMenu {

/*------------------------------------User Menus-----------------------------------------------*/

	/**This method is a menu shows bank manager options. Options are for account search and detailed info for each bank user. 
	 * @author David Morales
	 */
	public static void bankManagerOptions() throws IOException {
		try {
			System.out.println("Welcome bank manager!");
			System.out.println("[0] Inquire user by name (with option to print bank statement)");
			System.out.println("[1] Inquire user by account type/number (with option to print bank statement)");
			System.out.println("[2] Print all users detailed information");
			System.out.println("[3] Return to user menu");
			System.out.println("[4] Exit program");

			Customer bankUser; 
			double accountInput;
			int userInputInt = UserUtilities.userInputInteger(4); //Input validation

			if(userInputInt == -1 || userInputInt == -2) {
				System.out.println("Invalid input. Try again.");
				bankManagerOptions();
				return;
			}

			switch(userInputInt) { //Bank manager options
				case 0: //Search account by name (slow option)
					System.out.println("Who's account would you like to inquire about?");
					bankUser = UserUtilities.accountNameSearch();

					if(bankUser == null) {
						System.out.println("Sorry. User not found.");
						bankManagerOptions();
						return;
					}

					System.out.println(bankUser.toString());
					bankStatementMenu(bankUser);
					bankManagerOptions();
					return;

				case 1: //Search account by account number and type (fast option)
					System.out.println("What account type?");
					System.out.println("[0] Checking");
					System.out.println("[1] Savings");
					System.out.println("[2] Credit");

					userInputInt = UserUtilities.userInputInteger(2);

					if(userInputInt == -1 || userInputInt == -2) {
						System.out.println("Invalid input. Try again.");
						userInputInt = 1;
						break;
					}

					switch(userInputInt) { //Separate options for checking, savings, and credit
						case 0: //Searching for checking account numbers
							System.out.println("What is the checking account #?");
							bankUser = UserUtilities.accountNumSearch(userInputInt);

							if(bankUser == null) {
								System.out.println("Sorry. User not found.");
								bankManagerOptions();
								return;
							}

							System.out.println(bankUser.toString()); //Prints all user info.
							bankStatementMenu(bankUser);
							bankManagerOptions();
							return;

						case 1: //Searching for savings account numbers
							System.out.println("What is the savings account #?");
							bankUser = UserUtilities.accountNumSearch(userInputInt);

							if(bankUser == null) {
								System.out.println("Sorry. User not found.");
								return;
							}

							System.out.println(bankUser.toString()); //Prints all user info
							bankStatementMenu(bankUser);
							bankManagerOptions();
							return;

						case 2: //Searching for credit account numbers 
							System.out.println("What is the credit account #?");
							bankUser = UserUtilities.accountNumSearch(userInputInt);

							if(bankUser == null) {
								System.out.println("Sorry. User not found. /n");
								bankManagerOptions();
								return;
							}

							System.out.println(bankUser.toString());
							bankStatementMenu(bankUser);
							bankManagerOptions();
							return;
					}

					break;

				case 2: //Prints all bank users detail information
					for(int i = 0; i < RunBank.bankUserList.size(); i++) {
						bankUser = RunBank.bankUserList.get(i);
						System.out.println(bankUser.toString());
					}
					bankManagerOptions();
					return;

				case 3: //Returns to user menu
					userMenu();
					return;

				case 4: //Exits program
					System.out.println("Goodbye, bank manager.");
					System.exit(0);
			}
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}
	
	/**This method opens another menu for the user(bank manager) to write a bank statement on 
	* that specific user.
	*@param bankUser Passes specific bank user (Customer object) to use make the bank statement.
	* @author David Morales
	*/
	public static void bankStatementMenu(Customer bankUser) throws IOException {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
			Date date = new Date();
			String dateStr = formatter.format(date);

			System.out.println("Do you want to create a bank statement for this user? Press 0 for yes, press any key for no." );
			int userInputInt = UserUtilities.userInputInteger(0);
			
			if(userInputInt == -1 || userInputInt == -2) {
				return;
			}

			BankStatement userStatement = new BankStatement(bankUser, dateStr);
			userStatement.createBankStatement();
			return;
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}

	/**This method prints all users for the user to select their account. 
	 * @author David Morales
	 */
	public static void userMenu() throws IOException {
		try {
			System.out.println("Welcome to David Axel Bank! Press 'x' to exit. Select the options below:");
			System.out.println("[0] Search account by name ");
			System.out.println("[1] Search account by ID number");
			System.out.println("[2] Create new account");
			System.out.println("[3] Bank manager");
			System.out.println("[4] Read transactions from file (.csv format only!)");

			Customer bankUser;
			int userInputInt = UserUtilities.userInputInteger(4);

			if(userInputInt == -1) {
				System.out.println("Sorry. Number is not in the list. Please try again.");
				userMenu();
				return;
			}

			if(userInputInt == -2) {
				System.out.println("Goodbye");
				UserUtilities.updateDatabase(RunBank.bankUserList);
				System.exit(0);
			}

			switch(userInputInt) {
				case 0: //Account search by name
					bankUser = UserUtilities.accountNameSearch();

					if(bankUser == null) {
						System.out.println("Invalid input. Try again.");
						userMenu();
						return;
					}

					if(UserUtilities.passwordChecker(bankUser)) { //Password checker
						accountMenu(bankUser);
						return;
					}
					
					userMenu();
					return;

				case 1: //Account search by number
					System.out.println("What kind of account?"); 
					System.out.println("[0] Checking");
					System.out.println("[1] Savings");
					System.out.println("[2] Credit");

					userInputInt = UserUtilities.userInputInteger(2);

					if(userInputInt == -1 || userInputInt == -2) {
						System.out.println("Invalid input. Try again.");
						userMenu();
						return;
					}

					System.out.println("What is the account #?");
					bankUser = UserUtilities.accountNumSearch(userInputInt);

					if(bankUser == null) { //If bank user is not found 
						System.out.println("Bank user not found or invalid input. Try again.");
						userMenu();
						return;
					}

					if(UserUtilities.passwordChecker(bankUser)) { //Password checker
						accountMenu(bankUser);
						return;
					}

					userMenu();
					return;

				case 2:
					createAccountMenu(); //Account creation menu
					return;

				case 3:
					bankManagerOptions(); //Opens bank manager menu
					return;

				case 4:
					UserUtilities.transactionReader(); //Reads transactions from csv 
					userMenu();
					return;
			}

			accountMenu(RunBank.bankUserList.get(userInputInt)); //Opens another menu with the selected bank user object with the array list
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}
	/**This method prints is a user menu to create new bank users. 
	 * @author David Morales
	 */
	public static void createAccountMenu() throws IOException{
		try {
			Scanner userIn = new Scanner(System.in); 
			String firstName; //Declaring all the temp. values for account creation.
			String lastName;
			String dateOfBirth;
			String address;
			String phoneNumber;
			String email;
			String password;
			ArrayList<String> transactionLogSavings = new ArrayList<String>();
			int idNum;
			int savingsAccountNum;
			int checkingAccountNum;
			int creditAccountNum;
			int userInputInt; 
			int index = RunBank.bankUserList.size();

			//Getting hashmaps that can account numbers. 
			HashMap<Integer, Integer> checkingHash = RunBank.hashMapAccountNums.get(0); 
			HashMap<Integer, Integer> savingsHash = RunBank.hashMapAccountNums.get(1);
			HashMap<Integer, Integer> creditHash = RunBank.hashMapAccountNums.get(2); 
			HashMap<Integer, Integer> idHash = RunBank.hashMapAccountNums.get(3);

			System.out.println("Welcome to the user account wizard. \n");
			System.out.println("Enter first name: "); 
			firstName = userIn.nextLine();
			System.out.println("Enter last name: ");
			lastName = userIn.nextLine();
			System.out.println("Enter date of birth: ");
			dateOfBirth = userIn.nextLine();
			System.out.println("Enter address: ");
			address = userIn.nextLine();
			System.out.println("Enter phone number: ");
			phoneNumber = userIn.nextLine();
			System.out.println("Enter email: ");
			email = userIn.nextLine();
			System.out.println("Enter password: ");
			password = userIn.nextLine();

			RunBank.maxIDNum++; //Incrementing ID number
			idHash.put(RunBank.maxIDNum, index);
			RunBank.maxSavingsNum++; //Incrementing savings number
			Savings newSavings = new Savings(RunBank.maxSavingsNum, 0.0, 0.0, transactionLogSavings);
			savingsHash.put(RunBank.maxSavingsNum, index); //Updating hashmap

			Checking newChecking = new Checking();
			Credit newCredit = new Credit();

			System.out.println("Do you want to create a checking account? Press 0 for yes, press any key for no.");
			userInputInt = UserUtilities.userInputInteger(0);

			if(userInputInt == -1 || userInputInt == -2) { //If user doesn't want a checking account
				System.out.println("Checking account skipped.");
			}
			else { //Creates checking account, and updates hashmaps.
				ArrayList<String> transactionLogCheckings = new ArrayList<String>();
				RunBank.maxCheckingNum++;
				checkingHash.put(RunBank.maxCheckingNum, index);
				newChecking.setAccountNum(RunBank.maxCheckingNum);
				newChecking.setCurrentBalance(0.0);
				newChecking.setStartingBalance(0.0);
				newChecking.setTransactionLog(transactionLogCheckings);
			}

			System.out.println("Do you want to create a credit account? Press 0 for yes, press any key for no.");
			userInputInt = UserUtilities.userInputInteger(0);

			if(userInputInt == -1 || userInputInt == -2) {
				System.out.println("Credit account skipped.");
			}

			else { //Creates credit account and update hashmaps
				ArrayList<String> transactionLogCredit = new ArrayList<String>();
				RunBank.maxCreditNum++;
				creditHash.put(RunBank.maxCreditNum, index);
				newCredit.setAccountNum(RunBank.maxCreditNum);
				newCredit.setCurrentBalance(0.0);
				newCredit.setStartingBalance(0.0);
				newCredit.setTransactionLog(transactionLogCredit);
				newCredit.setCreditMax(2500.0);
			}
			//Creates the new account 
			Customer newCustomer = new Customer(firstName, lastName, dateOfBirth, address, phoneNumber, email, RunBank.maxIDNum, newChecking, newSavings, newCredit, password);
			RunBank.hashMapAccountNums.set(0, checkingHash); 
			RunBank.hashMapAccountNums.set(1, savingsHash);
			RunBank.hashMapAccountNums.set(2, creditHash);
			RunBank.hashMapAccountNums.set(3, idHash);
			RunBank.bankUserList.add(newCustomer);

			System.out.println("New account success.");
			userMenu();
			return;
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}

	/**This method loads the individual bank user's options. Here is where you can use the bank actions and the user inputs the following
	 * options. It allows the user to select individual account types, and allows the inquire their balances. 
	 *@param bankUser The user selected bank user where transactions are coming from
	 *@author David Morales
	 */
	public static void accountMenu(Customer bankUser)  throws IOException {
		try {
			//Printing directions for use on console.
			System.out.println("Hello " + bankUser.getFirstName() + ", which account do you want to use?" );
			System.out.println("[0] Checking account at #" + bankUser.getChecking().getAccountNum());
			System.out.println("[1] Savings account at #" + bankUser.getSavings().getAccountNum());
			System.out.println("[2] Credit account at #" + bankUser.getCredit().getAccountNum());
			System.out.println("[3] View balances of accounts");
			System.out.println("[4] View all account information");
			System.out.println("[5] Write bank statement");
			System.out.println("[6] View bank statement");
			System.out.println("[7] Return to main menu (log out)");
			System.out.println("[8] Exit program");

			int userInputInt = UserUtilities.userInputInteger(8);
			
			if(userInputInt == -1 || userInputInt == -2) {
				accountMenu(bankUser); //Reloads menu if input is invalid 
				return;
			}

			switch(userInputInt) { //Switch statement for user options
				case 0: //Selecting checking account 
					if(bankUser.getChecking().getAccountNum() == 0) {
						System.out.println("Account does not exist.");
						accountMenu(bankUser);
						return;
					}
					System.out.println("You have selected your checking account. Current balance is: $" + bankUser.getChecking().getCurrentBalance());
					transactionMenu(bankUser, userInputInt);
					break;

				case 1: //Selecting savings account
					if(bankUser.getSavings().getAccountNum() == 0) {
						System.out.println("Account does not exist.");
						accountMenu(bankUser);
						return;
					}
					System.out.println("You have selected your savings account. Current balance is: $" + bankUser.getSavings().getCurrentBalance());
					transactionMenu(bankUser, userInputInt);
					break;

				case 2: //Selecting credit account
					if(bankUser.getCredit().getAccountNum() == 0) {
						System.out.println("Account does not exist.");
						accountMenu(bankUser);
						return;
					}
					System.out.println("You have selected your credit account. Current balance is: $" + bankUser.getCredit().getCurrentBalance());
					transactionMenu(bankUser, userInputInt);
					break;

				case 3: //Prints all user's account balances
						System.out.println("For which account:");
						System.out.println("[0] Checking");
						System.out.println("[1] Savings");
						System.out.println("[2] Credit");

						userInputInt = UserUtilities.userInputInteger(2);

						if(userInputInt == -1 || userInputInt == -2) {
							accountMenu(bankUser); //Reloads menu if input is invalid 
							return;
						}

						switch(userInputInt) { //Switch statement for inquiry menu.
							case 0: //Inquiry for checking
								UserTransactions.inquiryAccount(bankUser, 0);
								accountMenu(bankUser);
								return;

							case 1: //Inquiry for savings
								UserTransactions.inquiryAccount(bankUser, 1);
								accountMenu(bankUser);
								return;

							case 2: //Inquiry for credit 
								UserTransactions.inquiryAccount(bankUser, 2);
								accountMenu(bankUser);
								return;
						}
						break;

				case 4:
					bankUser.print(); //Prints all account information
					accountMenu(bankUser);
					return;

				case 5: //Create bank statement
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
					Date date = new Date();
					String dateStr = formatter.format(date);
	
					BankStatement userStatement = new BankStatement(bankUser, dateStr);
					userStatement.createBankStatement();
					System.out.println("Bank statement for " + bankUser.getFirstName() + ", has been written."); 
					accountMenu(bankUser);
					return;

				case 6: //Prints bank statement
					bankUser.printBankStatement();
					accountMenu(bankUser);
					return;

				case 7: //Returns to main menu
					System.out.println("Goodbye, " + bankUser.getFirstName());
					userMenu();
					break;

				case 8: //Exits program
					System.out.println("Goodbye, " + bankUser.getFirstName());
					UserUtilities.updateDatabase(RunBank.bankUserList);
					System.exit(0);
					break;
			}
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}

	/**User menu for transaction options for the user. It allows the user to withdraw, deposit, and send money. 
	 * @param bankUser The user selected bank user where transactions are coming from
	 * @param accountType The account type (checking, savings, credit) the user is making transactions from.
	 * @author David Morales
	*/
	public static void transactionMenu(Customer bankUser, int accountType) throws IOException {
		try {
			Checking checkingAccount = bankUser.getChecking();
			Savings savingsAccount = bankUser.getSavings();
			Credit creditAccount = bankUser.getCredit();

			System.out.println("");
			System.out.println("Select the options below: ");
			System.out.println("[0] Deposit money");
			System.out.println("[1] Withdraw money");
			System.out.println("[2] Send money");
			System.out.println("[3] Go back to account menu");
			System.out.println("[4] Go back to main menu (logout)");
			System.out.println("[5] Exit");
			
			double moneyVal = -1;
			int userInputInt = UserUtilities.userInputInteger(5); //Input validation
			if(userInputInt == -1 || userInputInt == -2) { 
				transactionMenu(bankUser, accountType);
				return;
			}

			switch(userInputInt) { //Switch case statements are each bank action. 
				case 0: //Deposit option
					System.out.println("How much do want to deposit? ");
					moneyVal = UserUtilities.userInputDouble();

					if(moneyVal == -1.0) {
						transactionMenu(bankUser, accountType);
						return;
					}

					switch(accountType) { //Another switch statement for each account type.
						case 0: //To deposit to checking accounts (objects)
							UserTransactions.depositAccount(moneyVal, 0, bankUser); //Adds money to the bank user.
							break;
						case 1: //To deposit to savings accounts (objects)
							UserTransactions.depositAccount(moneyVal, 1, bankUser);
							break;
						case 2: //To deposit to credit accounts (objects)
							UserTransactions.depositAccount(moneyVal, 2, bankUser);
							break;
					}

					transactionMenu(bankUser, accountType); //Reloads menu again 
					return;

				case 1: //Withdraw option
					System.out.println("How much do want to withdraw?"); //For withdraw action
					moneyVal = UserUtilities.userInputDouble(); //User input validation

					if(moneyVal == -1.0) {
						transactionMenu(bankUser, accountType);
						return;
					}

					switch(accountType) { //For different account types
						case 0: //To withdraw from checking accounts (objects)
							UserTransactions.withdrawAccount(moneyVal, 0, bankUser); //Subtracts money from bank user.
							break;
						case 1: //To withdraw from savings accounts (objects)
							UserTransactions.withdrawAccount(moneyVal, 1, bankUser);
							break;
						case 2: //To withdraw from credit accounts (objects)
							UserTransactions.withdrawAccount(moneyVal, 2, bankUser);
							break;
					}

					transactionMenu(bankUser, accountType);
					return;

				case 2: //Loads different menu to transfer/send money
					transferMoneyMenu(bankUser, accountType);
					return;
				
				case 3:
					accountMenu(bankUser); //Returns to user account menu
					return;

				case 4: //Just returns to the main menu (logout)
					System.out.println("");
					userMenu(); 
					return;

				case 5: //Exits program and updates csv file. 
					System.out.println("Goodbye, " + bankUser.getFirstName());
					UserUtilities.updateDatabase(RunBank.bankUserList);
					System.exit(0);
			}
		}

			catch(IOException ioEx) { 
				throw new IOException("IOException");
			}
		}

	/**This method is a user menu to transfer money to other accounts (checking, savings, credit, and other bank users). You cannot
	 * send money to from and to the same account. Use cases are used for different banking scenarios. 
	 * @param bankUser The user selected bank user where transactions are coming from
	 * @param accountType The account type (checking, savings, credit) the user is making transactions from.
	 * @author David Morales
	*/
	public static void transferMoneyMenu(Customer bankUser, int accountType) throws IOException {
		try {
			Checking tempChecking;
			Savings tempSavings;
			Credit tempCredit;
			System.out.println("Where do want to send money to?");
			System.out.println("[0] Your checking account");
			System.out.println("[1] Your savings account");
			System.out.println("[2] Your credit account"); 
			System.out.println("[3] Send money to another bank user");
			System.out.println("[4] Return account menu");
			System.out.println("[5] Return to main menu (logout)");
			System.out.println("[6] Exit program");

			int userInputInt = UserUtilities.userInputInteger(6); //Input validation method
			
			if(userInputInt == -1 || userInputInt == -2) {
				transferMoneyMenu(bankUser, accountType);
				return;
			}

			double moneyVal = -1;

			if(userInputInt < 4) { //Makes sure user doesn't input anything if they select option 4 or greater since they return to other menus
				System.out.println("How much do you want to transfer?");
				moneyVal = UserUtilities.userInputDouble();

				if(moneyVal == -1.0) {
					transferMoneyMenu(bankUser, accountType);
					return;
				}
			}

			//Switch statement to have all user options
			switch(userInputInt) {
				case 0: //Transfer money to checking
					switch(accountType) {
						case 0: //From checking account to checking
							System.out.println("Same account! Try again.");
							transferMoneyMenu(bankUser, accountType);
							return;

						case 1: //From savings account to checking
							if(bankUser.getChecking().getAccountNum() == 0) {
								System.out.println("Account does not exist.");
								transferMoneyMenu(bankUser, accountType);
								return;
							}

							UserTransactions.withdrawAccount(moneyVal, 1, bankUser);

							if(moneyVal > bankUser.getSavings().getCurrentBalance()) { //Makes sure account balance is sufficient
								transferMoneyMenu(bankUser, accountType);
								return;
							}

							UserTransactions.depositAccount(moneyVal, 0, bankUser);
							break;

						case 2: //From credit account to checking
							if(bankUser.getChecking().getAccountNum() == 0 || bankUser.getCredit().getAccountNum() == 0) {
								System.out.println("Account does not exist.");
								transferMoneyMenu(bankUser, accountType);
								return;
							}

							UserTransactions.withdrawAccount(moneyVal, 2, bankUser);

							if(bankUser.getCredit().getCreditMax() < -(bankUser.getCredit().getCurrentBalance() - moneyVal)) {
								transferMoneyMenu(bankUser, accountType);
								return;
							}

							UserTransactions.depositAccount(moneyVal, 0, bankUser);
							break;
					}
					break;

				case 1: //Transfer money to savings
					switch(accountType) {
						case 0: //From checking account to savings

							if(bankUser.getChecking().getAccountNum() == 0) {
								System.out.println("Account does not exist.");
								transferMoneyMenu(bankUser, accountType);
								return;
							}

							UserTransactions.withdrawAccount(moneyVal, 0, bankUser);

							if(moneyVal > bankUser.getChecking().getCurrentBalance()) {
								transactionMenu(bankUser, accountType);
								return;
							}

							UserTransactions.depositAccount(moneyVal, 1, bankUser);
							break;

						case 1: //From savings account to savings
							System.out.println("Same account! Try again.");
							transferMoneyMenu(bankUser, accountType);
							return;

						case 2: //From credit account to savings

							if(bankUser.getCredit().getAccountNum() == 0) {
								System.out.println("Account does not exist.");
								transferMoneyMenu(bankUser, accountType);
								return;
							}

							UserTransactions.withdrawAccount(moneyVal, 2, bankUser);

							if(bankUser.getCredit().getCreditMax() < -(bankUser.getCredit().getCurrentBalance() - moneyVal)) {
								transferMoneyMenu(bankUser, accountType);
								return;
							}

							UserTransactions.depositAccount(moneyVal, 1, bankUser);
							break;
					}
					break;

				case 2: //Transfer money to credit
					switch(accountType) {
						case 0: //From checking account to credit
							if(bankUser.getChecking().getAccountNum() == 0 || bankUser.getCredit().getAccountNum() == 0) {
								System.out.println("Account does not exist.");
								transferMoneyMenu(bankUser, accountType);
								return;
							}

							UserTransactions.withdrawAccount(moneyVal, 0, bankUser);

							if(moneyVal > bankUser.getChecking().getCurrentBalance()) {
								transferMoneyMenu(bankUser, accountType);
								return;
							}

							UserTransactions.depositAccount(moneyVal, 2, bankUser);
							break;

						case 1: //From savings account to credit

							if(bankUser.getCredit().getAccountNum() == 0) {
								System.out.println("Account does not exist.");
								transferMoneyMenu(bankUser, accountType);
								return;
							}

							UserTransactions.withdrawAccount(moneyVal, 1, bankUser);

							if(moneyVal > bankUser.getSavings().getCurrentBalance()) {
								transferMoneyMenu(bankUser, accountType);
								return;
							}

							UserTransactions.depositAccount(moneyVal, 2, bankUser);
							break;

						case 2: //From credit account to credit
							System.out.println("Same account! Try again.");
							transferMoneyMenu(bankUser, accountType);
							return;
					}
					break;

				case 3: //Transfer into another account. Loads into another menu
					sendMoneyToAnotherAccount(bankUser, accountType, moneyVal);
					return;

				case 4: //Returns to user account menu
					accountMenu(bankUser);
					return;

				case 5: //Returns to main menu (logout)
					userMenu();
					return;

				case 6: //Updates .csv and exits the program
					System.out.println("Goodbye, " + bankUser.getFirstName());
					UserUtilities.updateDatabase(RunBank.bankUserList);
					System.exit(0);
			}

			transferMoneyMenu(bankUser, accountType); //Reloads menu
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}

	/** This method opens a separate menu to send money to different bank users. 
	 * @param bankUser The user selected bank user where transactions are coming from
	 * @param accountType The account type (checking, savings, credit) the user is making transactions from.
	 * @param moneyVal The user inputted (from transferMoneyMenu) money amount they want to send. 
	 */
	public static void sendMoneyToAnotherAccount(Customer bankUser, int accountType, double moneyVal) throws IOException {
		try {
			System.out.println("Send money to which account?");
			UserUtilities.printUsers(RunBank.bankUserList); //Prints all bank users to select to send money to
			int userInputInt = UserUtilities.userInputInteger(RunBank.bankUserList.size() - 1); //Input validation

			if(userInputInt == -1 || userInputInt == -2) {
				sendMoneyToAnotherAccount(bankUser, accountType, moneyVal);
				return;
			}

			Customer otherUser = RunBank.bankUserList.get(userInputInt);
			
			if(bankUser.getIdNum() == otherUser.getIdNum()) { //Prevent user to sending money to themself
				System.out.println("Same user. Try again.");
				sendMoneyToAnotherAccount(bankUser, accountType, moneyVal);
				return;
			}
			
			System.out.println("You have chosen " + otherUser.getFirstName());
			System.out.println("To send money to which type of account:"); //User selects account type
			System.out.println("[0] Checking");
			System.out.println("[1] Savings");
			System.out.println("[2] Credit");

			userInputInt = UserUtilities.userInputInteger(2); //Input validation

			if(userInputInt == -1 || userInputInt == -2) {
				sendMoneyToAnotherAccount(bankUser, accountType, moneyVal);
				return;
			}

			switch(accountType) { //Sends money to other user account type
				case 0: //Sending to other user's checking
					if(otherUser.getChecking().getAccountNum() == 0) { //Checks if account exists
						System.out.println("Account doesn't exist.");
						sendMoneyToAnotherAccount(bankUser, accountType, moneyVal);
						return;
					}

					UserTransactions.payAccount(bankUser, otherUser, moneyVal, userInputInt, accountType);  //Transfers money from one account to another
					break;

				case 1: //Sending to other user's savings
					if(otherUser.getSavings().getAccountNum() == 0) {
						System.out.println("Account doesn't exist.");
						sendMoneyToAnotherAccount(bankUser, accountType, moneyVal);
						return;
					}

					UserTransactions.payAccount(bankUser, otherUser, moneyVal, userInputInt, accountType);
					break;

				case 2: //Sending to other user's credit 
					if(otherUser.getCredit().getAccountNum() == 0) {
						System.out.println("Account doesn't exist.");
						sendMoneyToAnotherAccount(bankUser, accountType, moneyVal);
						return;
					}

					UserTransactions.payAccount(bankUser, otherUser, moneyVal, userInputInt, accountType);
					break;
			}

			transactionMenu(bankUser, accountType); //Returns back to menu 
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}
}