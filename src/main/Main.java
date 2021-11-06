/**
 Main class which is holding the main function to execute the program
 @author Zhaoyuan
 @version 1.0
 @since 2021-11-03
*/
package main;

import java.util.Scanner;

public class Main {
	/**
	 * Main Function to run the main logic of the program
	 */
	public static void main(String[] args) {
		//Scanner sc = new Scanner(System.in);
		RRPSS rrpss = new RRPSS();
		
		int input;
		
		rrpss.init();
		
		System.out.println("Welcome to the Restaurant Reservation and Point of Sale System (RRPSS)!");
	
		while(true)
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the number corresponding to the choices below:");
			System.out.println("1. Create/Update/Remove menu item");
			System.out.println("2. Create/Update/Remove promotion");
			System.out.println("3. Create order");
			System.out.println("4. View order");
			System.out.println("5. Add/Remove order item/s to/from order");
			System.out.println("6. Create reservation booking");
			System.out.println("7. Check/Remove reservation booking");
			System.out.println("8. Check table availability");
			System.out.println("9. Print order invoice");
			System.out.println("10. Print sale revenue report by period (eg day or month)");
			System.out.println("11. Exit");
			try
			{
				input = sc.nextInt();
				
				//sc.close();
				
				if(input >= 11)
				{
					System.out.println("Terminating Programming");
					sc.close();
					break;
				}
					
				switch(input)
				{
				case 1:
					rrpss.option1MenuItemManipulation();
					break;
				case 2:
					rrpss.option2PromotionManipulation();
					break;
				case 3:
					rrpss.option3OrderCreation();
					break;
				case 4:
					rrpss.option4ViewOrder();
					break;
				case 5:
					rrpss.option5UpdateItemsToOrder();
					break;
				case 6:
					rrpss.option6ReservationBookingCreation();
					break;
				case 7:
					rrpss.option7ReservationChecking();
					break;
				case 8:
					rrpss.option8TableAvailability();
					break;
				case 9:
					rrpss.option9PrintOrderInvoice();
					break;
				case 10:
					rrpss.option10PrintSaleRevenueReport();
					break;
				}
			}
			catch(Exception e)
			{
				System.out.println("\nInvalid Input Detected, Error Occurred. Returning to Main Menu...\n");
			}
		}
		
		rrpss.exit();
	}
}
