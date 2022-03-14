package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.upenn.cit594.data.Parking;

/**
 * This class implements the reader of Parking CVS files
 */
public class ParkingCsvReader implements ParkingReader {

	String fileName;

	/**
	 * Constructor of Csv reader
	 * 
	 * @param file name
	 */
	public ParkingCsvReader(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public ArrayList<Parking> readFile() {
		// TODO Auto-generated method stub

		ArrayList<Parking> parkingViolation = new ArrayList<Parking>();

		// try to read the file
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

			// read in the first line
			line = bread.readLine();
			// while not the last line
			while (line != null) {

				// store the splited line in curLine array
				String[] curLine = line.split(",");
				
				//if the length is 7, mean it contain the zip
				int zipCode;
				if (curLine.length == 7) {
					 zipCode = Integer.parseInt(curLine[6]);
				} else {
					 zipCode = -1;
				}
				// parse the fine and store to fine
				double fine = Double.parseDouble(curLine[1]);

				// get state
				String state = curLine[4];

				 
				parkingViolation.add(new Parking(zipCode, fine, state));

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
		return parkingViolation;

	}

}
