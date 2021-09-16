package com.pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.pack.FileHandler.ToyDB;
import com.pack.exception.DuplicateToyIdException;
import com.pack.exception.InsufficientToystoDeleteException;
import com.pack.exception.NoToyFoundException;

public class AdminImpl extends Admin{

	/**
	 * Assumption is taken that in the project there is a class whose work is only to extract data from
	 * the file storage and put them in appropriate static data structures like ArrayList,Maps etc.
	 * For me to use those structures ill just import the file and use them
	 * 
	 * Data structures Assumed:-
	 * 			ArrayList<Toy> toyList: Containing details of every toy currently in inventory
	 * 			HashMap<Integer,List<Integer>> : Containing current rental details wrt each customer,
	 * 												key is customerId,value is List of toyId 
	 * 		
	 */
	
	static {
//		ToyDB.toylist.add(new Toy(1,"sword","Musical",5,12,100,4,50,25));
//		ToyDB.toylist.add(new Toy(2,"bat","Electronic",7,12,400,2,250,25));
//		ToyDB.toylist.add(new Toy(4,"ball","Musical",10,18,150,5,60,25));
//		ToyDB.toylist.add(new Toy(3,"ring","Musical",2,6,10,1,5,25));
		
	}
	static ArrayList<Toy> completeRentedToyList = new ArrayList<Toy>();
	public static ArrayList<Customer> custDetails = new ArrayList<>();
	
	//static HashMap<Integer, ArrayList<Integer>> custMap = new HashMap<Integer, ArrayList<Integer>>();
	
	public static HashMap<Integer,ArrayList<ArrayList<Integer>>> custMap = new HashMap<Integer,ArrayList<ArrayList<Integer>>>();
	
	public AdminImpl() {
		
	}
	
	@Override
	public boolean checkCustomer(int custId) {
		// TODO Auto-generated method stub
		return custMap.containsKey(custId);
	}
	
	@Override
	public ArrayList<ArrayList<Integer>> getCustomerRentals(int custId){
		return custMap.get(custId);
	}
	
	@Override
	public String setCustomerRentals(int custId, ArrayList<ArrayList<Integer>> tclist) {
		// TODO Auto-generated method stub
		custMap.put(custId, tclist);
		return "Customer Shopping List Updated Successfully";
	}
	
	@Override
	public ArrayList<Toy> getAllToys() {
		// TODO Auto-generated method stub
		return ToyDB.toylist;
	}
	
	@Override
	public HashMap<Integer, ArrayList<ArrayList<Integer>> > getAllRentals() {
		// TODO Auto-generated method stub
		return custMap;
	}
	
	@Override
	public Toy searchToy(int toyId) {
		
		return ToyDB.toylist.stream().filter(toy->toy.getToyId()==toyId).collect(Collectors.toList()).get(0);
	}

	@Override
	public ArrayList<Toy> searchToysbyName(String toyName) {
//		ArrayList<Toy> res = new ArrayList<Toy>();
//		
//		Toy toy=null;
//		Iterator<Toy> it = ToyDB.toylist.iterator();
//		while(it.hasNext()) {
//			if((toy=it.next()).getToyName().equalsIgnoreCase(toyName))
//				res.add(toy);
//		}
//		return res;
		return (ArrayList<Toy>) ToyDB.toylist.stream().filter(toy->toy.getToyName().equalsIgnoreCase(toyName)).collect(Collectors.toList());
	}

	@Override
	public ArrayList<Toy> searchToysbyAgeRange(int lowAge, int highAge) {
//		ArrayList<Toy> res = new ArrayList<Toy>();
//		
//		Toy toy=null;
//		Iterator<Toy> it = ToyDB.toylist.iterator();
//		while(it.hasNext()) {
//			toy=it.next();
//			if(toy.getMaxAge()<=highAge && toy.getMaxAge()>=lowAge)
//				res.add(toy);
//			else if(toy.getMinAge()>=lowAge && toy.getMinAge()<=highAge)
//				res.add(toy);
//			
//			
//		}
//		return res;
		

		Predicate<Toy> p1 = toy->toy.getMinAge()<=highAge && toy.getMinAge()>=lowAge;
		Predicate<Toy> p2 = toy->toy.getMaxAge()<=highAge && toy.getMaxAge()>=lowAge;

		return (ArrayList<Toy>) ToyDB.toylist.stream().filter(toy->p1.test(toy) || p2.test(toy)).collect(Collectors.toList());
	}

	@Override
	public ArrayList<Toy> searchToysbyRentalAmountRange(int minRentalAmount, int maxRentalAmount) {
		

		Predicate<Toy> p1 = toy->toy.getRentalAmount()>=minRentalAmount && toy.getRentalAmount()<=maxRentalAmount;

		return (ArrayList<Toy>) ToyDB.toylist.stream().filter(toy->p1.test(toy)).collect(Collectors.toList());
	}

	@Override
	public String addToy (Toy toy) throws DuplicateToyIdException {
		//System.out.println("ggg");
		Toy t = searchToy(toy.getToyId());
		//System.out.println("h1");
		if(t==null) {
			ToyDB.toylist.add(toy);
			//System.out.println("h2");
		}
		else if(t.equals(toy)) {
			//System.out.println("h3");
			t.setQuantitiy(t.getQuantitiy()+toy.getQuantitiy());
		}
		else
			throw new DuplicateToyIdException();
		
//		System.out.println("Toylist:");
//		System.out.println(toyList);
//		System.out.println(completeRentedToyList);
		return "Toy Added Successfully to Inventory";
	}

	@Override
	public String removeToy(int toyId,int units) throws NoToyFoundException, InsufficientToystoDeleteException, CloneNotSupportedException {
		Toy t = searchToy(toyId);
		if(t==null)
			throw new NoToyFoundException();
		else if(t.getQuantitiy()== units)
			{
				completeRentedToyList.add(t);
				t.setQuantitiy(0);
//				ToyDB.toylist.remove(t);
				//System.out.println("rrr");
			}
		else if(t.getQuantitiy()>units)
			t.setQuantitiy(t.getQuantitiy()-units);
		else if(t.getQuantitiy()<units)
			throw new InsufficientToystoDeleteException();
		
//		System.out.println("Toylist:");
//		System.out.println(toyList);
//		System.out.println(completeRentedToyList);
		return "Toy Removed Successfully from Inventory";
	}

	
	public Toy getRemovedToy(int toyId) {
		Toy t = null;
		for(Toy toy:completeRentedToyList) {
			if(toy.getToyId()==toyId)
			{
				t=toy;
				break;
			}
		}
		
		return t;
	}
	
	
	public String removeFromCompleted(int toyId) {
		Toy t = getRemovedToy(toyId);
		completeRentedToyList.remove(t);
		return "Not Finished";
	}
	
	@Override
	public String updateToyName(int toyId, String toyName) throws NoToyFoundException {
		Toy t = searchToy(toyId);
		if(t==null)
			throw new NoToyFoundException();
		else
			t.setToyName(toyName);
		return "Toy Name Updated Successfully";
	}

	@Override
	public String updateToyType(int toyId, String toyType) throws NoToyFoundException {
		Toy t = searchToy(toyId);
		if(t==null)
			throw new NoToyFoundException();
		else
			t.setToyType(toyType);;
		return "Toy Type Updated Successfully";
	}

	@Override
	public String updateToyAge(int toyId, int minAge, int maxAge) throws NoToyFoundException {
		Toy t = searchToy(toyId);
		if(t==null)
			throw new NoToyFoundException();
		else{
				t.setMinAge(minAge);
				t.setMaxAge(maxAge);
		}
		return "Toy Age Range Updated Successfully";
	}

	@Override
	public String updatePrice(int toyId, int price) throws NoToyFoundException {
		Toy t = searchToy(toyId);
		if(t==null)
			throw new NoToyFoundException();
		else
			t.setPrice(price);
		return "Toy Price Updated Successfully";
	}

	@Override
	public String updateRentalAmount(int toyId, int rentalAmount) throws NoToyFoundException {
		Toy t = searchToy(toyId);
		if(t==null)
			throw new NoToyFoundException();
		else
			t.setRentalAmount(rentalAmount);;
		return "Toy Rental Amount Updated Successfully";
	}

	@Override
	public String updateRefundableDeposit(int toyId, int refundableDeposoit) throws NoToyFoundException {
		Toy t = searchToy(toyId);
		if(t==null)
			throw new NoToyFoundException();
		else
			t.setRefundDeposit(refundableDeposoit);
		return "Toy Refundable Deposit Updated Successfully";
	}

	@Override
	public long getInventoryValue() {
		
		long inventoryWorth = 0;
		for(Toy t:ToyDB.toylist) {
			inventoryWorth+=t.getPrice();
		}
		return inventoryWorth;
	}

	@Override
	public long getRentalIncome() {
		
		long rentalIncome = 0;
//		
//		for(Integer cid:custMap.keySet()) {
//			for(ArrayList<Integer> rec:custMap.get(cid))
//				rentalIncome+=rec.get(2);
//		}
//		
		for(Customer c : custDetails) {
//			System.out.println(c);
			for(ToyRental list : c.getCustRentalList()) {
				if(list.getStatus().equals("Closed")) {
					rentalIncome+=list.getTotalAmount();
					rentalIncome+=list.getFine();
				}
			}
		}
		return rentalIncome;
		
		
	}

	@Override
	public void updateCustMap(int custid,ArrayList<Integer> arr) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Integer>> list = custMap.get(custid);
		list.remove(arr);
		if(list.size() ==0) {
			custMap.remove(custid);
		}
	}

	@Override
	public void updateToyList(int toyid, int nooftoys) {
		// TODO Auto-generated method stub
		Toy t = searchToy(toyid);
		if(t == null) {
			for(Toy tobj: completeRentedToyList) {
				if(tobj.getToyId() == toyid) {
					t = tobj;
					break;
				}
			}
			completeRentedToyList.remove(toyid);
			t.setQuantitiy(nooftoys);
			
		}else {
			int quantity = t.getQuantitiy() + nooftoys;
			t.setQuantitiy(quantity);
		}
	}
	
	
	
//	public static void main(String[] args) throws DuplicateToyIdException, NoToyFoundException {
//		AdminImpl ai = new AdminImpl();
//		ai.addToy(new Toy(1,"sword","Musical",5,12,100,8,50,25));
//		ai.addToy(new Toy(7,"sword","Musical",5,12,100,8,50,25));
//		// ai.addToy(new Toy(1,"box","Musical",5,12,100,8,50,25));
//		for(Toy t:toyList)
//			System.out.println(t);
//		System.out.println("T");
//		
//		//ai.removeToy(2);
//		for(Toy t:toyList)
//			System.out.println(t);
//		System.out.println("T");
//		
//		//ai.removeToy(2);
//		for(Toy t:toyList)
//			System.out.println(t);
//		System.out.println("T");
//		
//		ai.updateToyName(8, "rose");
//		for(Toy t:toyList)
//			System.out.println(t);
//	}

	

	

	

	




	
	
}
