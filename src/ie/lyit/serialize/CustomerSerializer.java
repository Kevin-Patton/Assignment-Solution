package ie.lyit.serialize;

import java.util.ArrayList;
import ie.lyit.hotel.*;
import java.util.Scanner;
import java.io.*;


@SuppressWarnings("serial")
public class CustomerSerializer implements Serializable{
	private ArrayList<Customer> customers;

	private final String FILENAME = "customers.ser";	

	// Default Constructor
	public CustomerSerializer(){
		// Construct CustomerList ArrayList
		customers = new ArrayList<Customer>();
	}	

	public void add(){
		// Create a Customer object
		Customer customer = new Customer();
		// Read its details
		customer.read();	
		// And add it to the customers ArrayList
		customers.add(customer);
	}

	public void list(){
		// for every Customer object in customers
		for(Customer tmpCustomer:customers)
		{
			// display it
			System.out.println(tmpCustomer);
		}

	}

	public Customer view(){
		Scanner keyboard = new Scanner(System.in);		

		// Read the number of the customer to be viewed from the user
		System.out.print("ENTER NUMBER OF CUSTOMER : ");
		int customerToView=keyboard.nextInt();
		// for every customer object in customer
		for(Customer tmpCustomer:customers){
			// if it's number equals the number of the customerToView
			if(tmpCustomer.getNumber() == customerToView){
				// display it
				System.out.println(tmpCustomer);
				return tmpCustomer;
			}
		}

		// if we reach this code the customer was not found so return null
		System.out.println("Cannot find customer: Customer number does not exist!\n");
		return null;
	}

	public void delete(){	
		// Call view() to find, display, & return the customer to delete
		Customer tempCustomer = view();
		// If the customer != null, i.e. it was found then...
		if(tempCustomer != null)
			// ...remove it from customers
			System.out.println("Customer Removed");
		customers.remove(tempCustomer);
	}


	public void edit(){	
		// Call view() to find, display, & return the customer to edit
		Customer tempCustomer = view();
		// If the book != null, i.e. it was found then...
		if(tempCustomer != null){
			// get it's index
			int index=customers.indexOf(tempCustomer);
			// read in a new book and...
			tempCustomer.read();
			// reset the object in books
			System.out.println("Customer appended to have the details above^");
			customers.set(index, tempCustomer);
		}
	}

	// This method will serialize the customers ArrayList when called, 
	// i.e. it will write it to a file called customers.ser
	public void writeRecordsToFile(){
		ObjectOutputStream os=null;
		try {
			// Serialize the ArrayList...
			FileOutputStream fileStream = new FileOutputStream(FILENAME);

			os = new ObjectOutputStream(fileStream);

			os.writeObject(customers);
		}
		catch(FileNotFoundException fNFE){
			System.out.println("Cannot create file to store customers.");
		}
		catch(IOException ioE){
			System.out.println(ioE.getMessage());
		}
		finally {
			try {
				os.close();
			}
			catch(IOException ioE){
				System.out.println(ioE.getMessage());
			}
		}
	}

	// This method will deserialize the customers ArrayList when called, 
	// i.e. it will restore the ArrayList from the file customers.ser
	@SuppressWarnings("unchecked")
	public void readRecordsFromFile(){
		ObjectInputStream is=null;

		try {
			// Deserialize the ArrayList...
			FileInputStream fileStream = new FileInputStream(FILENAME);

			is = new ObjectInputStream(fileStream);

			customers = (ArrayList<Customer>)is.readObject();	
		}
		catch(FileNotFoundException fNFE){
			System.out.println("Cannot create file to store customers.");
		}
		catch(IOException ioE){
			System.out.println(ioE.getMessage());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally {
			try {
				is.close();
			}
			catch(IOException ioE){
				System.out.println(ioE.getMessage());
				
			}
		}
		
		//Code for reading the previous customer number when file is read
		int highestNo = 0;
		for(Customer tmpCustomer : customers)
		{	
			
			if(highestNo < tmpCustomer.getNumber())
			{	
				//if the customers arrayList is completely empty the first number will be 1
				//Note:Will not reallocate numbers, only works when arrayList is empty
				if(customers.isEmpty())
				{
					highestNo = 0;
				}
				//else the next customer entered will have the customer number 1 higher 
				//than the last customer in the list
				else
				{
					highestNo = tmpCustomer.getNumber();
				}
			}
		}
		//increments the highest number
		highestNo++;
		
		//sets the nextNumber in the customer class to the number found above
		Customer.setNextNumber(highestNo);
	}
}