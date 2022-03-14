
package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Parking;

/**
 * This class implements the reader of Json files
 * 
 */
public class ParkingJSONReader implements ParkingReader {
	String fileName;

	/**
	 * Constructor of json reader
	 * 
	 * @param file name
	 */
	public ParkingJSONReader(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public ArrayList<Parking> readFile() {
		// TODO Auto-generated method stub
		FileReader reader = null;
		try {
			reader = new FileReader(this.fileName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println(this.fileName + " doesn't exist");
			e1.printStackTrace();
		}

		ArrayList<Parking> parkingViolation = new ArrayList<Parking>();

		// create a parser
		JSONParser parser = new JSONParser();
		// open the file and get the array of JSON objects

		try {
			JSONArray file = (JSONArray) parser.parse(reader);
			// use an iterator to iterate over each element of the array
			Iterator<?> iter = file.iterator();

			// iterate while there are more objects in array
			while (iter.hasNext()) {
				// get the next JSON object
				JSONObject line = (JSONObject) iter.next();
				
				int zipCode;
				// try to parse the zipcode content, if fail then its empty so set zipcode to -1
				try{
					 zipCode = Integer.parseInt(line.get("zip_code").toString());
				}catch(NumberFormatException e) {
					 zipCode = -1;
				}

				// parse the fine from json file to double and store to fine
				double fine = Double.parseDouble(line.get("fine").toString());

				String state = line.get("state").toString();

				// System.out.println(zipCode + " " + fine + " " + state);

				parkingViolation.add(new Parking(zipCode, fine, state));
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(this.fileName + " Parse faiure for JSON");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(this.fileName + " doesn't open");
			e.printStackTrace();
		}

		return parkingViolation;
	}

}
