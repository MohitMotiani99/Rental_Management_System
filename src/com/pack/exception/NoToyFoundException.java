package com.pack.exception;

public class NoToyFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5155915556055195647L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid ToyID";
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		System.out.println(getMessage());
	}
	
}
