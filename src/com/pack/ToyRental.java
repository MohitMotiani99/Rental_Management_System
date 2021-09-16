package com.pack;

import java.io.Serializable;
import java.time.LocalDate;


public class ToyRental implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int counter =1;
	private final int rentalId;
	private int custId;
	private int toyId;
	private LocalDate rentalDate;
	private LocalDate rentalReturnDate;
	private int noofunits;
	private int totalAmount;
	private int fine;
	private String status;
	
	public ToyRental( int custId, int toyId, LocalDate rentalDate, LocalDate rentalReturnDate,
			int noofunits,int totalAmount, int fine, String status) {
		super();
		this.rentalId = counter++;
		this.custId = custId;
		this.toyId = toyId;
		this.rentalDate = rentalDate;
		this.rentalReturnDate = rentalReturnDate;
		this.noofunits = noofunits;
		this.totalAmount = totalAmount;
		this.fine = fine;
		this.status = status;
	}
	public int getNoofunits() {
		return noofunits;
	}
	public void setNoofunits(int noofunits) {
		this.noofunits = noofunits;
	}
	public int getRentalId() {
		return rentalId;
	}
	
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public int getToyId() {
		return toyId;
	}
	public void setToyId(int toyId) {
		this.toyId = toyId;
	}
	public LocalDate getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}
	public LocalDate getRentalReturnDate() {
		return rentalReturnDate;
	}
	public void setRentalReturnDate(LocalDate rentalReturnDate) {
		this.rentalReturnDate = rentalReturnDate;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
	public String isStatus() {
		return status;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ToyRental [rentalId=" + rentalId + ", custId=" + custId + ", toyId=" + toyId + ", rentalDate="
				+ rentalDate + "noofunits=" + noofunits+", rentalReturnDate=" + rentalReturnDate + ", totalAmount=" + totalAmount + ", fine="
				+ fine + ", status=" + status + "]";
	}
	
	
}
