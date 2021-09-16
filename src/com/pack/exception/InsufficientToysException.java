package com.pack.exception;

public class InsufficientToysException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -460343958258749747L;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Insufficient Toys in inventory";
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		System.out.println(getMessage());
	}
}
