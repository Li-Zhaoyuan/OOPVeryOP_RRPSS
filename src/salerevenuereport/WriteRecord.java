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
	
	public static void append(String menuItem, int quantity) throws IOException
	{
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
	    
	    data = menuItem + "," + quantity + "," + year + "," + month + "," + day;
	    CSVWriter write = new CSVWriter("src/resource/revenue.csv", data);
	}
	
	public static void main(String args[]) {
		try {
			append("Ice Cream", 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
