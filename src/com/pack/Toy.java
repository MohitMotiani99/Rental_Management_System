package com.pack;

import java.io.Serializable;
import java.util.Objects;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//@ToString
//@Getter
//@Setter
//@AllArgsConstructor
public class Toy implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -868474974799985424L;
	private int toyId;
	private String toyName;
	private String toyType;
	private int minAge;
	private int maxAge;
	private int price;
	private int quantitiy;
	private int rentalAmount;
	private int refundDeposit;
	public int getToyId() {
		return toyId;
	}
	public void setToyId(int toyId) {
		this.toyId = toyId;
	}
	public String getToyName() {
		return toyName;
	}
	public void setToyName(String toyName) {
		this.toyName = toyName;
	}
	public String getToyType() {
		return toyType;
	}
	public void setToyType(String toyType) {
		this.toyType = toyType;
	}
	public int getMinAge() {
		return minAge;
	}
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantitiy() {
		return quantitiy;
	}
	public void setQuantitiy(int quantitiy) {
		this.quantitiy = quantitiy;
	}
	public int getRentalAmount() {
		return rentalAmount;
	}
	public void setRentalAmount(int rentalAmount) {
		this.rentalAmount = rentalAmount;
	}
	public int getRefundDeposit() {
		return refundDeposit;
	}
	public void setRefundDeposit(int refundDeposit) {
		this.refundDeposit = refundDeposit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Toy(int toyId, String toyName, String toyType, int minAge, int maxAge, int price, int quantitiy,
			int rentalAmount, int refundDeposit) {
		super();
		this.toyId = toyId;
		this.toyName = toyName;
		this.toyType = toyType;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.price = price;
		this.quantitiy = quantitiy;
		this.rentalAmount = rentalAmount;
		this.refundDeposit = refundDeposit;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maxAge, minAge, price, refundDeposit, rentalAmount, toyId, toyName, toyType);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Toy other = (Toy) obj;
		return maxAge == other.maxAge && minAge == other.minAge && price == other.price && refundDeposit == other.refundDeposit && rentalAmount == other.rentalAmount && toyId == other.toyId
				&& Objects.equals(toyName, other.toyName) && Objects.equals(toyType, other.toyType);
	}
	@Override
	protected Toy clone() throws CloneNotSupportedException {
		
		Toy t=new Toy(this.toyId, this.toyName, this.toyType, this.minAge, this.maxAge, this.price, this.quantitiy, this.rentalAmount, this.refundDeposit);
		return t;
	}
	@Override
	public String toString() {
		return "Toy [toyId=" + toyId + ", toyName=" + toyName + ", toyType=" + toyType + ", minAge=" + minAge
				+ ", maxAge=" + maxAge + ", price=" + price + ", quantitiy=" + quantitiy + ", rentalAmount="
				+ rentalAmount + ", refundDeposit=" + refundDeposit + "]";
	}
		
	
	
}
