package edu.upenn.cit594.processor;

import java.util.ArrayList;

import edu.upenn.cit594.data.Population;

/**
 * This class will process the total of the Population ~ Option 1
 *  
 */
public class TotalPopulationProcessor {

	ArrayList<Population> population;

	// Memorization variable, store the total population
	private static int total = 0;

	public TotalPopulationProcessor(ArrayList<Population> population) {
		this.population = population;

	}

	public int totalPopulation() {
		
		//memoization check
		// check if total is already computed if yes no need to compute again
		if (total !=0) {
			System.out.println(total);
			return total;
		}

		// total = 0;

		for (Population p : population) {
			total += p.getPopulation();
		}
		System.out.println(total);
		return total;

	}

}
