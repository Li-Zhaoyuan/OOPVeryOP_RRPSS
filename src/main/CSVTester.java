package main;

import java.util.ArrayList;

import miscellaneous.CSVLoader;

public class CSVTester {

	public static void main(String[] args) {
		// How 2 use the csv loader 101
		// Load the file
		CSVLoader ldr = new CSVLoader("src/resource/menuitems.csv", true);
		
		// Get the header
		System.out.println(ldr.getCSVHeader());
		// Iterate the data
		for (ArrayList<String> l : ldr.getCSVData()) {
			// Do something with the list
			System.out.println(l);
		}

	}

}
