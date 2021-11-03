/**
 CSVLoader - Loads .csv files into a 2D arraylist
 @author Lim Rui An, Ryan
 @version 1.0
 @since 2021-10-23
*/

package miscellaneous;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVLoader {
	/**
	* Delimiter of the values within the csv file
	*/
	public static final String delimiter = ",";
	
	/**
	* 2D ArrayList to store values of the csv file
	*/
	private ArrayList<ArrayList<String>> csv;
	
	/**
	* ArrayList to store header values
	*/
	private ArrayList<String> header;

	/**
	* Constructor for CSVLoader
	*/
	public CSVLoader() {
		csv = new ArrayList<ArrayList<String>>();
	}
	
	/**
	* Alternative Constructor for CSVLoader
	*/
	public CSVLoader(String filePath) {
		csv = new ArrayList<ArrayList<String>>();
		readFile(filePath, false);
	}
	
	/**
	* Another alternative Constructor for CSVLoader
	*/
	public CSVLoader(String filePath, boolean csvHasHeader) {
		csv = new ArrayList<ArrayList<String>>();
		readFile(filePath, csvHasHeader);
	}
	
	/**
	* Public void function to read a csv file and record its details
	* into a local 2D array within the class
	* 
	* @param  filePath  an absolute file path to the target csv file
	*/
	public void readFile(String filePath, boolean csvHasHeader) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = "";
			int iterCount = 0;
			while((line = br.readLine()) != null) {
				// Split the line up into a String array by the delimiter
				String[] values = line.split(delimiter); 
				// Convert the String[] into a list
				ArrayList<String> list = new ArrayList<String>(Arrays.asList(values)); 
				if (csvHasHeader && iterCount == 0)
					header = list;
				// Insert row into the List of Lists
				else csv.add(list);
				iterCount++;
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error opening CSV file: " + filePath);
			e.printStackTrace();
		}
	}
	
	/**
	* Public accessor function to get the header of the csv file
	*/
	public ArrayList<String> getCSVHeader(){
		return header;
	}
	
	/**
	* Public ArrayList<ArrayList<String>> function to get the csv data
	*/
	public ArrayList<ArrayList<String>> getCSVData(){
		return csv;
	}
	
	/**
	* Public String function to access a String in the CSV based on Row and Column index
	* 
	* @param  row  the row index to access
	* @param  col  the col index to access
	*/
	public String at(int row, int col) {
		return csv.get(row).get(col);
	}
}