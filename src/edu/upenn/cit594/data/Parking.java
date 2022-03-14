package edu.upenn.cit594.data;

/**
 * This class represent the Parking Violation type
 */
public class Parking {

	int zipCode;
	double fine;
	String state;

	/**
	 * Constructor of Parking Violation class
	 * 
	 * @param zipCode of violation
	 * @param fine    of violation
	 * @param state   of violation
	 */
	public Parking(int zipCode, double fine, String state) {
	
		this.fine = fine;
		this.zipCode = zipCode;
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * @return the fine
	 */
	public double getFine() {
		return fine;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
}
