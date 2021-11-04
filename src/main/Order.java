/**
 Order - Order made by a staff.
 Each order has an ID, table number, staff, date/time, ordered items.
 @author Yi Jie
 @version 1.0
 @since 2021-10-23
*/

package main;

import java.util.HashMap;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import menuitem.MenuItem;

public class Order extends MenuItem {

	/**
	* Order ID of order
	*/
	private int orderId;

	/**
	* Table Number of order
	*/
	private int tableNumber;

	/**
	* Staff that created the order
	*/
	private Staff CreatedBy;
	
	/**
	* The date/time that the order was taken
	*/
	private Calendar OrderDateTime;
	
	/**
	* Simple date format used to format date/time for displaying
	*/
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("E, dd/MM/yyyy, HH:mm");
	
	/**
	* Hashmap that stores key value pairs of <MenuItems, Quantity> for every item in the order
	*/
	private HashMap<MenuItem, Integer> ItemsInOrder;

	/**
	* Constructor of Order object
	*/
	public Order(int orderId, int tableNumber, Staff CreatedBy, Calendar OrderDateTime) {
		this.orderId = orderId;
		this.tableNumber = tableNumber;
		this.CreatedBy = CreatedBy;
		this.OrderDateTime = OrderDateTime;
		this.ItemsInOrder = new HashMap<MenuItem, Integer>();
	}

	/**
	* Accessor of the Order object's items
	*/
	public HashMap<MenuItem, Integer> getItemsInOrder(){
		return ItemsInOrder;
	}

	/**
	* Mutator of the Order object's items
	* Adds a key value pair combination to the order
	* @param orderedItem is a MenuItem object to be added to the order
	* @param quantity is the number of the MenuItem to be added
	*/
	public void addItem(MenuItem orderedItem, int quantity){
		// Map does not have this item yet
		if (ItemsInOrder.get(orderedItem) == null) {
			ItemsInOrder.put(orderedItem, quantity);
		}
		else { // Map already has this item, increment quantity
			ItemsInOrder.put(orderedItem, ItemsInOrder.get(orderedItem) + quantity);
		}
	}

	/**
	* Mutator of Order object's items
	* Removes the specified item from the map
	* @param orderedItem is a MenuItem object to be removed from the order
	*/
	public void removeItem(MenuItem orderedItem){
		ItemsInOrder.remove(orderedItem);
	}

	/**
	* Get the order ID of this order
	* @return order id of order
	*/
	public int getOrderID(){ 
		return orderId; 
	}

	/**
	* Get the table number of this order
	* @return table number of order
	*/
	public int getTableNumber(){
		return tableNumber;
	}
	
	/**
	* Get the information of the staff that created the order
	* @return information of staff
	*/
	public Staff getCreatedBy(){
        return CreatedBy;
	}

	/**
	* Get the datetime the order was created
	* @return datetime of order
	*/
	public Calendar getOrderDateTime(){
		return OrderDateTime;
	}
	
	/**
	* Display Order invoice
	* Displayed information includes order ID, table number, staff information,
	* date/time of order and summary of ordered items
	*/
	public void printOrderInvoice(){

		System.out.print("========================================\n");
		System.out.print("-------------Order Invoice--------------\n");
		System.out.print("========================================\n");
		System.out.printf("Order ID: %30s%n", getOrderID());
		System.out.printf("Table Number: %26s%n", getTableNumber());
		System.out.printf("Order Date/Time: " + dateFormatter.format(OrderDateTime.getTime()) + "\n");
		System.out.print("========================================\n");
		System.out.print("-----------------Staff------------------\n");
		System.out.print("========================================\n");
		System.out.printf("Name: %34s%n", CreatedBy.getName());
		System.out.printf("Gender: %32s%n", CreatedBy.getGender());
		System.out.printf("Job Title: %29s%n", CreatedBy.getJobTitle());
		System.out.printf("Employee ID: %27s%n", CreatedBy.getEmployeeID());
		System.out.print("========================================\n");

		for(MenuItem item : ItemsInOrder.keySet()){
			String orderedItem = item.getName();
			int quantity = ItemsInOrder.get(item);
			double price = item.getPrice();
			System.out.printf("%-5s%-32s%s%n", quantity, orderedItem, price);
		}

		System.out.print("========================================\n");
	}

	/**
	* Display Order invoice
	* Displayed information includes order ID, table number, staff information,
	* date/time of order and summary of ordered items
	*/
	public void printCurrOrderInvoice() {
		String printOrderString = "";

		System.out.print("-----Current Order-----\n");
		System.out.printf("Order ID: " + getOrderID() + "\n");
		System.out.printf("Table Number: " + getTableNumber() + "\n");
		System.out.printf("-Staff Details-\n" + getCreatedBy() + "\n");
		System.out.printf("Order Date/Time: " + dateFormatter.format(OrderDateTime.getTime()) + "\n");

		for(MenuItem item : ItemsInOrder.keySet()){
			String orderedItem = item.toString();
			int quantity = ItemsInOrder.get(item);
			printOrderString += orderedItem + " " + quantity + "\n";
		}

		System.out.println(printOrderString);
	}

}