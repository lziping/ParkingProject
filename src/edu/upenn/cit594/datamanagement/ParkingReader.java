
package edu.upenn.cit594.datamanagement;

import java.util.ArrayList;

import edu.upenn.cit594.data.Parking;

/**
 * This class represent interface of File Readers
 * 
 *
 */
public interface ParkingReader {

	public abstract ArrayList<Parking> readFile();

}
