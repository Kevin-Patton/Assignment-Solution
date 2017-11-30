package ie.lyit.testers;
import java.util.Scanner;
import ie.lyit.serialize.CustomerSerializer;

public class CustomerSerializerTester{

	public static void main(String[] args) {
		CustomerSerializer customerSerializer = new CustomerSerializer();
		Scanner keyIn = new Scanner(System.in);
		
		

		int option;
		
		//menu
		do {
			//read the old file in before each loop
			customerSerializer.readRecordsFromFile();
			
			System.out.println("\nHOTEL MENU:\nEnter an option: ");
			System.out.println("1: Add");
			System.out.println("2. View");
			System.out.println("3: Edit");
			System.out.println("4. Delete");
			System.out.println("5: List");
			System.out.println("6. Quit");
			option = keyIn.nextInt();
			

			// Add a record to the ArrayList
			if(option == 1)
			{
				customerSerializer.add();
			}
			
			else if(option == 2)
			{
				customerSerializer.view();
			}
			
			else if(option == 3)
			{
				customerSerializer.edit();
			}
			
			else if(option == 4)
			{
				customerSerializer.delete();
			}
			
			else if(option == 5)
			{
				customerSerializer.list();
			}
			
			else if(option == 6)
			{
				System.out.println("Goodbye");
			}
			
			else
			{
				System.out.println("Invalid option: Enter another option");
			}	
			
			// Write the ArrayList to File at the end of each loop
			customerSerializer.writeRecordsToFile();

		}while(option!=6);
		
			
		
		
	}
}