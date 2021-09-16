package com.pack;

import java.util.ArrayList;
import java.util.HashMap;

import com.pack.exception.DuplicateToyIdException;
import com.pack.exception.InsufficientToysException;
import com.pack.exception.InsufficientToystoDeleteException;
import com.pack.exception.NoToyFoundException;

public abstract class Admin {
	protected String email;
	protected String password;
	protected String name;
	
	
	// Searches for the toy with given toyId
		// if found , return the Toy object
		// else return null
	public abstract Toy searchToy(int toyId);
	
	
	// Searches for the toy with given toyName
			// if found , return the ArrayList of Toy objects
			// else return null
	public abstract ArrayList<Toy> searchToysbyName(String toyName);
	
	
	// Searches for the toy for an age range
				// if found , return the ArrayList of Toy objects
				// else return null
	public abstract ArrayList<Toy> searchToysbyAgeRange(int lowAge,int highAge);
	
	
	// Searches for the toys with rentalAmount given toyName
				// if found , return the ArrayList of Toy objects
				// else return null
	public abstract ArrayList<Toy> searchToysbyRentalAmountRange(int minRentalAmount,int maxRentalAmount);
		
	
	// to add new toy : given all the toy parameters
	//public abstract String addToy(int toyId,String toyName,String toyType,int minAge,int maxAge,int price,int quantitiy,int rentalAmount,int refundDeposit);
	//returns message after the execution
	// if toyId already there though,then throw an exception DuplicateToyIdException
	public abstract String addToy(Toy toy) throws DuplicateToyIdException;
	
	
	// to remove a toy with a given toy id
	// if there is no toy to remove then throw an exception NoToyFoundException
	// else 
		// if quantity == 1 currently
			//remove the full toy object from the arraylist of toys
		//else
			//decrease the quantity by 1
	public abstract String removeToy(int toyId,int units) throws NoToyFoundException,InsufficientToysException,InsufficientToystoDeleteException, CloneNotSupportedException;
	
	public abstract Toy getRemovedToy(int toyId);
	
	
	// to update name for a toy with a given toy id
		// if there is no toy to update then throw an exception NoToyFoundException
		// else update the toyName
	public abstract String updateToyName(int toyId,String toyName) throws NoToyFoundException;
	
	
	// to update toyType for a toy with a given toy id
			// if there is no toy to update then throw an exception NoToyFoundException
			// else update the toyType
	//UI gives the user option to which toy type it needs for updated version and user selects the choice
	// depending on that the right string will be passed.
	public abstract String updateToyType(int toyId,String toyType) throws NoToyFoundException;
	
	
	// to update minAge & maxAge for a toy with a given toy id
			// if there is no toy to update then throw an exception NoToyFoundException
			// else update the minAge and maxAge
	public abstract String updateToyAge(int toyId,int minAge,int maxAge) throws NoToyFoundException;
	
	
	// to update name for a toy with a given toy id
			// if there is no toy to update then throw an exception NoToyFoundException
			// else update the price
	public abstract String updatePrice(int toyId,int price) throws NoToyFoundException;
	
	
	// to update rentalAmount for a toy with a given toy id
			// if there is no toy to update then throw an exception NoToyFoundException
			// else update the rentalAmount
	public abstract String updateRentalAmount(int toyId,int rentalAmount) throws NoToyFoundException;
	
	
	// to update refundableDeposit for a toy with a given toy id
			// if there is no toy to update then throw an exception NoToyFoundException
			// else update the refundableDeposit
	public abstract String updateRefundableDeposit(int toyId,int refundableDeposoit) throws NoToyFoundException;
	
	
	// gets the total of prices of each toy in the inventory
	public abstract long getInventoryValue();
	
	
	// gets the total rental income being generated with the current toys on rent
	public abstract long getRentalIncome();
	
	public abstract boolean checkCustomer(int custId);
	
	public abstract ArrayList<ArrayList<Integer>> getCustomerRentals(int custId);
	
	public abstract String setCustomerRentals(int custId,ArrayList<ArrayList<Integer>> tclist);
	
	public abstract HashMap<Integer, ArrayList<ArrayList<Integer>>> getAllRentals();
	
	public abstract ArrayList<Toy> getAllToys();
	
	public abstract void updateCustMap(int custid,ArrayList<Integer> arr);
	
	public abstract void updateToyList(int toyid,int noofunits);
}
