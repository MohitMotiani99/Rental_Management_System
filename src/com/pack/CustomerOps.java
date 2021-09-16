package com.pack;

import java.util.ArrayList;

import com.pack.exception.DuplicateToyIdException;
import com.pack.exception.InsufficientToysException;
import com.pack.exception.InsufficientToystoDeleteException;
import com.pack.exception.NoToyFoundException;

public interface CustomerOps {
	
    String rent(Customer cust,int toyId,int units) throws NoToyFoundException,InsufficientToysException, InsufficientToystoDeleteException, CloneNotSupportedException ; // Add details of rented toy
	Toy getToy(int toyId);
	ArrayList<Toy> getAllToys();
	ArrayList<Toy> searchToysbyName(String toyName);
	ArrayList<Toy> searchToysbyAgeRange(int lowAge, int highAge);
	ArrayList<Toy> searchToysbyRentalAmountRange(int minRentalAmount, int maxRentalAmount);
	ArrayList<Toy> viewCart(Customer cust) throws NoToyFoundException;
	String deleteFromCart(Customer cust,int toyId,int units) throws NoToyFoundException, InsufficientToystoDeleteException, DuplicateToyIdException;
	long generateBill(Customer cust);
	ArrayList<ToyRental> getRentedToys(Customer cust);
	long returnToys(Customer cust,int rentalid);
	ArrayList<ToyRental>  viewHistory(Customer cust);
}
