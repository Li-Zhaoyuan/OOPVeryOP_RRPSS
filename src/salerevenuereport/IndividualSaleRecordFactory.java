package salerevenuereport;

import java.util.ArrayList;

import miscellaneous.CSVLoader;

public class IndividualSaleRecordFactory {
	
	private ArrayList<IndividualSaleRecord> recordList;

	public IndividualSaleRecordFactory() {
		recordList = new ArrayList<IndividualSaleRecord>();
		CSVLoader ISRldr = new CSVLoader("src/resource/individualsalerecord.csv", true);
		constructFromCSV(ISRldr);
	}
	
	public ArrayList<IndividualSaleRecord> getRecordList(){
		return recordList;
	}
	
	public void constructFromCSV(CSVLoader ldr) {
		// Iterate the data and construct an item for each row
		for (ArrayList<String> l : ldr.getCSVData()) {
			constructRecord(l);
		}
	}
	
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
