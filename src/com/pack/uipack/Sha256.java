package com.pack.uipack;

import java.security.MessageDigest;

public class Sha256 {
	private final String base;
	private String hexVal;
	
	
	public Sha256(String base,String hexVal) {
		super();
		this.base = base;
		this.hexVal= hexVal;
		sha256(hexVal);
	}

	private String sha256(String hexVal) {
		try{
	        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        final byte[] hash = digest.digest(hexVal.getBytes("UTF-8"));
	        final StringBuilder hexString = new StringBuilder();
	        for (int i = 0; i < hash.length; i++) {
	            final String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) 
	              hexString.append('0');
	            hexString.append(hex);
	        }
	       this.hexVal=hexString.toString();
	       return hexVal;
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
	
	
    
	public String getHexVal() {
		return hexVal;
	}
	public String getBase() {
		return base;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Sha256 s1=(Sha256)obj;
		if(base.compareTo(s1.getBase())==0 && hexVal.compareTo(s1.getHexVal())==0)
				return true;
		else 
			return false;
	}

	@Override
	public String toString() {
		return "Sha256 [base=" + base + ", hexVal=" + hexVal + "]";
	}

	
	
		

}