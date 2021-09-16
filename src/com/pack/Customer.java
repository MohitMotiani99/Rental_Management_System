package com.pack;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4605096589342585419L;
	private int custId;
	private String custName;
	private String email;
	private String pass;
    private  Address address;
	private  ArrayList<Toy> custShoppingList ;
	private ArrayList<ToyRental> custRentalList;

	
	public Customer(int custId, String custName, String email, String pass, Address address)  {
		super();
		this.custId = custId;
		this.custName = custName;
		this.email = email;
		this.pass = pass;
		this.address = address;
		custShoppingList = new ArrayList<Toy>();
		this.custRentalList = new ArrayList<ToyRental>();
	}
	

	public int getCustId() {
		return custId;
	}



	public String getCustName() {
		return custName;
	}



	public String getEmail() {
		return email;
	}



	public String getPass() {
		return pass;
	}



	public Address getAddress() {
		return address;
	}



	public void setCustId(int custId) {
		this.custId = custId;
	}



	public void setCustName(String custName) {
		this.custName = custName;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public void setPass(String pass) {
		this.pass = pass;
	}



	public void setAddress(Address address) {
		this.address = address;
	}


	

	public ArrayList<Toy> getCustShoppingList() {
		return custShoppingList;
	}


	public void setCustShoppingList(ArrayList<Toy> custShoppingList) {
		this.custShoppingList = custShoppingList;
	}


	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", email=" + email + ", pass=" + pass
				+ ", address=" + address + "]";
	}


	public ArrayList<ToyRental> getCustRentalList() {
		return custRentalList;
	}


	public void setCustRentalList(ArrayList<ToyRental> custRentakList) {
		this.custRentalList = custRentakList;
	}
    
//	abstract String rent(int toyId,int units); // Add details of rented toy
//	abstract  Toy getToy(int toyId);
//	abstract  String searchToys(int toyId);

//	
//	abstract void viewCart();
//	abstract String deleteFromCart(int toyId);
//	abstract float getCustomerBill();

	
	
}