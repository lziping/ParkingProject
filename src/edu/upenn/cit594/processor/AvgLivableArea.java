package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;

import edu.upenn.cit594.data.Property;

/**
 * This class will process the Average Livable Area of the entered ZipCode ~
 * Option 4
 */
public class AvgLivableArea implements AvgProcessor {
	ArrayList<Property> properties;

	// memoization variable , store the avg livable area of each computed zip
	private static HashMap<Integer, Integer> memoization = new HashMap<Integer, Integer>();

	public AvgLivableArea(ArrayList<Property> properties) {
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

		// store the count of housing
		int count = 0;
		// store the total Livable Area
		double total = 0;

		for (Property p : properties) {
			// if its the input zipCode and valid Total livable area
			// increment the count and add to total
			if (p.getZipCode() == zipCode && p.getTotalLivableArea() != -1) {

				count++;
				total += p.getTotalLivableArea();
			}
		}
		// the average value 
		int avgValue = (int) (total / count);

		memoization.put(zipCode, avgValue);

		System.out.println(avgValue);
	}

}
