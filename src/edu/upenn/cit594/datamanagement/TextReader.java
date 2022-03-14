
package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.upenn.cit594.data.Population;

/**
 * This class implements the reader of TXT files
 */
public class TextReader {
	String fileName;

	/**
	 * Constructor of text reader
	 * 
	 * @param file name
	 */
	public TextReader(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * This class will read in the text file
	 * 
	 * @return
	 */
	public ArrayList<Population> readFile() {
		// TODO Auto-generated method stub
		ArrayList<Population> population = new ArrayList<Population>();
		
		FileReader reader = null;
		try {
			reader = new FileReader(this.fileName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println(this.fileName + " doesn't exist");
			e1.printStackTrace();
		}

		BufferedReader bread = new BufferedReader(reader);

		try {
			// to store the lines readed in
			String line;

			line = bread.readLine();

			// while there still line, read in the data
			while (line != null) {

				/*
				 * spilt the line with the space, and index 0 is ZIP code , index 1 is
				 * population amount and parse them to int and store in the variable
				 */
				int zipCode = Integer.parseInt(line.split(" ")[0]);
				int populationAmount = Integer.parseInt(line.split(" ")[1]);

				population.add(new Population(zipCode, populationAmount));

				// next line
				line = bread.readLine();

			}
		} catch (IOException e) {
			System.out.println(this.fileName + " doesn't open");
			e.printStackTrace();
		} finally {
			try {
				// close the readers
				bread.close();
			} catch (IOException e) {
				System.out.println(this.fileName + " doesn't close");
				e.printStackTrace();
			}
		}

		return population;
	}

}
