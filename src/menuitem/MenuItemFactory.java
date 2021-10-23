/**
 MenuItemFactory - Generates objects of the MenuItem class and extended sub-classes
 @author Lim Rui An, Ryan
 @version 1.0
 @since 2021-10-23
*/

package menuitem;

import java.util.HashMap;

public class MenuItemFactory {

	/**
	* Constructor for MenuItemFactory
	*/
	public MenuItemFactory() {
		
	}
	
	
	/**
	* Factory function that creates a new Dessert object
	* @param name - name of the object
	* @param description - description of the object
	* @param price - price of the object
	*/
	public Dessert CreateDessert(String name, String description, double price) {
		Dessert obj = new Dessert();
		obj.setName(name);
		obj.setDescription(description);
		obj.setPrice(price);
		return obj;
	}
	
	/**
	* Factory function that creates a new Drink object
	* @param name - name of the object
	* @param description - description of the object
	* @param price - price of the object
	*/
	public Drink CreateDrink(String name, String description, double price) {
		Drink obj = new Drink();
		obj.setName(name);
		obj.setDescription(description);
		obj.setPrice(price);
		return obj;
	}
	
	/**
	* Factory function that creates a new MainCourse object
	* @param name - name of the object
	* @param description - description of the object
	* @param price - price of the object
	*/
	public MainCourse CreateMainCourse(String name, String description, double price) {
		MainCourse obj = new MainCourse();
		obj.setName(name);
		obj.setDescription(description);
		obj.setPrice(price);
		return obj;
	}
	
	/**
	* Factory function that creates a new SideCourse object
	* @param name - name of the object
	* @param description - description of the object
	* @param price - price of the object
	*/
	public SideCourse CreateSideCourse(String name, String description, double price) {
		SideCourse obj = new SideCourse();
		obj.setName(name);
		obj.setDescription(description);
		obj.setPrice(price);
		return obj;
	}

	/**
	* Factory function that creates a new PromotionalSet object
	* @param name - name of the object
	* @param description - description of the object
	* @param price - price of the object
	* @param items - Map of <MenuItem Name, Quantity> for each item in the PromotionalSet
	*/
	public PromotionalSet CreatePromotionalSet(String name, String description, double price, HashMap<String, Integer> items) {
		PromotionalSet obj = new PromotionalSet();
		obj.setName(name);
		obj.setDescription(description);
		obj.setPrice(price);
		// TODO: Logic to add items into promo set
		return obj;
	}
}
