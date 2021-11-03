/** 
 MenuItemLoader - An object that handles IO operations for the MenuItemFactory
 @author Lim Rui An, Ryan 
 @version 1.0 
 @since 2021-11-3 
*/ 

package menuitem;

import java.util.ArrayList;

import miscellaneous.CSVLoader;
import miscellaneous.CSVWriter;

public class MenuItemLoader {
	
	/**
	* CSVLoader that holds read csv data for normal items
	*/
	private CSVLoader normalItemsCSV;
	
	/**
	* CSVLoader that holds read csv data for promotional items
	*/
	private CSVLoader promoItemsCSV;
	
	/**
	* String that holds the path to the MenuItem csv
	*/
	private String normalPath;
	
	/**
	* String that holds the path to the PromoItem csv
	*/
	private String promoPath;

	/**
	* Constructor for CSVLoader
	*/
	public MenuItemLoader() {	}

	/**
	* Public void function to setup the file paths to loaders
	*/
	public void initializeLoaders(String normalPath, String promoPath) {
		this.normalPath = normalPath;
		this.promoPath = promoPath;
		normalItemsCSV = new CSVLoader(normalPath, true);
		promoItemsCSV = new CSVLoader(promoPath, true);
	}
	
	/**
	* Public accessor function to get the normal item csv file
	*/
	CSVLoader getNormalCSV() {
		return normalItemsCSV;
	}
	
	/**
	* Public accessor function to get the promo item csv file
	*/
	CSVLoader getPromoCSV() {
		return promoItemsCSV;
	}
	
	/**
	* Public void function to update the csv files used for the construction of menu items
	*/
	public void updateCSV(ArrayList<MenuItem> itemList) {
		CSVWriter wrtr = new CSVWriter();
		ArrayList<String> menuData = new ArrayList<String>();
		ArrayList<String> promoData = new ArrayList<String>();
		// Read the item list, generate content to be written into the csv
		for (MenuItem i : itemList)
			if (i.getClass().getSimpleName() == PromotionalSet.class.getSimpleName())
				promoData.add(itemToString(i));
			else menuData.add(itemToString(i));
		
		// Update csv files making use of the writer
		// Menu Items
		// Set to not append the header, we rewrite a new file
		wrtr.writeFile(normalPath, false, String.join(",", normalItemsCSV.getCSVHeader()));
		// After the header, append the lines
		for (String s : menuData)
			wrtr.writeFile(normalPath, true, s);
		
		// Promo Items
		// Set to not append the header, we rewrite a new file
		wrtr.writeFile(promoPath, false, String.join(",", promoItemsCSV.getCSVHeader()));
		// After the header, append the lines
		for (String s : promoData)
			wrtr.writeFile(promoPath, true, s);
	}
	
	/**
	* Private void function to convert a MenuItem to a string
	* Convert item parameters as a concatenated string delimited with commas
	*/
	private String itemToString(MenuItem m) {
		String s = "";
		// Basic parameters for both menu and promo item
		s += m.getClass().getSimpleName() + ',';
		s += m.getName() + ',';
		s += m.getDescription() + ',';
		s += Double.toString(m.getPrice());
		
		// If this is a promotional item, append each other item in set
		if (m.getClass().getSimpleName().equals(PromotionalSet.class.getSimpleName())) {
			for (MenuItem i : ((PromotionalSet)m).getItems().keySet())
				s += ',' + i.getName();
		}
		return s;
	}
}
