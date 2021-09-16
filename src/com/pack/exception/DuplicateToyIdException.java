package com.pack.exception;

public class DuplicateToyIdException extends Exception{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Duplicate ToyID Found";
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		System.out.println(getMessage());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885073965232412876L;

}
