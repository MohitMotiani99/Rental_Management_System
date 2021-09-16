OOPS
	Abstraction
	Encapsulation
	Abstract Classes
	Interfaces
	Inheritance
	Aggregation
	Overriding

Exception Handling
	Handled Checked Exceptions:-FileNotFoundException,IOException,CloneNotSupportedException etc.
	Handled Unchecked Exceptions:- NullPointerException etc..
	User Defined Exceptions:- ToyNotFoundException,DuplicateToyException etc.

File handling
	2 databases ToyDB and CustomerDB in different files Toy.dat & Customer.dat respectively
	We have accessed the current working directory dynamically using System.getProperties which takes user.dir
	as the argument to access present working directory and there we create our files

	Note:-
		To persist changes in any database Toy or Customer press 0 to exit and answer as "no"

Java 8 LocalTime LocalDate API

Java 8 Functional Programming
	Stream API
	Lambda Expressions
	Predicates
	Functional Interface

Data Structures
	1D ArrayList
	2D ArrayList
	HashMap

UI
	Menu Driven
	Admin Dashboard
	Customer Dashboard

Project Structure
	Service Classes
	Implementation Classes
	Operation Classes
	Entity Classes

Main Functionalities
	Admin
		Add a new or existing toy
		Update toy info based on multiple parameters
		Remove the toy completely or by giving units also
		Search toy based multiple parameters
		Can get Inventory value & rental Income
	Customer
		Rent a toy with as many units only bounded by inventory
		Remove toys from the current shopping cart
		Return toys 
		Bill generation
		Fine Generation
		View current profile
		View rental history
		Search functions as above
	Data Persisted in files	
	Exceptions handled acc to use cases
