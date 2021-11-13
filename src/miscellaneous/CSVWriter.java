package miscellaneous;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
CSVWriter - Write String data into .csv file
@author Heng Zheng Ping
@version 1.0
@since 2021-11-03
*/
public class CSVWriter {
	private FileWriter fw = null; 
	private BufferedWriter bw = null; 
	private PrintWriter pw = null;
	
	/**
	* Constructor for CSVWriter
	*/
	public CSVWriter() {
		
	}
	
	/**
	* Constructor for CSVWriter
	*/
	public CSVWriter(String filePath, String data) {
		writeFile(filePath, true, data);
	}
	
	/**
	* Write string data into CSV file
	*/
	public void writeFile(String filePath, boolean append, String data) {
			try { 
					fw = new FileWriter(filePath, append);
					bw = new BufferedWriter(fw);
					pw = new PrintWriter(bw); 
					
					pw.printf(data+"\n");
					
					pw.flush(); 
				} 
			catch (IOException io) {
	    		System.out.println("Error opening CSV file: " + filePath);
				io.printStackTrace();
			}	
		    finally { 
		    	try { 
			    		pw.close(); 
						bw.close(); 
						fw.close(); 
					} 
		    	catch (IOException io) {
		    		System.out.println("Error opening CSV file: " + filePath);
					io.printStackTrace();
		    		}	
		    	}
	}
}
