/**
 * Flight search application.
 */
package net.luisalbertogh.flightsearch;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class.
 *
 * @author luisalbertogh@hotmail.com
 *
 */
@SpringBootApplication
public class FlightSearch implements CommandLineRunner {

	/** A logger reference */
	private static Logger logger = Logger.getLogger(FlightSearch.class);

	/**
	 * Run method.
	 */
	@Override
	public void run(String... args) {
		logger.info("Running FlightSearch...");
	}

	/**
	 * Main method.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FlightSearch.class, args);
	}

}
