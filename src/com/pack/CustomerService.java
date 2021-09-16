package com.pack;

import java.util.ArrayList;



import com.pack.exception.DuplicateToyIdException;
import com.pack.exception.InsufficientToysException;
import com.pack.exception.InsufficientToystoDeleteException;
import com.pack.exception.NoToyFoundException;
import com.pack.FileHandler.CustomerDB;

public class CustomerService {
	
	public static String email;
	public static String pass;
	
	
	
	private static CustomerOps cops= new CustomerImpl();
	
	static {
		
	}
	
	
	
	
	public static String sigIn() {
		for(Customer c:CustomerDB.clist) {
			if(c.getEmail().equals(email) && c.getPass().equals(pass))
				return "Logged In";
		}
		return "Sign Up Now";
	}
	public static String sigUp(int id,String name,String city,String state,String country,int zip)
	{
		Address ad = new Address(city, state, country, zip);
		CustomerDB.clist.add(new Customer(id, name, email, pass, ad));
		return "Sign Up Successful";
	}
	
	
	public static String rentService(int toyId,int units) throws NoToyFoundException,InsufficientToysException, InsufficientToystoDeleteException, CloneNotSupportedException {
		Customer cust = null;
		for(Customer c : CustomerDB.clist) {
			if(c.getEmail().equals(email) && c.getPass().equals(pass))
			{
				cust=c;
				break;
			}
		}
//		System.out.println(cust);
//		System.out.println("g");
		String s = null;
		try {
			s= cops.rent(cust,toyId, units);
		}
		catch(InsufficientToysException e){
			s= e.getMessage();
		}
		catch(NoToyFoundException e) {
			s = e.getMessage();
		}
		catch(InsufficientToystoDeleteException e) {
			s= e.getMessage();
		}
		catch(CloneNotSupportedException e) {
			s= e.getMessage();
		}
		finally {
			
		}
		
		return s;
	
	}
	
	public static ArrayList<Toy> getAllToysService() {
		return cops.getAllToys();
	}
	
	public static ArrayList<Toy> searchToysbyName(String toyName) {
		return cops.searchToysbyName(toyName);
	}
	
	public static ArrayList<Toy> searchToysbyAgeRange(int lowAge, int highAge) {
		return cops.searchToysbyAgeRange(lowAge, highAge);
	}
	
	public static ArrayList<Toy> searchToysbyRentalAmountRange(int minRentalAmount, int maxRentalAmount) {
		return cops.searchToysbyRentalAmountRange(minRentalAmount, maxRentalAmount);
	}	
	
	public static String deleteFromCart(int toyId,int units) throws NoToyFoundException, InsufficientToystoDeleteException, DuplicateToyIdException {
		Customer cust = null;
		for(Customer c:CustomerDB.clist) {
			if(c.getEmail().equals(email) && c.getPass().equals(pass))
			{
				cust=c;
				break;
			}
		}
		
		
		String s = null;
		try {
			s= cops.deleteFromCart(cust, toyId, units);
		}
		
		catch(NoToyFoundException e) {
			s = e.getMessage();
		}
		catch(InsufficientToystoDeleteException e) {
			s= e.getMessage();
		}
		
		finally {
			
		}
		
		return s;
	}
	
	public static ArrayList<Toy> viewCartService() throws NoToyFoundException{
		
		Customer cust1 = null;
		for(Customer c:CustomerDB.clist) {
			if(c.getEmail().equals(email) && c.getPass().equals(pass))
			{
				cust1=c;
				break;
			}
		}
		
//		ArrayList<ArrayList<Integer>> cart = AdminService.getCustomerRentalsService(cust1.getCustId());
//		for(ArrayList<Integer> rec:cart) {
//			System.out.println(rec.get(0)+"    "+rec.get(1)+"       "+rec.get(2));
//		}
		
		
//		ArrayList<ArrayList<Integer>>  s = null;
		try {
			return cops.viewCart(cust1);
		}
		catch(NullPointerException e) {
			System.out.println("Cart is Empty");
		}
		catch(NoToyFoundException e) {
			e.printStackTrace();
		}
		finally {
			
		}
		return null;
	}
	
	public static long generateBillService() {
		Customer cust1 = null;
		for(Customer c:CustomerDB.clist) {
			if(c.getEmail().equals(email) && c.getPass().equals(pass))
			{
				cust1=c;
				break;
			}
		}
		
		
		return cops.generateBill(cust1);
	}
	
	public static ArrayList<ToyRental> getRentedToysService(){
		Customer cust1 = null;
		for(Customer c:CustomerDB.clist) {
			if(c.getEmail().equals(email) && c.getPass().equals(pass))
			{
				cust1=c;
				break;
			}
		}
		return cops.getRentedToys(cust1);
	}
	
	public static long returnToysService(int rentalId) {
		Customer cust1 = null;
		for(Customer c:CustomerDB.clist) {
			if(c.getEmail().equals(email) && c.getPass().equals(pass))
			{
				cust1=c;
				break;
			}
		}
		return cops.returnToys(cust1, rentalId);
	}
	
	public static ArrayList<ToyRental>  viewHistoryService (){
		Customer cust1 = null;
		for(Customer c:CustomerDB.clist) {
			if(c.getEmail().equals(email) && c.getPass().equals(pass))
			{
				cust1=c;
				break;
			}
		}
		return cops.viewHistory(cust1);
	}
//	public static void main(String[] args) throws NoToyFoundException, InsufficientToysException, InsufficientToystoDeleteException, DuplicateToyIdException, CloneNotSupportedException {
//		//CustomerService cs = new CustomerService();
//		CustomerService.sigUp(0,"C","delhi","delhi","in",91);
//		
//		for(Customer c:CustomerDB.clist)
//			System.out.println(c);
//		System.out.println("T");
//		
//		CustomerService.rentService(2, 2);
//		CustomerService.viewCartService();
//		System.out.println();
//		CustomerService.deleteFromCart(2, 2);
//		CustomerService.viewCartService();
//		System.out.println();
//		CustomerService.rentService(2, 2);
//		CustomerService.viewCartService();
//		System.out.println();
//		CustomerService.rentService(3, 1);
//		CustomerService.viewCartService();
//		
//		System.out.println();
//		System.out.println();
//		
//		System.out.println(CustomerService.searchToysbyName("bat"));
//		System.out.println(CustomerService.searchToysbyAgeRange(5, 9));
//		System.out.println(CustomerService.searchToysbyRentalAmountRange(50, 200));
//		
//		System.out.println("Bill: "+CustomerService.generateBillService());
//	}
	
	
}
