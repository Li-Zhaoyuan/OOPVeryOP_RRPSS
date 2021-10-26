package salerevenuereport;

import menuitem.MenuItem;
import menuitem.MenuItemFactory;
import miscellaneous.CSVLoader;

public class GenerateReport {
	public static void main(String[] args) {
		double totalSales, MonthlySales, totalRevenue = 0;
		int countQuantity;
		String monthStr;
		String line = "";
		
		CSVLoader Rldr = new CSVLoader("src/resource/revenue.csv", true);
		RevenueRecordFactory rrf = new RevenueRecordFactory();
		rrf.constructFromCSV(Rldr);
		
		CSVLoader MIldr = new CSVLoader("src/resource/menuitems.csv", true);
		MenuItemFactory mif = new MenuItemFactory();
		mif.constructFromCSV(MIldr);
		
		// for every month
		for(int month=0; month<=11; month++)
		{
			MonthlySales = 0;
			switch (month) {
				case 0: monthStr = "January";
				 	break;
				case 1: monthStr = "February";
					break;
				case 2: monthStr = "March";
					break;
				case 3: monthStr = "April";
					break;
				case 4: monthStr = "May";
					break;
				case 5: monthStr = "June";
					break;
				case 6: monthStr = "July";
					break;
				case 7: monthStr = "August";
					break;
				case 8: monthStr = "September";
					break;
				case 9: monthStr = "October";
					break;
				case 10: monthStr = "November";
					break;
				default: monthStr = "December";
			}
			
			System.out.printf("\n\n\t%s\n----------------------------\n", monthStr);
			
			// for item in menuItems
			for (MenuItem m : mif.getItemList()) {
				totalSales = 0;
				countQuantity = 0;
				
				// split line row of name, quantity, price, year, month, day
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
					MonthlySales += totalSales;
					System.out.printf("%s x %d,     Total:$%.2f\n", m.getName(), countQuantity, totalSales);
				}
			}
			
			if (MonthlySales != 0)
			{
				totalRevenue += MonthlySales;
				System.out.printf("\nTotal Sales for %s: $%.2f\n", monthStr, MonthlySales);
			}
		}
		// To add in Total Discount and display after discount revenue
		System.out.printf("*******************************"
				+ "\nTotal Revenue: $%.2f\n", totalRevenue);
	}
}
