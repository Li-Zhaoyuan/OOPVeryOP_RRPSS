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
	private ArrayList<RevenueRecord> recordList;

	public RevenueRecordFactory() {
		recordList = new ArrayList<RevenueRecord>();
		CSVLoader Dldr = new CSVLoader("src/resource/revenuerecord.csv", true);
		constructFromCSV(Dldr);
	}
	
	public ArrayList<RevenueRecord> getRecordList(){
		return recordList;
	}
	
	public void constructFromCSV(CSVLoader ldr) {
		// Iterate the data and construct an item for each row
		for (ArrayList<String> l : ldr.getCSVData()) {
			constructRecord(l);
		}
	}
	
	public void constructRecord(ArrayList<String> parameterList){
		RevenueRecord record = new RevenueRecord();
		record.setNetSales(Double.valueOf(parameterList.get(0)));
		record.setYear(Integer.valueOf(parameterList.get(1)));
		record.setMonth(Integer.valueOf(parameterList.get(2)));
		record.setDay(Integer.valueOf(parameterList.get(3)));
		recordList.add(record);
	}
}
