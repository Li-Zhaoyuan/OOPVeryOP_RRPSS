package main;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
	
	private LocalDateTime datetime;
	private int noOfSeats;
	private String customerName;
	private int contact;
	private Table allocatedTable;

	//Constructor for Reservation
	public Reservation(int noOfSeats, String customerName, int contact) {
		
		this.datetime = LocalDateTime.now();
		this.noOfSeats = noOfSeats;
		this.customerName = customerName;
		this.contact = contact;
		this.allocatedTable = null;
		
	}
	
	//Assign table to reservation
	public void setAllocatedTable(Table table) {
		this.allocatedTable = table;
	}
	
	public String getDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatter.format(datetime);
	}
	
	public String getTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
		return formatter.format(datetime);
	}
	
	public String getDateAndTime() {
		return getDate() + " " + getTime();
	}
}





