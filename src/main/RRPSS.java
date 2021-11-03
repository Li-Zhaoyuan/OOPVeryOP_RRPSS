/**
 description of this class
 @author 
 @version 1.0
 @since 2021-10-20
*/
package main;

import java.util.ArrayList;
import java.util.Scanner;
import menuitem.MenuItemFactory;
import menuitem.MenuItem;
import menuitem.PromotionalSet;

public class RRPSS {
	
	MenuItemFactory  menuItemFactory;
	Order order;
	Scanner sc;
	
	int input;
	/*
	 * initialize all the necessary variables
	 */
	public void init()
	{
		menuItemFactory = new MenuItemFactory();
		
		sc = new Scanner(System.in);
	}
	
	/*
	 * to clear or to save variables before exiting
	 */
	public static void exit()
	{
		
	}
	
	/*
	 * Create/Update/Remove menu item
	 */
	public void option1MenuItemManipulation()
	{
		String inputName, inputDesc;
		Double inputPrice;
		MenuItem tempItem;
		
		System.out.println("Select Number Accordingly (1)Create/(2)Update/(3)Remove menu item/(0)Go Back");
		input = sc.nextInt();
		
		switch(input)
		{
		case 1:
			System.out.println("Create according to number (1)maincourse/(2)sidecourse/(3)dessert/(4)drink");
			input = sc.nextInt();
			
			System.out.println("Enter Name of Dish: ");
			inputName = sc.next();
			System.out.println("Enter Description of Dish: ");
			inputDesc = sc.next();
			System.out.println("Enter Price of Dish: ");
			inputPrice = sc.nextDouble();
			
			if(input == 1)
				menuItemFactory.getItemList().add(menuItemFactory.createMainCourse(inputName,inputDesc,inputPrice));
			else if(input == 2)
				menuItemFactory.getItemList().add(menuItemFactory.createSideCourse(inputName,inputDesc,inputPrice));
			else if(input == 3)
				menuItemFactory.getItemList().add(menuItemFactory.createDessert(inputName,inputDesc,inputPrice));
			else if(input == 4)
				menuItemFactory.getItemList().add(menuItemFactory.createDrink(inputName,inputDesc,inputPrice));
				
			break;
		case 2:
			System.out.println("Enter Name of Dish to Update: ");
			inputName = sc.nextLine();
			tempItem = menuItemFactory.getItem(inputName);
			if(tempItem == null)
			{
				System.out.println("No such dish");
				return;
			}
			System.out.println("Select which information to update (1)name/(2)description/(3)price");
			
			if(input == 1)
			{
				System.out.println("Enter New Name: ");
				inputName = sc.nextLine();
				tempItem.setName(inputName);
			}
			else if(input == 2)
			{
				System.out.println("Enter New Description: ");
				inputDesc = sc.nextLine();
				tempItem.setDescription(inputDesc);
			}
			else if(input == 3)
			{
				System.out.println("Enter New Price: ");
				inputPrice = sc.nextDouble();
				tempItem.setPrice(inputPrice);
			}
			
			break;
		case 3:
			System.out.println("Enter Name of Dish to Delete: ");
			inputName = sc.nextLine();
			tempItem = menuItemFactory.getItem(inputName);
			if(tempItem == null)
			{
				System.out.println("No such dish");
				return;
			}
			menuItemFactory.getItemList().remove(tempItem);
			System.out.println("Dish Removed!");
			break;
		case 0:
		default:
			System.out.println("Back to Main Menu");
			return;	
		}
	}
	
	/*
	 * Create/Update/Remove promotion
	 */
	public void option2PromotionManipulation()
	{
		String inputName, inputDesc, inputDishName, inputPromoSet;
		Double inputPrice;
		int inputQuantity;
		MenuItem tempItem;
		
		ArrayList<String> listOfDishName = new ArrayList<String>();
		
		System.out.println("Select Number Accordingly (1)Create/(2)Update/(3)Remove Promotion/(0)Go Back");
		input = sc.nextInt();
		switch(input)
		{
		case 1:
			System.out.println("Enter the Name of the Promotional Set: ");
			inputName = sc.next();
			System.out.println("Enter the Description of the Promotional Set: ");
			inputDesc = sc.next();
			System.out.println("Enter the Price of the Promotional Set: ");
			inputPrice = sc.nextDouble();
			
			while(true)
			{
				System.out.println("Enter Name of Dish to add to this Promotional Set, Enter(0) to save and go back");
				inputDishName = sc.next();
				
				if(inputDishName.equals("0"))
				{
					menuItemFactory.getItemList().add(menuItemFactory.createPromotionalSet(inputName,inputDesc,inputPrice,listOfDishName));
					System.out.println("Promotional Set Created! Returning to Main Menu ");
					break;
				}
				
				tempItem = menuItemFactory.getItem(inputDishName);
				if(tempItem != null)
				{
					listOfDishName.add(inputDishName);
				}
				else
				{
					System.out.println(inputDishName + " does not exist!");
				}
			}
			break;
		case 2:
			System.out.println("Enter Name of Promotional Set to Update, Enter(0) to save and go back");
			inputPromoSet = sc.next();
			
			tempItem = menuItemFactory.getItem(inputPromoSet);
			
			if(tempItem == null || tempItem instanceof PromotionalSet)
			{
				System.out.println("Promotional Set does not exist!");
				break;
			}
			
			while(true)
			{
				System.out.println("Current Promotional Set = " + tempItem.getName());
				System.out.println("Enter number to update (1)Promotional Set's name, (2)Promotional Set's Decription, (3)Promotional Set's Price, ");
				System.out.println("(4)Add Dish, (5)Remove Dish, (0)to save and go back");
				input = sc.nextInt();
				
				if(input == 0)
				{
					System.out.println("Returning back to main menu...");
					break;
				}
				else if(input == 1)
				{
					System.out.println("Enter new name for selected Promotional Set: ");
					inputPromoSet = sc.next();
					tempItem.setName(inputPromoSet);
					System.out.println("New name set!");
				}
				else if(input == 2)
				{
					System.out.println("Enter new Decription for selected Promotional Set: ");
					inputDesc = sc.next();
					tempItem.setDescription(inputDesc);
					System.out.println("New Decription set!");
				}
				else if(input == 3)
				{
					System.out.println("Enter new Price for selected Promotional Set: ");
					inputPrice = sc.nextDouble();
					tempItem.setPrice(inputPrice);
					System.out.println("New Price set!");
				}
				else if(input == 4)
				{
					System.out.println("Enter Name of existing Dish to be added to the Promotional Set: ");
					inputName = sc.next();
					MenuItem tempItem2;
					tempItem2 = menuItemFactory.getItem(inputName);
					if(tempItem2 == null)
					{
						System.out.println("Dish does not exist!");
						break;
					}
					System.out.println("Enter Quantity of existing Dish to be added to the Promotional Set: ");
					inputQuantity = sc.nextInt();
					((PromotionalSet)tempItem).addItems(tempItem2,inputQuantity);
					System.out.println("Dish Added!!");
				}
				else if(input == 5)
				{
					System.out.println("Enter Name of existing Dish to be removed from the Promotional Set: ");
					inputName = sc.next();
					MenuItem tempItem2;
					tempItem2 = menuItemFactory.getItem(inputName);
					if(tempItem2 == null || ((PromotionalSet)tempItem).getItems().get(tempItem2) == null)
					{
						System.out.println("Dish does not exist!");
						break;
					}
					
					((PromotionalSet)tempItem).removeItems(tempItem2);
					System.out.println("Dish Removed!!");
				}
			}
			
			break;
		case 3:
			System.out.println("Enter Name of Promotion to Delete: ");
			inputName = sc.next();
			tempItem = menuItemFactory.getItem(inputName);
			
			if(tempItem == null || tempItem instanceof PromotionalSet)
			{
				System.out.println("Promotional Set does not exist!");
				break;
			}
			menuItemFactory.getItemList().remove(tempItem);
			System.out.println("Promotion Set Removed!!");
			break;
		case 0:
		default:
			System.out.println("Back to Main Menu");
			return;	
		}
	}
	
	/*
	 * Create order
	 */
	public void option3OrderCreation()
	{
		
	}
	
	/*
	 * View order
	 */
	public void option4ViewOrder()
	{
		
	}
	
	/*
	 * Add/Remove order item/s to/from order
	 */
	public void option5UpdateItemsToOrder()
	{
		
	}
	
	/*
	 * Create reservation booking
	 */
	public void option6ReservationBookingCreation()
	{
		
	}
	
	/*
	 * Check/Remove reservation booking
	 */
	public void option7ReservationChecking()
	{
		
	}
	
	/*
	 * Check table availability
	 */
	public void option8TableAvailability()
	{
		
	}
	
	/*
	 * Print order invoice
	 */
	public void option9PrintOrderInvoice()
	{
		if(order == null)
		{
			System.out.println("Please Create an order first before printing the invoice!");
			return;
		}
		System.out.println("Printing current order's invoice! \n");
		order.printOrderInvoice();
	}
	
	/*
	 * Print sale revenue report by period (eg day or month)
	 */
	public void option10PrintSaleRevenueReport()
	{
		
	}
	
	
}
