package com.pack;

public class ElectronicToy extends Toy{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numberOfbatteries;
	private String operatingMode;

	

	public ElectronicToy(int toyId, String toyName, String toyType, int minAge, int maxAge, int price, int quantitiy,
			int rentalAmount, int refundDeposit, int numberOfbatteries, String operatingMode) {
		super(toyId, toyName, toyType, minAge, maxAge, price, quantitiy, rentalAmount, refundDeposit);
		this.numberOfbatteries = numberOfbatteries;
		this.operatingMode = operatingMode;
	}

	public int getNumberOfbatteries() {
		return numberOfbatteries;
	}

	public void setNumberOfbatteries(int numberOfbatteries) {
		this.numberOfbatteries = numberOfbatteries;
	}

	public String getOperatingMode() {
		return operatingMode;
	}

	public void setOperatingMode(String operatingMode) {
		this.operatingMode = operatingMode;
	}


	
	
}
