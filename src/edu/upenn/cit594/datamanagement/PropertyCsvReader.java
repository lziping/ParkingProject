package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import edu.upenn.cit594.data.Property;

/**
 * This class implements the reader of Parking CVS files
 */
public class PropertyCsvReader {

	String fileName;

	/**
	 * Constructor of Csv reader
	 * 
	 * @param file name
	 */
	public PropertyCsvReader(String fileName) {
		this.fileName = fileName;
	}

	public ArrayList<Property> readFile() {
		// TODO Auto-generated method stub

		ArrayList<Property> property = new ArrayList<Property>();

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

			// read in the first row - use to determine fields
			line = bread.readLine();

			int index[] = findFieldIndex(line);

			// read in second line which is the content
			line = bread.readLine();
			// while not the last line
			while (line != null) {
				
				// split the line, [0]=zip [1]=market value [2]=totalLivablearea
				String[] curLine = line.split(",");
				/*
				 * if the length is not same as the length of field header, then it have , with
				 * in the cell so split with following regex to get correct line
				 */
				if (curLine.length != index[3]) {
					curLine = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				}

				int zipCode = -1;
				double marketValue;
				double totalLivableArea;
				// try to parse the zipcode content, if fail then its empty so set zipcode to -1

				try { // take only the first 5 digit of the Zip if there are at least 5 digit
					if (curLine[index[0]].length() > 5) {
						zipCode = Integer.parseInt(curLine[index[0]].substring(0, 5));
					}
				} catch (NumberFormatException e) {
				}

				try {
					marketValue = Double.parseDouble(curLine[index[1]]);
				} catch (NumberFormatException e) {
					marketValue = -1;
				}

				try {
					totalLivableArea = Double.parseDouble(curLine[index[2]]);
				} catch (NumberFormatException e) {
					totalLivableArea = -1;
				}

				property.add(new Property(zipCode, marketValue, totalLivableArea));

				line = bread.readLine();

			}
		} catch (

		IOException e) {
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
		return property;

	}

	/**
	 * This method will find the index of the field correspond to the field name
	 * 
	 * @param fieldName that was checking for
	 * @param fieldRow  the line that contain all the fields
	 * @return the index of that field
	 */
	private int[] findFieldIndex(String line) {

		ArrayList<String> fieldArray = new ArrayList<String>();
		// add the array result with the split into fieldArray
		Collections.addAll(fieldArray, line.split(","));

		int[] index = new int[4];
		// store the index of field in to index array
		index[0] = fieldArray.indexOf("zip_code");
		index[1] = fieldArray.indexOf("market_value");
		index[2] = fieldArray.indexOf("total_livable_area");
		index[3] = fieldArray.size();

		return index;

	}

}
