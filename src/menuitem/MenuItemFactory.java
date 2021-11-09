/**
 MenuItemFactory - Generates objects of the MenuItem class and extended sub-classes
 @author Lim Rui An, Ryan
 @version 1.1
 @since 2021-10-23
*/

package menuitem;

import java.util.ArrayList;

import miscellaneous.CSVLoader;

public class MenuItemFactory {
	
	/**
	* Private MenuItemLoader used to load the csv files
	*/
	private MenuItemLoader mil;
	
	/**
	* Private list of all items constructed by the factory
	*/
	private ArrayList<MenuItem> itemList;

	/**
	* Constructor for MenuItemFactory
	*/
	public MenuItemFactory() {
		itemList = new ArrayList<MenuItem>();
		mil = new MenuItemLoader();
		mil.initializeLoaders("src/resource/menuitems.csv", "src/resource/promoitems.csv");
		constructFromCSV(mil.getNormalCSV());
		constructFromCSV(mil.getPromoCSV());
	}
	
	/**
	* Public accessor function to get the ItemList
	*/
	public ArrayList<MenuItem> getItemList(){
		return itemList;
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
	* Public void function to update the csv files used for the construction of menu items
	*/
	public void updateCSV() {
		if (mil != null)
			mil.updateCSV(itemList);
	}
	
	/**
	* Public void function to construct an item based on passed parameters
	* Constructed item is put into the ItemList
	* This function is hard-coded to abide by the formatting of the CSV file
	* @param parameterList - A list of parameters pertaining to the item
	*/
	public void constructItem(ArrayList<String> parameterList){
		// Conditionals based on MenuItem Type, using the class name to reduce hard-coding
		if (parameterList.get(0).equals(Dessert.class.getSimpleName()))
			createDessert(parameterList.get(1), parameterList.get(2), Double.parseDouble(parameterList.get(3)));
		else if (parameterList.get(0).equals(Drink.class.getSimpleName()))
			createDrink(parameterList.get(1), parameterList.get(2), Double.parseDouble(parameterList.get(3)));
		else if (parameterList.get(0).equals(MainCourse.class.getSimpleName()))
			createMainCourse(parameterList.get(1), parameterList.get(2), Double.parseDouble(parameterList.get(3)));
		else if (parameterList.get(0).equals(SideCourse.class.getSimpleName()))
			createSideCourse(parameterList.get(1), parameterList.get(2), Double.parseDouble(parameterList.get(3)));
		else if (parameterList.get(0).equals(PromotionalSet.class.getSimpleName()))
			createPromotionalSet(parameterList.get(1), parameterList.get(2), Double.parseDouble(parameterList.get(3)), new ArrayList<String>(parameterList.subList(4, parameterList.size())));
		else {
			System.out.println("MenuItemFactory: Unidentified Construction Parameter");
			System.out.println(parameterList.get(0));
		}
	}
	
	/**
	* Public accessor function to get an object from the ItemList
	* Returns the object if found, else null
	* @param name - The name of the item intended to be found
	*/
	public MenuItem getItem(String name){
		for (MenuItem i : itemList) {
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
		itemList.add(obj);
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
		itemList.add(obj);
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
		itemList.add(obj);
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
		itemList.add(obj);
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
		
		// For each item in list, get the MenuItem and add it to the map of promo set, with the quantity
		for (int i = 0; i < items.size() - 1; i+=2) {
			String id = items.get(i);
			MenuItem m = this.getItem(id);
			if (m != null) 
				obj.addItems(m, Integer.parseInt(items.get(i+1)));
			else System.out.println("MenuItemFactory: Cannot find item of id: " + id + " for PromoSet item " + name);
		}
		
		itemList.add(obj);
		return obj;
	}
}
