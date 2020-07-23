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

/**This abstract class file contains all personal account information
 * @author Axel Diaz
 */
public abstract class Person {
    protected String address;// Person
    protected String lastName;// Person
    protected String dob;// Person
    protected String firstName;// Person
    protected String phoneNum;
    protected String email;
    protected int idNum; 

    public Person(){
    }

    /**Person constructor with all attributes 
	 * @param fN First name.
	 * @param lN Last name.
	 * @param dob Date of birth.
	 * @param ad Address.
	 * @param pN Phone number.
	 * @param eM Email.
	 * @param id ID number.
	 * @author Axel Diaz 
	 */
    public Person(String fN, String lN, String dob, String ad, String pN, String eM, int id){
        this.address = ad;
        this.dob = dob;
        this.firstName = fN;
        this.lastName = lN;
        this.phoneNum = pN;
        this.email = eM;
        this.idNum = id;
    }

    //Getters
    public String getAddress() {
        return address;
    }
    public String getLastName(){
        return lastName;
    }
    public String getDob(){
        return dob;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public String getEmail() {
        return email;
    }
    public int getIdNum() {
        return idNum;
    }

    //Setters
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setDob(String dob){
        this.dob = dob;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setAddress(String address){
        this.address =  address;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

}