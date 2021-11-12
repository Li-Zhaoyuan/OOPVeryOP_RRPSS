package tableandreservation;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Reservation {
	
	private LocalDate date;
	private LocalTime time;
	private int noOfPax;
	private String customerName;
	private int contact;
	private int tableNum;

	/**
	 * Constructor of Reservation object
	 * Creates a new Reservation with the given information
	 * @param date - date of reservation
	 * @param time - time of reservation
	 * @param noOfPax - number of people
	 * @param customerName - name of customer that made reservation
	 * @param contact - contact number of customer
	 * @param tableNum - table number allocated to this reservation
	 */
	public Reservation(LocalDate date, LocalTime time, int noOfPax, String customerName, int contact, int tableNum) {
		
		this.date = date;
		this.time = time;
		this.noOfPax = noOfPax;
		this.customerName = customerName;
		this.contact = contact;
		this.tableNum = tableNum;
	}
	
	/**
	 * Get Date of Reservation
	 * @return Date of Reservation
	 */
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * Get Time of Reservation
	 * @return Time of Reservation
	 */
	public LocalTime getTime() {
		return time;
	}
	
	/**
	 * Get Date of Reservation as a String
	 * @return Date of Reservation as a String
	 */
	public String getStringDate() {
		return date.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
	}
	
	/**
	 * Get Time of Reservation as a String
	 * @return Time of Reservation as a String
	 */
	public String getStringTime() {
		return time.format(DateTimeFormatter.ofPattern("H:mm"));
	}
	
	/**
	 * Get Name of Customer that made reservation
	 * @return Customer Name
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * Get Contact Number of Customer
	 * @return Contact Number
	 */
	public int getContact() {
		return contact;
	}
	
	/**
	 * Get Table Number allocated to this reservation
	 * @return Table Number
	 */
	public int getTableNum() {
		return tableNum;
	}
	
	/**
	 * Get Number of People for this reservation
	 * @return Number of People
	 */
	public int getNoOfPax() {
		return noOfPax;
	}

}





