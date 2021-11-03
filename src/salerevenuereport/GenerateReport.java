/**
 GenerateReport - Generate a monthly revenue report from CSV as well as total revenue
 @author Heng Zheng Ping
 @version 1.0
 @since 2021-11-03
*/

package salerevenuereport;

import menuitem.MenuItem;
import menuitem.MenuItemFactory;
import miscellaneous.CSVLoader;

public class GenerateReport {
	public static void main(String[] args) {
		double totalSales, monthlySales, monthlyDiscount, totalRevenue = 0, totalDiscount = 0;
		String monthStr[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int countQuantity;
		String line = "";
		
		RevenueRecordFactory rrf = new RevenueRecordFactory();
		DiscountRecordFactory drf = new DiscountRecordFactory();
		MenuItemFactory mif = new MenuItemFactory();
		
		// for every month
		for(int month=0; month<=11; month++)
		{
			monthlySales = 0;
			monthlyDiscount = 0;
			
			System.out.printf("\n\n\t%s\n----------------------------\n", monthStr[month]);
			
			// for item in menuItems
			for (MenuItem m : mif.getItemList()) {
				totalSales = 0;
				countQuantity = 0;
				
				// split line row of name, quantity, year, month, day
				for (RevenueRecord rr : rrf.getItemList())
				{
					// if name = item
					if (rr.getName().compareTo(m.getName()) == 0 && Integer.valueOf(rr.getMonth()) == month) {
						// Add to totalSales and count quantity of each item
						countQuantity += Integer.valueOf(rr.getQuantity());
						totalSales += Integer.valueOf(rr.getQuantity()) * Double.valueOf(m.getPrice());
					}
				}
				
				if (countQuantity != 0)
				{
					monthlySales += totalSales;
					System.out.printf("%d x %s, $%.2f\n", countQuantity, m.getName(), totalSales);
				}
			}
			
			// split line row of discount, year, month, day
			for (DiscountRecord dr : drf.getItemList())
			{
				// if name = item
				if (Integer.valueOf(dr.getMonth()) == month) {
					// Add to discount of each month
					monthlyDiscount += Double.valueOf(dr.getDiscount());
				}		
			}
			
			
			if (monthlySales != 0)
			{
				monthlySales -= monthlyDiscount;
				System.out.printf("\nTotal Discount Applied in %s: $%.2f", monthStr[month], monthlyDiscount);
				System.out.printf("\nTotal Sales After Discount in %s: $%.2f\n", monthStr[month], monthlySales);
				totalRevenue += monthlySales;
				totalDiscount += monthlyDiscount;
			}
		}
		// To add in Total Discount and display after discount revenue
		System.out.printf("*******************************"
				+ "\nTotal Discount Applied: $%.2f"
				+ "\nTotal Revenue After Discount: $%.2f\n", totalDiscount, totalRevenue);
	}
}
