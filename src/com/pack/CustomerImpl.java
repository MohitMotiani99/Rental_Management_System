package com.pack;


import java.util.ArrayList;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.pack.exception.DuplicateToyIdException;
import com.pack.exception.InsufficientToysException;
import com.pack.exception.InsufficientToystoDeleteException;
import com.pack.exception.NoToyFoundException;




public class CustomerImpl implements CustomerOps{
	
	/**
	 * custMap : HashMap<Integer,ArrayList<Integer>> : Containing current rental details wrt each customer,
	 * 												key is customerId,value is List of toyId
	 * toyList : ArrayList<Toy> toyList: Containing details of every toy currently in inventory
	 * @throws InsufficientToysException 
	 * @throws InsufficientToystoDeleteException 
	 * @throws CloneNotSupportedException 
	 */

		
	
	// add to cart
	public String rent(Customer cust,int toyId, int units) throws NoToyFoundException, InsufficientToysException, InsufficientToystoDeleteException, CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		//System.out.println("cops");
		System.out.println(cust);
		//admin service
		Toy t = getToy(toyId);
//		System.out.println(t);
		if(t==null)
			throw new NoToyFoundException();
		if(units > t.getQuantitiy()) 
			throw new InsufficientToysException();
		//System.out.println(cust.getCustShoppingList());
		Toy t1=t.clone();
		t1.setQuantitiy(units);
		t1.setRentalAmount(t.getRentalAmount()*units);
		cust.getCustShoppingList().add(t1);
		//System.out.println(cust.getCustShoppingList());
		
		
		
		ArrayList<Integer> tclist= null;
		if(AdminService.checkCustomerService(cust.getCustId())==false) {
			tclist = new ArrayList<Integer>();
			tclist.add(t.getToyId());
			tclist.add(units);
			tclist.add(t.getRentalAmount()*units);
			//System.out.println(tclist);
			ArrayList<ArrayList<Integer>> cart = new ArrayList<ArrayList<Integer>>();
			cart.add(tclist);
			//System.out.println("A");
			AdminService.setCustomerRentalsService(cust.getCustId(), cart);
			System.out.println(AdminService.removeToyService(toyId, units));
			
		}
		else {
			
			boolean found=false;
			
			ArrayList<ArrayList<Integer>> cart = AdminService.getCustomerRentalsService(cust.getCustId()); 
			
			for(ArrayList<Integer> rec:cart) {
				if(rec.get(0)==toyId) {
					found=true;
					int var=units+rec.get(1);
					rec.set(1, var);
					rec.set(2, rec.get(2)+units*t.getRentalAmount());
					AdminService.removeToyService(toyId, units);
				}
			}
			
			if(!found) {
				ArrayList<Integer> rec = new ArrayList<Integer>();
				rec.add(toyId);
				rec.add(units);
				rec.add(units*t.getRentalAmount());
				cart.add(rec);
				AdminService.removeToyService(toyId, units);
			}
			
			AdminService.setCustomerRentalsService(cust.getCustId(), cart);
			
		}
		
		
		
		
		return "Product Added to the cart";
	}
	
	
	public String deleteFromCart(Customer cust,int toyId,int units) throws NoToyFoundException, InsufficientToystoDeleteException, DuplicateToyIdException {
		Toy t = getToy(toyId);
		if(t==null)
		{
			Toy toy = AdminService.getRemovedToyService(toyId);
			if(toy==null)
				throw new NoToyFoundException();
			toy.setQuantitiy(units);
			AdminService.removeFromCompletedService(toyId);

			//AdminService.addToyService(toy);
			cust.getCustShoppingList().remove(toy);//shoppilg basket remove
			
			
			ArrayList<ArrayList<Integer>> cart = AdminService.getCustomerRentalsService(cust.getCustId());
			for(int i=0;i<cart.size();i++) {
				ArrayList<Integer> rec = cart.get(i);
				if(rec.get(0)==toyId) {
					if(units== rec.get(1)) {
						//ArrayList<Integer> rec2 = rec;
						cart.remove(rec);
						AdminService.addToyService(toy);
					}
					else if(units<rec.get(1)) {
						rec.set(1, rec.get(1)-units);
						rec.set(2, rec.get(2)-units*toy.getRentalAmount());// TODO
						AdminService.addToyService(toy);
					}
					else
						throw new InsufficientToystoDeleteException();
				}
			}
			
			
			
			return "Selected toy Units deleted successfully";
		}
		
		
		// Removed from customer's shopping list
//		for(int i=0;i<units;i++) {
//			for(int j=0;j<cust.getCustShoppingList().size();j++) {
//				if(cust.getCustShoppingList().get(j).getToyId()==toyId)
//					{
//						cust.getCustShoppingList().remove(j);
//						break;
//					}
//			}
//		}
//		
		for(Toy toys : cust.getCustShoppingList()) {
			if(toys.getToyId() == toyId) {
				if(toys.getQuantitiy() > units) {
					
					toys.setQuantitiy(toys.getQuantitiy() -units);
					Toy dummyt = getToy(toys.getToyId());
					toys.setRentalAmount(toys.getQuantitiy() * dummyt.getRentalAmount());
				}else if(toys.getQuantitiy() == units) {
					ArrayList<Toy> tlist = cust.getCustShoppingList();
					tlist.remove(toys);
					cust.setCustShoppingList(tlist);
					break;
				}
			}
		}
		
		
		// Now we need to remove from custMap
		
		if(AdminService.checkCustomerService(cust.getCustId()) == false) {
			return "Invalid customer";
		}else {
			ArrayList<ArrayList<Integer>> cart = AdminService.getCustomerRentalsService(cust.getCustId());
			for(int i=0;i<cart.size();i++) {
				ArrayList<Integer> rec = cart.get(i);
				if(rec.get(0)==toyId) {
					if(units== rec.get(1)) {
						//ArrayList<Integer> rec2 = rec;
						cart.remove(rec);
						t.setQuantitiy(t.getQuantitiy()+units);
					}
					else if(units<rec.get(1)) {
						rec.set(1, rec.get(1)-units);
						rec.set(2, rec.get(2)-units*t.getRentalAmount());// TODO
						t.setQuantitiy(t.getQuantitiy()+units);
					}
					else
						throw new InsufficientToystoDeleteException();
				}
			}
		}
		
		return "Selected toy Units deleted successfully";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Toy getToy(int toyId) {
		return AdminService.searchToyService(toyId);
	}

	@Override
	public ArrayList<Toy> getAllToys() {
		return AdminService.getAllToysService();
	}
	
	
	
	@Override
	public ArrayList<Toy> searchToysbyName(String toyName) {
//		ArrayList<Toy> res = new ArrayList<Toy>();
//		
//		Toy toy=null;
//		Iterator<Toy> it = toyList.iterator();
//		while(it.hasNext()) {
//			if((toy=it.next()).getToyName()==toyName)
//				res.add(toy);
//		}
//		return res;
		
		return AdminService.searchToysbyNameService(toyName);
	}
	
	
	
	@Override
	public ArrayList<Toy> searchToysbyAgeRange(int lowAge, int highAge) {
//		ArrayList<Toy> res = new ArrayList<Toy>();
//		
//		Toy toy=null;
//		Iterator<Toy> it = toyList.iterator();
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
		return AdminService.searchToysbyAgeRangeService(lowAge, highAge);
		
	}
	
	
	
	@Override
	public ArrayList<Toy> searchToysbyRentalAmountRange(int minRentalAmount, int maxRentalAmount) {
//		ArrayList<Toy> res = new ArrayList<Toy>();
//		
//		Toy toy=null;
//		Iterator<Toy> it = toyList.iterator();
//		while(it.hasNext()) {
//			toy=it.next();
//			if(toy.getRentalAmount()>=minRentalAmount && toy.getRentalAmount()<=maxRentalAmount)
//				res.add(toy);
//		}
//		return res;
		
		return AdminService.searchToysbyRentalAmountRangeService(minRentalAmount, maxRentalAmount);
	}


	@Override
	public ArrayList<Toy> viewCart(Customer cust) {
		// TODO Auto-generated method stub
		return cust.getCustShoppingList();
	}


	@Override
	public long generateBill(Customer cust) {
		
		long bill = 0;
		
		ArrayList<ArrayList<Integer>> cart = AdminService.getCustomerRentalsService(cust.getCustId());
		ArrayList<ToyRental> tr = new ArrayList<>();
		
		for(ArrayList<Integer> rec: cart) {
			
			ToyRental obj = new ToyRental(cust.getCustId(),rec.get(0),LocalDate.now()
					,LocalDate.now().plusDays(1),rec.get(1),rec.get(2),0,"Open");
			tr.add(obj);
		}
		ArrayList<ToyRental> newlist = cust.getCustRentalList();
		newlist.addAll(tr);
		cust.setCustRentalList(newlist);
		ArrayList<Toy> removecart = cust.getCustShoppingList();
		removecart.clear();
		cust.setCustShoppingList(removecart);
		
		for(ArrayList<Integer> rec:cart) {
			bill+=rec.get(2);
		}
		return bill;
	}


	@Override
	public ArrayList<ToyRental> getRentedToys(Customer cust) {
		// TODO Auto-generated method stub
		ArrayList<ToyRental> result = new ArrayList<>();
		ArrayList<ToyRental> data = cust.getCustRentalList();
		for(ToyRental obj: data) {
			if(obj.getStatus().equals("Open")) {
				long datediff = obj.getRentalReturnDate().until(LocalDate.now(), ChronoUnit.DAYS);
				if(datediff>0) {
					obj.setFine((int)(obj.getTotalAmount() * (datediff+1) *10/100));
				}
				result.add(obj);
			}
		}
		
		return result;
	}


	@Override
	public long returnToys(Customer cust, int rentalid) {
		// TODO Auto-generated method stub
		ArrayList<ToyRental> data = cust.getCustRentalList();
		ToyRental t =null;
		for(ToyRental tr : data) {
			if(tr.getRentalId()== rentalid) {
				t = tr;
				tr.setStatus("Closed");
				break;
			}
		}
		cust.setCustRentalList(data);

		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(t.getToyId());
		arr.add(t.getNoofunits());
		arr.add(t.getTotalAmount());
		
		AdminService.updateCustMapService(cust.getCustId(),arr);
		AdminService.updateToyListService(t.getToyId(),t.getNoofunits());
		
		return t.getTotalAmount()+t.getFine();
	}
	
	public ArrayList<ToyRental> viewHistory(Customer cust){
		
		return cust.getCustRentalList();
	}
	
	
	

}
