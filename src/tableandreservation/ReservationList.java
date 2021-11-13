package tableandreservation;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
ReservationList - List Of Reservations
@author Lim Yuh Horng, Lim Rui An, Ryan
@version 1.0
@since 2021-10-20
*/

public class ReservationList {
	
	/**
	 * ArrayList of Reservation to store active reservations in the system
	 */
	private ArrayList<Reservation> listOfReservation = new ArrayList<Reservation>();
	
	/**
	 * LocalDate to store the formatted date of reservation
	 */
	private LocalDate parsedDate;
	
	/**
	 * LocalTime to store the formatted time of reservation
	 */
	private LocalTime parsedTime;
	
	/**
	 * DateTimeFormatter to store the format for date of reservation
	 */
	private DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d/MM/yyyy");
	
	/**
	 * DateTimeFormatter to store the format for time of reservation
	 */
	private DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H:mm");

	/**
	 * Add reservation into Reservation List
	 * @param date - Date of Reservation
	 * @param time - Time of Reservation
	 * @param noOfPax - Number of people
	 * @param customerName - Name of customer
	 * @param contact - Contact number of customer
	 * @return True or False (Success or Unsuccessful)
	 */
	public boolean addReservation(LocalDate date, LocalTime time, int noOfPax, String customerName, int contact) {
		
		int tableNum;
		boolean success = false;
		
		//Check if there is reservation under this contact number
		for (int i = 0; i < listOfReservation.size(); i++) {
			if (listOfReservation.get(i).getContact() == contact) {
				System.out.println("There is already a reservation made under this contact number");
				return success;
			}
		}
		
		//Get Table Number for this reservation
		tableNum = getTableNum(date, time, noOfPax);
		
		//If tableNum == -1 means no table available for this reservation
		if (tableNum == -1) {
			System.out.println("No table available");
			return success;
		}
		
		//Add Reservation into Reservation
		listOfReservation.add(new Reservation(date, time, noOfPax, customerName, contact, tableNum));
		success = true;
		
		return success;
	}
	
	/**
	 * Remove Reservation from List
	 * @param contact - contact number used to make reservation
	 * @return true or false (removed or not exist)
	 */
	public boolean removeReservation(int contact) {
		
		boolean removed = false;
		int index;
		
		//Get index of reservation that matches this contact number
		index = checkReservation(contact);
		if (index == -1) {
			return removed;
		}
		
		//Remove reservation from Reservation list
		removed = removeReservationWithIndex(index);
		
		return removed;
	}
	
	/**
	 * Remove Expired Reservations
	 * reservations that is more than 2 hrs past the reservation time
	 */
	public void removeExpiredReservation() {
		
		for (int i = 0; i < listOfReservation.size(); i++) {
			if (listOfReservation.get(i).getDate().isBefore(LocalDate.now())) {
				listOfReservation.remove(i);
				i--;
			}
			else if(listOfReservation.get(i).getDate().isEqual(LocalDate.now())) {
				if (listOfReservation.get(i).getTime().plusHours(2).isBefore(LocalTime.now())) {
					listOfReservation.remove(i);
					i--;
				}
			}
		}
	}
	
	/**
	 * Check if there is reservation under this contact number
	 * @param contact - contact number to check for in Reservation list
	 * @return index of contact number or -1 (if does not exist)
	 */
	public int checkReservation(int contact) {
		
		int index = -1;
		
		//Check if contact number exist in Reservation, if it does, get the index number
		for (int i = 0; i < listOfReservation.size(); i++) {
			if (listOfReservation.get(i).getContact() == contact) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	/**
	 * Remove reservation from Reservation list with Index
	 * @param index - index of reservation in Reservation list
	 * @return true or false (removed or invalid index)
	 */
	public boolean removeReservationWithIndex (int index) {
		
		//Check if index is valid
		if (index < 0 || index >= listOfReservation.size()) {
			return false;
		}
		
		//Remove reservation from list
		else {
			listOfReservation.remove(index);
			return true;
		}
	}
	
	/**
	* Public void function to be used in tandem with csv loading
	* Generates a Reservation object and inserts it into the list based on a csv entry
	* @param entry represents a singular row entry of the csv file, parameters of a Reservation
	*/
	public void createReservationFromArray(ArrayList<String> entry) {
		// The csv data should be curated beforehand, hence we need no error check here
		// Instantiate and add the new Reservation
		listOfReservation.add(new Reservation(LocalDate.parse(entry.get(0), formatterDate), LocalTime.parse(entry.get(1), formatterTime), Integer.parseInt(entry.get(5)), entry.get(2), Integer.parseInt(entry.get(3)), Integer.parseInt(entry.get(4))));
	}
	
	/**
	 * Check if Date entered is Valid
	 * @param date - date of reservation
	 * @return valid or not (true or false)
	 */
	public boolean isValidDate(String date) {

        boolean valid = false;

		//Try to parse date in d/MM/yyyy format and catch any parse exception
        try {
        	parsedDate = LocalDate.parse(date, formatterDate);
            valid = true;

        } catch (DateTimeParseException e) {
        	//FOR DEBUGGING
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
		
		//Try to parse time in H:mm format and catch any parse exception
        try {
    		parsedTime = LocalTime.parse(time, formatterTime);
            valid = true;

        } catch (DateTimeParseException e) {
        	//FOR DEBUGGING
            //e.printStackTrace();
        	System.out.println("Invalid Time");
            valid = false;
        }
		
		return valid;
		
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
				LocalTime endTime = time.plusHours(2);
				if (time.equals(listOfReservation.get(i).getTime()) || (time.isAfter(listOfReservation.get(i).getTime()) && time.isBefore(listOfReservation.get(i).getTime().plusHours(2))) || (endTime.isAfter(listOfReservation.get(i).getTime()) && endTime.isBefore(listOfReservation.get(i).getTime().plusHours(2)))) {
					//Set that table to unavailable
					listOfTables.setUnavailable(listOfReservation.get(i).getTableNum());
				}
			}
		}
		
		return listOfTables.getAvailableTable(noOfPax);
		
	}
	
	/**
	 * Print reservation with given index
	 * @param index - index of reservation in reservation list
	 */
	public void printReservationWithIndex(int index) {
		
		String date, time, sNumOfPax, customerName, sTableNum;
		int numOfPax, tableNum;
		
		date = listOfReservation.get(index).getStringDate();
		time = listOfReservation.get(index).getStringTime();
		numOfPax = listOfReservation.get(index).getNoOfPax();
		sNumOfPax = String.valueOf(numOfPax);
		customerName = listOfReservation.get(index).getCustomerName();
		tableNum = listOfReservation.get(index).getTableNum();
		sTableNum = String.valueOf(tableNum);
		
		System.out.println("Reservation for " + customerName + " is on " + date + " at " + time + " for " + sNumOfPax + " peoples \nTable Number: " + sTableNum );
		
	}
	
	/**
	 * Print Reservation List
	 */
	public void printReservationList() {
	
		String date, time, sNumOfPax, customerName, sTableNum, sContact;
		int numOfPax, tableNum, contact;
		
		for (int i = 0; i < listOfReservation.size(); i++) {
			date = listOfReservation.get(i).getStringDate();
			time = listOfReservation.get(i).getStringTime();
			numOfPax = listOfReservation.get(i).getNoOfPax();
			sNumOfPax = String.valueOf(numOfPax);
			customerName = listOfReservation.get(i).getCustomerName();
			contact = listOfReservation.get(i).getContact();
			sContact = String.valueOf(contact);
			tableNum = listOfReservation.get(i).getTableNum();
			sTableNum = String.valueOf(tableNum);
			
			System.out.println(String.valueOf(i+1) + ". Date:" + date + " Time:" + time + " Name:" + customerName + " Contact:" + sContact
					+ " Table Number:" + sTableNum + " NumOfPax:" + sNumOfPax);
		}
	}
	
	/**
	* Public ArrayList<Reservation> function that returns a copy of the internal list
	*/
	public ArrayList<Reservation> getlistOfReservation(){
		return this.listOfReservation;
	}
}
