/**
 description of this class
 @author 
 @version 1.0
 @since 2021-10-20
*/
package main;

import java.util.Scanner;

public class RRPSS {
	public void option1MenuItemManipulation()
	{
		
	}
	
	public void option2PromotionManipulation()
	{
		
	}
	
	public void option3OrderCreation()
	{
		
	}
	
	public void option4ViewOrder()
	{
		
	}
	
	public void option5UpdateItemsToOrder()
	{
		
	}
	
	public void option6ReservationBookingCreation()
	{
		
	}
	
	public void option7ReservationChecking()
	{
		
	}
	
	public void option8TableAvailability()
	{
		
	}
	
	public void option9PrintOrderInvoice()
	{
		
	}
	
	public void option10PrintSaleRevenueReport()
	{
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int input;
		System.out.println("Welcome to the Restaurant Reservation and Point of Sale System (RRPSS)!");
	
		while(true)
		{
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
			input = sc.nextInt();
			
			if(input >= 11)
			{
				System.out.println("Terminating Programming");
					break;
			}
				
			switch(input)
			{
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			}
		}
	}
}
