package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;

/***
 * This class will process Residential market value per capita of entered
 * ZipCode ~ Option 5 
 */
public class ResidentialMarketValue {
	ArrayList<Population> population;
	ArrayList<Property> properties;

	// memoization variable , store the market value per capita of each computed zip
	private static HashMap<Integer, Integer> memoization = new HashMap<Integer, Integer>();

	public ResidentialMarketValue(ArrayList<Population> population, ArrayList<Property> properties) {
		this.population = population;
		this.properties = properties;
	}

	/**
	 * This class will process and compute the Average total market value per capita
	 * 
	 * @param zipCode to process
	 */
	public void avgValue(int zipCode) {

		// memoization check,
		// check if computed already no need to compute again
		if (memoization.containsKey(zipCode)) {
			System.out.println(memoization.get(zipCode));
			return;
		}

		HashMap<Integer, Integer> pop = new HashMap<Integer, Integer>();
		// get the population per zip
		for (Population p : population) {
			pop.put(p.getZipCode(), p.getPopulation());
		}

		// check if key is contain or if population is 0
		if (!pop.containsKey(zipCode) || pop.get(zipCode) == 0) {
			memoization.put(zipCode, 0);
			System.out.println(0);
			return;
		}

		int count = 0;

		// get the population of the zip Code
		for (Population p : population) {
			if (p.getZipCode() == zipCode) {
				count = p.getPopulation();
				break;
			}
		}

		double total = 0;

		// get the total market Value
		for (Property p : properties) {

			if (p.getZipCode() == zipCode && p.getMarketValue() != -1) {

				total += p.getMarketValue();
			}
		}

		// average market value
		int avgValue = (int) (total / count);

		// add to memoization
		memoization.put(zipCode, avgValue);

		System.out.println(avgValue);
	}
}
