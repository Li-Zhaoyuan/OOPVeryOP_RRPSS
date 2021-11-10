/**
 GenerateReport - Generate a monthly revenue report or revenue report for the day from CSV
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
	/**
	* double variable that stores sales of each individual menu items
	*/
	private static double totalSales;
	
	/**
	* double variable that stores sales in each month
	*/
	private static double monthlySales;
	
	/**
	* double variable that stores sales of the day
	*/
	private static double daySales;
	
	/**
	* double variable that stores revenue of the year
	*/
	private static double totalRevenue;
	
	/**
	*  String variable that stores Month for displays
	*/
	private static String monthStr[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	/**
	*  integer variable that stores quantity of each menu item for each month or day
	*/
	private static int countQuantity;
	
	/**
	*  integer variable that stores year
	*/
	private static int year;
	
	/**
	*  integer variable that stores month
	*/
	private static int month;
	
	/**
	*  integer variable that stores day
	*/
	private static int day;
	
	/**
	*  RevenueRecordFactory class to load revenue record from CSV
	*/
	private static RevenueRecordFactory rrf;
	
	/**
	*  IndividualSaleRecordFactory class to load individual sale record from CSV
	*/
	private static IndividualSaleRecordFactory isrf;
	
	/**
	*  MenuItemFactory class to load menu item from CSV
	*/
	private static MenuItemFactory mif;
	
	
	/**
	* Generate Revenue Report for the day
	* The sales of each menu item will be reported if the day, month and year in the individualsalerecord CSV matches the current day, month and year
	* It will print out each individual item in the following format:
	* quantity x menu item name............totalSales 
	* 
	* The daySales will be add up together if the day, month and year in the revenuerecord CSV matches the current day, month and year
	*/
	public static void GenerateReportForTheDay() {
		year = Calendar.getInstance().get(Calendar.YEAR);
		month = Calendar.getInstance().get(Calendar.MONTH);
	    day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		rrf = new RevenueRecordFactory();
		isrf = new IndividualSaleRecordFactory();
		mif = new MenuItemFactory();
		
		System.out.printf("\n---------------SALE REVENUE REPORT FOR %d %s %d---------------\n", day, monthStr[month], year);
	    
		for (MenuItem m : mif.getItemList()) {
			totalSales = 0;
			countQuantity = 0;

			for (IndividualSaleRecord isr : isrf.getRecordList()){
				if (isr.getName().compareTo(m.getName()) == 0 && Integer.valueOf(isr.getDay()) == day && Integer.valueOf(isr.getMonth()) == month && Integer.valueOf(isr.getYear()) == year) {
					countQuantity += Integer.valueOf(isr.getQuantity());
					totalSales += Integer.valueOf(isr.getQuantity()) * Double.valueOf(m.getPrice());
				}
			}
			
			if (countQuantity != 0){
				System.out.printf("%d x %-20s $%.2f\n", countQuantity, m.getName(), totalSales);
			}
		}
		
		for (RevenueRecord rr : rrf.getRecordList()){
			if (Integer.valueOf(rr.getDay()) == day && Integer.valueOf(rr.getMonth()) == month && Integer.valueOf(rr.getYear()) == year) {
				daySales += Double.valueOf(rr.getNetSales());
			}		
		}
		
		if (daySales != 0){
			System.out.printf("*******************************" + "\nTotal Net Sales: $%.2f\n", daySales);
		}
		else {
			System.out.println("No Record");
		}
	}
	
	/**
	* Generate Monthly Revenue Report
	* The monthly sales of each menu item of the current year will be reported
	* It will print out each individual item of every month in the following format:
	* quantity x menu item name............totalSales 
	* 
	* Total monthlySales will be add up together if the month and year in the revenuerecord CSV matches the current month and year
	* The monthlySales will be add up to calculate the total revenue of the year
	*/
	public static void GenerateMonthlyReport() {
		year = Calendar.getInstance().get(Calendar.YEAR);
		month = Calendar.getInstance().get(Calendar.MONTH);
	    day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		rrf = new RevenueRecordFactory();
		isrf = new IndividualSaleRecordFactory();
		mif = new MenuItemFactory();
		
		System.out.printf("\n---------------SALE REVENUE REPORT FOR %d---------------\n", year);

		for(int monthly=0; monthly<=11; monthly++){
			monthlySales = 0;
			totalRevenue = 0;
		
			System.out.printf("\n\n\t%s\n-------------------------------\n", monthStr[monthly]);
			
			for (MenuItem m : mif.getItemList()) {
				totalSales = 0;
				countQuantity = 0;
				
				for (IndividualSaleRecord isr : isrf.getRecordList()){
					if (isr.getName().compareTo(m.getName()) == 0 && Integer.valueOf(isr.getMonth()) == monthly && Integer.valueOf(isr.getYear()) == year) {
						countQuantity += Integer.valueOf(isr.getQuantity());
						totalSales += Integer.valueOf(isr.getQuantity()) * Double.valueOf(m.getPrice());
					}
				}
				
				if (countQuantity != 0){
					System.out.printf("%d x %-20s $%.2f\n", countQuantity, m.getName(), totalSales);
				}
			}
			
			for (RevenueRecord rr : rrf.getRecordList()){
				if (Integer.valueOf(rr.getMonth()) == monthly && Integer.valueOf(rr.getYear()) == year) {
					monthlySales += Double.valueOf(rr.getNetSales());
				}		
			}
			
			if (monthlySales != 0){
				System.out.printf("\nTotal Net Sales in %s: $%.2f\n", monthStr[monthly], monthlySales);
				totalRevenue += monthlySales;
			}
			else {
				System.out.println("No Record");
			}
		}
		System.out.printf("*******************************" + "\nTotal Revenue in %d: $%.2f\n\n", year, totalRevenue);
	}
}
