package tableandreservation;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ReservationList {
	
	private ArrayList<Reservation> listOfReservation = new ArrayList<Reservation>();
	
	private LocalDate parsedDate;
	private LocalTime parsedTime;
	
	private static final String LastReservationTime = "22:00";
	
	DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d/MM/yyyy");
	DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H:mm");

	public boolean addReservation(String date, String time, int noOfPax, String customerName, int contact) {
		
		int tableNum;
		boolean success = false;
		
		//Check if there is reservation under this contact number
		for (int i = 0; i < listOfReservation.size(); i++) {
			if (listOfReservation.get(i).getContact() == contact) {
				System.out.println("There is already a reservation made under this contact number");
				return success;
			}
		}
		
		//Check if the date and time entered is valid
		if (!isValidDateTime(date, time)) {
			return success;
		}
		
		//Get Table Number for this reservation
		tableNum = getTableNum(parsedDate, parsedTime, noOfPax);
		
		//If tableNum == -1 means no table available for this reservation
		if (tableNum == -1) {
			System.out.println("No table available");
			return success;
		}
		
		//Add Reservation into Reservation
		listOfReservation.add(new Reservation(parsedDate, parsedTime, noOfPax, customerName, contact, tableNum));
		success = true;
		
		return success;
	}
	
	/**
	 * Check if Date entered is Valid
	 * @param date - date of reservation
	 * @return valid or not (true or false)
	 */
	public boolean isValidDate(String date) {

        boolean valid = false;

        try {
        	parsedDate = LocalDate.parse(date, formatterDate);
            valid = true;

        } catch (DateTimeParseException e) {
            //e.printStackTrace();
        	System.out.println("Invalid Date");
            valid = false;
        }

        return valid;
    }
	
	/**
	 * Check if Time entered is Valid
	 * @param time - time of reservation
	 * @return valid or not (true or false)
	 */
	public boolean isValidTime(String time) {
		
		boolean valid = false;
		
        try {
    		parsedTime = LocalTime.parse(time, formatterTime);
            valid = true;

        } catch (DateTimeParseException e) {
            //e.printStackTrace();
        	System.out.println("Invalid Time");
            valid = false;
        }
		
		return valid;
		
	}
	
	/**
	 * Check if Date and Time entered is Valid and after now
	 * @param date - date of reservation
	 * @param time - time of reservation
	 * @return valid or not (true or false)
	 */
	public boolean isValidDateTime(String date, String time) {
		
		//Check if format of date entered is valid
		if (!isValidDate(date)) {
			return false;
		}
		
		//Check if format of time entered is valid
		else if (!isValidTime(time)) {
			return false;
		}
		
		if (parsedTime.isAfter(LocalTime.parse(LastReservationTime, formatterTime))) {
			System.out.println("Reservation Time cannot be later than 22:00hrs");
			return false;
		}
		
		//Get difference in date
		int datediff = parsedDate.compareTo(LocalDate.now());
		
		//Check if the date is the same
		if (datediff == 0) {
			//Check if reservation time is before now
			if (parsedTime.isBefore(LocalTime.now())) {
				System.out.println("Reservation can only be made in advance");
				return false;
			}
		}
		//Check if date is before today
		else if (datediff < 0) {
			System.out.println("Reservation can only be made in advance");
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * Get Table Number to be allocated to this reservation
	 * @param date - date of reservation
	 * @param time - time of reservation
	 * @param noOfPax - number of people 
	 * @return Table Number
	 */
	public int getTableNum(LocalDate date, LocalTime time, int noOfPax) {
		
		TableList listOfTables = new TableList();
		
		//Loop through Reservation List
		for (int i = 0; i < listOfReservation.size(); i++) {
			//Check if date is same date in reservation
			if (listOfReservation.get(i).getDate().equals(date)) {
				//Check if time is between 2hrs of reservation
				if (time.equals(listOfReservation.get(i).getTime()) || (time.isAfter(listOfReservation.get(i).getTime()) && time.isBefore(listOfReservation.get(i).getTime().plusHours(2)))) {
					//Set that table to unavailable
					listOfTables.setUnavailable(listOfReservation.get(i).getTableNum());
				}
			}
		}
		
		return listOfTables.getAvailableTable(noOfPax);
		
	}
	
	public void getInputs() {
		
		String date;
		String time;
		int noOfPax = 0;
		String customerName;
		int contact;
		int tableNum;
		
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.print("Please enter Reservation Date in this format dd/mm/yyyy (eg. 20/10/2021): ");
			date = sc.nextLine();
		} while (!isValidDate(date));
		
		do {
			System.out.print("Please enter Reservation Time in this format hh:mm (eg. 20:10): ");
			time = sc.nextLine();
		} while (!isValidTime(time));
		
		do {
			System.out.print("Please enter the number of people you are reserving for (max 10): ");
			if(sc.hasNextInt()){
				noOfPax = sc.nextInt();
				if (noOfPax > 10) {
					System.out.println("Number of people cannot exceed 10.");
				}
			}
			else{
				System.out.println("Please only enter number.");
			}
		} while (!sc.hasNextInt() || noOfPax > 10);	
		
	}
	
	public void printReservationList() {
	
		String date;
		String time;
		int numOfPax;
		String sNumOfPax;
		String customerName;
		int contact;
		String sContact;
		int tableNum;
		String sTableNum;
		
		for (int i = 0; i < listOfReservation.size(); i++) {
			date = listOfReservation.get(i).getDate().toString();
			time = listOfReservation.get(i).getTime().toString();
			numOfPax = listOfReservation.get(i).getNoOfPax();
			sNumOfPax = String.valueOf(numOfPax);
			customerName = listOfReservation.get(i).getCustomerName();
			contact = listOfReservation.get(i).getContact();
			sContact = String.valueOf(contact);
			tableNum = listOfReservation.get(i).getTableNum();
			sTableNum = String.valueOf(tableNum);
			
			System.out.println(String.valueOf(i+1) + ". Date:" + date + " Time:" + time + " Name:" + customerName + " Contact:" + sContact
					+ " Table Number:" + sTableNum);
		}
	}
}
