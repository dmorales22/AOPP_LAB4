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

/**This class contains methods for banking functions
 * @author David Morales
 */
public class UserUtilities {

	/*------------------------------Helper/Utility Methods----------------------------------*/

	/** This method searches for bank user accounts using account number in the hashmaps (three hashmaps for each type of account),
	 * and returns the user found. Returns null if no user is found.
	 * @param accountType The account type the user is searching for. Serves as an index to get the specific hash map from hashMapAccountNums.
	 * @return The found user.
	 * @author David Morales
	 */
	public static Customer accountNumSearch(int accountType) {
		try {
			Customer bankUser;
			int userInputInt = userInputInteger(999999); //User input validation with a huge range.
			int index = -1;

			if(userInputInt == -1 || userInputInt == -2) {
				return null;
			}

			HashMap<Integer, Integer> tempAccountHmap = RunBank.hashMapAccountNums.get(accountType); //Gets specific hash map for account type
			index = tempAccountHmap.get(userInputInt); //Gets index from account number
			bankUser = RunBank.bankUserList.get(index); //Gets bank user from found index

			return bankUser;
		}

		catch(NullPointerException npe) {
			return null;
		}
	}

	/**This method is for name search for transactionReader. 
	* @param firstName User's first name
	* @param lastName user's last name
	* @return returns found user
	* @author David Morales
	*/
	public static Customer accountNameSearchForReader(String firstName, String lastName) {
		Customer bankUser;
		String bankUserName = firstName + " " + lastName;
		String iter;

		for(int i = 0; i < RunBank.bankUserList.size(); i++) { //Searches (linear search) whole list to find user
			bankUser = RunBank.bankUserList.get(i);
			iter = bankUser.getFirstName() + " " + bankUser.getLastName();

			if(iter.equals(bankUserName)) { //If user has both same first and last name
				return bankUser;
			}
		}

		return null;
	}

	/** This method searches for accounts by name. It uses linear search O(n) to find the bank user. This is the slow search.
	 * @return Found user
	 * @author David Morales
	 */
	public static Customer accountNameSearch() {
		ArrayList<Customer> sameNameUserList = new ArrayList<Customer>(); 
		Scanner userIn = new Scanner(System.in);
		Customer bankUser;
		System.out.println("Please input first name: (case sensitive)"); //Gets first and last name from user
		String userInput = userIn.nextLine();
		System.out.println("Please input last name: (case sensitive)");
		userInput = userInput + " " + userIn.nextLine();
		String bankUserName;
		System.out.println("");

		//This loop runs through the list and adds users with the same name
		for(int i = 0; i < RunBank.bankUserList.size(); i++) { //Searches whole list to find user
			bankUser = RunBank.bankUserList.get(i);
			bankUserName = bankUser.getFirstName() + " " + bankUser.getLastName();

			if(userInput.equals(bankUserName)) { //If user has both same first and last name
				sameNameUserList.add(bankUser);
			}
		}

		if(sameNameUserList.size() == 0) { //If not user is found
			System.out.println("No user found. Try again. \n");
			return null;
		}

		System.out.println("Which account?");

		for(int i = 0; i < sameNameUserList.size(); i++) { //Prints user with same name
			System.out.println("[" + i + "] " + sameNameUserList.get(i).getFirstName() + " " + sameNameUserList.get(i).getLastName() + " ID: " + sameNameUserList.get(i).getIdNum());
		}

		int userInputInt = userInputInteger(sameNameUserList.size()); //Input 

		if(userInputInt == -1 || userInputInt == -2) { //If input is wrong
			accountNameSearch();
			return null;
		}
		
		bankUser = sameNameUserList.get(userInputInt); //Returns bank user
		return bankUser;
	}

	/** This is user input validation for selecting options on the menu.
	 * @param rangeLimit This ensures a specific range of numbers allowed for user input
	 * @return Returns converted string to int. 
	 * @author David Morales
	 */
	public static int userInputInteger(int rangeLimit) { //User input validation 
		Scanner userIn = new Scanner(System.in);
		String userInput = userIn.next();

		if(userInput.equals("x") || userInput.equals("X")) { //If 'x' is entered, then it updates the .csv, and closes the program
			return -2; 
		}

		if(!isInputValid(userInput)) { //Input validation to check if string is only numbers
			return -1;
		}

		int userInputInt = Integer.parseInt(userInput);

		if(userInputInt > rangeLimit || userInputInt < 0) { //Range check
			System.out.println("Invalid input. Please try again. \n");
			return -1;
		}

		return userInputInt;
	}

	/** This method does input validation for double values (when user inputs money)
	 * @return Returns converted string into double
	 * @author David Morales
	 */
	public static double userInputDouble() {
		Scanner userIn = new Scanner(System.in);
		String userInput = userIn.next();

		if(!isInputValidDouble(userInput)) { //Input validation
			System.out.println("Not a valid value. Try again. \n");
			return -1.0;
		}
		double userInputDouble = Double.parseDouble(userInput);
		
		if(userInputDouble < 0) {
			System.out.println("No negative amounts please. Try again. \n");
			return -1.0;
		}
		return userInputDouble;
	}

	/**This method reads bank user(s) info from a .csv file, creates objects, and adds them into a array list. 
	 * @return An arrayList of all bank user objects
	 * @author David Morales
	 */
	public static ArrayList<Customer> fileReader() throws FileNotFoundException, IOException {
		try {
			ArrayList<Customer> bankUsers = new ArrayList<Customer>(); //Declaring a Checking array list
			ArrayList<HashMap<Integer,Integer>> tempHash = new ArrayList<HashMap<Integer,Integer>>();
			HashMap<String, Integer> tokenIndex = new HashMap<String, Integer>();
			int stringSize = 0;
			String[] tokenizedLine;
			FileReader fr = new FileReader(RunBank.filename);
			BufferedReader textReader = new BufferedReader(fr);

			//These hash maps binds account numbers to indexes in the bankUserList arrayList
			HashMap<Integer, Integer> checkingAccountHmap = new HashMap<Integer, Integer>(); 
			HashMap<Integer, Integer> savingsAccountHmap = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> creditAccountHmap = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> idNumHmap = new HashMap<Integer, Integer>();

			//Declaring temporary variables to be added into Customer objects
			int index = 0;
			double creditMax; 
			double creditAccountBalance; 
			String address; 
			int idNum; 
			int savingsAccountNum; 
			String lastName; 
			String dateOfBirth; 
			int checkingAccountNum; 
			String firstName; 
			int creditAccountNum; 
			String phoneNum;
			double checkingAccountBalance; 
			double savingsAccountBalance;
			String email;
			String password; 
			String strLine;

			strLine = textReader.readLine(); //Reads first line since it is a header. It's not added to the array list.
			tokenizedLine = strLine.split(",");
			tokenIndex = dynamicParser(tokenizedLine);
			stringSize = tokenizedLine.length;

			while ((strLine = textReader.readLine()) != null) { //This loop adds new users line by line
				tokenizedLine = fileParser(strLine, stringSize); //Tokenizes each line in the textfile.

				if(tokenizedLine[tokenIndex.get("First Name")].equals("") || tokenizedLine[tokenIndex.get("Last Name")].equals("") || tokenizedLine[tokenIndex.get("Phone Number")].equals("")) { //Prevents empty space rows for being added.
					continue;
				}

				creditMax = Double.parseDouble(tokenizedLine[tokenIndex.get("Credit Max")]); //Each attribute is extracted from the string line
				creditAccountBalance = Double.parseDouble(tokenizedLine[tokenIndex.get("Credit Starting Balance")]);
				address = tokenizedLine[tokenIndex.get("Address")];
				idNum = Integer.parseInt(tokenizedLine[tokenIndex.get("Identification Number")]);
				savingsAccountNum = Integer.parseInt(tokenizedLine[tokenIndex.get("Savings Account Number")]);
				lastName = tokenizedLine[tokenIndex.get("Last Name")];
				dateOfBirth = tokenizedLine[tokenIndex.get("Date of Birth")];
				checkingAccountNum = Integer.parseInt(tokenizedLine[tokenIndex.get("Checking Account Number")]);
				firstName = tokenizedLine[tokenIndex.get("First Name")];
				creditAccountNum = Integer.parseInt(tokenizedLine[tokenIndex.get("Credit Account Number")]);
				phoneNum = tokenizedLine[tokenIndex.get("Phone Number")];
				checkingAccountBalance = Double.parseDouble(tokenizedLine[tokenIndex.get("Checking Starting Balance")]);
				savingsAccountBalance = Double.parseDouble(tokenizedLine[tokenIndex.get("Savings Starting Balance")]);
				email = tokenizedLine[tokenIndex.get("Email")];
				password = tokenizedLine[tokenIndex.get("Password")];

				//ArrayList for record transactions for user
				ArrayList<String> transactionLogChecking = new ArrayList<String>();
				ArrayList<String> transactionLogSavings = new ArrayList<String>();
				ArrayList<String> transactionLogCredit = new ArrayList<String>();

				//Creating Checking, Savings, and Credit objects from the textfile attributes
				Checking bankChecking = new Checking(checkingAccountNum, checkingAccountBalance, checkingAccountBalance, transactionLogChecking);
				Savings bankSavings = new Savings(savingsAccountNum, savingsAccountBalance, savingsAccountBalance, transactionLogSavings);
				Credit bankCredit = new Credit(creditAccountNum, creditAccountBalance, creditAccountBalance, transactionLogCredit, creditMax);

				//Creating Customer objects. 
				Customer bankUser = new Customer(firstName, lastName, dateOfBirth, address, phoneNum, email, idNum, bankChecking, bankSavings, bankCredit, password); 

				bankUsers.add(bankUser); //Adds them to the array list

				//Adding account numbers to hashmaps
				checkingAccountHmap.put(checkingAccountNum, index);
				savingsAccountHmap.put(savingsAccountNum, index);
				creditAccountHmap.put(creditAccountNum, index);
				idNumHmap.put(idNum, index);

				//Finds max value of account numbers
				RunBank.maxIDNum = findMaxValue(RunBank.maxIDNum, idNum); 
				RunBank.maxCheckingNum = findMaxValue(RunBank.maxCheckingNum, checkingAccountNum);
				RunBank.maxSavingsNum = findMaxValue(RunBank.maxSavingsNum, savingsAccountNum);
				RunBank.maxCreditNum =  findMaxValue(RunBank.maxCreditNum, creditAccountNum);
				index++;
			}
			textReader.close();

			//Adding account number hashmaps to an ArrayList
			tempHash.add(checkingAccountHmap);
			tempHash.add(savingsAccountHmap);
			tempHash.add(creditAccountHmap);
			tempHash.add(idNumHmap);

			RunBank.hashMapAccountNums = tempHash; //Adds to the static variable

			return bankUsers;
		}

		catch(FileNotFoundException fnFE) {
			throw new FileNotFoundException("File not found. Please input the filename again.");
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}

	/**A simple method to replace a lower value with a higher value.
	 * @param oldNum Older number to be compared to
	 * @param newNum Newer number to be compared to
	 * @return returns higher value
	 * @author David Morales
	*/
	public static int findMaxValue(int oldNum, int newNum) { 
		if(newNum > oldNum) {
			return newNum;
		}
		return oldNum;
	}

	/**This method reads the transaction file and with the use of switch cases statements, 
	* it directs the information to execute commands in the banking system.
	* @author David Morales
	*/
	public static void transactionReader() throws FileNotFoundException, IOException {
		try {
			Scanner userIn = new Scanner(System.in);
			System.out.println("Input csv to read transactions from: ");
			String transactionFilename = userIn.next(); //Gets input of transaction file
			FileReader fr = new FileReader(transactionFilename);
			BufferedReader textReader = new BufferedReader(fr);
			//Declares all temp variables to perform transactions
			HashMap<String, Integer> tokenIndex = new HashMap<String, Integer>();
			String strLine;
			String[] tokenizedLine;
			strLine = textReader.readLine();
			tokenizedLine = strLine.split(",");
			int stringSize = tokenizedLine.length;
			tokenIndex = dynamicParser(tokenizedLine);
			String action; 
			String accountType;
			String otherAccountType;
			Customer toBankUser;
			Customer fromBankUser;
			int index = 1; 

			while ((strLine = textReader.readLine()) != null) { //This goes through the transaction text file and runs the commands
				System.out.println("Transaction #" + index);
				tokenizedLine = fileParser(strLine, stringSize); //Tokenizes each line in the textfile
				//tokenizedLine = strLine.split(",");
				action = tokenizedLine[tokenIndex.get("Action")];

				switch(action) { //This has the logic to run commands from the textfile
					case "inquires": //All case statements for inquires scenarios
						toBankUser = accountNameSearchForReader(tokenizedLine[tokenIndex.get("From First Name")], tokenizedLine[tokenIndex.get("From Last Name")]);

						if (toBankUser == null) {
							System.out.println("Transaction failed. User does not exist. \n");
							break;
						}

						accountType = tokenizedLine[tokenIndex.get("From Where")];
						switch(accountType) {
							case "Checking":
								UserTransactions.inquiryAccount(toBankUser, 0);
								break;
							case "Savings":
								UserTransactions.inquiryAccount(toBankUser, 1);
								break;
							case "Credit":
								UserTransactions.inquiryAccount(toBankUser, 2);
								break;
						}
						break;

					case "deposits": //All cases statements for deposits scenarios
						toBankUser = accountNameSearchForReader(tokenizedLine[tokenIndex.get("To First Name")], tokenizedLine[tokenIndex.get("To Last Name")]);
						if(toBankUser == null) {
							System.out.println("Transaction failed. User does not exist. \n");
							break;
						}
						accountType = tokenizedLine[tokenIndex.get("To Where")];
						switch(accountType) {
							case "Checking":
								UserTransactions.depositAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 0, toBankUser);
								break;
							case "Savings":
								UserTransactions.depositAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 1, toBankUser);
								break;
							case "Credit":
								UserTransactions.depositAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 2, toBankUser);
								break;
						}
						break;

					case "withdraws": //All case statement for withdraws scenarios 
						fromBankUser = accountNameSearchForReader(tokenizedLine[tokenIndex.get("From First Name")], tokenizedLine[tokenIndex.get("From Last Name")]);

						if(fromBankUser == null) {
							System.out.println("Transaction failed. User does not exist. \n");
							break;
						}

						accountType = tokenizedLine[tokenIndex.get("From Where")];
						switch(accountType) {
							case "Checking":
								UserTransactions.withdrawAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 0, fromBankUser);
								break;
							case "Savings":
								UserTransactions.withdrawAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 1, fromBankUser);
								break;
							case "Credit":
								UserTransactions.withdrawAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 2, fromBankUser);
								break;
						}
						break;

					case "transfers": //All case statements for transfers scenarios
						fromBankUser = accountNameSearchForReader(tokenizedLine[tokenIndex.get("From First Name")], tokenizedLine[tokenIndex.get("From Last Name")]);
						if(fromBankUser == null) {
							System.out.println("Transaction failed. User does not exist. \n");
							break;
						}
						accountType = tokenizedLine[tokenIndex.get("From Where")];
						otherAccountType = tokenizedLine[tokenIndex.get("To Where")];
						switch(accountType) {
							case "Checking":
								UserTransactions.withdrawAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 0, fromBankUser);
								if(fromBankUser.getChecking().getCurrentBalance() < Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")])) {
									break;
								}
							
								switch(otherAccountType) {
									case "Checking":
										UserTransactions.depositAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 0, fromBankUser);
										break;
									case "Savings":
										UserTransactions.depositAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 1, fromBankUser);
										break;
									case "Credit":
										UserTransactions.depositAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 2, fromBankUser);
										break;
								}
								break;

							case "Savings":
								UserTransactions.withdrawAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 1, fromBankUser);
								if(fromBankUser.getSavings().getCurrentBalance() < Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")])) {
									break;
								}

								switch(otherAccountType) {
									case "Checking":
										UserTransactions.depositAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 0, fromBankUser);
										break;
									case "Savings":
										UserTransactions.depositAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 1, fromBankUser);
										break;
									case "Credit":
										UserTransactions.depositAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 2, fromBankUser);
										break;
								}
								break;

							case "Credit":
								UserTransactions.withdrawAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 2, fromBankUser);
								if(fromBankUser.getCredit().getCreditMax() < -(fromBankUser.getCredit().getCurrentBalance() - Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]))) {
									break;
								}

								switch(otherAccountType) {
									case "Checking":
										UserTransactions.depositAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 0, fromBankUser);
										break;
									case "Savings":
										UserTransactions.depositAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 1, fromBankUser);
										break;
									case "Credit":
										UserTransactions.depositAccount(Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 2, fromBankUser);
										break;
								}
								break;
						}
					break;

					case "pays": //All case statements for pays scenarios
						fromBankUser = accountNameSearchForReader(tokenizedLine[tokenIndex.get("From First Name")], tokenizedLine[tokenIndex.get("From Last Name")]);
						toBankUser = accountNameSearchForReader(tokenizedLine[tokenIndex.get("To First Name")], tokenizedLine[tokenIndex.get("To Last Name")]);

						if(toBankUser == null || fromBankUser == null) {
							System.out.println("Transaction failed. User does not exist. \n");
							break;
						}

						accountType = tokenizedLine[tokenIndex.get("From Where")];
						otherAccountType = tokenizedLine[tokenIndex.get("To Where")];
						switch(accountType) {
							case "Checking":
								switch(otherAccountType) {
									case "Checking":
										UserTransactions.payAccount(toBankUser, fromBankUser, Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 0, 0);
										break;
									case "Savings":
										UserTransactions.payAccount(toBankUser, fromBankUser, Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 1, 0);
										break;
									case "Credit":
										UserTransactions.payAccount(toBankUser, fromBankUser, Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 2, 0);
										break;
								}
								break;

							case "Savings":
								switch(otherAccountType) {
									case "Checking":
										UserTransactions.payAccount(toBankUser, fromBankUser, Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 0, 1);
										break;
									case "Savings":
										UserTransactions.payAccount(toBankUser, fromBankUser, Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 1, 1);
										break;
									case "Credit":
										UserTransactions.payAccount(toBankUser, fromBankUser, Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 2, 1);
										break;
								}
								break;

							case "Credit":
								switch(otherAccountType) {
									case "Checking":
										UserTransactions.payAccount(toBankUser, fromBankUser, Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 0, 2);
										break;
									case "Savings":
										UserTransactions.payAccount(toBankUser, fromBankUser, Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 1, 2);
										break;
									case "Credit":
										UserTransactions.payAccount(toBankUser, fromBankUser, Double.parseDouble(tokenizedLine[tokenIndex.get("Action Amount")]), 2, 2);
										break;
								}
								break;
						}
					break;
				}
				index++;
			}
			
			textReader.close();
			System.out.println("Transactions completed. \n");
		}

		catch(FileNotFoundException fnFE) {
			System.out.println("File not found. Please input the filename again. \n");
			UserMenu.userMenu();
		}

		catch(IOException ioEx) { 
			System.out.println("IOException \n");
			UserMenu.userMenu();
		}
	}

	/**This method gets the bank user list and gets specific indexes in the csv file
	 * @param headerString Takes the tokenized header string from the csv
	 * @return A hashmap has strings as keys, and specific indexes where they correlate. 
	 * @author David Morales
	*/
	public static HashMap<String, Integer> dynamicParser(String[] headerString) {
		String header;
		HashMap<String, Integer> hashMapTokenIndex = new HashMap<String, Integer>();

		for(int i = 0; i < headerString.length; i++) { //This runs checks if string matches use case to map index 
			header = headerString[i];
			switch(header) {
				case "First Name": //Uses the string as key, and the index as the value 
					hashMapTokenIndex.put("First Name", i);
					break;

				case "Last Name":
					hashMapTokenIndex.put("Last Name", i);
					break;

				case "Date of Birth":
					hashMapTokenIndex.put("Date of Birth", i);
					break;

				case "Address":
					hashMapTokenIndex.put("Address", i);
					break;

				case "Identification Number":
					hashMapTokenIndex.put("Identification Number", i);
					break;

				case "Phone Number":
					hashMapTokenIndex.put("Phone Number", i);
					break;

				case "Checking Account Number":
					hashMapTokenIndex.put("Checking Account Number", i);
					break;

				case "Savings Account Number":
					hashMapTokenIndex.put("Savings Account Number", i);
					break;

				case "Credit Account Number":
					hashMapTokenIndex.put("Credit Account Number", i);
					break;

				case "Checking Starting Balance":
					hashMapTokenIndex.put("Checking Starting Balance", i);
					break;

				case "Savings Starting Balance":
					hashMapTokenIndex.put("Savings Starting Balance", i);
					break;

				case "Credit Starting Balance":
					hashMapTokenIndex.put("Credit Starting Balance", i);
					break;

				case "Credit Max":
					hashMapTokenIndex.put("Credit Max", i);
					break;

				case "Email":
					hashMapTokenIndex.put("Email", i);
					break;

				case "Password":
					hashMapTokenIndex.put("Password", i);
					break;

				case "From First Name":
					hashMapTokenIndex.put("From First Name", i);
					break;

				case "From Last Name":
					hashMapTokenIndex.put("From Last Name", i);
					break;

				case "From Where":
					hashMapTokenIndex.put("From Where", i);
					break;

				case "Action":
					hashMapTokenIndex.put("Action", i);
					break;

				case "To First Name":
					hashMapTokenIndex.put("To First Name", i);
					break;

				case "To Last Name":
					hashMapTokenIndex.put("To Last Name", i);
					break;

				case "To Where":
					hashMapTokenIndex.put("To Where", i);
					break;

				case "Action Amount":
					hashMapTokenIndex.put("Action Amount", i);
					break;
			}
		}

		return hashMapTokenIndex;
	}

	/**This method parses the textfile line by line to remove unwanted characters from the string.
	 * @param strLine A unparsed string (a whole line from the .csv)
	 * @param numOfElements The number (int) of elements that would be parsed from the file
	 * @return Returns tokenized string
	 * @author David Morales
	 */
	public static String[] fileParser(String strLine, int numOfElements) {
		String[] tokenizedLine = new String[numOfElements]; 
		int index = 0;
		int i = 0;
		tokenizedLine[index] = "";

		while(i != strLine.length()) { //Runs through the whole string
			while(strLine.charAt(i) == ',') { //Splits the string at the comma
				i++;
				index++;
				tokenizedLine[index] = "";
				if(i >= strLine.length()) { //Prevent out of bounds.
					break;
				}
			}

			if(i >= strLine.length()) {
				break;
			}

			if(strLine.charAt(i) == '"') { //If character is " then parses the segment
				tokenizedLine[index] = tokenizedLine[index] + strLine.charAt(i);
				i++;

				while(!(strLine.charAt(i) == '"')) {
					tokenizedLine[index] = tokenizedLine[index] + strLine.charAt(i);
					i++;
					if(i >= strLine.length()){
						break;
					}
				}

				if(i >= strLine.length()){
					break;
				}

				tokenizedLine[index] = tokenizedLine[index] + strLine.charAt(i);
				i++;
				index++;
				tokenizedLine[index] = "";
			}

			if(i >= strLine.length()){
					break;
			}

			if(strLine.charAt(i) == ',') {
				i++;
			}

			tokenizedLine[index] = "" + tokenizedLine[index] + strLine.charAt(i);
			i++;

			if(i >= strLine.length()){
				break;
			}
		}
		return tokenizedLine;
	}

	
	/**This method parses id number from the textfile and returns the integer representation 
	 * @param strIdNum String version of ID numbers
	 * @return Returns a parsed (String to int) ID number 
	 * @author David Morales
	 */
	public static int idNumParser(String strIdNum) {
		String parsedNum = ""; 
		for(int i = 0; i < strIdNum.length(); i++){ 
			if(strIdNum.charAt(i) == '-') { //Parses out '-' character
				i++;
			}
			parsedNum = parsedNum + strIdNum.charAt(i);
		}
		int idNum = Integer.parseInt(parsedNum);
		return idNum;
		
	}

	/**Prints all users for the menu.
	 * @param bankUsers This arrayList contains all bank users (Customer) 
	 * @author David Morales
	 */
	public static void printUsers(ArrayList<Customer> bankUsers) { 
		Customer iter;
		for(int i = 0; i < bankUsers.size(); i++) {
			iter = bankUsers.get(i);
			System.out.println("[" + i + "]" + " Name: " + iter.getFirstName() + " " + iter.getLastName() + " ID #" + iter.getIdNum());
		}
	}

	/**Rewrites the .csv with updated information
	 * @param bankUserList The arrayList of all bank users and their attributes/info
	 * @author David Morales
	 */
	public static void updateDatabase(ArrayList<Customer> bankUserList) throws IOException {
		try {
			FileWriter fw = new FileWriter("updatedDatabase.csv");
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter textWriter = new PrintWriter(bw);
			Customer temp;

			textWriter.println("First Name,Last Name,Date of Birth,Identification Number,Address,Phone Number,Checking Account Number,Savings Account Number,Credit Account Number,Checking Starting Balance,Savings Starting Balance,Credit Starting Balance,Email,Password,Credit Max"); //Writes the header info on first line

			//This for loop writes all the updated info from the objects in the same file 
			for(int i = 0; i < bankUserList.size(); i++) {
				temp = bankUserList.get(i); 
				textWriter.println(temp.getFirstName() + "," + temp.getLastName() + "," + temp.getDob() + "," + temp.getIdNum() + "," + temp.getAddress() + "," + temp.getPhoneNum() + "," + temp.getChecking().getAccountNum() + "," + temp.getSavings().getAccountNum() + "," + temp.getCredit().getAccountNum() + "," + temp.getChecking().getCurrentBalance() + "," + temp.getSavings().getCurrentBalance() + "," + temp.getCredit().getCurrentBalance() + "," + temp.getEmail() + "," + temp.getPassword() + "," + temp.getCredit().getCreditMax()); //Writes in the same format
			}

			textWriter.close();
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}

	/**Checks if string input are numbers using try/catch exceptions.
	 * @param input The user inputted String
	 * @return Returns true if string is a number
	 * @author David Morales
	 */
	public static boolean isInputValid(String input) { 
		try {
			Integer.parseInt(input);
			return true;
		}

		catch (NumberFormatException ex) {
			return false;
		}
	}

	/**Checks if string input are double numbers using try/catch exceptions.
	 * @param input The user inputted String
	 * @return Returns true if string a number
	 * @author David Morales
	 */
	public static boolean isInputValidDouble(String input) { 
		try {
			Double.parseDouble(input);
			return true;
		}

		catch (NumberFormatException ex) {
			return false;
		}
	}

	/**Checks user password using a string comparison.
	* @param bankUser The bank user account.
	* @return Returns true or false about the user's password.
	* @author Axel Diaz 
	*/
	public static boolean passwordChecker(Customer bankUser) {
		Scanner userIn = new Scanner(System.in);
		System.out.println("Please input user password: ");
		String userInput = userIn.nextLine();

		if(userInput.equals(bankUser.getPassword())) {
			System.out.println("Login success! \n");
			return true; 
		}

		System.out.println("Invalid password. Try again. \n");
		return false;

	}

	/**Writes to a separate text file to log all transactions of the session
	 * @param transaction The string that gets written to transactionLog.txt
	 * @author David Morales
	 */
	public static void transactionLogger(String transaction) throws IOException {
		try { 
			File loggerTxt = new File("transactionsLog.csv");
			
			if(!loggerTxt.exists()) { //If text file doesn't exit, then it creates one
				loggerTxt.createNewFile();
			}

			FileWriter fw = new FileWriter("transactionsLog.csv", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter textWriter = new PrintWriter(bw);
			textWriter.println(transaction); //Writes any string pass into this function to transactionLog.txt
			textWriter.close();
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}
	}
}