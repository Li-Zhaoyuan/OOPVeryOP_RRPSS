package tableandreservation;

/**
Table - Table Object
@author Lim Yuh Horng
@version 1.0
@since 2021-10-20
*/

public class Table {
	
	/**
	 * int to store table num
	 */
	private int tableNum;
	
	/**
	 * int to store seating capacity of table
	 */
	private int seatingCapacity;
	
	/**
	 * boolean to store if table is available or unavailable
	 */
	private boolean available;
	
	/**
	 * Constructor for Table object
	 * Creates a new Table with the given information
	 * @param tableNum - Table number
	 * @param seatingCapacity - Number of seats for this table
	 */
	public Table(int tableNum, int seatingCapacity) {
		
		this.tableNum = tableNum;
		this.seatingCapacity = seatingCapacity;
		this.available = true;
		
	}
	
	/**
	 * Get Table Number
	 * @return Table Number
	 */
	public int getTableNum() {
		return tableNum;
	}
	
	/**
	 * Get Seating Capacity of Table
	 * @return Seating Capacity of Table
	 */
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	
	
	/**
	 * Set Available to False
	 */
	public void setUnavailable() {
		this.available = false;
	}
	
	/**
	 * Get Availability of Table
	 * @return Availability of Table
	 */
	public boolean getAvailable() {
		return available;
	}
	
	
}

