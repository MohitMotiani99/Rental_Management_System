package com.pack.exception;

public class InsufficientToystoDeleteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3987041569299200867L;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Insufficient Toys to delete";
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		System.out.println(getMessage());
	}
}
