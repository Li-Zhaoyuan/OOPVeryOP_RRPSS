/**
 RevenueRecordFactory - create array of revenue record object from CSV
 @author Heng Zheng Ping
 @version 1.0
 @since 2021-11-03
*/

package salerevenuereport;

import java.util.ArrayList;
import miscellaneous.CSVLoader;

public class RevenueRecordFactory {
	/**
	* array variable that stores multiple revenue record
	*/
	private ArrayList<RevenueRecord> recordList;
	
	/**
	* Constructor for RevenueRecordFactory
	*/
	public RevenueRecordFactory() {
		recordList = new ArrayList<RevenueRecord>();
		CSVLoader Dldr = new CSVLoader("src/resource/revenuerecord.csv", true);
		constructFromCSV(Dldr);
	}
	
	/**
	* Accessor of the array list of revenue record
	* @return recordList
	*/
	public ArrayList<RevenueRecord> getRecordList(){
		return recordList;
	}
	
	/**
	* Public method that call the constructRecord method for each row loaded from the CSV
	* @param ldr is a CSVLoader object, this object should have already loaded 
	* a CSV file prior to this function call.
	*/
	public void constructFromCSV(CSVLoader ldr) {
		// Iterate the data and construct an item for each row
		for (ArrayList<String> l : ldr.getCSVData()) {
			constructRecord(l);
		}
	}
	
	/**
	* Public method to construct every record based on passed parameters
	* Constructed record is put into the recordList
	* This function is hard-coded to abide by the formatting of the CSV file
	* @param parameterList - A list of parameters pertaining to the record
	*/
	public void constructRecord(ArrayList<String> parameterList){
		RevenueRecord record = new RevenueRecord();
		record.setNetSales(Double.valueOf(parameterList.get(0)));
		record.setYear(Integer.valueOf(parameterList.get(1)));
		record.setMonth(Integer.valueOf(parameterList.get(2)));
		record.setDay(Integer.valueOf(parameterList.get(3)));
		recordList.add(record);
	}
}
