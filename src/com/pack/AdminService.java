package com.pack;

import java.util.ArrayList;
import java.util.HashMap;

import com.pack.exception.DuplicateToyIdException;
import com.pack.exception.InsufficientToystoDeleteException;
import com.pack.exception.NoToyFoundException;

public class AdminService {
	
	static AdminImpl aops = new AdminImpl();
	
	public static boolean checkCustomerService(int custId) {
		return aops.checkCustomer(custId);
	}
	
	public static ArrayList<ArrayList<Integer>> getCustomerRentalsService(int custId){
		return aops.getCustomerRentals(custId);
	}
	
	public static String setCustomerRentalsService(int custId,ArrayList<ArrayList<Integer>> tclist){
		return aops.setCustomerRentals(custId,tclist);
	}
	
	public static ArrayList<Toy> getAllToysService(){
		return aops.getAllToys();
	}
	
	public static Toy searchToyService(int toyId) {
		return aops.searchToy(toyId);
	}
	
	public static HashMap<Integer, ArrayList<ArrayList<Integer>>> getAllRentalsService(){
		return aops.getAllRentals();
	}
	
	public static ArrayList<Toy> searchToysbyNameService(String toyName) {
		return aops.searchToysbyName(toyName);
	}
	
	public static ArrayList<Toy> searchToysbyAgeRangeService(int lowAge, int highAge) {
		return aops.searchToysbyAgeRange(lowAge, highAge);
	}
	
	public static ArrayList<Toy> searchToysbyRentalAmountRangeService(int minRentalAmount, int maxRentalAmount) {
		return aops.searchToysbyRentalAmountRange(minRentalAmount, maxRentalAmount);
	}
	
	public static String addToyService(Toy toy) throws DuplicateToyIdException {
		try {
			return aops.addToy(toy);
		}
		catch(DuplicateToyIdException e) {
			return e.getMessage();
		}
		
		
	}
	
	public static String removeToyService(int toyId,int units) throws NoToyFoundException, InsufficientToystoDeleteException, CloneNotSupportedException {
		try {
			return aops.removeToy(toyId,units);
		}
		catch(NoToyFoundException e) {
			return e.getMessage();
		}
		catch(InsufficientToystoDeleteException e) {
			return e.getMessage();
		}
		catch(CloneNotSupportedException e) {
			return e.getMessage();
		}
		
	}
	
	public static String updateToyNameService(int toyId, String toyName) throws NoToyFoundException {
	
		try {
			return  aops.updateToyName(toyId, toyName);
		}
		catch(NoToyFoundException e) {
			return e.getMessage();
		}
	}
	
	public static String updateToyTypeService(int toyId, String toyType) throws NoToyFoundException {
	
		try {
			return  aops.updateToyType(toyId, toyType);
		}
		catch(NoToyFoundException e) {
			return e.getMessage();
		}
	}
	
	public static String updateToyAgeService(int toyId, int minAge, int maxAge) throws NoToyFoundException {
		
		try {
			return aops.updateToyAge(toyId, minAge, maxAge);
			}
		catch(NoToyFoundException e) {
			return e.getMessage();
		}
	}
	
	public static String updatePriceService(int toyId, int price) throws NoToyFoundException {
		
		try {
			return aops.updatePrice(toyId, price);
		}
		catch(NoToyFoundException e) {
			return e.getMessage();
		}
	}
	
	public static String updateRentalAmountService(int toyId, int rentalAmount) throws NoToyFoundException {
		
		try {
			return aops.updateRentalAmount(toyId, rentalAmount);
		}
		catch(NoToyFoundException e) {
			return e.getMessage();
		}
	}
	
	public static String updateRefundableDepositService(int toyId, int refundableDeposoit) throws NoToyFoundException {
		
		try {
			return aops.updateRefundableDeposit(toyId, refundableDeposoit);
		}
		catch(NoToyFoundException e) {
			return e.getMessage();
		}
	}
	
	public static long getInventoryValueService() {
		return aops.getInventoryValue();
	}
	
	public static long getRentalIncomeService() {
		return aops.getRentalIncome();
	}
	
	public static Toy getRemovedToyService(int toyId) {
		return aops.getRemovedToy(toyId);
	}
	
	public static String removeFromCompletedService(int toyId) {
		return aops.removeFromCompleted(toyId);
	}

	public static void updateCustMapService(int custId, ArrayList<Integer> arr) {
		aops.updateCustMap(custId, arr);
	}
	public static void updateToyListService(int toyID, int nooftoys) {
		aops.updateToyList(toyID,nooftoys);
	}
}
