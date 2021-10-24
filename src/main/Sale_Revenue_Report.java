package main;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.io.*;

public class Sale_Revenue_Report {
	
	public static void append(String menuItem, double price) throws IOException
	{
		FileWriter fw = null; 
		BufferedWriter bw = null; 
		PrintWriter pw = null;
		
		// example formating - for more, refer to Java API for SimpleDateFormat class
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
	    // Using Calendar class
	    Calendar cal = Calendar.getInstance();
        // get Date from calendar
	    Date dateNow = cal.getTime();
	    System.out.println(dateFormatter.format(dateNow));
	    // extract individual fields from calendar
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH);      // NOTE!!! : Month from 0 to 11
	    
	    try { 
			fw = new FileWriter("src/resource/revenue.csv", true); 
			bw = new BufferedWriter(fw); 
			pw = new PrintWriter(bw); 
			
			pw.printf("%s, %.2f, %d, %d\n", menuItem, price, year, month);
			
			System.out.println("Data Successfully appended into file"); 
			pw.flush(); 
		} 
	    finally { 
	    	try { 
		    		pw.close(); 
					bw.close(); 
					fw.close(); 
				} 
	    	catch (IOException io) {
	    			// can't do anything
	    		}	
	    }
	}
	
	public static void main(String args[]) {
		try {
			append("Ice Cream", 5.30);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
