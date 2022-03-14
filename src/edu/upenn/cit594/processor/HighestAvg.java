package edu.upenn.cit594.processor;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;

/**
 * This class will find the ZIP code with highest average market value per capita and highest average fine per capita 
 * ~option 6 
 */
public class HighestAvg {
	ArrayList<Population> population;
	ArrayList<Property> properties;
	ArrayList<Parking> parking;
	
	// memoization variable, store the compute answer
	private static String memoization;

	public HighestAvg(ArrayList<Population> population, ArrayList<Property> properties, ArrayList<Parking> parking) {
		this.population = population;
		this.properties = properties;
		this.parking = parking;
	}
	
	
	/**
	 * Find the highest average
	 */
	public void findAvg() {
		
		if(memoization!=null) {
			System.out.println(memoization);
			return;
		}
		

		HashMap<Integer, Double> marketValue = new HashMap<Integer, Double>();
		HashMap<Integer, Double> fine = new HashMap<Integer, Double>();
		
		// add all the property to hashMap
		for (Property current : properties) {
			int zip = current.getZipCode();
			if (marketValue.containsKey(zip)) {
				marketValue.put(zip, marketValue.get(zip) + current.getMarketValue());
			} else {
				marketValue.put(zip, current.getMarketValue());
			}

		}
		// add all the parking fine to hashMap
		for (Parking current : parking) {
			int zip = current.getZipCode();
			// if not PA plate or ZipCode is invalid or not in the population input file,
			// move to next
			if (!current.getState().toUpperCase().equals("PA") || zip == -1) {
				continue;
			}
			// if already exist, add the fine to the values
			if (fine.containsKey(zip)) {
				fine.put(zip, fine.get(zip) + current.getFine());

			} // not contain, add the zip as key , and fine as value
			else if (!fine.containsKey(zip)) {
				fine.put(zip, current.getFine());

			}

		}

		double maxMarket = 0;
		double maxFine = 0;

		int maxMarketZip = 0;
		int maxFineZip = 0;
		
		/**
		 * compare for the highest value
		 */
		for (Population p : population) {

			int pop = p.getPopulation();
			int zip = p.getZipCode();
			
			// if this zip's market value is > maxMarket, reset maxMarket to this value and maxZip to this Zip
			if ((marketValue.get(zip) / pop) > maxMarket) {
				maxMarket = (marketValue.get(zip) / pop);
				maxMarketZip = zip;
			}
			
			// if this zip's fine is > max Fine, reset maxFine to this fine
			if (fine.containsKey(zip)) {
				if ((fine.get(zip) / pop) > maxFine) {
					maxFine = (fine.get(zip) / p.getPopulation());
					maxFineZip = zip;
				}
			}

		}

		NumberFormat f = new DecimalFormat("#0.0000");
		f.setRoundingMode(RoundingMode.DOWN);

		String message = ("Zip Code with highest average market value per capita: " + maxMarketZip
		+"\nHighest average market value per capita: $" + (int) maxMarket
		+"\nZip Code with highest average fine per capita: " + maxFineZip
		+"\nHighest average fine per capita: $" + f.format(maxFine));
		
		System.out.println(message);
		
		memoization = message;
		
	}

}
