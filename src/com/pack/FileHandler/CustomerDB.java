package com.pack.FileHandler;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.pack.Customer;

public class CustomerDB {
	private static String path;
	public static ArrayList<Customer> clist=new ArrayList<Customer>();

	public static void SetPath(String file_name) {
		String working_dir = System.getProperty("user.dir");
		working_dir = working_dir.replace("\\","/");

		path = working_dir +"/"+ file_name;
	}

	//get all data of the file

	public static void getAllData() throws EOFException{
		
		SetPath("Customers.dat");
		
		ArrayList<Customer> arr= new ArrayList<Customer>();
		File f = new File(path);

		if (!f.exists()) {
			return;
		}
		try(FileInputStream input = new FileInputStream(path);
				ObjectInputStream in = new ObjectInputStream(input);) {
			while(input.available()>0) {
				Customer user= (Customer) in.readObject();
				arr.add(user);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		clist=arr;
	}

	// add data to the file
	
	public static void add() {
		
		SetPath("Customers.dat");
		
		try(FileOutputStream output = new FileOutputStream(path);
				ObjectOutputStream out = new ObjectOutputStream(output);) 
		{
			for(Customer u : clist)
				out.writeObject(u);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
