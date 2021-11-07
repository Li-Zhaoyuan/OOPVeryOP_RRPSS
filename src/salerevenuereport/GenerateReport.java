/**
 GenerateReport - Generate a monthly revenue report from CSV as well as total revenue
 @author Heng Zheng Ping
 @version 1.0
 @since 2021-11-03
*/

package salerevenuereport;

import java.util.Calendar;

import menuitem.MenuItem;
import menuitem.MenuItemFactory;
import miscellaneous.CSVLoader;

public class GenerateReport {
	private double totalSales, monthlySales, daySales, totalRevenue = 0;
	private String monthStr[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private int countQuantity;
	
	private Calendar cal;
	private static int year;
	private static int month;
	private static int day;
	
	private RevenueRecordFactory rrf;
	private IndividualSaleRecordFactory isrf;
	private MenuItemFactory mif;
	
	
	public GenerateReport(Boolean dayOrMonth) {
		cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
	    day = cal.get(Calendar.DAY_OF_MONTH);
		
		rrf = new RevenueRecordFactory();
		isrf = new IndividualSaleRecordFactory();
		mif = new MenuItemFactory();
		
		if(dayOrMonth)
			GenerateReportForTheDay();
		else
			GenerateMonthlyReport();
	}
	
	public void GenerateReportForTheDay() {
		System.out.printf("\n---------------SALE REVENUE REPORT FOR %d %s %d---------------\n", day, monthStr[month], year);
	    
		for (MenuItem m : mif.getItemList()) {
			totalSales = 0;
			countQuantity = 0;
			
			// split line row of name, quantity, year, month, day
			for (IndividualSaleRecord isr : isrf.getRecordList())
			{
				// if name = item
				if (isr.getName().compareTo(m.getName()) == 0 && Integer.valueOf(isr.getDay()) == day && Integer.valueOf(isr.getMonth()) == month && Integer.valueOf(isr.getYear()) == year) {
					// Add to totalSales and count quantity of each item
					countQuantity += Integer.valueOf(isr.getQuantity());
					totalSales += Integer.valueOf(isr.getQuantity()) * Double.valueOf(m.getPrice());
				}
			}
			
			if (countQuantity != 0)
			{
				System.out.printf("%d x %-20s $%.2f\n", countQuantity, m.getName(), totalSales);
			}
		}
		
		// split line row of discount, year, month, day
		for (RevenueRecord rr : rrf.getRecordList())
		{
			// if name = item
			if (Integer.valueOf(rr.getDay()) == day && Integer.valueOf(rr.getMonth()) == month && Integer.valueOf(rr.getYear()) == year) {
				// Add to net sales of each month
				daySales += Double.valueOf(rr.getNetSales());
			}		
		}
		
		if (daySales != 0)
		{
			System.out.printf("*******************************"
					+ "\nTotal Net Sales: $%.2f\n", daySales);
		}
		else {
			System.out.println("No Record");
		}
	}
	
	public void GenerateMonthlyReport() {
		System.out.printf("\n---------------SALE REVENUE REPORT FOR %d---------------\n", year);
		// for every month
		for(int month=0; month<=11; month++)
		{
			monthlySales = 0;
			
			System.out.printf("\n\n\t%s\n-------------------------------\n", monthStr[month]);
			
			// for item in menuItems
			for (MenuItem m : mif.getItemList()) {
				totalSales = 0;
				countQuantity = 0;
				
				// split line row of name, quantity, year, month, day
				for (IndividualSaleRecord isr : isrf.getRecordList())
				{
					// if name = item
					if (isr.getName().compareTo(m.getName()) == 0 && Integer.valueOf(isr.getMonth()) == month && Integer.valueOf(isr.getYear()) == year) {
						// Add to totalSales and count quantity of each item
						countQuantity += Integer.valueOf(isr.getQuantity());
						totalSales += Integer.valueOf(isr.getQuantity()) * Double.valueOf(m.getPrice());
					}
				}
				
				if (countQuantity != 0)
				{
					System.out.printf("%d x %-20s $%.2f\n", countQuantity, m.getName(), totalSales);
				}
			}
			
			// split line row of discount, year, month, day
			for (RevenueRecord rr : rrf.getRecordList())
			{
				// if name = item
				if (Integer.valueOf(rr.getMonth()) == month && Integer.valueOf(rr.getYear()) == year) {
					// Add to net sales of each month
					monthlySales += Double.valueOf(rr.getNetSales());
				}		
			}
			
			if (monthlySales != 0)
			{
				System.out.printf("\nTotal Net Sales in %s: $%.2f\n", monthStr[month], monthlySales);
				totalRevenue += monthlySales;
			}
			else {
				System.out.println("No Record");
			}
		}
		// To add in Total Discount and display after discount revenue
		System.out.printf("*******************************"
				+ "\nTotal Revenue in %d: $%.2f\n\n", year, totalRevenue);
	}
}
