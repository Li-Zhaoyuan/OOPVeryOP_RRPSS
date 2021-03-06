
package main;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import main.Discount.discountType;

import java.util.Calendar;
import menuitem.MenuItemFactory;
import menuitem.MenuItem;
import menuitem.PromotionalSet;
import salerevenuereport.GenerateReport;
import tableandreservation.Reservation;
import tableandreservation.ReservationList;
import tableandreservation.ReservationApp;

/**
RRPSS - This class acts as the hub that holds the functions that will perform operations depending on the different options.
@author Li Zhaoyuan
@version 1.0
@since 2021-10-20
*/

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
	 * ArrayList of Order objects for multiple different orders.
	 */
	ArrayList<Order> orderList;
	/**
	 * variable to hold the contact number of order
	 */
	ArrayList<Integer> contactList;
	/**
	 * variable holding the ReservationApp object
	 */
	ReservationApp reservationApp;
	
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
		while(true)
		{
			System.out.println("Enter Staff Employee ID: ");
			if(!sc.hasNextInt())
			{
				sc.nextLine();
				System.out.println("Please Enter Staff Employee ID that only consist of numbers\n");
			}
			else
			{
				inputEmployeeID = sc.nextInt();
				break;
			}
		}
		
		
		currStaff = new Staff(inputName,inputGender,inputJobTitle,inputEmployeeID);
		reservationApp = new ReservationApp();
		orderList = new ArrayList<Order>();
		contactList = new ArrayList<Integer>();
		for(int i = 0; i <= 25; ++i)
		{
			orderList.add(null);
			contactList.add(-1);
		}
	}
	
	/**
	 * Function to clear or to save variables before exiting
	 */
	public void exit()
	{
		menuItemFactory.updateCSV();
		reservationApp.saveReservations();
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
					System.out.println("Enter Quantity of Menu Item to be added to the Promotional Set: ");
					inputQuantity = sc.nextInt();
					sc.nextLine();
					listOfDishName.add(Integer.toString(inputQuantity));
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
	 * Function to perform operations regarding the 3rd option.
	 * Creates the order
	 */
	public void option3OrderCreation()
	{
		reservationApp.getReservationList().removeExpiredReservation();
		System.out.println("\n>>>Create new order<<<\n");
		Scanner sc = new Scanner(System.in);
		String inputMenuItemName;
		int inputOrderID, inputTableNumber, inputQuantity, inputDiscountType, inputContact,index;
		MenuItem tempItem;
		Order tempOrder;
		discountType disType; 
		
		
		System.out.println("Enter Order ID:");
		inputOrderID = sc.nextInt();
		sc.nextLine();
		//System.out.println("Enter Table Number:");
		
		System.out.println("Enter Contact Number registered with Reservation: ");
		inputContact = sc.nextInt();
		sc.nextLine();
		
		index = reservationApp.getReservationList().checkReservation(inputContact);
		if(index == -1)
		{
			System.out.println("Please make a reservation!");
			return;
		}
		if(!reservationApp.getReservationList().checkNow(index))
		{
			System.out.println("This is not your allocated time slot!!! ");
			return;
		}
		inputTableNumber = reservationApp.getReservationList().getlistOfReservation().get(index).getTableNum();
		System.out.println("Your assigned table is: " + inputTableNumber);
		contactList.set(inputTableNumber,inputContact);
		//sc.nextLine();
		
		System.out.println("Enter Discount type (0)NON MEMBER, (1)MEMBER, (2)SILVER, (3)GOLD:");
		inputDiscountType = sc.nextInt();
		sc.nextLine();
		
		if(inputDiscountType < 0 && inputDiscountType > 3)
		{
			inputDiscountType = 0;
			System.out.println("INVALID Discount type, set to (0)NON MEMBER by default.");
		}
		disType = discountType.values()[inputDiscountType];
		tempOrder = new Order(inputOrderID,inputTableNumber,currStaff,Calendar.getInstance(),disType);
		
		PrintAllALaCarteMenuItems();
		PrintAllPromotionalSet();
		
		while(true)
		{
			System.out.println("Enter Menu Item/Promotional Set to be added: (Enter (0) to complete)");
			inputMenuItemName = sc.nextLine();
			
			if(inputMenuItemName.equals("0"))
			{
				orderList.set(inputTableNumber,tempOrder);
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
			
			tempOrder.addItem(tempItem,inputQuantity);
			System.out.println(tempItem.getName() + " Added! Quantity: " + inputQuantity);
		}
		
		
	}
	
	/**
	 * Function to perform operations regarding the 4th option.
	 * View current order.
	 */
	public void option4ViewOrder()
	{
		System.out.println("\n>>>View order<<<\n");
		
		int inputTableNumber;
		inputTableNumber = getInputForTableNumber();
		
		if(inputTableNumber == -1)
		{
			System.out.println("\nBack to Main Menu...\n");
			return;
		}
		
		orderList.get(inputTableNumber).viewCurrentOrder();
		System.out.println("\nBack to Main Menu...\n");
	}
	
	/**
	 * Function to perform operations regarding the 5th option.
	 * Add/Remove order item/s to/from order.
	 */
	public void option5UpdateItemsToOrder()
	{
		System.out.println("\n>>>Add/Remove order item/s to/from order<<<\n");
		
		Scanner sc = new Scanner(System.in);
		int inputTableNumber;
		inputTableNumber = getInputForTableNumber();
		
		if(inputTableNumber == -1)
		{
			System.out.println("\nBack to Main Menu...\n");
			return;
		}
		
		while(true)
		{
			String inputMenuItemName;
			int inputQuantity;
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
				orderList.get(inputTableNumber).addItem(tempItem,inputQuantity);
				
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
				orderList.get(inputTableNumber).removeItem(tempItem);
				System.out.println("Item removed!!!Back to Main Menu...\n");
			}
		}
	}
	
	/**
	 * Function to perform operations regarding the 6th option.
	 * Creates a reservation booking.
	 */
	public void option6ReservationBookingCreation()
	{
		System.out.println("\n>>>Create reservation booking<<<\n");
		
		Scanner sc = new Scanner(System.in);
		String inputDate, inputTime, inputName;
		int inputPax, inputContact, input;
		
		LocalDate parsedDate;
		LocalTime parsedTime;
		
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d/MM/yyyy");
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H:mm");
		
		while(true)
		{
			reservationApp.getReservationList().removeExpiredReservation();
			while(true)
			{
				System.out.println("Please enter Reservation Date in this format dd/mm/yyyy (eg. 20/10/2021): ");
				inputDate = sc.nextLine();
				
				if(reservationApp.getReservationList().isValidDate(inputDate))
				{
					parsedDate = LocalDate.parse(inputDate, formatterDate);
					if(parsedDate.compareTo(LocalDate.now()) < 0)
					{
						System.out.println("Reservation can only be made in advance");
						continue;
					}
					break;
				}
				else
				{
					System.out.println("Please try to enter a valid Date again!");
					continue;
				}
			}
			while(true)
			{
				System.out.println("Please enter Reservation Time in this format hh:mm (eg. 20:10): ");
				inputTime = sc.nextLine();
				
				if(reservationApp.getReservationList().isValidTime(inputTime))
				{
					parsedTime = LocalTime.parse(inputTime, formatterTime);
					if(parsedDate.compareTo(LocalDate.now()) == 0 && parsedTime.isBefore(LocalTime.now()))
					{
						System.out.println("Reservation can only be made in advance");
						continue;
					}
					else if(parsedTime.isAfter(LocalTime.parse("22:00", formatterTime)))
					{
						System.out.println("Reservation Time cannot be later than 22:00hrs");
						continue;
					}
					break;
				}
				else
				{
					System.out.println("Please try to enter a valid Time again!");
					continue;
				}
			}
			while(true)
			{
				System.out.println("Please enter the number of people you are reserving for (max 10): ");
				inputPax = sc.nextInt();
				sc.nextLine();
				
				if(inputPax < 1 || inputPax > 10)
				{
					System.out.println("Please enter a number from 1 to 10 !!");
				}
				else
				{
					break;
				}
			} 
			
			if(reservationApp.getReservationList().getTableNum(parsedDate, parsedTime, inputPax) == -1)
			{
				System.out.println("No table available for this date and time!!! Enter (1) to try again, (0) to return to main menu: ");
				input = sc.nextInt();
				sc.nextLine();
				
				if(input != 1)
				{
					System.out.println("\nBack to Main Menu...\n");
					return;
				}
			}
			else
			{
				break;
			}
		}
		
		
		System.out.println("Please enter Name who made this Reservation: ");
		inputName = sc.nextLine();
		while(true)
		{
			System.out.println("Please enter your Contact Number for this Reservation: ");
			if(!sc.hasNextInt())
			{
				System.out.println("Please enter only numbers for your contact number!!!");
				sc.nextLine();
				continue;
			}
				
			inputContact = sc.nextInt();
			sc.nextLine();
			break;
		}
			
		if(reservationApp.getReservationList().addReservation(parsedDate, parsedTime, inputPax, inputName, inputContact))
		{
			System.out.println("Reservations Added!!!");
		}
		
		System.out.println("\nBack to Main Menu...\n");
	}
	
	/**
	 * Function to perform operations regarding the 7th option.  
	 * Check/Remove reservation booking
	 */
	public void option7ReservationChecking()
	{
		System.out.println("\n>>>Check/Remove reservation booking<<<\n");
		
		Scanner sc = new Scanner(System.in);
		int inputContact, index;
		
		while(true)
		{
			reservationApp.getReservationList().removeExpiredReservation();
			System.out.println("Enter (1) Check Reservation, (2) Remove Reservation, (0) To Return: ");
			input = sc.nextInt();
			sc.nextLine();
			if(input > 2 || input < 1)
			{
				break;
			}
			System.out.println("Please enter your Contact Number for this Reservation: ");
			if(!sc.hasNextInt())
			{
				System.out.println("Please enter only numbers for your contact number!!!");
				sc.nextLine();
				continue;
			}
				
			inputContact = sc.nextInt();
			sc.nextLine();
			
			
			if(input == 1)
			{
				index = reservationApp.getReservationList().checkReservation(inputContact);
				if (index == -1) {
					System.out.println("There is no reservation made with this contact number!");
				}
				else {
					reservationApp.getReservationList().printReservationWithIndex(index);
				}
			}
			else if(input == 2)
			{
				if (reservationApp.getReservationList().removeReservation(inputContact)) {
					System.out.println("Reservations Removed!!!");
				}
				else {
					System.out.println("There is no reservation made with this contact number!");
				}
			}
			
		}
		System.out.println("\nBack to Main Menu...\n");
	}
	
	/**
	 * Function to perform operations regarding the 8th option
	 * Check table availability
	 */
	public void option8TableAvailability()
	{
		System.out.println("\n>>>Table availability<<<\n");
		
		Scanner sc = new Scanner(System.in);
		String inputDate, inputTime;
		int inputPax, input;
		
		LocalDate parsedDate;
		LocalTime parsedTime;
		
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d/MM/yyyy");
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H:mm");
		
		
		while(true)
		{
			reservationApp.getReservationList().removeExpiredReservation();
			System.out.println("Enter (1)Check For Current Time, (2)Check based on a Given Time, (0)To Return: ");
			input = sc.nextInt();
			sc.nextLine();
			
			if(input == 1) // current PC time
			{
				parsedDate = LocalDate.now();
				parsedTime = LocalTime.now();
				
				System.out.println("Current Date: " + parsedDate + " Current Time: " + parsedTime);
			}
			else if(input == 2) // given time
			{
				while(true)
				{
					System.out.println("Please enter Date in this format dd/mm/yyyy (eg. 20/10/2021): ");
					inputDate = sc.nextLine();
					
					if(reservationApp.getReservationList().isValidDate(inputDate))
					{
						parsedDate = LocalDate.parse(inputDate, formatterDate);
						if(parsedDate.compareTo(LocalDate.now()) < 0)
						{
							System.out.println("You can only check Table availablity from today onwards!!!");
							continue;
						}
						break;
					}
					else
					{
						System.out.println("Please try to enter a valid Date again!");
					}
				}
				while(true)
				{
					System.out.println("Please enter Time in this format hh:mm (eg. 20:10): ");
					inputTime = sc.nextLine();
					
					if(reservationApp.getReservationList().isValidTime(inputTime))
					{
						parsedTime = LocalTime.parse(inputTime, formatterTime);
						if(parsedDate.compareTo(LocalDate.now()) == 0 && parsedTime.isBefore(LocalTime.now()))
						{
							System.out.println("You can only check Table availablity from " + LocalTime.now() + " onwards!!!");
							continue;
						}
						else if(parsedTime.isAfter(LocalTime.parse("22:00", formatterTime)))
						{
							System.out.println("You can only check Table availablity before 22:00!!!");
							continue;
						}
						break;
					}
					else
					{
						System.out.println("Please try to enter a valid Time again!");
					}
				}
			}
			else
			{
				System.out.println("\nBack to Main Menu...\n");
				return;
			}
			
			
			while(true)
			{
				System.out.println("Please enter the number of people you are reserving for (max 10): ");
				inputPax = sc.nextInt();
				sc.nextLine();
				
				if(inputPax < 1 || inputPax > 10)
				{
					System.out.println("Please enter a number from 1 to 10 !!");
				}
				else
				{
					break;
				}
			} 
			
			if(reservationApp.getReservationList().getTableNum(parsedDate, parsedTime, inputPax) == -1)
			{
				System.out.println("No table available for this date and time!!! Enter (1) to try again, (0) to return to main menu: ");
				input = sc.nextInt();
				sc.nextLine();
				
				if(input != 1)
				{
					System.out.println("\nBack to Main Menu...\n");
					return;
				}
			}
			else
			{
				System.out.println("There exist a table for Date: " + parsedDate + ", Time: " + parsedTime + ", For " + inputPax + " pax!");
				break;
			}
		}
		
		System.out.println("\nBack to Main Menu...\n");
	}
	
	/**
	 * Function to perform operations regarding the 9th option
	 * Print order invoice
	 */
	public void option9PrintOrderInvoice()
	{
		System.out.println("\n>>>Print order invoice<<<\n");
		
		int inputTableNumber;
		inputTableNumber = getInputForTableNumber();
		
		if(inputTableNumber == -1)
		{
			System.out.println("\nBack to Main Menu...\n");
			return;
		}
		
		System.out.println("Printing current order's invoice! \n");
		orderList.get(inputTableNumber).printOrderInvoice();
		reservationApp.getReservationList().removeReservation(contactList.get(inputTableNumber));
		//reservationApp.getReservationList().removeReservationWithIndex(order.getTableNumber());
		orderList.set(inputTableNumber, null);
		System.out.println("\nBack to Main Menu...\n");
	}
	
	/**
	 * Function to perform operations regarding the 10 option
	 * Print sale revenue report by period (eg day or month)
	 */
	public void option10PrintSaleRevenueReport()
	{
		System.out.println("\n>>>Print sale revenue report by period (day or month)<<<\n");
		Scanner sc = new Scanner(System.in);
		
		int input;
		
		System.out.println("Select Number Accordingly (1)Day/(2)Month/(0)Go Back");
		input = sc.nextInt();
		sc.nextLine();
		
		switch(input)
		{
		case 1:
			GenerateReport.GenerateReportForTheDay();
			break;
		case 2:
			GenerateReport.GenerateMonthlyReport();
			break;
		default:
			break;
		}
		//GenerateReport temp = new GenerateReport(false);
		System.out.println("\nBack to Main Menu...\n");
	}
	
	/**
	 * Function to get the table number from the user. and checks if the table have an order
	 * @return returns a table number from 1 to 25 if invalid input it will be -1
	 */
	public int getInputForTableNumber()
	{
		Scanner sc = new Scanner(System.in);
		int inputTableNumber;
		System.out.println("Enter Table Number: ");
		inputTableNumber = sc.nextInt();
		sc.nextLine();
		
		if(inputTableNumber > 25 || inputTableNumber < 1)
		{
			System.out.println("This Table does not exist!!!");
			//System.out.println("\nBack to Main Menu...\n");
			return -1;
		}
		
		if(orderList.get(inputTableNumber) == null)
		{
			System.out.println("This Table does not have an order!!!");
			//System.out.println("\nBack to Main Menu...\n");
			return -1;
		}
		
		return inputTableNumber;
	}
	
	
}

