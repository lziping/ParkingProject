package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.upenn.cit594.Main;


/**
 * This class will log the information to the log File
 */
public class Logger {

	//private FileWriter fileWriter;
	private PrintWriter writer;

	/**
	 * construtor
	 * 
	 * @param file name of the log file
	 */
	private Logger(String file) {

		try {
			//this.fileWriter = new FileWriter(new File(file),true);
			
			this.writer = new PrintWriter(new FileWriter(new File(file),true));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File to Log does not exist");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Log File can not be open");
			e.printStackTrace();
		}
	}

	private static Logger instance = new Logger(Main.logFile);

	public static Logger getInstance() {
		return instance;
	}

	/**
	 * Log the message to the log File
	 * @param message
	 */
	public void log(String message) {

			writer.println(System.currentTimeMillis()+" " + message);
			writer.flush();

	}

}
