package tableandreservation;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
	* @return the reservationList object
	*/
	public ReservationList getReservationList() {
		return mainReservation;
	}
	

}
