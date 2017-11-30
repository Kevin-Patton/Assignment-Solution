package ie.lyit.hotel;
import java.io.Serializable;
//import javax.swing.JOptionPane; ** No need for swing now, was trying GUI but didnt work**
//import javax.swing.JTextField; ** No need for swing now, was trying GUI but didnt work**


import java.util.Scanner;

public class Customer extends Person implements Serializable{// INHERITANCE - Customer IS-A Person
	// Customer has name, address, & phoneNumber from Person
	private String emailAddress;    // AND emailAddress
	private int number;			    // AND number
	
	private static int nextNumber=1;// static for unique number - starts off at 1

	
	
	// Default Constructor
	// Called when object is created like this ==> 
	//   Customer cObj = new Customer();	
	public Customer(){
		super();			// NOTE:Don't need to call super() explicitly.
		emailAddress=null;
		// Set number to static nextNumber before incrementing nextNumber
		number=nextNumber++;
	}
	
	// Initialization Constructor
	// Called when object is created like this ==>
	// Customer cObj = new Customer("Mr","Joe","Doe","12 Hi Rd,Letterkenny",
	//                              "0871234567","joe@hotmail.com");
	public Customer(String t, String fN, String sn, String address, 
			        String pNo, String email){
		// Call super class constructor - Passing parameters required by Person ONLY!
		super(t, fN, sn, address, pNo);
		// And then initialise Customers own instance variables
		emailAddress=email;
		// And finally set number to the highestNumber
		number=nextNumber++;
	}

	// OVERRIDING the Person toString() method!
	// Calling Persons toString() method, and adding additional bits
	@Override
	public String toString(){
		return "\nCustomer Number: " + number + "\n" + super.toString() + "\n" + emailAddress;
	}

	// equals() method
	// ==> Called when comparing an object with another object, 
	//     e.g. - if(c1.equals(c2))				
	// ==> Probably sufficient to compare customer numbers as they're unique
	@Override
	public boolean equals(Object obj){
		Customer cObject;
		if (obj instanceof Customer)
		   cObject = (Customer)obj;
		else
		   return false;
		   
	    return(this.number==cObject.number);
	}

	// set() and get() methods
	public void setEmailAddress(String emailAddress){
		this.emailAddress=emailAddress;
	}
	public String getEmailAddress(){
		return emailAddress;
	}	
	// You shouldn't be able to setNumber() as it is unique, 
	// so don't provide a setNumber() method
	public int getNumber(){
		return number;
	}	
	
	//While number should not have a setNumber(), this code is used in the 
	//serializer so when the program closes the numbers continue from the last one
	public static void setNextNumber(int newNumber)
	{
		nextNumber = newNumber;
	}
	
	// read() - To read a customer from the user	
	public void read(){
//		JTextField txtCustomerNo = new JTextField();
//		txtCustomerNo.setText("" + this.getNumber());
//		
//		JTextField txtTitle = new JTextField();
//		JTextField txtFirstName = new JTextField();
//		JTextField txtLastName = new JTextField();
//	    JTextField txtAddress = new JTextField();
//	    JTextField txtPhoneNo = new JTextField();
//	    JTextField txtEmail = new JTextField();
//
//		Object[] message = {
//				"Customer Number: ", txtCustomerNo,
//				"Customer Title: ", txtTitle, 
//				"Customer First Name", txtFirstName,
//				"Customer Last Name", txtLastName,
//				"Customer Address: ", txtAddress,
//				"Customer PhoneNumber: ", txtPhoneNo,
//				"Customer Email: ", txtEmail,
//		};
//		
//		int option = JOptionPane.showConfirmDialog(null, message, "Enter customer details", JOptionPane.OK_CANCEL_OPTION);
//
//	      if (option == JOptionPane.OK_OPTION){
//	    	  this.number = Integer.parseInt(txtCustomerNo.getText());
//	    	  this.name.setTitle(txtTitle.getText());
//	    	  this.name.setFirstName(txtFirstName.getText()); 
//	    	  this.name.setSurname(txtLastName.getText());
//	          this.address = txtAddress.getText();
//	          this.phoneNumber= txtPhoneNo.getText();
//	          this.emailAddress= txtEmail.getText();
//	      }   
		
		//NOTE: Tried to read in from a GUI, worked fine until Menu so opted for console input instead
		
		
		Scanner keyIn = new Scanner(System.in);
		//Read in all details of a customer
		
		//do while loop to not allow an invalid title
		do {
			System.out.print("\nEnter Customer Title: ");
			this.name.setTitle(keyIn.nextLine());
			
			if(!this.name.getTitle().equalsIgnoreCase("Ms") && !this.name.getTitle().equalsIgnoreCase("Mrs") && 
					!this.name.getTitle().equalsIgnoreCase("Mr"))
			{
				System.out.println("Invalid title: Enter Mr, Ms or Mrs as title!");
			}
		}while(!this.name.getTitle().equalsIgnoreCase("Ms") && !this.name.getTitle().equalsIgnoreCase("Mrs") && 
				!this.name.getTitle().equalsIgnoreCase("Mr"));
		
		System.out.print("\nEnter Customer First Name: ");
		this.name.setFirstName(keyIn.nextLine());
		System.out.print("\nEnter Customer Surname: ");
		this.name.setSurname(keyIn.nextLine());
		System.out.print("\nEnter Customer Address: ");
		this.address = keyIn.nextLine();
		System.out.print("\nEnter Customer Phone Number: ");
		this.phoneNumber = keyIn.nextLine();
		System.out.print("\nEnter Customer Email Address: ");
		this.emailAddress = keyIn.nextLine();
		
		}
}
