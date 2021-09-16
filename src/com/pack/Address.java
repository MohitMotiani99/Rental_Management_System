package com.pack;

import java.io.Serializable;

public class Address implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5209065184103506933L;
	private String city;
	private String state;
	private String country;
	private int zip;
	public Address(String city, String state, String country, int zip) {
		super();
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
	}
	@Override
	public String toString() {
		return "Address [city=" + city + ", state=" + state + ", country=" + country + ", zip=" + zip + "]";
	}
	
	

}