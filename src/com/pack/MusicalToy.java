package com.pack;

public class MusicalToy extends Toy{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int noofspeakers;



	public MusicalToy(int toyId, String toyName, String toyType, int minAge, int maxAge, int price, int quantitiy,
			int rentalAmount, int refundDeposit, int noofspeakers) {
		super(toyId, toyName, toyType, minAge, maxAge, price, quantitiy, rentalAmount, refundDeposit);
		this.noofspeakers = noofspeakers;
	}

	public int getNoofspeakers() {
		return noofspeakers;
	}

	public void setNoofspeakers(int noofspeakers) {
		this.noofspeakers = noofspeakers;
	}

	

	
}
