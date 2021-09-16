package com.pack.FileHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.pack.Toy;

public class ToyDB {
	private static String path;
	public static ArrayList<Toy> toylist=new ArrayList<Toy>();

	public static void SetPath(String file_name) {
		String working_dir = System.getProperty("user.dir");
		working_dir = working_dir.replace("\\","/");

		path = working_dir +"/"+ file_name;
	}

	//get all data of the file

	public static void getAllData(){
		
		SetPath("Toy.dat");
		ArrayList<Toy> arr= new ArrayList<Toy>();

		try(FileInputStream input = new FileInputStream(path);
				ObjectInputStream in = new ObjectInputStream(input);) {
			while(input.available()>0) {
				Toy user= (Toy) in.readObject();
				arr.add(user);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		toylist= arr;
		
	}

	// add data to the file
	
	public static void add() {

		SetPath("Toy.dat");
		try(FileOutputStream output = new FileOutputStream(path);
				ObjectOutputStream out = new ObjectOutputStream(output);) 
		{
			for(Toy u : toylist)
				out.writeObject(u);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
