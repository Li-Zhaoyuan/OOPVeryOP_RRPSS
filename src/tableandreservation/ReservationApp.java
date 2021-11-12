package tableandreservation;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationApp {

	public static void main(String[] args) {
		
	ReservationList mainReservation = new ReservationList();
	
	mainReservation.getInputs();
	
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
	
	mainReservation.printReservationList();
	
	}
	
}
