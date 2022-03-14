package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;

import edu.upenn.cit594.data.Property;

/**
 * This class will process the average Market Value of the entered ZipCode ~
 * Option 3
 * 
 */
public class AvgMarketValue implements AvgProcessor {
	ArrayList<Property> properties;

	// memoization variable , store the avg value of each computed zip
	private static HashMap<Integer, Integer> memoization = new HashMap<Integer, Integer>();

	public AvgMarketValue(ArrayList<Property> properties) {
		this.properties = properties;
	}

	
	@Override
	public void avgValue(int zipCode) {

		// memoization check,
		// check if computed already no need to compute again
		if (memoization.containsKey(zipCode)) {
			System.out.println(memoization.get(zipCode));
			return;
		}

		int count = 0;
		double total = 0;

		for (Property p : properties) {
			// if its the input zipCode and valid market value
			// increment the count and add to total
			if (p.getZipCode() == zipCode && p.getMarketValue() != -1) {

				count++;
				total += p.getMarketValue();
			}
		}
		// average market Value
		int avgValue = (int) (total / count);

		memoization.put(zipCode, avgValue);

		System.out.println(avgValue);
	}

}
