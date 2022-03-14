package edu.upenn.cit594.data;

/**
 * This class represent the Property type
 */
public class Property {
	int zipCode;
	double marketValue;
	double totalLivableArea;

	/**
	 * Constructor of Property class
	 * 
	 * @param zipCode          of property
	 * @param marketValue      of property
	 * @param totalLivableArea property
	 */
	public Property(int zipCode, double marketValue, double totalLivableArea) {
		this.zipCode = zipCode;
		this.marketValue = marketValue;
		this.totalLivableArea = totalLivableArea;
	}

	/**
	 * @return the zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * @return the marketValue
	 */
	public double getMarketValue() {
		return marketValue;
	}

	/**
	 * @return the totalLivableArea
	 */
	public double getTotalLivableArea() {
		return totalLivableArea;
	}

}
