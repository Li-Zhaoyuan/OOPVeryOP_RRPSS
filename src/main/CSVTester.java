package main;

import java.util.ArrayList;

import menuitem.MenuItem;
import menuitem.MenuItemFactory;
import miscellaneous.CSVLoader;

public class CSVTester {

	public static void main(String[] args) {
		// How 2 use the csv loader 101
		// Load the file
		CSVLoader ldr = new CSVLoader("src/resource/menuitems.csv", true);
		
		// Get the header
		System.out.println(ldr.getCSVHeader());
		
		// Iterate the data
		for (ArrayList<String> l : ldr.getCSVData()) {
			// Do something with the list
			System.out.println(l);
		}
		
		System.out.println();
		
		// Use the menu factory to construct items
		MenuItemFactory mif = new MenuItemFactory();
		mif.constructFromCSV(ldr);
		
		// NOTE: ALWAYS CONSTRUCT NORMAL MENU ITEMS BEFORE PROMOTIONAL ITEMS
		ldr = new CSVLoader("src/resource/promoitems.csv", true);
		mif.constructFromCSV(ldr);
		
		// Check construction
		for (MenuItem m : mif.getItemList()) {
			System.out.println(m.getName() + " | " + m.getDescription() + " | $" + m.getPrice());
		}
		
		System.out.println();
		// Get items by their string name
		System.out.println(mif.getItem("Fries") != null);

	}

}
