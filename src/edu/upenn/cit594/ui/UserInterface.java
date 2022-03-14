package edu.upenn.cit594.ui;

import java.util.ArrayList;
import java.util.Scanner;

import edu.upenn.cit594.data.*;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.*;

/***
 * This class will interact with User, taking input from the with for the
 * options 
 */
public class UserInterface {

	private Scanner scan = new Scanner(System.in);

	private Logger logger;
	ArrayList<Parking> parking;
	ArrayList<Property> property;
	ArrayList<Population> population;

	public UserInterface(ArrayList<Parking> parking, ArrayList<Property> property, ArrayList<Population> population,
			Logger logger) {
		this.parking = parking;
		this.property = property;
		this.population = population;
		this.logger = logger;
	}

	public void start() {

		while (true) {
			System.out.println("\nEnter 0 - Exit the Program");
			System.out.println("Enter 1 - Show the total Population for all ZIP Codes");
			System.out.println("Enter 2 - Show the total parking fines per capita for each ZIP Code");
			System.out.println("Enter 3 - Show the average market value for residences in a specified ZIP Code");
			System.out.println("Enter 4 - Show the average total livable area for residences in a specified ZIP Code");
			System.out.println("Enter 5 - Show the total residential market value per capita in a specified ZIP Code");
			System.out.println(
					"Enter 6 - Show the ZIP Code with highest average market value per capita and highest average parking fine per capita");

			String option = scan.nextLine();

			logger.log("user selection: " + option);

			/**
			 * Determine what options user input and execute the option if user enter
			 * anything other than 0-6, the program will end
			 */
			if (option.equals("0")) {
				return;
			} else if (option.equals("1")) {

				new TotalPopulationProcessor(population).totalPopulation();

			} else if (option.equals("2")) {

				new FineProcessor(parking).totalFine(population);

			} else if (option.equals("3")) {

				new AvgMarketValue(property).avgValue(getZipCode());

			} else if (option.equals("4")) {

				new AvgLivableArea(property).avgValue(getZipCode());

			} else if (option.equals("5")) {

				new ResidentialMarketValue(population, property).avgValue(getZipCode());

			} else if (option.equals("6")) {

				new HighestAvg(population, property, parking).findAvg();

			} else {
				System.out.println("Input other than 0-6 is invalid input - program terminated");
				scan.close();
				return;
			}

		}
	}

	/**
	 * This class will prompt the user for zipcode and determine if its valid
	 * 
	 * @return the zipCode for process
	 */
	private int getZipCode() {
		System.out.println("Enter a Zip Code");

		String zipCode = scan.nextLine();

		// while zipcode is not a 5digit number, ask again for the input
		while (!zipCode.matches("^[0-9]{5}$")) {
			System.out.println("Invalid Zip Code, Enter again");
			zipCode = scan.nextLine();
		}

		logger.log("zip code: " + zipCode);

		return Integer.parseInt(zipCode);

	}

}
