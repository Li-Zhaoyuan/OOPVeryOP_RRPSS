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
import main.Discount.discountType;

public class Order extends MenuItem {

	/**
	* 10% Service charge for orders
	*/
	private static final double SERVICE_CHARGE = 1.10;

	/**
	* 7% Goods and Services Tax for orders
	*/
	private static final double GOODS_SERVICES_TAX = 1.07;

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
	* Original total price of order
	*/
	private double originalPrice;

	/**
	* Acutal total price of order
	*/
	private double nettPrice;

	/**
	* Discount of respective membership tier of customer
	*/
	private discountType discount;

	/**
	* Constructor of Order object
	* Creates a new order with the given information
	* @param orderId is the order ID of the order
	* @param tableNumber is the table number of the order
	* @param CreatedBy is the staff that took the order
	* @param OrderDateTime is the date/time when order was made
	* @param discount is the membership tier of the customer 
	*/
	public Order(int orderId, int tableNumber, Staff CreatedBy, Calendar OrderDateTime, discountType discount) {
		this.orderId = orderId;
		this.tableNumber = tableNumber;
		this.CreatedBy = CreatedBy;
		this.OrderDateTime = OrderDateTime;
		this.originalPrice = 0;
		this.nettPrice = 0;
		this.ItemsInOrder = new HashMap<MenuItem, Integer>();
		this.discount = discount;
	}

	/**
	* Accessor of the Order object's items
	*/
	public HashMap<MenuItem, Integer> getItemsInOrder() {
		return ItemsInOrder;
	}

	/**
	* Mutator of the Order object's items
	* Adds a key value pair combination to the order
	* @param orderedItem is a MenuItem object to be added to the order
	* @param quantity is the number of the MenuItem to be added
	*/
	public void addItem(MenuItem orderedItem, int quantity) {
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
	public void removeItem(MenuItem orderedItem) {
		ItemsInOrder.remove(orderedItem);
	}

	/**
	* Calculate original and actual total price
	* Original price is sum of actual price of each order item 
	* which is (order item price * quantity)
	* Actual price is addition of service charge and GST to original price
	* with discount given according to membership tier of customer
	*/
	public void calculateTotalPrices() {
		if(ItemsInOrder.isEmpty()) {
			return;
		}

		for(MenuItem item: ItemsInOrder.keySet()) {
			originalPrice += (item.getPrice() * ItemsInOrder.get(item));
		}
		setOriginalPrice(originalPrice);

		nettPrice = (originalPrice * SERVICE_CHARGE * GOODS_SERVICES_TAX) - (getDiscount() * originalPrice);
		setNettPrice(nettPrice);
	}

	/**
	* Get the order ID of this order
	* @return order id of order
	*/
	public int getOrderID() { 
		return orderId; 
	}

	/**
	* Get the table number of this order
	* @return table number of order
	*/
	public int getTableNumber() {
		return tableNumber;
	}
	
	/**
	* Get the information of the staff that created the order
	* @return information of staff
	*/
	public Staff getCreatedBy() {
        return CreatedBy;
	}

	/**
	* Get the datetime the order was created
	* @return datetime of order
	*/
	public Calendar getOrderDateTime() {
		return OrderDateTime;
	}

	/**
	* Get the discount of respective membership tier of customer
	* @return discount
	*/
	public double getDiscount() {
		double discountGiven = discount.getDiscount() / 100;
		return discountGiven;
	}

	/**
	* Get the original total price of the order
	* @return original total price
	*/
	public double getOriginalPrice() {
		return originalPrice;
	}

	/**
	* Get the actual total price of the order
	* @return actual total price
	*/
	public double getNettPrice() {
		return nettPrice;
	}

	/**
	* Change the original total price of order
	* @param newOriginalPrice is the order's new original price
	*/
	public void setOriginalPrice(double newOriginalPrice) {
		originalPrice = newOriginalPrice;
	}

	/**
	* Change the actual total price of order
	* @param newNettPrice is the order's new actual price
	*/
	public void setNettPrice(double newNettPrice) {
		nettPrice = newNettPrice;
	}
	
	/**
	* Display Order invoice
	* Displayed information includes order ID, table number, date/time of order,
	* staff details, list of ordered items, subtotal, 
	* service charge, gst, discount and nett total
	*/
	public void printOrderInvoice() {

		System.out.print("=========================================\n");
		System.out.print("--------------Order Invoice--------------\n");
		System.out.print("=========================================\n");
		System.out.printf("Order ID: %31s%n", getOrderID());
		System.out.printf("Table Number: %27s%n", getTableNumber());
		System.out.printf("Order Date/Time: " + dateFormatter.format(OrderDateTime.getTime()) + "\n");
		System.out.print("=========================================\n");
		System.out.print("------------------Staff------------------\n");
		System.out.print("=========================================\n");
		System.out.printf("Name: %35s%n", CreatedBy.getName());
		System.out.printf("Gender: %33s%n", CreatedBy.getGender());
		System.out.printf("Job Title: %30s%n", CreatedBy.getJobTitle());
		System.out.printf("Employee ID: %28s%n", CreatedBy.getEmployeeID());
		System.out.print("=========================================\n");

		for(MenuItem item : ItemsInOrder.keySet()) {
			String orderedItem = item.getName();
			int quantity = ItemsInOrder.get(item);
			double price = item.getPrice();
			System.out.printf("%-3s%-34s%.2f%n", quantity, orderedItem, price);
		}

		calculateTotalPrices();
		System.out.print("=========================================\n");
		System.out.printf("SUBTOTAL: %31.2f%n", getOriginalPrice());
		System.out.printf("SERVICE CHARGE: %25.2f%n", 0.10 * originalPrice);
		System.out.printf("GST: %36.2f%n", 0.07 * 1.10 * originalPrice);
		System.out.printf("DISCOUNT: %26s%.2f%s%n", "(", getDiscount() * originalPrice, ")");
		System.out.printf("TOTAL: %34.2f%n", getNettPrice());
		System.out.print("=========================================\n");
		System.out.print("------Thank you for dining with us!------\n");
		System.out.print("=========================================\n");
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

		for(MenuItem item : ItemsInOrder.keySet()) {
			String orderedItem = item.toString();
			int quantity = ItemsInOrder.get(item);
			printOrderString += orderedItem + " " + quantity + "\n";
		}

		System.out.println(printOrderString);
	}

}