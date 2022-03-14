package edu.upenn.cit594;

import edu.upenn.cit594.datamanagement.*;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.ui.UserInterface;

/***
 * This is the Main class for the project This class will take and determine the
 * runtime argument And declare the class and file readers
 *  
 */
public class Main {

	public static String logFile;

	public static void main(String[] args) {
		//check if the length of arguments, is 5 it not end the program
		if (args.length != 5) {
			System.out.println("Number of Main Argument is incorrect, Should be 5 Argument");
			return;
		}

		String format = args[0];

		// check if the format is JSON or CSV, if not end the program
		if (!format.equals("json") && !format.equals("csv")) {
			System.out.println(format + " Format is neither json nor text ( case-sensitive)");
			return;
		}

		String parkingFile = args[1];
		String propertyFile = args[2];
		String populationFile = args[3];
		logFile = args[4];

		Logger logger = Logger.getInstance();
		// log the runtime arguments
		logger.log((format + " " + parkingFile + " " + propertyFile + " " + populationFile + " " + logFile));

		// create the correct reader the inputed Parking file
		ParkingReader parking;
		if (format.equals("csv")) {
			parking = new ParkingCsvReader(parkingFile);
			logger.log(parkingFile);
		} else {
			parking = new ParkingJSONReader(parkingFile);
			logger.log(parkingFile);
		}
		
		// open file and log file name
		PropertyCsvReader property = new PropertyCsvReader(propertyFile);
		logger.log(propertyFile);;

		TextReader population = new TextReader(populationFile);
		logger.log(populationFile);

		UserInterface ui = new UserInterface(parking.readFile(), property.readFile(), population.readFile(),logger);

		// start the asking user for options
		ui.start();

		return;

	}

}
