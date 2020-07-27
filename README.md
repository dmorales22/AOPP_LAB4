# Programming Assignment #4 
Written and developed by Axel Diaz and David Morales

Usage:
	NOTE: This program only opens .csv files only.

	To compile, input on command line: 
		javac RunBank.java

	To run this program, input on command line 
		java RunBank <filename>.csv

		Example: java RunBank bankUsers.csv
	
	NOTE: This program does not override original .csv, but instead saves new information on 
		  updatedDatabase.csv in the original format. All transactions are recorded to
		  transactionsLog.csv. To use the transactions reader, it has be in a specific format.
		  An example of this format is in transactionsToRead.csv. 