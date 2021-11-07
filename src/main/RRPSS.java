/**
 This class holds the functions that will perform operations depending on the different options.
 @author 
 @version 1.0
 @since 2021-10-20
*/
package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import main.Discount.discountType;

import java.util.Calendar;
import menuitem.MenuItemFactory;
import menuitem.MenuItem;
import menuitem.PromotionalSet;
import salerevenuereport.GenerateReport;

public class RRPSS {
	
	/**
	 * variable holding the MenuItemFactory object
	 */
	MenuItemFactory  menuItemFactory;
	/**
	 * variable holding the Staff object
	 */
	Staff currStaff;
	/**
	 * variable holding the Order object
	 */
	Order order;
	/**
	 * variable to hold the integer input from the user
	 */
	int input;
	
	/**
	 * Function to initialize all the necessary variables
	 */
	public void init()
	{
		Scanner sc = new Scanner(System.in);
		String inputName, inputGender, inputJobTitle;
		int inputEmployeeID;
		
		menuItemFactory = new MenuItemFactory();
		System.out.println(">>>Enter Staff Details<<<\n");
		System.out.println("Enter Staff Name: ");
		inputName = sc.nextLine();
		System.out.println("Enter Staff Gender: ");
		inputGender = sc.nextLine();
		System.out.println("Enter Staff Job Title: ");
		inputJobTitle = sc.nextLine();
		System.out.println("Enter Staff Employee ID: ");
		inputEmployeeID = sc.nextInt();
		
		currStaff = new Staff(inputName,inputGender,inputJobTitle,inputEmployeeID);
	}
	
	/**
	 * Function to clear or to save variables before exiting
	 */
	public void exit()
	{
		menuItemFactory.updateCSV();
	}
	
	/**
	 * Function to Print all available A La Carte Menu Items
	 */
	public void PrintAllALaCarteMenuItems()
	{
		System.out.println("\n**********List of A La Carte Menu Items**********");
		for (MenuItem i : menuItemFactory.getItemList()) 
		{
			if(i instanceof PromotionalSet)
				continue;
			System.out.println("\nName Of Menu Item: " + i.getName());
			System.out.println("Description Of Menu Item: " + i.getDescription());
			System.out.println("Price Of Menu Item: $" + i.getPrice());
			
		}
		System.out.println("\n**********End of list**********\n");
	}
	
	/**
	 * Function to Print all available Promotional Set Menu Items
	 */
	public void PrintAllPromotionalSet()
	{
		System.out.println("\n**********List of Promotional Sets**********");
		for (MenuItem i : menuItemFactory.getItemList()) 
		{
			if(!(i instanceof PromotionalSet))
				continue;
			System.out.println("\nName Of Promotional Set: " + i.getName());
			System.out.println("Description Of Promotional Set: " + i.getDescription());
			System.out.println("Price Of Promotional Set: $" + i.getPrice());
			
			System.out.println("\n	**********List of Menu Items in Promotional Set**********");
			for(MenuItem item : ((PromotionalSet)i).getItems().keySet()) {
				System.out.println("\n	Name Of Menu Item: " + item.getName());
				System.out.println("	Description Of Menu Item: " + item.getDescription());
				System.out.println("	Quantity Of Menu Item: " + ((PromotionalSet)i).getItems().get(item));
				//System.out.println("Price Of Promotional Set: $" + i.getPrice());
			}
			System.out.println("\n	**********End of list**********\n");
		}
		System.out.println("\n**********End of list**********\n");
	}
	
	/**
	 * Function to perform operations regarding the 1st option
	 * Create/Update/Remove menu item
	 */
	public void option1MenuItemManipulation()
	{
		System.out.println("\n>>>Create/Update/Remove menu item<<<\n");
		PrintAllALaCarteMenuItems();
		Scanner sc = new Scanner(System.in);
		String inputName, inputDesc;
		Double inputPrice;
		MenuItem tempItem;
		
		System.out.println("\nSelect Number Accordingly (1)Create/(2)Update/(3)Remove menu item/(0)Go Back");
		input = sc.nextInt();
		sc.nextLine();
		switch(input)
		{
		case 1:
			System.out.println("\nCreate according to number (1)maincourse/(2)sidecourse/(3)dessert/(4)drink");
			input = sc.nextInt();
			sc.nextLine();
			System.out.println("\nEnter Name of Menu Item: ");
			inputName = sc.nextLine();
			
			System.out.println("\nEnter Description of Menu Item: ");
			inputDesc = sc.nextLine();
			System.out.println("\nEnter Price of Menu Item: ");
			inputPrice = sc.nextDouble();
			
			if(input == 1)
				menuItemFactory.createMainCourse(inputName,inputDesc,inputPrice);
			else if(input == 2)
				menuItemFactory.createSideCourse(inputName,inputDesc,inputPrice);
			else if(input == 3)
				menuItemFactory.createDessert(inputName,inputDesc,inputPrice);
			else if(input == 4)
				menuItemFactory.createDrink(inputName,inputDesc,inputPrice);
			
			System.out.println("Menu Item added! Back to Main Menu...\n");	
			break;
		case 2:
			System.out.println("Enter Name of Menu Item to Update: ");
			inputName = sc.nextLine();
			tempItem = menuItemFactory.getItem(inputName);
			
			if(tempItem == null)
			{
				System.out.println("No such Menu Item, Back to Main Menu...\n");
				return;
			}
			
			//menuItemFactory.getItemList().remove(tempItem);
			
			System.out.println("Select which information to update (1)name/(2)description/(3)price");
			input = sc.nextInt();
			sc.nextLine();
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
			System.out.println("Menu Item updated! Back to Main Menu...\n");	
			break;
		case 3:
			System.out.println("Enter Name of Menu Item to Delete: ");
			inputName = sc.nextLine();
			tempItem = menuItemFactory.getItem(inputName);
			if(tempItem == null)
			{
				System.out.println("No such Menu Item, Back to Main Menu...\n");
				break;
			}
			menuItemFactory.getItemList().remove(tempItem);
			System.out.println("Menu Item Removed! Back to Main Menu...\n");
			break;
		case 0:
		default:
			System.out.println("Back to Main Menu...\n");
			break;	
		}
		//sc.close();
	}
	
	/**
	 * Function to perform operations regarding the 2nd option
	 * Create/Update/Remove promotion
	 */
	public void option2PromotionManipulation()
	{
		System.out.println("\n>>>Create/Update/Remove promotion<<<\n");
		PrintAllPromotionalSet();
		Scanner sc = new Scanner(System.in);
		String inputName, inputDesc, inputDishName, inputPromoSet;
		Double inputPrice;
		int inputQuantity;
		MenuItem tempItem;
		
		ArrayList<String> listOfDishName = new ArrayList<String>();
		
		System.out.println("Select Number Accordingly (1)Create/(2)Update/(3)Remove Promotion/(0)Go Back");
		input = sc.nextInt();
		sc.nextLine();
		switch(input)
		{
		case 1:
			System.out.println("Enter the Name of the Promotional Set: ");
			inputName = sc.nextLine();
			System.out.println("Enter the Description of the Promotional Set: ");
			inputDesc = sc.nextLine();
			System.out.println("Enter the Price of the Promotional Set: ");
			inputPrice = sc.nextDouble();
			sc.nextLine();
			while(true)
			{
				System.out.println("Enter Name of Menu Item to add to this Promotional Set, Enter(0) to save and go back");
				inputDishName = sc.nextLine();
				
				if(inputDishName.equals("0"))
				{
					menuItemFactory.createPromotionalSet(inputName,inputDesc,inputPrice,listOfDishName);
					System.out.println("Promotional Set Created! Back to Main Menu...\n");
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
			inputPromoSet = sc.nextLine();
			
			tempItem = menuItemFactory.getItem(inputPromoSet);
			
			if(tempItem == null || !(tempItem instanceof PromotionalSet))
			{
				System.out.println("Promotional Set does not exist! Back to Main Menu...\n");
				break;
			}
			
			while(true)
			{
				System.out.println("Current Promotional Set = " + tempItem.getName());
				System.out.println("Enter number to update (1)Promotional Set's name, (2)Promotional Set's Decription, (3)Promotional Set's Price, ");
				System.out.println("(4)Add Menu Item, (5)Remove Menu Item, (0)to save and go back");
				input = sc.nextInt();
				sc.nextLine();
				if(input == 0)
				{
					System.out.println("Back to Main Menu...\n");
					break;
				}
				else if(input == 1)
				{
					System.out.println("Enter new name for selected Promotional Set: ");
					inputPromoSet = sc.nextLine();
					tempItem.setName(inputPromoSet);
					System.out.println("New name set!\n");
				}
				else if(input == 2)
				{
					System.out.println("Enter new Decription for selected Promotional Set: ");
					inputDesc = sc.nextLine();
					tempItem.setDescription(inputDesc);
					System.out.println("New Decription set!\n");
				}
				else if(input == 3)
				{
					System.out.println("Enter new Price for selected Promotional Set: ");
					inputPrice = sc.nextDouble();
					tempItem.setPrice(inputPrice);
					System.out.println("New Price set!\n");
				}
				else if(input == 4)
				{
					PrintAllALaCarteMenuItems();
					
					System.out.println("\n**********List of Menu Items in SELECTED Promotional Set**********");
					for(MenuItem item : ((PromotionalSet)tempItem).getItems().keySet()) {
						System.out.println("\n	Name Of Menu Item: " + item.getName());
						System.out.println("	Description Of Menu Item: " + item.getDescription());
						System.out.println("	Quantity Of Menu Item: " + ((PromotionalSet)tempItem).getItems().get(item));
						//System.out.println("Price Of Promotional Set: $" + i.getPrice());
					}
					System.out.println("\n**********End of list**********\n");
					
					
					System.out.println("Enter Name of existing Menu Item to be added to the Promotional Set: ");
					inputName = sc.nextLine();
					MenuItem tempItem2;
					tempItem2 = menuItemFactory.getItem(inputName);
					if(tempItem2 == null)
					{
						System.out.println("Menu Item does not exist! Back to Main Menu...\n");
						break;
					}
					
					System.out.println("Enter Quantity of existing Menu Item to be added to the Promotional Set: ");
					inputQuantity = sc.nextInt();
					sc.nextLine();
					((PromotionalSet)tempItem).addItems(tempItem2,inputQuantity);
					System.out.println("Menu Item Added!! Back to Main Menu...\n");
				}
				else if(input == 5)
				{
					System.out.println("\n**********List of Menu Items in SELECTED Promotional Set**********");
					for(MenuItem item : ((PromotionalSet)tempItem).getItems().keySet()) {
						System.out.println("\n	Name Of Menu Item: " + item.getName());
						System.out.println("	Description Of Menu Item: " + item.getDescription());
						System.out.println("	Quantity Of Menu Item: " + ((PromotionalSet)tempItem).getItems().get(item));
					}
					System.out.println("\n**********End of list**********\n");
					System.out.println("Enter Name of existing Menu Item to be removed from the Promotional Set: ");
					inputName = sc.nextLine();
					MenuItem tempItem2;
					tempItem2 = menuItemFactory.getItem(inputName);
					if(tempItem2 == null || ((PromotionalSet)tempItem).getItems().get(tempItem2) == null)
					{
						System.out.println("Menu Item does not exist! Back to Main Menu...\n");
						break;
					}
					
					((PromotionalSet)tempItem).removeItems(tempItem2);
					System.out.println("Menu Item Removed!! Back to Main Menu...\\n");
				}
			}
			
			break;
		case 3:
			System.out.println("Enter Name of Promotion to Delete: ");
			inputName = sc.nextLine();
			tempItem = menuItemFactory.getItem(inputName);
			
			if(tempItem == null || !(tempItem instanceof PromotionalSet))
			{
				System.out.println("Promotional Set does not exist! Back to Main Menu...\n");
				break;
			}
			menuItemFactory.getItemList().remove(tempItem);
			System.out.println("Promotion Set Removed!! Back to Main Menu...\n");
			break;
		case 0:
		default:
			System.out.println("Back to Main Menu...\n");
			break;	
		}
		//sc.close();
	}
	
	/**
	 * Function to perform operations regarding the 3rd option
	 * Create order
	 */
	public void option3OrderCreation()
	{
		System.out.println("\n>>>Create new order<<<\n");
		Scanner sc = new Scanner(System.in);
		String inputMenuItemName;
		int inputOrderID, inputTableNumber, inputQuantity, inputDiscountType;
		MenuItem tempItem;
		discountType disType; 
		
		
		System.out.println("Enter Order ID:");
		inputOrderID = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Table Number:");
		inputTableNumber = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter Discount type (0)NON MEMBER, (1)MEMBER, (2)SILVER, (3)GOLD:");
		inputDiscountType = sc.nextInt();
		sc.nextLine();
		
		if(inputDiscountType < 0 && inputDiscountType > 3)
		{
			inputDiscountType = 0;
			System.out.println("INVALID Discount type, set to (0)NON MEMBER by default.");
		}
		disType = discountType.values()[inputDiscountType];
		order = new Order(inputOrderID,inputTableNumber,currStaff,Calendar.getInstance(),disType);
		
		PrintAllALaCarteMenuItems();
		PrintAllPromotionalSet();
		
		while(true)
		{
			System.out.println("Enter Menu Item/Promotional Set to be added: (Enter (0) to complete)");
			inputMenuItemName = sc.nextLine();
			
			if(inputMenuItemName.equals("0"))
			{
				System.out.println("Back to Main Menu...\n");
				break;
			}
			tempItem = menuItemFactory.getItem(inputMenuItemName);
			
			if(tempItem == null)
			{
				System.out.println("Item does not exist!");
				continue;
			}
			System.out.println("Enter item Quantity: ");
			inputQuantity = sc.nextInt();
			sc.nextLine();
			
			order.addItem(tempItem,inputQuantity);
			System.out.println(tempItem.getName() + " Added! Quantity: " + inputQuantity);
		}
		
		
	}
	
	/**
	 * Function to perform operations regarding the 4th option
	 * View order
	 */
	public void option4ViewOrder()
	{
		System.out.println("\n>>>View order<<<\n");
		
		order.viewCurrentOrder();
		
		System.out.println("\nBack to Main Menu...\n");
	}
	
	/**
	 * Function to perform operations regarding the 5th option
	 * Add/Remove order item/s to/from order
	 */
	public void option5UpdateItemsToOrder()
	{
		System.out.println("\n>>>Add/Remove order item/s to/from order<<<\n");
		if(order == null)
		{
			System.out.println("Order Not Created Yet!!");
			return;
		}
		while(true)
		{
			Scanner sc = new Scanner(System.in);
			String inputMenuItemName;
			int input,inputQuantity;
			MenuItem tempItem;
			
			PrintAllALaCarteMenuItems();
			PrintAllPromotionalSet();
			
			System.out.println("Enter (1)Add Menu Items, (2)Remove Menu Items, (0)Return back to Menu");
			input = sc.nextInt();
			sc.nextLine();
			
			if(input == 0)
			{
				System.out.println("Back to Main Menu...\n");
				break;
			}
			else if(input == 1)
			{
				System.out.println("Enter Name of Item to add: ");
				inputMenuItemName = sc.nextLine();
				tempItem = menuItemFactory.getItem(inputMenuItemName);
				if(tempItem == null)
				{
					System.out.println("Invalid Item to add!!!");
					continue;
				}
				System.out.println("Enter item Quantity: ");
				inputQuantity = sc.nextInt();
				sc.nextLine();
				order.addItem(tempItem,inputQuantity);
				
				System.out.println("Item added!!!Back to Main Menu...\n");
			}
			else if(input == 2)
			{
				System.out.println("Enter Name of Item to remove: ");
				inputMenuItemName = sc.nextLine();
				tempItem = menuItemFactory.getItem(inputMenuItemName);
				if(tempItem == null)
				{
					System.out.println("Invalid Item to remove!!!Back to Main Menu...\n");
					continue;
				}
				//System.out.println("Enter item Quantity: ");
				//inputQuantity = sc.nextInt();
				//sc.nextLine();
				order.removeItem(tempItem);
				System.out.println("Item removed!!!Back to Main Menu...\n");
			}
		}
	}
	
	/**
	 * Function to perform operations regarding the 6th option
	 * Create reservation booking
	 */
	public void option6ReservationBookingCreation()
	{
		System.out.println("\n>>>Create reservation booking<<<\n");
		
		System.out.println("\nBack to Main Menu...\n");
	}
	
	/**
	 * Function to perform operations regarding the 7th option
	 * Check/Remove reservation booking
	 */
	public void option7ReservationChecking()
	{
		System.out.println("\n>>>Check/Remove reservation booking<<<\n");
		
		System.out.println("\nBack to Main Menu...\n");
	}
	
	/**
	 * Function to perform operations regarding the 8th option
	 * Check table availability
	 */
	public void option8TableAvailability()
	{
		System.out.println("\n>>>Check table availability<<<\n");
		
		System.out.println("\nBack to Main Menu...\n");
	}
	
	/**
	 * Function to perform operations regarding the 9th option
	 * Print order invoice
	 */
	public void option9PrintOrderInvoice()
	{
		System.out.println("\n>>>Print order invoice<<<\n");
		if(order == null)
		{
			System.out.println("Please Create an order first before printing the invoice! Back to Main Menu...\n");
			return;
		}
		System.out.println("Printing current order's invoice! \n");
		order.printOrderInvoice();
		System.out.println("\nBack to Main Menu...\n");
	}
	
	/**
	 * Function to perform operations regarding the 10 option
	 * Print sale revenue report by period (eg day or month)
	 */
	public void option10PrintSaleRevenueReport()
	{
		System.out.println("\n>>>Print sale revenue report by period (eg day or month)<<<\n");
		GenerateReport temp = new GenerateReport(false);
		System.out.println("\nBack to Main Menu...\n");
	}
	
	
}
