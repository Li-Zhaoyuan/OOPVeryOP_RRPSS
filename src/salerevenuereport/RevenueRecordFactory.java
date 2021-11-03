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
		CSVLoader Rldr = new CSVLoader("src/resource/revenue.csv", true);
		constructFromCSV(Rldr);
	}
	
	public ArrayList<RevenueRecord> getItemList(){
		return recordList;
	}
	
	public void constructFromCSV(CSVLoader ldr) {
		// Iterate the data and construct an item for each row
		for (ArrayList<String> l : ldr.getCSVData()) {
			constructItem(l);
		}
	}
	
	public void constructItem(ArrayList<String> parameterList){
		RevenueRecord record = new RevenueRecord();
		record.setName(parameterList.get(0));
		record.setQuantity(Integer.valueOf(parameterList.get(1)));
		record.setYear(Integer.valueOf(parameterList.get(2)));
		record.setMonth(Integer.valueOf(parameterList.get(3)));
		record.setDay(Integer.valueOf(parameterList.get(4)));
		recordList.add(record);
	}
}
