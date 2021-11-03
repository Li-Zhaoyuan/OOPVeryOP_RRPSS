package miscellaneous;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVWriter {
	FileWriter fw = null; 
	BufferedWriter bw = null; 
	PrintWriter pw = null;
	
	/**
	* Constructor for CSVWriter
	*/
	public CSVWriter() {
		
	}
	
	public CSVWriter(String filePath, String data) {
		writeFile(filePath, true, data);
	}
	
	public void writeFile(String filePath, boolean append, String data) {
			try { 
					fw = new FileWriter(filePath, append);
					bw = new BufferedWriter(fw);
					pw = new PrintWriter(bw); 
					
					pw.printf(data+"\n");
					
					System.out.println("Data Successfully appended into file"); 
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
