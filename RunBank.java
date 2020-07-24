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

/** RunBank has all the methods for the user to interface (menu) with the objects. 
 * @author David Morales
 */
public class RunBank {
	public static String filename; //Filename from command line args. It is public so that any class can use it.
	public static ArrayList<Customer> bankUserList; //Complete list of bank user (Customer objects) and their attributes/info.
	public static ArrayList<HashMap<Integer, Integer>> hashMapAccountNums; // An arrayList of hashmaps of account number for account search. Account numbers are keys for indexes in bankUserList. Makes searching for users fast. 

	public static int maxIDNum;
	public static int maxCheckingNum;
	public static int maxSavingsNum;
	public static int maxCreditNum;

	/** Main starts the necessary beginning functions for the user menus. Filename is inputed from args in command line
	 * @param args The argument passed through the command line. 
	 * @author David Morales
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ArrayIndexOutOfBoundsException {
		try {
			filename = args[0];
			bankUserList = UserUtilities.fileReader(); //Gets bank users from text file
			UserMenu.userMenu(); //Opens main user menu
		}

		catch(FileNotFoundException fnFE) {
			System.out.println("File not found. Please input the filename again.");
		}

		catch(IOException ioEx) { 
			throw new IOException("IOException");
		}

		catch(ArrayIndexOutOfBoundsException AIOEx) {
			System.out.println("Make sure you input the filename like this 'java RunBank filename.csv'");
		}
	}
}