package main;


public class Table {

	private int tableID;
	private int seatingCapacity;
	private boolean availabilityStatus;
	
	//constructors for Table
	public Table(int tableID, int seatingCapacity) {
		
		this.tableID = tableID;
		this.seatingCapacity = seatingCapacity;
		this.availabilityStatus = true;
		
	}
	
	//return tableID
	public int getTableID() {
		return tableID;
	}
	
	//return seatingCapacity
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	
	//return availabilityStatus
	public boolean isAvailable() {
		return availabilityStatus;
	}
	
	//set availabilityStatus to true
	public void makeAvailable() {
		this.availabilityStatus = true;
	}
	
	//set availabilityStatus to false
	public void makeNotAvailable() {
		this.availabilityStatus = false;
	}
	
}

