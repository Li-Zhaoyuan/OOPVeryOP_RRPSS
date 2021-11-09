package tableandreservation;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationApp {

	public static void main(String[] args) {
		
	ReservationList mainReservation = new ReservationList();
	
	//LocalDate date = LocalDate.of(2021, 11, 22);
	//LocalTime time = LocalTime.of(23, 11);
	String date = "20/11/2021";
	String time = "23:11";
	int noOfPax = 10;
	String name = "Yuh Horng";
	int contact = 96735940;
	
	String date1 = "20/11/2021";
	String time1 = "23:11";
	int noOfPax1 = 10;
	String name1 = "Yuh Horng";
	int contact1 = 96735941;
	
	//tableNum = mainReservation.getTableNum(date, time, noOfPax);
	//System.out.println(tableNum);
	
	mainReservation.addReservation(date, time, noOfPax, name, contact);
	mainReservation.addReservation(date1, time1, noOfPax1, name1, contact1);
	
	mainReservation.printReservationList();
	
	}
	
}
