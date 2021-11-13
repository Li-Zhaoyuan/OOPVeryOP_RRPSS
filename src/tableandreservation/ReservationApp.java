package tableandreservation;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import miscellaneous.CSVLoader;
import miscellaneous.CSVWriter;

/**
ReservationApp - System that manages reservations to tables
@author Lim Yuh Horng, Lim Rui An, Ryan
@version 1.0
@since 2021-10-20
*/
public class ReservationApp {
	/**
	* ReservationList object that stores active reservations in the system
	*/
	private static final String csvPath = "src/resource/reservations.csv";

	/**
	* ReservationList object that stores active reservations in the system
	*/
	private ReservationList mainReservation;
	
	/**
	* CSVLoader that holds read csv data for reservations
	*/
	private CSVLoader ldr;
	
	/**
	* CSVWriter that is used to write back into the csv
	*/
	private CSVWriter wtr;
	
	/**
	* Temporary main function for testing
	*/
	public static void main(String[] args) {
		ReservationApp ra = new ReservationApp();
		
		// Perform some input testing
		ra.testMain();
		
		ra.printReservations();
		
		// Save the reservations to csv when done
		ra.saveReservations();
	}
	
	/**
	* Constructor of the ReservationApp class
	*/
	public ReservationApp() {
		// Initialize required variables
		mainReservation = new ReservationList();
		// Load Reservations
		loadReservations();
	}
	
	/**
	* Public void function to read the csv file containing information of reservations
	*/
	public void loadReservations() {
		// Initialize Loader
		ldr = new CSVLoader(csvPath, true);
		
		// Iterate the data and construct a reservation for each row
		for (ArrayList<String> l : ldr.getCSVData()) {
			// Add a the reservation to RL, bypassing checks and table assignment
			// This is because the data saved to the csv should be a priori curated
			// Meaning that no invalid Reservations should be in the CSV
			mainReservation.createReservationFromArray(l);
		}
	}
	
	/**
	* Public void function to print the list of reservations
	* USED FOR DEBUGGING
	*/
	public void printReservations()	{
		mainReservation.printReservationList();
	}
	
	/**
	* Public void function to update the csv file used for the construction of reservations
	*/
	public void saveReservations() {
		// Initialize Writer
		wtr = new CSVWriter();
		
		ArrayList<String> rData = new ArrayList<String>();
		// Read the reservation list and generate a comma delimited string per object
		for (Reservation r : mainReservation.getlistOfReservation()) {
			String tmp = "";
			tmp += r.getStringDate() + ldr.delimiter;
			tmp += r.getStringTime() + ldr.delimiter;
			tmp += r.getCustomerName() + ldr.delimiter;
			tmp += Integer.toString(r.getContact()) + ldr.delimiter;
			tmp += Integer.toString(r.getTableNum()) + ldr.delimiter;
			tmp += Integer.toString(r.getNoOfPax());
			rData.add(tmp);
		}
		
		// Use the writer and update the csv
		// Set to not append the header, rewrite a new file
		wtr.writeFile(csvPath, false, String.join(ldr.delimiter, ldr.getCSVHeader()));
		// Insert data making use of the writer
		for (String s : rData)
			wtr.writeFile(csvPath, true, s);
	}
	
	/**
	* Public ReservationList function that returns the internal ReservationList object
	*/
	public ReservationList getReservationList() {
		return mainReservation;
	}
	
	/**
	* Testing function that used to be the original static main
	*/
	public void testMain() {
		
		//mainReservation = new ReservationList();
		
		//mainReservation.getInputs();
		
		String date = "21/11/2021";
		String time = "21:11";
		int noOfPax = 7;
		String name = "Yuh Horng";
		int contact = 96735940;
		
		mainReservation.addReservation(date, time, noOfPax, name, contact);
		
		String date1 = "20/11/2021";
		String time1 = "21:12";
		int noOfPax1 = 7;
		String name1 = "Yuh Horng";
		int contact1 = 96735941;
		
		mainReservation.addReservation(date1, time1, noOfPax1, name1, contact1);
		
		String date2 = "20/11/2021";
		String time2 = "21:13";
		int noOfPax2 = 7;
		String name2 = "Yuh Horng";
		int contact2 = 96735942;
		
		mainReservation.addReservation(date2, time2, noOfPax2, name2, contact2);
		
		String date3 = "20/11/2021";
		String time3 = "21:14";
		int noOfPax3 = 7;
		String name3 = "Yuh Horng";
		int contact3 = 96735943;
		
		mainReservation.addReservation(date3, time3, noOfPax3, name3, contact3);
		
		String date4 = "20/11/2021";
		String time4 = "22:01";
		int noOfPax4 = 7;
		String name4 = "Yuh Horng";
		int contact4 = 96735944;
		
		mainReservation.addReservation(date4, time4, noOfPax4, name4, contact4);
		
		String date5 = "20/11/2021";
		String time5 = "22:00";
		int noOfPax5 = 7;
		String name5 = "Yuh Horng";
		int contact5 = 96735945;
		
		mainReservation.addReservation(date5, time5, noOfPax5, name5, contact5);
		
		//mainReservation.printReservationList();
	
	}

}
