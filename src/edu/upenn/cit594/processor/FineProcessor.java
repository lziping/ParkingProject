/**
 * 
 */
package edu.upenn.cit594.processor;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Population;

/**
 * This class will process the total fine per capita ~ Option 2
 * 
 */
public class FineProcessor {
	ArrayList<Parking> parking;

	// memoization variable to store the computed result
	static ArrayList<String> fineMem = new ArrayList<String>();

	public FineProcessor(ArrayList<Parking> parking) {
		this.parking = parking;

	}

	public ArrayList<String> totalFine(ArrayList<Population> population) {

		// check if already computed, if yes display the information already computed
		if (!fineMem.isEmpty()) {
			for (String line : fineMem) {
				System.out.println(line);
			}
			return null;
		}

		HashMap<Integer, Double> fine = new HashMap<Integer, Double>();

		PriorityQueue<Integer> minZip = new PriorityQueue<Integer>();

		HashMap<Integer, Integer> pop = new HashMap<Integer, Integer>();

		for (Population p : population) {
			pop.put(p.getZipCode(), p.getPopulation());
		}

		for (Parking current : this.parking) {
			// if not PA plate or ZipCode is invalid or not in the population input file,
			// move to next
			if (!current.getState().toUpperCase().equals("PA") || current.getZipCode() == -1
					|| !pop.containsKey(current.getZipCode())) {
				continue;
			}
			// add the zipCode to the queue if not in already
			if (!minZip.contains(current.getZipCode())) {
				minZip.add(current.getZipCode());
			}

			// if already exist, add the fine to the values
			if (fine.containsKey(current.getZipCode())) {
				fine.put(current.getZipCode(), fine.get(current.getZipCode()) + current.getFine());

			} // not contain, add the zip as key , and fine as value
			else if (!fine.containsKey(current.getZipCode())) {
				fine.put(current.getZipCode(), current.getFine());

			}

		}
		// truncate number that will be use to display to last for decimal, no rounding
		NumberFormat f = new DecimalFormat("#0.0000");
		f.setRoundingMode(RoundingMode.DOWN);

		// display all the fine per Zip in ascending order
		while (!minZip.isEmpty()) {
			int zip = minZip.poll();

			String message = (zip + " " + f.format(fine.get(zip) / pop.get(zip)));
			System.out.println(message);

			// add to memoization
			fineMem.add(message);

		}

		return fineMem;

	}

}
