package main;
import java.io.*;

public class Read_Revenue {
	public static void main(String[] args) {
		double TotalPrice = 0;
		
		String file = "src/resource/revenue.csv";
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
			// for x in menuItems
			while((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				
				if (row[0].compareTo("Ice Cream") == 0) {
					System.out.printf("%s\n", row[1]);
					TotalPrice += Double.valueOf(row[1]);
				}		
			}
			System.out.printf("The Total Price: %.2f", TotalPrice);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
