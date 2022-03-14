package edu.upenn.cit594.data;

/**
 * This Class represent the Population type
 */

public class Population {

	int zipCode;
	int population;

	/**
	 * Construtor of Population type
	 * 
	 * @param zipCode    of population
	 * @param population amount of that zipcode
	 */
	public Population(int zipCode, int population) {

		this.zipCode = zipCode;
		this.population = population;
	}

	/**
	 * @return the zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}

}
