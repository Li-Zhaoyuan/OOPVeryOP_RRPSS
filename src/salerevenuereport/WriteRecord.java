/**
 WriteRecord - contains public methods for discounts and revenue records  
 @author Heng Zheng Ping
 @version 1.0
 @since 2021-11-03
*/

package salerevenuereport;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import miscellaneous.CSVWriter;

public class WriteRecord {
	private static String data;
	private static int year;
	private static int month;
	private static int day;
	
	private static String getDate() {
		// example formating - for more, refer to Java API for SimpleDateFormat class
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
	    
	    // Using Calendar class
	    Calendar cal = Calendar.getInstance();
	    
        // get Date from calendar
	    Date dateNow = cal.getTime();
	    
	    System.out.println(dateFormatter.format(dateNow));
	    
	    // extract individual fields from calendar
	    year = cal.get(Calendar.YEAR);
	    month = cal.get(Calendar.MONTH); // NOTE!!! : Month from 0 to 11
	    day = cal.get(Calendar.DAY_OF_MONTH);
	    
	    return year + "," + month + "," + day;
	}
	
	public static void appendRevenue(String menuItem, int quantity) throws IOException
	{   
	    data = menuItem + "," + quantity + "," + getDate();
	    CSVWriter write = new CSVWriter("src/resource/revenue.csv", data);
	}
	
	public static void appendDiscount(double discount) throws IOException
	{   
	    data = discount + "," + getDate();
	    CSVWriter write = new CSVWriter("src/resource/discount.csv", data);
	}
	
	// FOR TEST ONLY
	public static void main(String args[]) {
		try {
			appendDiscount(3.05);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
