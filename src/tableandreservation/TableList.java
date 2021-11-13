package tableandreservation;
import java.util.ArrayList;

/**
TableList - List of Tables
@author Lim Yuh Horng
@version 1.0
@since 2021-10-20
*/

public class TableList {
	
	/**
	 * ArrayList of Table to store tables in system
	 */
	private ArrayList<Table> listOfTables;
	
	/**
	 * Constructor for TableList object
	 * Creates a new TableList with 5 tables of each seating capacity (2,4,6,8,10)
	 * With a running table number
	 */
	public TableList() {
		//Initialize required variables
		listOfTables = new ArrayList<Table>();
		
		int tableNum = 1;
		int seatingCapacity;
		
		//Create 5 tables for each seating capacity (2,4,6,8,10) with running table number
		for (int i = 0; i < 5; i++) {
			
			seatingCapacity = (i+1)*2;
			
			for (int j = 0; j < 5; j++) {
			
				Table table = new Table(tableNum, seatingCapacity);
				listOfTables.add(table);
				tableNum++;
				
			}
		}
	}
	
	/**
	 * Get Table Number of that can accommodate the number of people dining
	 * @return Table Number or -1 (if no table available)
	 */
	public int getAvailableTable(int numOfPax) {
	
		for (int i = 0; i < listOfTables.size(); i++) {
			if (listOfTables.get(i).getAvailable() == true && listOfTables.get(i).getSeatingCapacity() >= numOfPax) {
				return listOfTables.get(i).getTableNum();
			}
		}
		return -1;
	}
	
	/**
	 * Set Table to be Unavailable
	 * @param tableNum - Table Number
	 */
	public void setUnavailable(int tableNum) {
		listOfTables.get(tableNum-1).setUnavailable();
	}
	
	/**
	 * Get List Of Tables
	 * @return List Of Tables
	 */
	public ArrayList<Table> getListOfTables(){
		return listOfTables;
	}
	
}
