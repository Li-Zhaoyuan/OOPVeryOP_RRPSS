/**
 IndividualSaleRecordFactory - create array of individual sale record object from CSV
 @author Heng Zheng Ping
 @version 1.0
 @since 2021-11-05
*/

package salerevenuereport;

import java.util.ArrayList;

import miscellaneous.CSVLoader;

public class IndividualSaleRecordFactory {
	/**
	* array variable that stores multiple individual sale record
	*/
	private ArrayList<IndividualSaleRecord> recordList;
	
	/**
	* Constructor for IndividualSaleRecordFactory
	*/
	public IndividualSaleRecordFactory() {
		recordList = new ArrayList<IndividualSaleRecord>();
		CSVLoader ISRldr = new CSVLoader("src/resource/individualsalerecord.csv", true);
		constructFromCSV(ISRldr);
	}
	
	/**
	* Accessor of the array list of individual sale record
	* @return recordList
	*/
	public ArrayList<IndividualSaleRecord> getRecordList(){
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
		IndividualSaleRecord record = new IndividualSaleRecord();
		record.setName(parameterList.get(0));
		record.setQuantity(Integer.valueOf(parameterList.get(1)));
		record.setYear(Integer.valueOf(parameterList.get(2)));
		record.setMonth(Integer.valueOf(parameterList.get(3)));
		record.setDay(Integer.valueOf(parameterList.get(4)));
		recordList.add(record);
	}
}
