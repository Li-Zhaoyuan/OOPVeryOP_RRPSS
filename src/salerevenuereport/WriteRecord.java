package salerevenuereport;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import miscellaneous.CSVWriter;

/**
WriteRecord - contains public methods for writing the individual sale and revenue records into CSV  
@author Heng Zheng Ping
@version 1.0
@since 2021-11-03
*/
public class WriteRecord {
	
	/**
	* String variable that stores record's data
	*/
	private static String data;
	
	/**
	* integer variable that stores the year of record
	*/
	private static int year;
	
	/**
	* integer variable that stores the month of record
	*/
	private static int month;
	
	/**
	* integer variable that stores the day of record
	*/
	private static int day;
	
	/**
	* Accessor of the date (year, month and day) for the append method to save into CSV
	* @return String of year, month, day
	*/
	private static String getDate() {
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
	    Calendar cal = Calendar.getInstance();
	    year = cal.get(Calendar.YEAR);
	    month = cal.get(Calendar.MONTH);
	    day = cal.get(Calendar.DAY_OF_MONTH);
	    
	    return year + "," + month + "," + day;
	}
	
	/**
	* public method for printInvoice method in the order class 
	* to append new individual sale record in the CSV for the sale revenue report
	* @param menuItem is the name of menu item in the invoice, quantity the quantity of each menu item in the invoice
	* the method append the menuItem, quantity, date and uses the CSVWriter class to save data into the individualsalerecord CSV
	* @param quantity the amount of the menu item
	* @throws IOException error caught
	*/
	public static void appendIndividualSaleRecord(String menuItem, int quantity) throws IOException
	{   
	    data = menuItem + "," + quantity + "," + getDate();
	    CSVWriter write = new CSVWriter("src/resource/individualsalerecord.csv", data);
	}
	
	/**
	* public method for printInvoice method in the order class 
	* to append new revenue record in the CSV for the sale revenue report
	* @param netSales is the net price of each invoice printed
	* the method append the netSale, date and uses the CSVWriter class to save data into the revenuerecord CSV
	* @throws IOException error caught
	*/
	public static void appendRevenueRecord(double netSales) throws IOException
	{   
	    data = netSales + "," + getDate();
	    CSVWriter write = new CSVWriter("src/resource/revenuerecord.csv", data);
	}
}
