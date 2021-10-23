/**
 PromotionalSet - A type of menu item that contains a map of other items
 @author Lim Rui An, Ryan
 @version 1.0
 @since 2021-10-23
*/

package menuitem;

import java.util.HashMap;

public class PromotionalSet extends MenuItem{
	
	/**
	* Hashmap that stores key value pairs of <MenuItems, Quantity> for every item in this set 
	*/
	private HashMap<MenuItem, Integer> items;

	/**
	* Constructor of this PromotionalSet Object
	*/
	public PromotionalSet() {
		items = new HashMap<MenuItem, Integer>();
	}
	
	/**
	* Accessor of the PromotionalSet Object's items
	*/
	public HashMap<MenuItem, Integer> getItems(){
		return items;
	}
	
	/**
	* Mutator of the PromotionalSet Object's items
	* Adds an key value pair combination to the set
	* @param item is a MenuItem object to be added to the set
	* @param quantity is the number of the MenuItem to be added
	*/
	public void addItems(MenuItem item, int quantity){
		// TODO: Implement addition of KVp to Promotional Set
	}

	/**
	* Mutator of the PromotionalSet Object's items
	* Removes the specified item from the map
	* @param item is a MenuItem object to be removed from the set
	*/
	public void removeItems(MenuItem item){
		// TODO: Implement removal of KVp from Promotional Set
	}
}
