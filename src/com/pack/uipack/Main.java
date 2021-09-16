package com.pack.uipack;

import java.io.EOFException;
import java.util.*;

import com.pack.*;
import com.pack.FileHandler.CustomerDB;
import com.pack.FileHandler.ToyDB;
import com.pack.exception.DuplicateToyIdException;
import com.pack.exception.InsufficientToysException;
import com.pack.exception.InsufficientToystoDeleteException;
import com.pack.exception.NoToyFoundException;


public class Main {

	private static String adminName="admin";
	private static String adminPass="admin";

	private static void init() throws EOFException {
		try {
			CustomerDB.getAllData();
			ToyDB.getAllData();
		}
		catch(EOFException e) {
			System.out.println("Database is not loaded");
		}
		finally {
			
		}
		HashMap<Integer,ArrayList<ArrayList<Integer>>> cmap= AdminImpl.custMap;
		
		for(Customer c : CustomerDB.clist) {
			HashMap<Integer,ArrayList<Integer>> temp=new HashMap<Integer,ArrayList<Integer>>();
			for(Toy t : c.getCustShoppingList()) {
				ArrayList<Integer>arr=new ArrayList<Integer>();
				if(temp.containsKey(t.getToyId())) {
					arr.add(temp.get(t.getToyId()).get(0)+t.getQuantitiy());
					arr.add(temp.get(t.getToyId()).get(1)+t.getQuantitiy()*t.getRentalAmount());
				}else {
					arr.add(t.getQuantitiy());
					arr.add(0+t.getQuantitiy()*t.getRentalAmount());	
				}
				temp.put(t.getToyId(),arr);
			}
			
			ArrayList<ArrayList<Integer>> arr2d=new ArrayList<ArrayList<Integer>>();
			for(Integer i : temp.keySet()) {
				ArrayList<Integer>arr=new ArrayList<Integer>();
				arr.add(i);
				arr.add(temp.get(i).get(0));
				arr.add(temp.get(i).get(1));
				arr2d.add(arr);
			}
			cmap.put(c.getCustId(), arr2d);
		}
		
		AdminImpl.custMap=cmap;
		AdminImpl.custDetails = CustomerDB.clist;
	}

	public static void main(String[] args) throws NoToyFoundException, InsufficientToystoDeleteException, CloneNotSupportedException, DuplicateToyIdException, InsufficientToysException, EOFException {

		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);

		//creating an arraylist of dummy custs and admins
		init();

		boolean status=true;
		do {

			//Asking the person to enter the login details
			System.out.println("Please enter Username and Password");
			String unName=sc.next();
			String password=sc.next();
			boolean userFound=false;
			for(Customer c : CustomerDB.clist) {
				if(c.getEmail().equals(unName) && c.getPass().equals(password))
				{
					userFound=true;
					break;
				}
			}

			//checks if the customer arraylist contains "person", logs in as customer and displays customer dashboard 
			if(userFound)
			{
				CustomerService.email=unName;
				CustomerService.pass=password;

				System.out.println("Logged in as a Customer");
				boolean flag=true;
				while(flag){
					System.out.println("1. Rent");  
					System.out.println("2. List of all toys"); 
					System.out.println("3. Search toys by name"); 
					System.out.println("4. Search toys by age range");
					System.out.println("5. Search toys by rental amount "); 
					System.out.println("6. View Cart "); 
					System.out.println("7. Delete from cart "); 
					System.out.println("8. Generate the bill ");
					System.out.println("9. View your profile");
					System.out.println("10. Return Toys");
					System.out.println("11. View History");
					System.out.println("0. Exit");
					System.out.println("Enter your choice");
					int choice = sc.nextInt();

					switch (choice) {
					case 1:
					{
						System.out.println("Enter the toy ID");
						int toyId=sc.nextInt();
						System.out.println("Enter the toy units");
						int units=sc.nextInt();
						String rent=CustomerService.rentService(toyId,units);
						System.out.println(rent);
						break;

					}
					case 2:
					{
						ArrayList<Toy> tlist=CustomerService.getAllToysService();
						for (Toy toy : tlist)
						{
							if(toy.getQuantitiy()!=0)
								System.out.println(toy);
						}
						break;
					}
					case 3:
					{  
						// TODO 
						System.out.println("Enter the toy name");
						String toyName=sc.next();
						ArrayList<Toy> tlist=CustomerService.searchToysbyName(toyName);
						for (Toy toy : tlist)
						{
							System.out.println(toy);
						}


						break;
					}
					case 4:
					{   
						System.out.println("Enter the age range for which you wish to search toys");
						int lowAge=sc.nextInt();
						int highAge=sc.nextInt();
						ArrayList<Toy> tlist=CustomerService.searchToysbyAgeRange(lowAge,highAge);
						for (Toy toy : tlist)
						{
							System.out.println(toy);
						}


						break;
					}
					case 5:
					{   
						System.out.println("Enter the rental amount range for which you wish to search toys");
						int minRentalAmount=sc.nextInt();
						int maxRentalAmount=sc.nextInt();
						ArrayList<Toy> tlist=CustomerService.searchToysbyRentalAmountRange(minRentalAmount,maxRentalAmount);
						for (Toy toy : tlist)
						{
							System.out.println(toy);
						}


						break;
					}
					case 6:
					{
						ArrayList<Toy> cart = CustomerService.viewCartService();
						// TODO System.out.println(CustomerService);
						if(cart.size() == 0) {
							System.out.println("Cart is Empty");
						}else {
							for(Toy rec:cart) {
								System.out.println("Toy ID: "+rec.getToyId()+" Number of Units : "+rec.getQuantitiy()+" Cost: "+rec.getRentalAmount());
							}
						}
						break;
					}
					case 7:
					{
						System.out.println("Enter the toy ID and units for the toy ");
						int toyId = sc.nextInt();
						int units = sc.nextInt();

						String msg = CustomerService.deleteFromCart(toyId, units);
						System.out.println(msg);

						break;
					}

					case 8:
					{
						long bill = CustomerService.generateBillService();
						System.out.println("Bill Generated for the Rentals is : " + bill);
						break;
					}
					case 9:
					{
						Customer cust = null;
						for(Customer c : CustomerDB.clist) {
							if(c.getEmail().equals(unName) && c.getPass().equals(password))
							{
								cust=c;
								break;
							}
						}
						System.out.println(cust);
						System.out.println(cust.getCustShoppingList());
						break;
					}
					case 10:{
						ArrayList<ToyRental> al = CustomerService.getRentedToysService();
						if(al.size() ==0) {
							System.out.println("No Toy rented");
						}else {
							System.out.println("List of Rented toys you have");
							for(ToyRental t : al) {
								System.out.println(t);
							}
							System.out.println("Please Select the rental id of the toy you want to return");
							int sid = sc.nextInt();
							System.out.println("Total price you need to pay: "+ CustomerService.returnToysService(sid));
						}
						break;
					}
					case 11: {
						ArrayList<ToyRental>  list = CustomerService.viewHistoryService();
						for(ToyRental t : list) {
							System.out.println(t);
						}
						break;
					}
					
					default:
					{

						System.out.println("Bye");
						flag=false;
						break;
					}

					}
				}

			}

			//checks if the admin arraylist contains "person", logs in as admin and displays admin dashboard 
			else if(unName.equals(adminName) && password.equals(adminPass))    
			{   System.out.println("Logged in as a Admin"); 
			boolean flag=true;
			while(flag){
				System.out.println("1. Add Toy");  
				System.out.println("2. List of toys"); 
				System.out.println("3. Update toy");
				System.out.println("4. Remove toy "); 
				System.out.println("5. Get Inventory Value");
				System.out.println("6.Get Rental Income");
				System.out.println("7. Search toys by name"); 
				System.out.println("8. Search toys by age range");
				System.out.println("9. Search toys by rental amount ");
				System.out.println("0. Exit");System.out.println();
				System.out.println("Enter your choice");
				int choice = sc.nextInt();

				switch(choice){
				case 1:
				{
					System.out.println("Kindly fill toy Details");
					System.out.println("Enter Toy ID");
					int toyId = sc.nextInt();
					System.out.println("Enter Toy Name");
					String toyName=sc.next();
					System.out.println("Enter Toy Type");
					String toyType = sc.next();
					System.out.println("Enter MinAge for Toy");
					int minAge = sc.nextInt();
					System.out.println("Enter Max Age for Toy");
					int maxAge = sc.nextInt();
					System.out.println("Enter Price of Toy");
					int price = sc.nextInt();
					System.out.println("Enter Quantity of the Toys");
					int quantity = sc.nextInt();
					System.out.println("Enter Rental Price of the Toy");
					int rentalAmount = sc.nextInt();
					System.out.println("Enter Refundable Deposite of the Toy");
					int refundableDeposit = sc.nextInt();

					System.out.println(AdminService.addToyService(new Toy(toyId,toyName,toyType,minAge,maxAge,price,quantity,rentalAmount,refundableDeposit)));
					break;			

				}

				case 2:
				{
					ArrayList<Toy> tlist = AdminService.getAllToysService();

					for (Toy toy : tlist)
					{
						System.out.println(toy);
					}

					break;
				}

				case 3:
				{
					System.out.println("Enter the toy id");
					int toyId = sc.nextInt();

					System.out.println("1.Update Name");
					System.out.println("2.Update Price");
					System.out.println("3.Update Type");
					System.out.println("4.Update Rental Amount ");
					System.out.println("5. Exit");

					System.out.println( "Enter Update Option");
					int num = sc.nextInt();

					switch(num)
					{
					case 1:
					{

						System.out.println("Enter Updated toy Name");
						String toyName = sc.next();
						System.out.println(AdminService.updateToyNameService(toyId, toyName));


						break;
					}
					case 2:
					{
						System.out.println("Enter the updated toy price");
						int price = sc.nextInt();
						System.out.println(AdminService.updatePriceService(toyId, price));
						break;
					}

					case 3:
					{
						System.out.println("Enter the updated Toy Type");
						String toyType = sc.next();
						System.out.println(AdminService.updateToyTypeService(toyId, toyType));
						break;
					}
					case 4:
					{
						System.out.println("Enter the updated rental Amount");
						int rentalAmount = sc.nextInt();
						System.out.println(AdminService.updateRentalAmountService(toyId, rentalAmount));

						break;
					}
					default:
					{
						//sc.close();
						System.out.println("No updatation");
						//System.exit(0);

					}
					}

					//				System.out.println("Enter the updated toy price");
					//				int price = sc.nextInt();
					//				System.out.println(AdminService.updatePriceService(toyId, price));
					//						
					break;
				}

				case 4:
				{
					System.out.println("Enter the toy id & number of units for the toy to remove");
					int toyId = sc.nextInt();
					int units = sc.nextInt();
					System.out.println (AdminService.removeToyService(toyId,units)); 

					break;
				}

				case 5:
				{
					System.out.println("Total Inventory value:   Rs. "+AdminService.getInventoryValueService());
					break;
				}
				case 6:
				{

					System.out.println("Total Rental Income:   Rs. "+AdminService.getRentalIncomeService());
					break;
				}
				
				case 7:
				{  
					// TODO 
					System.out.println("Enter the toy name");
					String toyName=sc.next();
					ArrayList<Toy> tlist=AdminService.searchToysbyNameService(toyName);
					for (Toy toy : tlist)
					{
						System.out.println(toy);
					}


					break;
				}
				case 8:
				{   
					System.out.println("Enter the age range for which you wish to search toys");
					int lowAge=sc.nextInt();
					int highAge=sc.nextInt();
					ArrayList<Toy> tlist=AdminService.searchToysbyAgeRangeService(lowAge,highAge);
					for (Toy toy : tlist)
					{
						System.out.println(toy);
					}


					break;
				}
				case 9:
				{   
					System.out.println("Enter the rental amount range for which you wish to search toys");
					int minRentalAmount=sc.nextInt();
					int maxRentalAmount=sc.nextInt();
					ArrayList<Toy> tlist=AdminService.searchToysbyRentalAmountRangeService(minRentalAmount,maxRentalAmount);
					for (Toy toy : tlist)
					{
						System.out.println(toy);
					}


					break;
				}



				default:
				{  System.out.println("Bye");
				flag=false;
				break;
				}
				}

			}
			}
			//if login details do not match either as a customer or as an admin
			else {
				System.out.println("Wrong Username or Password. Try again or register");
				System.out.println("Enter Customer ID");
				int custId=sc.nextInt();
				System.out.println("Enter Customer Name");
				String custName=sc.next();	
				System.out.println("Enter Customer City");
				String city=sc.next();
				System.out.println("Enter Customer State");
				String state=sc.next();
				System.out.println("Enter Customer Country");
				String country=sc.next();
				System.out.println("Enter Customer Zip Code");
				int zip=sc.nextInt();  
				System.out.println("Enter username and paswword");
				CustomerService.email=sc.next();
				CustomerService.pass=sc.next();

				System.out.println(CustomerService.sigUp( custId, custName, city, state, country, zip));

			}

			System.out.println("Do you want to login again (yes/no)");
			String str=sc.next();

			status=str.equalsIgnoreCase("yes");

		}while(status);

		{
			CustomerDB.add();
			ToyDB.add();	
		}

	}}