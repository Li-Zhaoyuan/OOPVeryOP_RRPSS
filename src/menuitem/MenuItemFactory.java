/**
 MenuItemFactory - Generates objects of the MenuItem class and extended sub-classes
 @author Lim Rui An, Ryan
 @version 1.0
 @since 2021-10-23
*/

package menuitem;

import java.util.ArrayList;
import java.util.HashMap;

import miscellaneous.CSVLoader;

public class MenuItemFactory {
	
	/**
	* Private list of all items constructed by the factory
	*/
	private ArrayList<MenuItem> ItemList;

	/**
	* Constructor for MenuItemFactory
	*/
	public MenuItemFactory() {
		ItemList = new ArrayList<MenuItem>();
	}
	
	/**
	* Public accessor function to get the ItemList
	*/
	public ArrayList<MenuItem> getItemList(){
		return ItemList;
	}
	
	/**
	* Public void function to set up the ItemList
	* For each row in the ldr, we perform some logic to construct our objects
	* @param ldr is a CSVLoader object, this object should have already loaded 
	* a CSV file prior to this function call.
	*/
	public void constructFromCSV(CSVLoader ldr) {
		// Iterate the data and construct an item for each row
		for (ArrayList<String> l : ldr.getCSVData()) {
			constructItem(l);
		}
	}
	
	/**
	* Public void function to construct an item based on passed parameters
	* Constructed item is put into the ItemList
	* This function is hardcoded to abide by the formatting of the CSV file
	* Not the best in terms of coding practices, but it works
	* @param parameterList - A list of parameters pertaining to the item
	*/
	public void constructItem(ArrayList<String> parameterList){
		// Switch based on MenuItem Type
		switch(parameterList.get(0)) {
		case "dessert": ItemList.add(createDessert(parameterList.get(1), parameterList.get(2), Double.parseDouble(parameterList.get(3))));
			break;
		case "drink": ItemList.add(createDrink(parameterList.get(1), parameterList.get(2), Double.parseDouble(parameterList.get(3))));
			break;
		case "maincourse": ItemList.add(createMainCourse(parameterList.get(1), parameterList.get(2), Double.parseDouble(parameterList.get(3))));
			break;
		case "sidecourse": ItemList.add(createSideCourse(parameterList.get(1), parameterList.get(2), Double.parseDouble(parameterList.get(3))));
			break;
		case "promotionalset": ItemList.add(createPromotionalSet(parameterList.get(1), parameterList.get(2), Double.parseDouble(parameterList.get(3)), new ArrayList<String>(parameterList.subList(4, parameterList.size()-1))));
			break;
		default: System.out.println("MenuItemFactory: Unidentified Construction Parameter");
		}
	}
	
	/**
	* Public accessor function to get an object from the ItemList
	* Returns the object if found, else null
	* @param name - The name of the item intended to be found
	*/
	public MenuItem getItem(String name){
		for (MenuItem i : ItemList) {
			if (i.getName().equals(name))
				return i;
		}
		return null;
	}
	
	/**
	* Factory function that creates a new Dessert object
	* @param name - name of the object
	* @param description - description of the object
	* @param price - price of the object
	*/
	public Dessert createDessert(String name, String description, double price) {
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
	public Drink createDrink(String name, String description, double price) {
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
	public MainCourse createMainCourse(String name, String description, double price) {
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
	public SideCourse createSideCourse(String name, String description, double price) {
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
	* @param items - List of item names for each item in the PromotionalSet
	*/
	public PromotionalSet createPromotionalSet(String name, String description, double price, ArrayList<String> items) {
		PromotionalSet obj = new PromotionalSet();
		obj.setName(name);
		obj.setDescription(description);
		obj.setPrice(price);
		
		// For each item in list, get the MenuItem and add it to the map of promo set
		for (String id : items) {
			MenuItem m = this.getItem(id);
			if (m != null) 
				obj.addItems(m, 1);
			else System.out.println("MenuItemFactory: Cannot find item of id: " + id + " for PromoSet item " + name);
		}
		
		return obj;
	}
}
